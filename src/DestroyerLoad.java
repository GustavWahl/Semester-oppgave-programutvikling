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
 * retrives attributes for Destroyer objects from JSON-file
 * 
 * @author Gustav Wehn
 */
public class DestroyerLoad {
    
    private final Image DESTROYERIMG = new Image("bilder/misilboss.png");
    
    /**
     * loads all the attributes for Destroyer objects from JSON-file and makes Destroyer objects of them
     * 
     * @return a list of Destroyer objects with attributes loaded from the JSON-file
     */
    public List<Destroyer> loaderDestroyer(){
        
        JSONParser parser = new JSONParser();
        List<Destroyer> mellom = new ArrayList<>();
        
        try {
            Object obj = parser.parse(new FileReader("Destroyer.json"));
            JSONObject jsonO = (JSONObject) obj;
            if(jsonO.get("Ant") != null){
                int antF = (int) (long) jsonO.get("Ant");
                for(int i = 0; i < antF; i++){
                    JSONObject jsonOi = (JSONObject) jsonO.get(""+i);
                    mellom.add(new Destroyer((int)(long)jsonOi.get("Hp"), true, destroyer(new ImageView(DESTROYERIMG)), 
                            (double)(float)(long)jsonOi.get("PosX"), (double)(float)(long)jsonOi.get("PosY")));
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BossLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(BossLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mellom;
    }
    
    /**
     * sets what part of the image is shown
     * 
     * @param b the image
     * @return the part of the picture that is shown, as a Node
     */
    public Node destroyer(ImageView b){
        b.setViewport(new Rectangle2D(0, 0, 40, 40));
        return b;
    }
}
