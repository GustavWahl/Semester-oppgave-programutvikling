
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

public class BossLoad {
    
    private final Image BOSSIMAGE = new Image("bilder/bosstank.png");
    
    public List<Boss> loaderBoss(){
        
        JSONParser parser = new JSONParser();
        List<Boss> mellom = new ArrayList<>();
        
        try {
            Object obj = parser.parse(new FileReader("Boss.json"));
            JSONObject jsonO = (JSONObject) obj;
            if(jsonO.get("Ant") != null){
                int antF = (int) (long) jsonO.get("Ant");
                //System.out.println(antF);
                for(int i = 0; i < antF; i++){
                    JSONObject jsonOi = (JSONObject) jsonO.get(""+i);
                    mellom.add(new Boss((int)(long)jsonOi.get("Hp"), 10, true, boss((new ImageView(BOSSIMAGE)),128), (double)jsonOi.get("PosX"), (double)jsonOi.get("PosY")));
                    //System.out.println(jsonOi);
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BossLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(BossLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mellom;
    }
    
    public Node boss(ImageView b, int minY){
        b.setViewport(new Rectangle2D(0, minY, 60, 60));
       // final Animation animation = new Sprite(b,Duration.millis(3000),4,4,0,-16,60,60);
        return b;
    }
}
