import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FinedeSkudd represents the bullets fiered by Fiender and Boss objects
 * 
 * @author Gustav Wahl
 */
class FiendeSkudd extends GameObject {
    
    /**
     * Creates new bullet
     * 
     * @param x the horizontal position of the center of the circle in pixels
     * @param y the vertical position of the center of the circle in pixels 
     * @param r radius of the bullet in pixels
     * @param c color of the bullet
     */
    FiendeSkudd(double x, double y, double r, Color c) {
        super(new Circle(x, y, r, c));
    }
}
