import java.io.Serializable;
import javafx.scene.Node;

/**
 * Spiller represents the player in the game
 * 
 * @author Gustav Wahl and Gustav Wehn
 */
public class Spiller extends GameObject implements Serializable{

    private double hp;
    private int damage;
    private int score;
    private String name;
    private double posX;
    private double posY;
    private boolean powerUp;

    /**
     * Constructs and initializes a Spiller object
     * 
     * @param hp the hp of the player 
     * @param damage the damage of the player
     * @param score the score of the player
     * @param name the name of the player
     * @param view position of the player
     * @param posX the X coordinate of the player
     * @param posY the Y coordinate of the player 
     * @param powerup if the player has a powerup or not
     */
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
    
    /**
     * Sets the hp of the player
     * 
     * @param hp hp of the player
     */
    public void setHp(double hp){
        this.hp = hp;
    }
    
    /**
     * Gets the hp of the player
     * 
     * @return hp of the player
     */
    public double getHp() {
        return hp;
    }
    
    /**
     * Sets the damage of the player
     * 
     * @param damage damage of the player
     */
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    /**
     * Gets the damage of the player
     * 
     * @return damage of the player
     */
    public int getDamage() {
        return damage;
    }
    
    /**
     * increases the score of the player by 1
     */
    public void setScore() {
        score++;
    }
    
    /**
     * Gets the score of the players score
     * 
     * @return score of the player
     */
    public int getScore(){
        return score;
    }
    
    /**
     * Sets the value of powerup
     * 
     * @param up powerup value
     */
    public void setPowerup(boolean up){
        this.powerUp = up;
    }
    
    /**
     * Gets the players value of powerup
     * 
     * @return the value of powerup
     */
    public boolean getPowerup(){
        return powerUp;
    }
    
    /**
     * sets X and Y coordinate
     * 
     * @param pX X coordinate
     * @param pY Y coordinate
     */
    public void setXY(double pX, double pY){
        posX = pX;
        posY = pY;
    }
    
    /**
     * Gets X coordinates
     * 
     * @return X coordinate
     */
    public double getPosX(){
        return posX;
    }
    
    /**
     * Gets Y coordinates 
     * 
     * @return Y coordinate
     */
    public double getPosY(){
        return posY;
    }
}

