import javafx.animation.AnimationTimer;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import  javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;

import static javafx.scene.paint.Color.BLACK;
import javafx.util.Duration;
import javafx.stage.Screen;


public class Main extends Application{
    
    public Main() {
        createPause();
        createGameOver();
    }
    
    private enum STATUS{
        SPILL,
        LOAD,
        MENY,
        GAMEOVER
    }
    
    private STATUS Status = STATUS.MENY;

    private Pane root;
    private Spiller player;
    private VBox stats;
    private VBox pause;
    private VBox meny;
    private VBox gameOver;
    private Label helse;
    private Label score;
    private Label loadNy;
    private Label lagret;
    private Label gameO;
    private Label gameScore;
    private GameObject wall1;
    private GameObject wall2;
    private GameObject wall3;
    private GameObject wall4;
    private Button strt;
    private Button fortsett;
    private Button lagre;
    private Button quit;
    private Button load;
    private Button gameOverB;
    private AnimationTimer timer;
    private AnimationTimer timer2;
    private double height;
    private double width;
    private boolean loaded = false;

    private final Image IMAGE = new Image("bilder/testSprite.png");
    private final Image PLAYERIMAGE = new Image("bilder/player.png");
    private final Image BOSSIMAGE = new Image("bilder/bosstank.png");
    private final Image FIENDERIMAGE = new Image("bilder/fiender.png");
    private final Image EXPLOSION = new Image("bilder/explosionAnim.png");
    private final Image DESTROYERIMG = new Image("bilder/misilboss.png");
    private final Image FLAMETHROWERIMG = new Image("bilder/flamethrower.png");
    private final Image CLOAKER = new Image("bilder/cloaker2.png");
    private final Image HPUPS = new Image("bilder/hp.png");
    
    save filen = new save();
    SpillerLoad loader = new SpillerLoad();
    FiendeLoad fLoad = new FiendeLoad();
    BossLoad bLoad = new BossLoad();
    DestroyerLoad dLoad = new DestroyerLoad();

    List<Boss> bosser = new ArrayList<>();
    List<Fiender> fiender = new ArrayList<>();
    List<Skudd> bullets = new ArrayList<>();
    List<GameObject> explosions = new ArrayList<>();
    List<GameObject> walls = new ArrayList<>();
    List<FiendeSkudd> Enemybullets = new ArrayList<>();
    List<GameObject> powerups = new ArrayList<>();
    List<Destroyer> destroyers = new ArrayList<>();
    List<Misiler> misiler = new ArrayList<>();
    List<Flamethrower> flamethrowers = new ArrayList<>();
    List<Cloaker> cloakers = new ArrayList<>();
    List<GameObject> hpups = new ArrayList<>();
    
    public static void main(String[] args) {

        launch(args);
    }


    public boolean equalsX(GameObject gameObject, Point2D point2D){

        return gameObject.getVelocity().getX() == point2D.getX();
    }

    public boolean equalsY(GameObject gameObject, Point2D point2D){

        return gameObject.getVelocity().getY() == point2D.getY();
    }

    
    // Denne metoden Viser og lager alt innenfor Scenen
    private Parent createContent(){
        root = new Pane();


        BackgroundImage bkg = new BackgroundImage(new Image("bilder/bakgrunnMain.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bkg));
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight();
        
        root.setPrefSize(height, width);
        
        if(Status == STATUS.SPILL){
            player = new Spiller(100, 50, 0,"Gustav",playerAnim(new ImageView(PLAYERIMAGE),0),width/2,height/2, false);
            player.setVelocity(new Point2D(0,-0.001));
            addGameObject(player, width/2, height/2);
        }else if(Status == STATUS.LOAD){
            loader.loadSpiller();
            player = new Spiller(loader.getHp(), 10, loader.getScore(), "Gustav", playerAnim(new ImageView(PLAYERIMAGE),0), width/2, height/2, loader.getPowerup());
            player.setVelocity(new Point2D(0,-0.001));
            addGameObject(player, loader.getPosX(), loader.getPosY());
        }       

        // (new Rectangle(20,20,Color.BLUE)),(new Circle(5,5,5,(new Color(0.1f,0.3f,1.0f, 1.0))))


        wall1 = new GameObject(new Rectangle(width,10,new Color(0, 0, 0, 0.4824)));
        wall2 = new GameObject(new Rectangle(width,10,new Color(0, 0, 0, 0.4824)));
        wall3 = new GameObject(new Rectangle(10,height,new Color(0, 0, 0, 0.4824)));
        wall4 = new GameObject(new Rectangle(10,height,new Color(0, 0, 0, 0.4824)));

        addGameObject(wall1,0,0);
        addGameObject(wall2,0,height - 30);
        addGameObject(wall3,0,0);
        addGameObject(wall4,width - 10,0);

        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);
        
        helse = new Label();
        score = new Label();
        
        stats = new VBox(10);
        stats.setPadding(new Insets(10, 10, 10, 10));
        stats.setPrefWidth(300);
        stats.setPrefHeight(600);
        stats.getChildren().addAll(helse, score);
        root.getChildren().addAll(stats);

        // timern som får alt til å oppdateres
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(Status == STATUS.SPILL || Status == STATUS.LOAD){
                    onUpdate();
                }
            }
        };
        timer.start();


