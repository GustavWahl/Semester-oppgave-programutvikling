import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * retrives attributes for Fiender objects from JSON-file
 * 
 * @author Gustav Wehn
 */
public class FiendeLoad{
    
    private final Image FIENDERIMAGE = new Image("bilder/fiender.png");
    
    /**
     * loads all the attributes for Fiender objects from JSON-file and makes Fiender objects of them
     * 
     * @return a list of Fiende objects with attributes loaded from the JSON-file
     */
    public List<Fiender> loaderFiende(){
        
        JSONParser parser = new JSONParser();
        List<Fiender> mellom = new ArrayList<>();
        
        try {
            Object obj = parser.parse(new FileReader("Fiender.json"));
            JSONObject jsonO = (JSONObject) obj;
            if(jsonO.get("Ant") != null){
                int antF = (int) (long) jsonO.get("Ant");
                for(int i = 0; i < antF; i++){
                    JSONObject jsonOi = (JSONObject) jsonO.get(""+i);
                    mellom.add(new Fiender((int)(long)jsonOi.get("Hp"), true, fiender((new ImageView(FIENDERIMAGE)),0), 
                            (double)(float)(long)jsonOi.get("PosX"), (double)(float)(long)jsonOi.get("PosY")));
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mellom;
    }
    
    /**
     * sets what part of the image is shown
     * 
     * @param b the image
     * @param minY where in the pictures height it starts to show
     * @return the part of the picture that is shown, as a Node
     */
    public Node fiender(ImageView b, int minY){
        b.setViewport(new Rectangle2D(0, minY, 20, 20));
        // final Animation animation = new Sprite(b,Duration.millis(3000),4,4,0,-16,60,60);
        return b;
    }
}
