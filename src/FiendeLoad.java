
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


public class FiendeLoad{
    
    private final Image FIENDERIMAGE = new Image("bilder/fiender.png");
    
    public List<Fiender> loaderFiende(){
        
        JSONParser parser = new JSONParser();
        List<Fiender> mellom = new ArrayList<>();
        
        try {
            Object obj = parser.parse(new FileReader("Fiender.json"));
            JSONObject jsonO = (JSONObject) obj;
            if(jsonO.get("Ant") != null){
                int antF = (int) (long) jsonO.get("Ant");
                //System.out.println(antF);
                for(int i = 0; i < antF; i++){
                    JSONObject jsonOi = (JSONObject) jsonO.get(""+i);
                    mellom.add(new Fiender((int)(long)jsonOi.get("Hp"), true, fiender((new ImageView(FIENDERIMAGE)),40), 
                            Double.valueOf((long)jsonOi.get("PosX")),  Double.valueOf((long)jsonOi.get("PosX"))));
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mellom;
    }
    
    public Node fiender(ImageView b, int minY){
        b.setViewport(new Rectangle2D(0, minY, 20, 20));
        // final Animation animation = new Sprite(b,Duration.millis(3000),4,4,0,-16,60,60);
        return b;
    }
}