        timer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(Status == STATUS.SPILL || Status == STATUS.LOAD){
                   explosionTimer();
                }
            }
        };

        timer2.start();

        return root;
    }
    
    public Parent createMeny(){
        
        loadNy = new Label("Starter nytt spill");
        strt = new Button("Start Spill");
        load = new Button("Last lagret spill");
        
        meny = new VBox();
        meny.setPrefSize(400, 300);
        meny.setSpacing(30);
        meny.setAlignment(Pos.CENTER);
        
        meny.getChildren().addAll(loadNy, strt, load);
        
        return meny;
    }
    
    public void createPause(){
        pause = new VBox(20);
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight();
        
        pause.setAlignment(Pos.CENTER);
        pause.setPrefWidth(width);
        pause.setPrefHeight(height);
        pause.setBackground(new Background( new BackgroundFill( Color.web( "#000000" ), CornerRadii.EMPTY, Insets.EMPTY ) ));
        
        lagret = new Label();
        fortsett = new Button("Fortsett");
        lagre = new Button("Lagre");
        quit = new Button("Avslutt");
        
        pause.getChildren().addAll(lagret, fortsett, lagre, quit);
    }
    
    public void createGameOver(){
        
        gameOver = new VBox(20);
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight();
        
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setPrefWidth(width);
        gameOver.setPrefHeight(height);
        gameOver.setBackground(new Background( new BackgroundFill( Color.web( "#000000" ), CornerRadii.EMPTY, Insets.EMPTY ) ));
        
        gameO = new Label("GAME OVER");
        gameScore = new Label(" ");
        gameOverB = new Button("Tilbake til startsiden");
        
        gameOver.getChildren().addAll(gameO, gameScore, gameOverB);
    }
    
    
    public double generateX(){
        double kordX = Math.random()*width;
        if(kordX < 30){
            kordX+= 30;
            return kordX;
        }else if(kordX > width - 30){
            kordX-= 30;
            return kordX;
        }else{
            return kordX;
        }
    }
    
    public double generateY(){
        double kordY = Math.random()*height;
        if(kordY < 30){
            kordY+= 30;
            return kordY;
        }else if(kordY > height - 30){
            kordY-= 30;
            return kordY;
        }else{
            return kordY;
        }
    }


    // metode for å legge til og gjøre dem visible
    private void addBullet(Skudd bullet, double x, double y){
        bullets.add(bullet);
        addGameObject(bullet,x,y);
    }

    private void addMisiler(Misiler bullet, double x, double y){
        misiler.add(bullet);
        addGameObject(bullet,x,y);
    }
    private void addFlamethrower(Flamethrower fl, double x, double y){

        flamethrowers.add(fl);
        addGameObject(fl,x,y);
    }

    private void addCloaker(Cloaker fl, double x, double y){

        cloakers.add(fl);
        addGameObject(fl,x,y);
    }


    private void addEnemy(Fiender fiende, double x, double y){

        fiender.add(fiende);
        addGameObject(fiende,x,y);
    }

    private void addFiendeBullet(FiendeSkudd bullet, double x, double y){
        Enemybullets.add(bullet);
        addGameObject(bullet,x,y);
    }
    
    private void addPowerUp(GameObject ups, double x, double y){
        powerups.add(ups);
        addGameObject(ups,x,y);

    }

    private void addHpUps(GameObject ups, double x, double y){
        hpups.add(ups);
        addGameObject(ups,x,y);

    }

    private void addBoss(Boss boss, double x, double y){

        bosser.add(boss);
        addGameObject(boss,x,y);

    }

    private void addDestroyer(Destroyer destroyer, double x, double y){

        destroyers.add(destroyer);
        addGameObject(destroyer,x,y);
    }

    private void bulletExplosion(GameObject s , double x, double y){
        explosions.add(s);
        addGameObject(s,x,y);

    }

    private void explosionTimer(){

    }



    private Node playAnimation(ImageView w){

        w.setViewport(new Rectangle2D(0,10,10,10));
        final Animation animation = new Sprite(w,Duration.millis(500),2,0,10,10,10);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        return w;
    }

    private Node cloak(ImageView w){

        w.setViewport(new Rectangle2D(0,0,20,30));
        final Animation animation = new Sprite(w,Duration.millis(5000),2,0,0,20,30);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        return w;
    }

    public Node playerAnim(ImageView p, int minY){

        p.setViewport(new Rectangle2D(0, minY, 20, 20));

        return p;
    }

    public Node explosionAnim(ImageView p){

        p.setViewport(new Rectangle2D(0, 0, 20, 20));
        final Animation animation = new Sprite(p,Duration.millis(1000),4,0,0,10,10);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        return p;
    }

    public Node imageViewruu(ImageView b, int width, int height){
        b.setViewport(new Rectangle2D(0, 0, width, height));
        return b;
    }


    public Node boss(ImageView b, int minY){
        b.setViewport(new Rectangle2D(0, minY, 60, 60));
        return b;
    }



    // GameObject er Parent klassen til alle spill objektene

    public void addGameObject(GameObject object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }


    // metoden som oppdaterer spille via en timer i createContent
    private  void onUpdate() {
        
        helse.setText("HP: " + String.valueOf(player.getHp()));
        score.setText("Score: " + String.valueOf(player.getScore()));
        gameScore.setText("Score: "+player.getScore());

        // Disse Foreach'ene går gjennom Arraylistene våre for å kunne sjekke alle fiender og skudd

        for (Skudd bullet : bullets) {
            for (Fiender fiende : fiender) {
                if (bullet.isColliding(fiende)) {
                    bullet.setAlive(false);
                    fiende.setHp(fiende.getHp() - player.getDamage());

                    root.getChildren().remove(bullet.getView());
                    if (fiende.getHp() <= 0) {
                        fiende.setAlive(false);
                        player.setScore();
                        


                        root.getChildren().remove(fiende.getView());
                    }
                }
            }


            if (bullet.isColliding(wall1) || bullet.isColliding(wall2) || bullet.isColliding(wall3) || bullet.isColliding(wall4)) {



                bullet.setAlive(false);
                root.getChildren().remove(bullet.getView());
                //bullet.setVelocity(new Point2D(bullet.getVelocity().getX() + Math.random() * 10 - 5, bullet.getVelocity().getY() + Math.random() * 10 - 5).multiply(-1));
            }
        }


        for (Fiender fiende3 : fiender) {
            for (FiendeSkudd fbullet : Enemybullets) {
                if (fbullet.isColliding(player)) {
                    player.setHp(player.getHp() - 1);
                    fbullet.setAlive(false);
                    root.getChildren().remove(fbullet.getView());
                    fbullet.update();
                }
                if (fbullet.isColliding(wall1) || fbullet.isColliding(wall2) || fbullet.isColliding(wall3) || fbullet.isColliding(wall4)) {

                    if (Math.random() * 13 - 5 < 4) {
                        fbullet.setAlive(false);
                        root.getChildren().remove(fbullet.getView());

                    } else {
                        fbullet.setAlive(false);
                        root.getChildren().remove(fbullet.getView());
                    }
                }
            }

                if (fiende3.getDecideActiveState() == 0) {

                    if (Math.random() <= 0.05) {

                        FiendeSkudd fiendeBullet2 = new FiendeSkudd(2.5, 2.5, 2.5, Color.GREEN);
                        addFiendeBullet(fiendeBullet2, fiende3.getView().getTranslateX(), fiende3.getView().getTranslateY());
                        fiendeBullet2.setVelocity((fiende3.getVelocity().normalize().multiply(3)));

                        fiendeBullet2.update();
                    }
                }

                fiende3.FSM(player, fiende3);


        }

        for (Destroyer des : destroyers) {
            for (Skudd bullet : bullets) {
                if (bullet.isColliding(des)) {
                    bullet.setAlive(false);
                    des.setHp(des.getHp() - player.getDamage());

                    root.getChildren().remove(bullet.getView());
                    if (des.getHp() <= 0) {
                        des.setAlive(false);
                        player.setScore();

                        if (des.isDead()) {

                            addHpUps(new GameObject(playAnimation(new ImageView(HPUPS))), des.getX(), des.getY());

                        }

                        root.getChildren().remove(des.getView());
                    }
                }


            }

            if (des.isColliding(player)) {
                des.setVelocity(new Point2D(des.getVelocity().getX() + Math.random() * 2 - 1, des.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));



                player.setHp(player.getHp() - 1);
                if (player.getHp() <= 0) {

                }
            }


            if (des.isColliding(wall1) || des.isColliding(wall2) || des.isColliding(wall3) || des.isColliding(wall4)) {
                des.setVelocity(new Point2D(des.getVelocity().getX() + Math.random() * 2 - 1, des.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));

                des.update();
            }


            if (des.getDecideActiveState() == 1) {

                if (Math.random() < 0.01) {
                    addMisiler(new Misiler(new Circle(3,3,3,Color.BROWN)),des.getView().getTranslateX() + 20,des.getView().getTranslateY() + 40);
                }
            }

            for(Misiler m : misiler){
                if(m.isColliding(player)){
                    player.setHp(player.getHp() - 5);
                    m.setAlive(false);
                    root.getChildren().remove(m.getView());
                    m.update();


                }

                if (destroyers.isEmpty()){
                    m.setAlive(false);
                    root.getChildren().remove(m.getView());

                }

            if (m.getPxBeforeExplosion() >= 500){
                m.setAlive(false);
                root.getChildren().remove(m.getView());
                m.update();
            }


                m.FSM(player,m);
                m.update();
            }


            des.FSM(player, des);
            des.update();


        }





        for (Flamethrower fl : flamethrowers) {
            for (Skudd bullet : bullets) {
                if (bullet.isColliding(fl)) {
                    bullet.setAlive(false);
                    fl.setHp(fl.getHp() - player.getDamage());

                    root.getChildren().remove(bullet.getView());
                    if (fl.getHp() <= 0) {
                        fl.setAlive(false);
                        player.setScore();

                        if (fl.isDead()) {

                            addHpUps(new GameObject(playAnimation(new ImageView(HPUPS))), fl.getX(), fl.getY());

                        }


                        root.getChildren().remove(fl.getView());
                    }
                }


            }

            if (fl.isColliding(player)) {
                fl.setVelocity(new Point2D(fl.getVelocity().getX() + Math.random() * 2 - 1, fl.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));



                player.setHp(player.getHp() - 1);
                if (player.getHp() <= 0) {

                }
            }


            if (fl.isColliding(wall1) || fl.isColliding(wall2) || fl.isColliding(wall3) || fl.isColliding(wall4)) {
                fl.setVelocity(new Point2D(fl.getVelocity().getX() + Math.random() * 2 - 1, fl.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));

                fl.update();
            }


            if (fl.getDecideActiveState() == 1) {

                if (Math.random() < 0.2) {

                    bulletExplosion((new GameObject( explosionAnim(new ImageView(EXPLOSION)))), fl.getX() -5, fl.getY() );

                    bulletExplosion((new GameObject( explosionAnim(new ImageView(EXPLOSION)))), fl.getX() + 25, fl.getY());

                    if (Math.random() <0.08) {
                        FiendeSkudd fiendeBullet2 = new FiendeSkudd(2.5, 2.5, 2.5, Color.ORANGE);
                        addFiendeBullet(fiendeBullet2, fl.getView().getTranslateX() +15, fl.getView().getTranslateY() +15);
                        fiendeBullet2.setVelocity((fl.getVelocity().multiply(2)));
                    }

                }
            }

            fl.FSM(player, fl);
            fl.update();


        }

        for (GameObject ex : explosions) {
            if(ex.isColliding(player)){
                player.setHp(player.getHp()-1);
                ex.setAlive(false);
                root.getChildren().remove(ex.getView());
                ex.update();

            }


        }



        for (Cloaker fl : cloakers) {
            for (Skudd bullet : bullets) {
                if (bullet.isColliding(fl)) {
                    bullet.setAlive(false);
                    fl.setHp(fl.getHp() - player.getDamage());

                    root.getChildren().remove(bullet.getView());
                    if (fl.getHp() <= 0) {
                        fl.setAlive(false);
                        player.setScore();

                        if (fl.isDead()) {

                            addHpUps(new GameObject(playAnimation(new ImageView(HPUPS))), fl.getX(), fl.getY());

                        }



                        root.getChildren().remove(fl.getView());
                    }
                }


            }


            if (fl.getDecideActiveState() == 1) {

                if (Math.random() < 0.2) {
                    FiendeSkudd fiendeBullet2 = new FiendeSkudd(2.5, 2.5, 2.5, Color.PURPLE);
                    addFiendeBullet(fiendeBullet2, fl.getView().getTranslateX() + 10, fl.getView().getTranslateY() + 15);
                    fiendeBullet2.setVelocity((fl.getVelocity().multiply(2)));


                    if (Math.random() < 0.08) {

                    }
                }

                    if (fl.isColliding(player)) {
                        fl.setVelocity(new Point2D(fl.getVelocity().getX() + Math.random() * 2 - 1, fl.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));



                        player.setHp(player.getHp() - 1);
                        if (player.getHp() <= 0) {

                        }
                    }


                    if (fl.isColliding(wall1) || fl.isColliding(wall2) || fl.isColliding(wall3) || fl.isColliding(wall4)) {
                        fl.setVelocity(new Point2D(fl.getVelocity().getX() + Math.random() * 2 - 1, fl.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));

                        fl.update();
                    }


            }

            fl.FSM(player, fl);
            fl.update();


        }


            for (Boss boss1 : bosser) {
                for (Skudd bullet : bullets) {
                    if (bullet.isColliding(boss1)) {
                        bullet.setAlive(false);
                        boss1.setHp(boss1.getHp() - player.getDamage());

                        root.getChildren().remove(bullet.getView());
                        if (boss1.getHp() <= 0) {
                            boss1.setAlive(false);
                            player.setScore();

                            if (boss1.isAlive() != true) {

                                addPowerUp(new GameObject(playAnimation(new ImageView(IMAGE))), boss1.getX(), boss1.getY());

                            }

                            root.getChildren().remove(boss1.getView());
                        }
                    }


                }


                if (boss1.getDecideActiveState() == 0) {

                    if (Math.random() < 0.05) {

                        FiendeSkudd fiendeBullet2 = new FiendeSkudd(5, 5, 5, Color.GREEN);
                        FiendeSkudd fiendeBullet3 = new FiendeSkudd(5, 5, 5, Color.GREEN);
                        FiendeSkudd fiendeBullet4 = new FiendeSkudd(5, 5, 5, Color.GREEN);
                        FiendeSkudd fiendeBullet5 = new FiendeSkudd(5, 5, 5, Color.GREEN);
                        FiendeSkudd fiendeBullet6 = new FiendeSkudd(5, 5, 5, Color.GREEN);
                        FiendeSkudd fiendeBullet7 = new FiendeSkudd(5, 5, 5, Color.GREEN);

                        addFiendeBullet(fiendeBullet2, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);
                        addFiendeBullet(fiendeBullet3, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);
                        addFiendeBullet(fiendeBullet4, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);
                        addFiendeBullet(fiendeBullet5, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);
                        addFiendeBullet(fiendeBullet6, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);
                        addFiendeBullet(fiendeBullet7, boss1.getView().getTranslateX() + 30, boss1.getView().getTranslateY() + 60);

                        fiendeBullet2.setVelocity(new Point2D(-5, 0));
                        fiendeBullet3.setVelocity(new Point2D(-4, 3));
                        fiendeBullet4.setVelocity(new Point2D(-2, 5));
                        fiendeBullet5.setVelocity(new Point2D(2, 5));
                        fiendeBullet6.setVelocity(new Point2D(4, 3));
                        fiendeBullet7.setVelocity(new Point2D(5, 0));


                        fiendeBullet2.update();
                        fiendeBullet3.update();
                        fiendeBullet4.update();
                        fiendeBullet5.update();
                        fiendeBullet6.update();
                        fiendeBullet7.update();
                    }
                }


                boss1.FSM(player, boss1);
                boss1.update();

        }

        for (Fiender fiende1 : fiender) {
            if (fiende1.isColliding(player)) {
                fiende1.setVelocity(new Point2D(fiende1.getVelocity().getX() + Math.random() * 2 - 1, fiende1.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));

                fiende1.update();

                player.setHp(player.getHp() - 1);
                if (player.getHp() <= 0) {

                }
            }


            if (fiende1.isColliding(wall1) || fiende1.isColliding(wall2) || fiende1.isColliding(wall3) || fiende1.isColliding(wall4)) {
                fiende1.setVelocity(new Point2D(fiende1.getVelocity().getX() + Math.random() * 2 - 1, fiende1.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));

                fiende1.update();
            }

        }


        misiler.removeIf(Misiler::isDead);
        destroyers.removeIf(Destroyer::isDead);
        bosser.removeIf(Boss::isDead);
        Enemybullets.removeIf(FiendeSkudd::isDead);
        bullets.removeIf(Skudd::isDead);
        fiender.removeIf(Fiender::isDead);
        explosions.removeIf(GameObject::isDead);
        flamethrowers.removeIf(Flamethrower::isDead);
        cloakers.removeIf(Cloaker::isDead);
        powerups.removeIf(GameObject::isDead);
        hpups.removeIf(GameObject::isDead);
        explosions.removeIf((GameObject::isDead));
        bullets.forEach(Skudd::update);
        fiender.forEach(Fiender::update);
        Enemybullets.forEach(FiendeSkudd::update);
        misiler.forEach(Misiler::update);


        player.update();
        

        if(loaded == false && Status == STATUS.LOAD){
            for(int m = 0; m < fLoad.loaderFiende().size(); m++){
                addEnemy(fLoad.loaderFiende().get(m), fLoad.loaderFiende().get(m).getPosX(), fLoad.loaderFiende().get(m).getPosY());
            }
            for(int b = 0; b < bLoad.loaderBoss().size(); b++){
                addBoss(bLoad.loaderBoss().get(b), bLoad.loaderBoss().get(b).getPosX(), bLoad.loaderBoss().get(b).getPosY());
            }
            for(int d = 0; d < dLoad.loaderDestroyer().size(); d++){
                addDestroyer(dLoad.loaderDestroyer().get(d), dLoad.loaderDestroyer().get(d).getPosX(), dLoad.loaderDestroyer().get(d).getPosY());
            }
            loaded = true;
        }

        if (player.getScore() <20 ) {


            if (Math.random() < 0.003 || fiender.isEmpty()) {
                addEnemy((new Fiender(100, true, imageViewruu((new ImageView(FIENDERIMAGE)), 20, 20), 1, 1)), generateX(), generateY());
            }

            if (player.getScore() == 5 && destroyers.isEmpty()) {
                addDestroyer((new Destroyer(200, true, imageViewruu((new ImageView(DESTROYERIMG)), 40, 40), 1, 1)), generateX(), generateY());
            }

            if (player.getScore() == 10 && flamethrowers.size() <= 2) {
                addFlamethrower((new Flamethrower(150, true, imageViewruu((new ImageView(FLAMETHROWERIMG)), 30, 30), 1, 1)), generateX(), generateY());
            }

            if (player.getScore() == 15 && cloakers.isEmpty()) {
                addCloaker((new Cloaker(100, true, cloak((new ImageView(CLOAKER))), 1, 1)), generateX(), generateY());
            }
        }

        if (player.getScore() <40 && player.getScore()>20){

            if (player.getScore() == 21 && bosser.isEmpty()) {
                addBoss((new Boss(1000, 10, true, boss(new ImageView(BOSSIMAGE), 128), 1, 1)), 900, 250);
            }

            if (Math.random() < 0.005) {
                addEnemy((new Fiender(100, true, imageViewruu((new ImageView(FIENDERIMAGE)), 20, 20), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 27 || player.getScore() == 32) && destroyers.isEmpty()) {
                addDestroyer((new Destroyer(200, true, imageViewruu((new ImageView(DESTROYERIMG)), 40, 40), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 30 || player.getScore() == 29) && flamethrowers.size() <= 2) {
                addFlamethrower((new Flamethrower(150, true, imageViewruu((new ImageView(FLAMETHROWERIMG)), 30, 30), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 36 || player.getScore() == 35) && cloakers.size() <=1) {
                addCloaker((new Cloaker(100, true, cloak((new ImageView(CLOAKER))), 1, 1)), generateX(), generateY());
            }
        }

        if (player.getScore() <100 && player.getScore()>40){
            if (Math.random() < 0.008) {
                addEnemy((new Fiender(100, true, imageViewruu((new ImageView(FIENDERIMAGE)), 20, 20), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 43 || player.getScore() == 44) && destroyers.isEmpty()) {
                addDestroyer((new Destroyer(200, true, imageViewruu((new ImageView(DESTROYERIMG)), 40, 40), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 50 || player.getScore() == 51 || player.getScore() == 70 || player.getScore() == 71) && flamethrowers.size() <= 3) {
                addFlamethrower((new Flamethrower(150, true, imageViewruu((new ImageView(FLAMETHROWERIMG)), 30, 30), 1, 1)), generateX(), generateY());
            }

            if ((player.getScore() == 55 || player.getScore() == 58 || player.getScore() == 81 || player.getScore() == 82) && cloakers.size() <=1) {
                addCloaker((new Cloaker(100, true, cloak((new ImageView(CLOAKER))), 1, 1)), generateX(), generateY());
            }
        }

        if (player.getScore() >100){
            if (Math.random() < 0.05) {
                addEnemy((new Fiender(100, true, imageViewruu((new ImageView(FIENDERIMAGE)), 20, 20), 1, 1)), generateX(), generateY());
            }

            if (destroyers.isEmpty()) {
                addDestroyer((new Destroyer(200, true, imageViewruu((new ImageView(DESTROYERIMG)), 40, 40), 1, 1)), generateX(), generateY());
            }

            if ( flamethrowers.size() <= 3) {
                addFlamethrower((new Flamethrower(150, true, imageViewruu((new ImageView(FLAMETHROWERIMG)), 30, 30), 1, 1)), generateX(), generateY());
            }

            if (cloakers.size() <=2) {
                addCloaker((new Cloaker(100, true, cloak((new ImageView(CLOAKER))), 1, 1)), generateX(), generateY());
            }
        }


        for (GameObject x : powerups) {
            if (x.isColliding(player)) {

                player.setPowerup(true);
                x.setAlive(false);
                root.getChildren().remove(x.getView());
                x.update();
            }
        }

        for (GameObject y: hpups){
            if(y.isColliding(player)){
                player.setHp(player.getHp()+20);
                y.setAlive(false);
                root.getChildren().remove(y.getView());
                y.update();
            }

        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        

        Scene start = new Scene(createMeny());
        
        stage.setScene(start);
        
        load.setOnAction((ActionEvent ev) ->{
            if(Status == STATUS.LOAD){
                Status = STATUS.MENY;
                loadNy.setText("Starter nytt spill");
                load.setText("Last lagret spill");
            }else{
                if(loader.testLoad()){
                    Status = STATUS.LOAD;
                    loadNy.setText("Laster lagret spill");
                    load.setText("Fjern lagret spill");
                }else{
                    loadNy.setText("Fant ingen lagrede spill");
                }
            }
        });
        
        strt.setOnAction((ActionEvent event) -> {
            
            if(Status == STATUS.MENY){
                Status = STATUS.SPILL;
            }
            
            stage.setScene(new Scene(createContent()));
            
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            stage.setMaximized(true);
            
            stage.getScene().setOnKeyPressed((KeyEvent e) ->{
                if(Status == STATUS.SPILL || Status == STATUS.LOAD){
                    
                    if(player.getHp() <= 0){
                        Status = STATUS.GAMEOVER;
                        root.getChildren().add(gameOver);
                    }

                    if (null != e.getCode())
                        switch (e.getCode()) {
                            case A:


                                if(player.getRotate() == -180){
                                    player.setRotateVar(90);
                                }
                                else if(player.getRotate() == 0){
                                    player.setRotateVar(-90);
                                }
                                else if(player.getRotate() == 180){
                                    player.setRotateVar(-270);
                                }
                                else if(player.getRotate() == -90){
                                    player.setRotateVar(0);
                                }
                                else if(player.getRotate() == 90){
                                    player.setRotateVar(-180);
                                }
                                else if(player.getRotate() == 270){
                                    player.setRotateVar(-360);
                                }
                                else if(player.getRotate() == -270){
                                    player.setRotateVar(180);
                                }
                                else if(player.getRotate() == 360){
                                    player.setRotateVar(-450);
                                }
                                else if(player.getRotate() == -360){
                                    player.setRotateVar(270);
                                }




                                player.rotateRight();
                                player.setRotateVar(0);
                                player.rotateRight();
                                if (Math.abs(player.getVelocity().getX()) <=5 ) {

                                    player.setVelocity(player.getVelocity().add(new Point2D(-2.5, 0)));

                                }else{
                                    player.setVelocity(player.getVelocity());
                                }

                                break;
                            case D:

                                if(player.getRotate() == 180){
                                    player.setRotateVar(-90);
                                }
                                else if(player.getRotate() == 0){
                                    player.setRotateVar(90);
                                }
                                else if(player.getRotate() == -180){
                                    player.setRotateVar(270);
                                }
                                else if(player.getRotate() == -90){
                                    player.setRotateVar(180);
                                }
                                else if(player.getRotate() == 90){
                                    player.setRotateVar(0);
                                }
                                else if(player.getRotate() == 270){
                                    player.setRotateVar(-180);
                                }
                                else if(player.getRotate() == -270){
                                    player.setRotateVar(360);
                                }
                                else if(player.getRotate() == 360){
                                    player.setRotateVar(-270);
                                }
                                else if(player.getRotate() == -360){
                                    player.setRotateVar(450);
                                }


                                player.rotateRight();
                                player.setRotateVar(0);
                                player.rotateRight();

                                if (Math.abs(player.getVelocity().getX()) <=5 ) {

                                    player.setVelocity(player.getVelocity().add(new Point2D(2.5, 0)));

                                }else{
                                    player.setVelocity(player.getVelocity());
                                }


                                break;
                            case W:

                                if(player.getRotate() == 180){
                                    player.setRotateVar(-180);
                                }
                                else if(player.getRotate() == 0){
                                    player.setRotateVar(0);
                                }
                                else if(player.getRotate() == 90){
                                    player.setRotateVar(-90);
                                }
                                else if(player.getRotate() == -180){
                                    player.setRotateVar(180);
                                }
                                else if(player.getRotate() == -90){
                                    player.setRotateVar(90);
                                }
                                else if(player.getRotate() == 270){
                                    player.setRotateVar(-270);
                                }
                                else if(player.getRotate() == -270){
                                    player.setRotateVar(270);
                                }
                                else if(player.getRotate() == 360){
                                    player.setRotateVar(-360);
                                }
                                else if(player.getRotate() == -360){
                                    player.setRotateVar(360);
                                }


                                player.rotateRight();
                                player.setRotateVar(0);
                                player.rotateRight();

                                if (Math.abs(player.getVelocity().getY()) <=5 ) {

                                    player.setVelocity(player.getVelocity().add(new Point2D(0, -2.5)));

                                }else{
                                    player.setVelocity(player.getVelocity());
                                }
                                break;
                            case S:
                                if(player.getRotate() == 180){
                                    player.setRotateVar(0);
                                }
                                else if(player.getRotate() == 0){
                                    player.setRotateVar(180);
                                }
                                else if(player.getRotate() == -180){
                                    player.setRotateVar(360);
                                }
                                else if(player.getRotate() == -90){
                                    player.setRotateVar(270);
                                }
                                else if(player.getRotate() == 90){
                                    player.setRotateVar(90);
                                }
                                else if(player.getRotate() == 270){
                                    player.setRotateVar(-90);
                                }
                                else if(player.getRotate() == -270){
                                    player.setRotateVar(450);
                                }
                                else if(player.getRotate() == 360){
                                    player.setRotateVar(-180);
                                }
                                else if(player.getRotate() == -360){
                                    player.setRotateVar(540);
                                }


                                player.rotateRight();
                                player.setRotateVar(0);
                                player.rotateRight();
                                if (Math.abs(player.getVelocity().getY()) <=5 ) {

                                    player.setVelocity(player.getVelocity().add(new Point2D(0, 2.5)));

                                }else{
                                    player.setVelocity(player.getVelocity());
                                }
                            default:
                                break;
                        }
                }

                if(e.getCode() == KeyCode.P || e.getCode() == KeyCode.ESCAPE){
                    if(Status == STATUS.SPILL || Status == STATUS.LOAD){
                        Status = STATUS.MENY;
                        root.getChildren().add(pause);
                        stage.show();
                    }else{
                        Status = STATUS.SPILL;
                        lagret.setText(" ");
                        root.getChildren().removeAll(pause);
                    }
                }
                
                fortsett.setOnAction((ActionEvent eo) -> {
                    Status = STATUS.SPILL;
                    lagret.setText(" ");
                    root.getChildren().removeAll(pause);
                });
                
                lagre.setOnAction((ActionEvent ej) -> {
                    player.setXY(player.getView().getTranslateX(), player.getView().getTranslateY());
                    for(int t = 0; t < fiender.size(); t++){
                        fiender.get(t).setPosXY(fiender.get(t).getView().getTranslateX(), fiender.get(t).getView().getTranslateY());
                    }
                    filen.saveFiende(fiender);
                    for(int t = 0; t < bosser.size(); t++){
                        bosser.get(t).setPosXY(bosser.get(t).getView().getTranslateX(), bosser.get(t).getView().getTranslateY());
                    }
                    filen.saveBoss(bosser);
                    for(int t = 0; t < destroyers.size(); t++){
                        destroyers.get(t).setPosXY(destroyers.get(t).getView().getTranslateX(), destroyers.get(t).getView().getTranslateY());
                    }
                    filen.saveDestroyer(destroyers);
                    filen.saveSpiller(player.getHp(), player.getScore(), player.getPosX(), player.getPosY(), player.getPowerup());
                    lagret.setText("Lagret.");
                });
                
                quit.setOnAction((ActionEvent ek) ->{
                    Status = STATUS.MENY;
                    loadNy.setText("Starter nytt spill");
                    load.setText("Last lagret spill");
                    lagret.setText(" ");
                    timer.stop();
                    timer2.stop();
                    fiender.clear();
                    bosser.clear();
                    destroyers.clear();
                    bullets.clear();
                    loaded = false;
                    stage.setMaximized(false);
                    stage.setScene(start);
                });
                
                gameOverB.setOnAction((ActionEvent ep) ->{
                    Status = STATUS.MENY;
                    loadNy.setText("Starter nytt spill");
                    load.setText("Last lagret spill");
                    lagret.setText(" ");
                    timer.stop();
                    timer2.stop();
                    fiender.clear();
                    bosser.clear();
                    destroyers.clear();
                    bullets.clear();
                    loaded = false;
                    stage.setMaximized(false);
                    stage.setScene(start);
                });
            });



            stage.getScene().setOnKeyReleased(e ->{
                if(Status == STATUS.SPILL || Status == STATUS.LOAD){
                    if (null != e.getCode())switch (e.getCode()) {
                        case A:
                            player.setVelocity(new Point2D(-0.1,0));
                            break;
                        case D:
                            player.setVelocity(new Point2D(0.1,0));
                            break;
                        case W:
                            player.setVelocity(new Point2D(0,-0.1));
                            break;
                        case S:
                            player.setVelocity(new Point2D(0,0.1));
                            break;

                        case SPACE:

                            Skudd bullet = new Skudd(2.5,2.5,2.5,BLACK);


                            if(player.getPowerup()){
                                if (equalsX(player, new Point2D(0.1,0)) || equalsX(player,new Point2D(-0.1,0))){
                                    Skudd bullet1 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet2 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet3 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet4 = new Skudd(2.5,2.5,2.5,BLACK);
                                    bullet1.setVelocity(player.getVelocity().add(0, 0.01).normalize().multiply(10));
                                    bullet2.setVelocity(player.getVelocity().add(0, 0.02).normalize().multiply(10));
                                    addBullet(bullet1, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    addBullet(bullet2, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    bullet3.setVelocity(player.getVelocity().add(0, -0.01).normalize().multiply(10));
                                    bullet4.setVelocity(player.getVelocity().add(0, -0.02).normalize().multiply(10));
                                    addBullet(bullet3, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    addBullet(bullet4, player.getView().getTranslateX(), player.getView().getTranslateY());

                                } else if (equalsY(player, new Point2D(0,0.1)) || equalsY(player,new Point2D(0,-0.1))){

                                    Skudd bullet1 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet2 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet3 = new Skudd(2.5,2.5,2.5,BLACK);
                                    Skudd bullet4 = new Skudd(2.5,2.5,2.5,BLACK);
                                    bullet1.setVelocity(player.getVelocity().add(0.01, 0).normalize().multiply(10));
                                    bullet2.setVelocity(player.getVelocity().add(0.02, 0).normalize().multiply(10));
                                    addBullet(bullet1, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    addBullet(bullet2, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    bullet3.setVelocity(player.getVelocity().add(-0.01, 0).normalize().multiply(10));
                                    bullet4.setVelocity(player.getVelocity().add(-0.02, 0).normalize().multiply(10));
                                    addBullet(bullet3, player.getView().getTranslateX(), player.getView().getTranslateY());
                                    addBullet(bullet4, player.getView().getTranslateX(), player.getView().getTranslateY());
                                }
                            }

                            bullet.setVelocity(player.getVelocity().normalize().multiply(10));
                            addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());


                            if(false){
                                bullet.setVelocity(player.getVelocity().normalize().multiply(30));
                            }
                        break;

                        default:
                            break;
                    }
                }
            });
        });
        stage.show();
    }
}
