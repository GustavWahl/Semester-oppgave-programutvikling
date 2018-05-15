import javafx.geometry.Point2D;
import javafx.scene.Node;
    
/**
 * Boss represents the bosses in the game
 * 
 * @author Gustav Wahl and Gustav Wehn
 */
public class Boss extends GameObject{

    private boolean alive = true;
    private int hp;
    private int damage;
    private int decideActiveState;
    private double posX;
    private double posY;

    /**
     * Constructs and initializes a Boss object
     * 
     * @param hp hp of the boss
     * @param damage damage of the boss
     * @param alive alive state of the boss
     * @param view position of the Boss
     * @param posX X coordinate of the boss
     * @param posY Y coordinate of the boss
     */
    public Boss(int hp, int damage,boolean alive, Node view, double posX, double posY){
        super(view);
        this.hp = hp;
        this.damage = damage;
        this.alive = alive;
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Decides the state of the boss, what the boss does.
     * It runs a method that constatly runs. Gets called from animation timer.
     * 
     * @param object the player in the game
     * @param object2 the boss
     */
    public void FSM (GameObject object , Boss object2) {

        switch (decideActiveState) {

            case 0:
                StateShoot(object,object2);

                break;

            case 1:
                StateWalk(object,object2);

                break;

            case 2:
                StandStill(object,object2);
                break;

            default:
                StandStill(object,object2);

                break;
        }
    }

    /**
     * Sets the state of the boss
     * 
     * @param state state
     */
    public void setState(int state) {
        decideActiveState = state;
    }

    /**
     * Decides that the boss should shoot
     * 
     * @param object the player in the game
     * @param object2 the boss
     */
    public void StateShoot(GameObject object, Boss object2) {

        object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.001)), (((object.getY() - object2.getY()) * 0.001))));

        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 && Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }
    
    /**
     * Decides that the boss should stand still
     * 
     * @param object the player in the game
     * @param object2 the boss
     */
    public void StandStill(GameObject object, Boss object2) {

        object2.setVelocity(new Point2D(0, 0));

        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 150 && Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 150 ) {
            setState(1);
        }
    }
    
    /**
     * Decides that the boss should walk
     * 
     * @param object the player in the game
     * @param object2 the enemy
     */
    public void StateWalk(GameObject object, Boss object2) {

        object2.setVelocity(new Point2D(((object.getX() - object2.getX()) * 0.001), ((object.getY() - object2.getY()) * 0.001)));

        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 50 && Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 50 ) {
            setState(0);
        }
       else if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 350 && Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 350 ) {
            setState(2);
        }
    }

    /**
     * Gets the active state
     * 
     * @return dedcideActiveState
     */
    public int getDecideActiveState() {
        return decideActiveState;
    }
    
    /**
     * Sets the hp of the boss
     * 
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    /**
     * Gets the hp of the boss
     * 
     * @return hp
     */
    public int getHp() {
        return hp;
    }
    
    /**
     * Gets the alive state of the boss
     * 
     * @return alive
     */
    public boolean isAlive(){
        return  alive;
    }
    
    /**
     * Gets the opisite of the alivestate of the boss
     * 
     * @return !alive
     */
    public boolean isDead(){
        return !alive;
    }
    
    /**
     * Sets the damage of the boss
     * 
     * @param damage damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    /**
     * Sets the alive state of the boss
     * 
     * @param alive alive state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * Sets the X and Y coordinates of the boss
     * 
     * @param posX X coordinate
     * @param posY Y coordinates
     */
    public void setPosXY(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Gets the X coordinate of the boss
     * 
     * @return X coordinate
     */
    public double getPosX(){
        return posX;
    }
    
    /**
     * Gets the Y coordinate of the boss
     * 
     * @return 
     */
    public double getPosY(){
        return posY;
    }
}