import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Fiender represents the enemies in the game
 * 
 * @author Gustav Wahl and Gustav Wehn
 */
public class Fiender extends GameObject{

    private boolean alive = true;
    private int hp;
    private int decideActiveState;
    private double posX;
    private double posY;
    private int walk;

    /**
     * Constructs and initializes a Fiender object
     * 
     * @param hp hp of the enemy
     * @param alive if the enemy is alive or not
     * @param view position of the enemy
     * @param posX X coordenate of the enemy
     * @param posY Y coordinate of the enemy
     */
    public Fiender(int hp,boolean alive, Node view, double posX, double posY){
        super(view);
        this.hp = hp;
        this.alive = alive;
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Decides the state of the enemy, what the enemy does.
     * It runs a method that constatly runs. Gets called from animation timer.
     * 
     * @param object is the player in the game
     * @param object2 the enemy
     */
    public void FSM (GameObject object , Fiender object2) {

        switch (decideActiveState) {

            case 0:
                StateShoot(object,object2);

                break;

            case 1:
                StateWalk(object,object2);

                break;

            default:
                StateWalk(object,object2);

                break;
        }
    }

    /**
     * Sets the state of the enemy
     * 
     * @param state state
     */
    public void setState(int state) {
        decideActiveState = state;
    }

    /**
     * Localizes the player relative to the enemy
     * 
     * @param object the player in the game
     * @param object2 the enemy
     */
    public void StateShoot(GameObject object, Fiender object2) {

        if (object2.getVelocity().getX() <= -1) {

            if (object2.getRotate() == -180) {
                object2.setRotateVar(90);
            } else if (object2.getRotate() == 0) {
                object2.setRotateVar(-90);
            } else if (object2.getRotate() == 180) {
                object2.setRotateVar(-270);
            } else if (object2.getRotate() == -90) {
                object2.setRotateVar(0);
            } else if (object2.getRotate() == 90) {
                object2.setRotateVar(-180);
            } else if (object2.getRotate() == 270) {
                object2.setRotateVar(-360);
            } else if (object2.getRotate() == -270) {
                object2.setRotateVar(180);
            } else if (object2.getRotate() == 360) {
                object2.setRotateVar(-450);
            } else if (object2.getRotate() == -360) {
                object2.setRotateVar(270);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getX() >= 1) {
            if (object2.getRotate() == 180) {
                object2.setRotateVar(-90);
            } else if (object2.getRotate() == 0) {
                object2.setRotateVar(90);
            } else if (object2.getRotate() == -180) {
                object2.setRotateVar(270);
            } else if (object2.getRotate() == -90) {
                object2.setRotateVar(180);
            } else if (object2.getRotate() == 90) {
                object2.setRotateVar(0);
            } else if (object2.getRotate() == 270) {
                object2.setRotateVar(-180);
            } else if (object2.getRotate() == -270) {
                object2.setRotateVar(360);
            } else if (object2.getRotate() == 360) {
                object2.setRotateVar(-270);
            } else if (object2.getRotate() == -360) {
                object2.setRotateVar(450);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getY() <= -1){
            if(object2.getRotate() == 180){
                object2.setRotateVar(-180);
            }
            else if(object2.getRotate() == 0){
                object2.setRotateVar(0);
            }
            else if(object2.getRotate() == 90){
                object2.setRotateVar(-90);
            }
            else if(object2.getRotate() == -180){
                object2.setRotateVar(180);
            }
            else if(object2.getRotate() == -90){
                object2.setRotateVar(90);
            }
            else if(object2.getRotate() == 270){
                object2.setRotateVar(-270);
            }
            else if(object2.getRotate() == -270){
                object2.setRotateVar(270);
            }
            else if(object2.getRotate() == 360){
                object2.setRotateVar(-360);
            }
            else if(object2.getRotate() == -360){
                object2.setRotateVar(360);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getY() >= 1){

            if(object2.getRotate() == 180){
                object2.setRotateVar(0);
            }
            else if(object2.getRotate() == 0){
                object2.setRotateVar(180);
            }
            else if(object2.getRotate() == -180){
                object2.setRotateVar(360);
            }
            else if(object2.getRotate() == -90){
                object2.setRotateVar(270);
            }
            else if(object2.getRotate() == 90){
                object2.setRotateVar(90);
            }
            else if(object2.getRotate() == 270){
                object2.setRotateVar(-90);
            }
            else if(object2.getRotate() == -270){
                object2.setRotateVar(450);
            }
            else if(object2.getRotate() == 360){
                object2.setRotateVar(-180);
            }
            else if(object2.getRotate() == -360){
                object2.setRotateVar(540);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }



        //object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));



        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }
    
    /**
     * Localizes and calculates distance to the player
     * 
     * @param object the player in the game
     * @param object2 the enemy
     */
    public void StateWalk(GameObject object, Fiender object2) {


        if (object2.getVelocity().getX() <= -1) {

            if (object2.getRotate() == -180) {
                object2.setRotateVar(90);
            } else if (object2.getRotate() == 0) {
                object2.setRotateVar(-90);
            } else if (object2.getRotate() == 180) {
                object2.setRotateVar(-270);
            } else if (object2.getRotate() == -90) {
                object2.setRotateVar(0);
            } else if (object2.getRotate() == 90) {
                object2.setRotateVar(-180);
            } else if (object2.getRotate() == 270) {
                object2.setRotateVar(-360);
            } else if (object2.getRotate() == -270) {
                object2.setRotateVar(180);
            } else if (object2.getRotate() == 360) {
                object2.setRotateVar(-450);
            } else if (object2.getRotate() == -360) {
                object2.setRotateVar(270);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getX() >= 1) {
            if (object2.getRotate() == 180) {
                object2.setRotateVar(-90);
            } else if (object2.getRotate() == 0) {
                object2.setRotateVar(90);
            } else if (object2.getRotate() == -180) {
                object2.setRotateVar(270);
            } else if (object2.getRotate() == -90) {
                object2.setRotateVar(180);
            } else if (object2.getRotate() == 90) {
                object2.setRotateVar(0);
            } else if (object2.getRotate() == 270) {
                object2.setRotateVar(-180);
            } else if (object2.getRotate() == -270) {
                object2.setRotateVar(360);
            } else if (object2.getRotate() == 360) {
                object2.setRotateVar(-270);
            } else if (object2.getRotate() == -360) {
                object2.setRotateVar(450);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getY() <= -1){
            if(object2.getRotate() == 180){
                object2.setRotateVar(-180);
            }
            else if(object2.getRotate() == 0){
                object2.setRotateVar(0);
            }
            else if(object2.getRotate() == 90){
                object2.setRotateVar(-90);
            }
            else if(object2.getRotate() == -180){
                object2.setRotateVar(180);
            }
            else if(object2.getRotate() == -90){
                object2.setRotateVar(90);
            }
            else if(object2.getRotate() == 270){
                object2.setRotateVar(-270);
            }
            else if(object2.getRotate() == -270){
                object2.setRotateVar(270);
            }
            else if(object2.getRotate() == 360){
                object2.setRotateVar(-360);
            }
            else if(object2.getRotate() == -360){
                object2.setRotateVar(360);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        else if(object2.getVelocity().getY() >= 1){

            if(object2.getRotate() == 180){
                object2.setRotateVar(0);
            }
            else if(object2.getRotate() == 0){
                object2.setRotateVar(180);
            }
            else if(object2.getRotate() == -180){
                object2.setRotateVar(360);
            }
            else if(object2.getRotate() == -90){
                object2.setRotateVar(270);
            }
            else if(object2.getRotate() == 90){
                object2.setRotateVar(90);
            }
            else if(object2.getRotate() == 270){
                object2.setRotateVar(-90);
            }
            else if(object2.getRotate() == -270){
                object2.setRotateVar(450);
            }
            else if(object2.getRotate() == 360){
                object2.setRotateVar(-180);
            }
            else if(object2.getRotate() == -360){
                object2.setRotateVar(540);
            }


            object2.rotateRight();
            object2.setRotateVar(0);
            object2.rotateRight();
        }

        boolean pIsFurtherToTheXAway = object.getX() > object2.getX();
        boolean pIsFurtherToTheYAway = object.getY() > object2.getY();
        boolean pIsFurtherToTheMinusXAway = object2.getX() > object.getX();
        boolean pIsFurtherToTheMinusYAway = object2.getY() > object.getY();



        if (pIsFurtherToTheXAway){
            object2.setVelocity(new Point2D(2.5,0));

            if (Math.abs(object.getY() - object2.getY()) > 50){

                if(pIsFurtherToTheYAway){
                    object2.setVelocity(new Point2D(0,2.5));
                }

                else if (pIsFurtherToTheMinusYAway){
                    object2.setVelocity(new Point2D(0,-2.5));
                }
            }
        }

        else if (pIsFurtherToTheMinusXAway){
            object2.setVelocity(new Point2D(-2.5,0));

            if (Math.abs(object.getY() - object2.getY()) > 50){

                if(pIsFurtherToTheYAway){
                    object2.setVelocity(new Point2D(0,2.5));
                }

                else if (pIsFurtherToTheMinusYAway){
                    object2.setVelocity(new Point2D(0,-2.5));
                }
            }

        }




        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 5 || Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 5 ) {
            setState(0);
        }
    }
    
    /**
     * Gets the activestate of the enemy
     * 
     * @return active state
     */
    public int getDecideActiveState() {
        return decideActiveState;
    }
    
    /**
     * Sets the hp of the enemy
     * 
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    /**
     * Gets the hp of the enemy
     * 
     * @return hp
     */
    public int getHp() {
        return hp;
    }
    
    /**
     * Gets the alive state of the enemy
     * 
     * @return the alive-sate of the enemy
     */
    public boolean isDead(){
        return !alive;
    }
    
    /**
     * Sets the alive state of the enemy
     * 
     * @param alive alive state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * sets the X and Y coordinates of the enemy
     * 
     * @param posX X coordinate
     * @param posY Y coordinate
     */
    public void setPosXY(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * returns the X coordinte of the enemy
     * 
     * @return X coordinate
     */
    public double getPosX(){
        return posX;
    }
    
    /**
     * returns the Y coordinate
     * 
     * @return X coordinate
     */
    public double getPosY(){
        return posY;
    }
}
