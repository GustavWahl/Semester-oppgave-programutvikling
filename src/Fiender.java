import javafx.geometry.Point2D;
import javafx.scene.Node;


public class Fiender extends GameObject{


    private boolean alive = true;
    private int hp;
    private int decideActiveState;
    private double posX;
    private double posY;


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

                double vektorX = ((object.getX() - object2.getX()) * 0.01);
                double vektorY =  ((object.getY() - object2.getY()) * 0.01);
                double pVektorX =  (object.getVelocity().getX());
                double pVektorY =  (object.getVelocity().getY());


                object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));
           //     double rotering = Math.atan2(object.getX()-object2.getX(),object.getY()-object2.getY());
             //   object2.rotate(rotering);


                int rotate =  (int)Math.acos(((vektorX * pVektorX) + (vektorY * pVektorY))/ Math.sqrt((Math.pow(vektorX,2))+(Math.pow(vektorY,2)))*
                Math.sqrt((Math.pow(pVektorX,2))+(Math.pow(pVektorY,2))));


            object2.setRotateVar(rotate);
            object2.rotateRight();
        //System.out.println(rotate);


        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }

    public void StateWalk(GameObject object, Fiender object2) {
        double vektorX;
        double vektorY;
        double pVektorX;
        double pVektorY;

        if (Math.abs(object2.getVelocity().normalize().getX()) >= 1) {
            vektorX = ((object2.getVelocity().normalize().getX()));
        }else{
            vektorX = 1;
        }

        if (Math.abs(object2.getVelocity().normalize().getY()) >= 1) {
            vektorY = ((object2.getVelocity().normalize().getY()));
        }else{
            vektorY = 1;
        }

        if (Math.abs(object.getVelocity().normalize().getX()) >= 1) {
            pVektorX = (object.getVelocity().normalize().getX());
        }else{
            pVektorX = 1;
        }
        if (Math.abs(object.getVelocity().normalize().getY()) >= 1) {
            pVektorY = (object.getVelocity().normalize().getY());
        }else{
            pVektorY = 1;
        }





        object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));

        double rotering = Math.atan2(pVektorX-vektorX,pVektorY-vektorY);
         object2.rotate(rotering);

               // int rotate =  (int)Math.acos(((vektorX * pVektorX) + (vektorY * pVektorY))/ Math.sqrt((Math.pow(vektorX,2))+(Math.pow(vektorY,2)))*
                //Math.sqrt((Math.pow(pVektorX,2))+(Math.pow(pVektorY,2))));


          /*  object2.setRotateVar(rotate);
            object2.rotateRight();
        System.out.println(rotate);
*/

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
        this.posX = Math.round(posX);
        this.posY = Math.round(posY);
    }
    
    public double getPosX(){
        return posX;
    }
    
    public double getPosY(){
        return posY;
    }
}
