import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Screen;

public class Main extends Application{
    
    private enum STATUS{
        SPILL,
        MENY
    }
    
    private STATUS Status = STATUS.SPILL;

    private Pane root;
    private Spiller player;
    private GameObject wall1;
    private GameObject wall2;
    private GameObject wall3;
    private GameObject wall4;
    private Button strt;
    private boolean powerup;

    List<Fiender> fiender = new ArrayList<>();
    List<Skudd> bullets = new ArrayList<>();
    List<GameObject> walls = new ArrayList<>();
    List<FiendeSkudd> Enemybullets = new ArrayList<>();
    List<GameObject> powerups = new ArrayList<>();

    public static void Main(String[] args) {

        launch(args);
    }


    //Her lager man designe til spilleren
    public StackPane lagkarakter() {

        StackPane pane = new StackPane();
        Rectangle pistol = new Rectangle(3,22,(new Color(0, 0, 0, 1)));
        Rectangle kropp = new Rectangle(20, 20, (new Color(0.0f, 0.0f, 1.0f, 1.0)));
        Circle hode = new Circle(10, 15, 12, (new Color(0.3f, 0.6f, 1.0f, 1.0)));
        Circle flame1 = new Circle(3,3,3);
        flame1.setFill(new Color(1.0f,0f,0f,1.0f));

        pane.getChildren().addAll(kropp, hode,pistol,flame1);
        pane.setAlignment(pistol, Pos.CENTER_LEFT);
        pane.setAlignment(flame1,Pos.TOP_LEFT);
        pane.setPrefSize(20, 20);

        return pane;
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
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth();
        double height = screenBounds.getHeight();
        
        root.setPrefSize(height, width);

        player = new Spiller(100, 10, 0,"Gustav",lagkarakter());
        player.setVelocity(new Point2D(0,-0.001));

        // (new Rectangle(20,20,Color.BLUE)),(new Circle(5,5,5,(new Color(0.1f,0.3f,1.0f, 1.0))))

        addGameObject(player, 300, 300);

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



        // timern som får alt til å oppdateres
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(Status == STATUS.SPILL){
                    onUpdate();
                }
            }
        };
        timer.start();

        return root;
    }
    
    public Parent createMeny(){
        strt = new Button("Start Nytt Spill");
        
        
        javafx.scene.control.Button hScore = new javafx.scene.control.Button();
        hScore.setText("High Score");
        hScore.setOnAction((ActionEvent event) -> {
            System.out.println("Laster High");
        });
        
        javafx.scene.control.Button load = new javafx.scene.control.Button();
        load.setText("Load");
        load.setOnAction((ActionEvent event) -> {
            System.out.println("Laster");
        });
        
        VBox root = new VBox();
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(strt, hScore, load);
        
        return root;
    }


    // metode for å legge til og gjøre dem visible
    private void addBullet(Skudd bullet, double x, double y){
        bullets.add(bullet);
        addGameObject(bullet,x,y);
    }


    // Her er en metode for å legge til fiender
    private void addEnemy(Fiender fiende, double x, double y){

        fiender.add(fiende);
        addGameObject(fiende,x,y);

        System.out.println(fiende.getDecideActiveState());
    }

    private void addFiendeBullet(FiendeSkudd bullet, double x, double y){
        Enemybullets.add(bullet);
        addGameObject(bullet,x,y);
    }

    private void addPowerUp(GameObject ups, double x, double y){
        powerups.add(ups);
        addGameObject(ups,x,y);

    }



    // GameObject er Parent klassen til alle spill objektene

    public void addGameObject(GameObject object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }


    // metoden som oppdaterer spille via en timer i createContent
    private  void onUpdate(){

        // Disse Foreach'ene går gjennom Arraylistene våre for å kunne sjekke alle fiender og skudd

        for (Skudd bullet: bullets) {
            for (Fiender fiende : fiender) {
                if (bullet.isColliding(fiende)) {
                    bullet.setAlive(false);
                    fiende.setHp(fiende.getHp() - 34);

                    root.getChildren().remove(bullet.getView());
                    if (fiende.getHp() <= 0) {
                        fiende.setAlive(false);

                        // Tror jeg fucka litt opp litt koden så nå funker den ikke, men den lager litt så mekk java.nio her
                     /*   FileInputStream in;
                        try{
                            in = new FileInputStream(new File("G:\\ProgramUtviklingOppdatert\\BoxHeadTest\\src\\Roblox-Death-Sound-Effect.wav"));
                            AudioStream audio = new AudioStream(in);
                            AudioPlayer.player.start(audio);

                        }catch (Exception e){

                        }*/
                     
                        root.getChildren().remove(fiende.getView());
                    }
                }


            }
            // kollidering med vegger her bruker vi random for å skape random rikosjett i en random retning som er motsatt av der kulen kom fra, eller at kulen dør
            if (bullet.isColliding(wall1) || bullet.isColliding(wall2) || bullet.isColliding(wall3) || bullet.isColliding(wall4)) {
                // bullet.setVelocity(new Point2D(Math.random()*10-5,Math.random()*10-5));
                if (Math.random()*10-5 < 4) {
                    bullet.setVelocity(new Point2D(bullet.getVelocity().getX() + Math.random() * 10 - 5, bullet.getVelocity().getY() + Math.random() * 10 - 5).multiply(-1));
                }else {
                    bullet.setAlive(false);
                    root.getChildren().remove(bullet.getView());

                }

            }

        }

        for (Fiender fiende3 : fiender) {
            for (FiendeSkudd fbullet : Enemybullets) {
                if (fbullet.isColliding(player)) {
                    player.setHp(player.getHp() - 10);
                    fbullet.setAlive(false);
                    root.getChildren().remove(fbullet.getView());
                    fbullet.update();
                }
                if (fbullet.isColliding(wall1) || fbullet.isColliding(wall2) || fbullet.isColliding(wall3) || fbullet.isColliding(wall4)) {
                    // bullet.setVelocity(new Point2D(Math.random()*10-5,Math.random()*10-5));
                    if (Math.random() * 13 - 5 < 4) {
                        fbullet.setVelocity(new Point2D(fbullet.getVelocity().getX() + Math.random() * 10 - 5, fbullet.getVelocity().getY() + Math.random() * 10 - 5).multiply(-1));
                    } else {
                        fbullet.setAlive(false);
                        root.getChildren().remove(fbullet.getView());

                    }

                }
            }
            if (fiende3.getDecideActiveState() == 0) {

                if (Math.random() < 0.1) {

                    FiendeSkudd fiendeBullet2 = new FiendeSkudd();
                    addFiendeBullet(fiendeBullet2, fiende3.getView().getTranslateX(), fiende3.getView().getTranslateY());
                    fiendeBullet2.setVelocity((fiende3.getVelocity().normalize().multiply(3)));

                    fiendeBullet2.update();
                }
            }


                    fiende3.FSM(player, fiende3);
                //   fiende3.update();
        }


        for (Fiender fiende1 : fiender) {
            if (fiende1.isColliding(player) || fiende1.isColliding(wall1) || fiende1.isColliding(wall2) || fiende1.isColliding(wall3) || fiende1.isColliding(wall4)) {
                // fiende1.setVelocity(new Point2D(Math.random()*4-2,Math.random()*4-2));
                fiende1.setVelocity(new Point2D(fiende1.getVelocity().getX() + Math.random() * 2 - 1, fiende1.getVelocity().getY() + Math.random() * 2 - 1).multiply(-1));



                fiende1.update();


                player.setHp(player.getHp() - 1);
                if (player.getHp() <= 0) {
                    System.out.println("Game Over");
                }
            }
        }



        //her fjerner man kuller og fiender hvis de er døde

        Enemybullets.removeIf(FiendeSkudd::isDead);
        bullets.removeIf(Skudd::isDead);
        fiender.removeIf(Fiender::isDead);

        bullets.forEach(Skudd::update);
        fiender.forEach(Fiender::update);
        Enemybullets.forEach(FiendeSkudd:: update);


        player.update();

        //her leger man til fiender, vi bruker konstruktøren fra klassen fiender for å kunne putte inn verdier , da kan vi også gjøre fiendene sterker over tid

        if (Math.random() < 0.005){
            addEnemy((new Fiender(100,10,true,(new Rectangle(20,20, RED)))),300,250 /*Math.random() * 600, Math.random() * 600*/);

        }


        if (Math.random() < 0.005){

            addPowerUp(  new GameObject(new Circle(5,5,5,Color.BLACK)), Math.random()*600, 600);

        }
        for (GameObject x:powerups) {
            if (x.isColliding(player)){

                powerup = true;
                root.getChildren().remove(x.getView());
            }

        }


//_________________________TESTING OF METHODS_______________________//

        // testing av hp system
        //  System.out.println(player.getHp());
        // System.out.println((player.getView().getTranslateX() - 300)+ "+" + (player.getView().getTranslateY()- 250));

       /*for (Fiender fiende5 : fiender) {
          System.out.println( Math.pow((player.getX() - (fiende5.getX())),2)*0.001);
           System.out.println( Math.pow((player.getY() - (fiende5.getY())),2)*0.001);
        }*/
    }


    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setScene(new Scene(createMeny()));
        
        // Dette er scenen, vi puter inn createcontent i setScene.
        strt.setOnAction((ActionEvent event) -> {
            stage.setScene(new Scene(createContent()));
        
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        stage.getScene().setOnKeyPressed((KeyEvent e) ->{
            
            if(Status == STATUS.SPILL){
            
            if (null != e.getCode()) //dette er bevegelse , Point2D blir brukt dette er en farts vektor og vi har x, -x, y, -y
            //Keycode er satt opp mot en lambda metode som gjør at når man presser en tast eller releaser den(nedenfor) så gjøres det en handling
            switch (e.getCode()) {
                case A:
                    player.setVelocity(new Point2D(-5,0));
                    break;
                case D:
                    player.setVelocity(new Point2D(5,0));
                    break;
                case W:
                    player.setVelocity(new Point2D(0,-5));
                    break;
                case S:
                    player.setVelocity(new Point2D(0,5));
                    break;
                case Q:
                    player.rotateLeft();
                    break;
                case E:
                    player.rotateRight();
                    break;
                default:
                    break;
            }


            if (e.getCode() == KeyCode.SPACE) {

                Skudd bullet = new Skudd();

                // POWERUPS , skriv in true ini der så kan man aktivere de, denne gjør at du skyter 4 kuler ekstra
                if(powerup){
                    if (equalsX(player, new Point2D(0.1,0)) || equalsX(player,new Point2D(-0.1,0))){
                        Skudd bullet1 = new Skudd();
                        Skudd bullet2 = new Skudd();
                        Skudd bullet3 = new Skudd();
                        Skudd bullet4 = new Skudd();
                        bullet1.setVelocity(player.getVelocity().add(0, 0.01).normalize().multiply(10));
                        bullet2.setVelocity(player.getVelocity().add(0, 0.02).normalize().multiply(10));
                        addBullet(bullet1, player.getView().getTranslateX(), player.getView().getTranslateY());
                        addBullet(bullet2, player.getView().getTranslateX(), player.getView().getTranslateY());
                        bullet3.setVelocity(player.getVelocity().add(0, -0.01).normalize().multiply(10));
                        bullet4.setVelocity(player.getVelocity().add(0, -0.02).normalize().multiply(10));
                        addBullet(bullet3, player.getView().getTranslateX(), player.getView().getTranslateY());
                        addBullet(bullet4, player.getView().getTranslateX(), player.getView().getTranslateY());

                    } else if (equalsY(player, new Point2D(0,0.1)) || equalsY(player,new Point2D(0,-0.1))){

                        Skudd bullet1 = new Skudd();
                        Skudd bullet2 = new Skudd();
                        Skudd bullet3 = new Skudd();
                        Skudd bullet4 = new Skudd();
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

                // Også Powerup
                if(false){
                    bullet.setVelocity(player.getVelocity().normalize().multiply(30));
                }
            }
            }
            
            if(e.getCode() == KeyCode.P){
                if(Status == STATUS.SPILL){
                    Status = STATUS.MENY;
                }else{
                    Status = STATUS.SPILL;
                }
            }

            //_______________________TESTING AV VELOCITY_____________________//
          /*
            // System.out.println(player.getVelocity().normalize().multiply(10));
            System.out.println(player.getVelocity());
            System.out.println(player.getVelocity().getX());
            System.out.println(player.getVelocity().getY());
            //System.out.println(new Point2D(0,5.0).getY());
        */
        });

        /*fordi fartsvektoren aldri stopper etter du har presset den , må man lage en keyrelease som setter farten din ned til nærmere 0 verdi
            Det er putta 0.1 som verdi fordi skuddene må vite hvilken retning man står mot, når skuddene blir skutt ut ganges de opp sånn at de går mye raskere.

        */

        stage.getScene().setOnKeyReleased(e ->{
            
            if(Status == STATUS.SPILL){
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
                default:
                    break;
            }
            }
        });
        });

        stage.show();
    }
}
