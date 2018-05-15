import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * retrives attributes for Spiller from JSON-file
 * 
 * @author Gustav Wehn
 */
public class SpillerLoad {
    
    private double hp;
    private int score;
    private double posX;
    private double posY;
    private boolean powerUp;
    
    /**
     * loads attributes from the JSON-file into this object
     */
    public void loadSpiller(){
        
        JSONParser sParse = new JSONParser();
        
        try {
            Object sObj = sParse.parse(new FileReader("Spiller.json"));
            JSONObject sJson = (JSONObject) sObj;
            hp = (double) (float) (long) sJson.get("Hp");
            score = (int) (long) sJson.get("Score");
            posX = (double) (float) (long) sJson.get("posX");
            posY = (double) (float) (long) sJson.get("posY");
            powerUp = (boolean) sJson.get("PUp");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean testLoad(){
        
        JSONParser sParse = new JSONParser();
        
        try {
            Object sObj = sParse.parse(new FileReader("Spiller.json"));
            JSONObject sJson = (JSONObject) sObj;
            hp = (double) (float) (long) sJson.get("Hp");
            score = (int) (long) sJson.get("Score");
            posX = (double) (float) (long) sJson.get("posX");
            posY = (double) (float) (long) sJson.get("posY");
            powerUp = (boolean) sJson.get("PUp");
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException | ParseException ex) {
            return false;
        }
    }
    
    /**
     * 
     * @return hp loaded from the JSON-file
     */
    public double getHp(){
        return hp;
    }
    
    /**
     * 
     * @return score loaded from the JSON-file
     */
    public int getScore(){
        return score;
    }
    
    /**
     * 
     * @return X coordinate loaded from the JSON-file
     */
    public double getPosX(){
        return posX;
    }
    
    /**
     * 
     * @return Y coordinate loaded from the JSON-file
     */
    public double getPosY(){
        return posY;
    }
    
    /**
     * 
     * @return powerup boolean loaded from the JSON-file
     */
    public boolean getPowerup(){
        return powerUp;
    }
}
