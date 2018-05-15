import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Sprite creates the animations of sprites in the game
 * 
 * @author Gustav Wahl
 */
public class Sprite extends Transition{
    
    private final ImageView imgView;
    private final int c;
    private final int offX;
    private final int offY;
    private final int width;
    private final int height;
    private int nextIndex;
    
    /**
     * Constructs and initalizes a sprite object
     * @param imgView
     * @param duration
     * @param c
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Sprite(ImageView imgView,Duration duration, int c, int x,int y,int width,int height ){
        this.imgView = imgView;
        this.c = c;
        this.offX = x;
        this.offY = y;
        this.width = width;
        this.height = height;


        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    // ikke fult fungerende enda med sprites som er mer en to kolonner....
    @Override
    protected void interpolate(double frac) {
        int index =  Math.min((int) Math.floor(frac * c ), c- 1);

        if(index != nextIndex){
            int y = (index % c ) * height;

            imgView.setViewport(new Rectangle2D(offX,y,width,height));
            nextIndex = index;
        }
    }
}

