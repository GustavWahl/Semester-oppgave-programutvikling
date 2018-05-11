import java.io.Serializable;
import javafx.scene.Node;

public class Spiller extends GameObject implements Serializable{

    private double hp;
    private int damage;
    private int score;
    private String name;
    private double posX;
    private double posY;
    private boolean powerUp;


    public Spiller(double hp, int damage, int score, String name, Node view, double posX, double posY, boolean powerup){
        super(view);
        this.hp = hp;
        this.damage = damage;
        this.score = score;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.powerUp = powerup;
    }

    public void setHp(double hp){
        this.hp = hp;
    }
    public double getHp() {
        return hp;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
    
    public int getDamage() {
        return damage;
    }

    public void setScore() {
        score++;
    }

    public int getScore(){
        return score;
    }
    
    public void setPowerup(boolean up){
        this.powerUp = up;
    }
    
    public boolean getPowerup(){
        return powerUp;
    }
    
    public void setXY(double pX, double pY){
        posX = pX;
        posY = pY;
    }
    
    public double getPosX(){
        return posX;
    }
    
    public double getPosY(){
        return posY;
    }
}

