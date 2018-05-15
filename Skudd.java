import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Skudd represents the bullets fiered by the player in the game
 * 
 * @author Gustav
 */
class Skudd extends GameObject {

    /**
     * Creates new bullet
     * 
     * @param x the horizontal position of the center of the circle in pixels
     * @param y the vertical position of the center of the circle in pixels 
     * @param r radius of the bullet in pixels
     * @param c color of the bullet
     */
    Skudd(double x, double y, double r, Color c) {
        super(new Circle(x, y, r, c));
    }

}
