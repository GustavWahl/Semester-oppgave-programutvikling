import java.io.Serializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * GameObject is the superclass of all the objects in the game
 * 
 * @author Gustav Wahl
 */
public class GameObject{

    private Node view;
    private Point2D velocity = new Point2D(0,0);
    private int rotate;
    private boolean alive = true;

    /**
     * Constructs and inializes a GameObject
     * @param view 
     */
    public GameObject(Node view){
        this.view = view;
    }
    
    /**
     * Sets the velocity of a GameObject
     * 
     * @param velocity velocity
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    
    /**
     * Gets the position of the GameObject
     * 
     * @return Node
     */
    public Node getView() {
        return view;
    }

    /**
     * 
     * @return 
     */
    public double getRotate(){
        return view.getRotate();
    }

    /**
     * Gets the velocity of the GameObject
     * 
     * @return 
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * check if the GameObject is dead
     *
     * @return
     */

    public  boolean isDead(){
        return !alive;
    }

    /**
     * Sets the alive state of a GameObject
     *
     * @param alive alive
     */

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the x position of the GameObject
     *
     * @return
     */

    public double getX(){

        return  view.getTranslateX();
    }

    /**
     * Gets the y position of the GameObject
     *
     * @return
     */
    public double getY(){

        return  view.getTranslateY();
    }

    /**
     * updates to the current position of a GameObject
     *
     *
     */

    public void update(){
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    /**
     * Sets the rotation value of a GameObject
     *
     * @param rotate rotate
     */

    public void setRotateVar(int rotate){
        this.rotate = rotate;
    }

    /**
     * rotates the GameObject to the angle the rotate value has
     *
     *
     */

    public void rotateRight(){
        view.setRotate(view.getRotate() + rotate);

    }


    /**
     * checks if the GameObject collides with another GameObject
     *
     * @return
     */
    public Boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());

    }

}