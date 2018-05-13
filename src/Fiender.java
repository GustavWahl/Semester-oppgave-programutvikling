import javafx.geometry.Point2D;
import javafx.scene.Node;


public class Fiender extends GameObject{


    private boolean alive = true;
    private int hp;
    private int decideActiveState;
    private double posX;
    private double posY;
    private int walk;


    public Fiender(int hp,boolean alive, Node view, double posX, double posY){
        super(view);
        this.hp = hp;
        this.alive = alive;
        this.posX = posX;
        this.posY = posY;
    }

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


    public void setState(int state) {
        decideActiveState = state;
    }


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



  //      object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));



        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }

    public void StateWalk(GameObject object, Fiender object2) {
        double vektorX;
        double vektorY;
        double pVektorX;
        double pVektorY;
        //vektorX = ((object2.getVelocity().normalize().getX()));

        //double rotering = Math.atan2(pVektorX-vektorX,pVektorY-vektorY);
        //   object2.rotate(rotering);

        // int rotate =  (int)Math.acos(((vektorX * pVektorX) + (vektorY * pVektorY))/ Math.sqrt((Math.pow(vektorX,2))+(Math.pow(vektorY,2)))*
        //Math.sqrt((Math.pow(pVektorX,2))+(Math.pow(pVektorY,2))));


          /*  object2.setRotateVar(rotate);
            object2.rotateRight();
        System.out.println(rotate);
*/

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




        /*else{
            object2.setVelocity(new Point2D(-1,0));

            if(object2.getY() < object.getY()){

                object2.setVelocity(new Point2D(0,1));
            }  else{
                object2.setVelocity(new Point2D(0,-1));
            }

            if (object2.getX() < object.getX()) {
                object2.setVelocity(new Point2D(1, 0));
            }
        }
*/
       // object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));


        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 5 || Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 5 ) {
            setState(0);
        }
    }

    public int getDecideActiveState() {
        return decideActiveState;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public boolean isDead(){
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public void setPosXY(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    public double getPosX(){
        return posX;
    }
    
    public double getPosY(){
        return posY;
    }
}
