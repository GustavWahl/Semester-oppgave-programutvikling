import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Destroyer represents the bosses with lock on shots in the game
 * 
 * @author Gustav Wahl and Gustav Wehn
 */
public class Destroyer extends GameObject{

    private boolean alive = true;
    private int hp;
    private int decideActiveState;
    private double posX;
    private double posY;
    private int walk;

    /**
     * Constructs and initializes the a Destroyer object
     * 
     * @param hp hp of the destroyer
     * @param alive alive state of the destroyer
     * @param view position of the destroyer
     * @param posX the X coordinate of the destroyer
     * @param posY the Y coordinate of the destroyer
     */
    public Destroyer(int hp,boolean alive, Node view, double posX, double posY){
        super(view);
        this.hp = hp;
        this.alive = alive;
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Decides the state of the destroyer, what the destroyer does.
     * It runs a method that constatly runs. Gets called from animation timer.
     * 
     * @param object the player in the game
     * @param object2 the destroyer
     */
    public void FSM (GameObject object , Destroyer object2) {


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
     * Sets the state of the destroyer
     * 
     * @param state state
     */
    public void setState(int state) {
        decideActiveState = state;
    }

    /**
     * Localizes the player relative to the destroyer
     * 
     * @param object the player in the game
     * @param object2 the destroyer
     */
    public void StateShoot(GameObject object, Destroyer object2) {

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



        //      object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));



        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }
    
    /**
     * Localizes and calculates distance to the player
     * 
     * @param object the player in the game
     * @param object2 the destroyer
     */
    public void StateWalk(GameObject object, Destroyer object2) {


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
     * Gets the active state of the destroyer
     * 
     * @return decideActiveState
     */
    public int getDecideActiveState() {
        return decideActiveState;
    }

    /**
     * Sets the hp of the destroyer
     * 
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    /**
     * Gets the hp of the destroyer
     * 
     * @return hp
     */
    public int getHp() {
        return hp;
    }
    
    /**
     * Gets the opisite of the alive state of the destroyer
     * 
     * @return !alive
     */
    public boolean isDead(){
        return !alive;
    }
    
    /**
     * Sets the alive state of the destroyer
     * 
     * @param alive alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * Sets the X and Y coordinates of the destroyer
     * 
     * @param posX X coordinate
     * @param posY Y coordinate
     */
    public void setPosXY(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Gets the X coordinate of the destroyer 
     * 
     * @return X coordinate
     */
    public double getPosX(){
        return posX;
    }
    
    /**
     * Gets the Y coordinate of the destroyer
     * 
     * @return Y coordinate
     */
    public double getPosY(){
        return posY;
    }
}



