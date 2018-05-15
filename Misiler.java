import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Represents the shots fiered by the Destroyer objects
 * 
 * @author Gustav Wahl
 */
public class Misiler extends GameObject{
    private int decideActiveState;
    private int pxBeforeExplosion;

    /**
     * 
     * @param view 
     */
    public Misiler( Node view){
        super(view);
    }


    public void FSM (GameObject object , Misiler object2) {


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

    public void setPxBeforeExplosion() {
        this.pxBeforeExplosion++;
    }

    public void StateShoot(GameObject object, Misiler object2) {


      object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));

        setPxBeforeExplosion();

        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);
        }
    }

    public void StateWalk(GameObject object, Misiler object2) {

        object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));

        setPxBeforeExplosion();
        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 5 || Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 5 ) {
            setState(0);
        }
    }


    public int getDecideActiveState() {
        return decideActiveState;
    }

    public int getPxBeforeExplosion() {
        return pxBeforeExplosion;
    }
}




