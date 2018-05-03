import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class GameObject {

    private Node view;
    private Point2D velocity = new Point2D(0,0);



    private boolean alive = true;


    public GameObject(Node view){
        this.view = view;
    }


    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    // Node gjør sånn at vi kan se objectet
    public Node getView() {
        return view;
    }

    public double getRotate(){
        return view.getRotate();
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public  boolean isDead(){
        return !alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getX(){

        return  view.getTranslateX();
    }

    public double getY(){

        return  view.getTranslateY();
    }


    public void update(){
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void rotateRight(){
        view.setRotate(view.getRotate() + 45);
        //  setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public void rotateLeft() {
        view.setRotate(view.getRotate() - 45);
        //setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public Boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());

    }

}