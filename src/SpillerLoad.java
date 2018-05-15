import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SpillerLoad {
    
    private double hp;
    private int score;
    private double posX;
    private double posY;
    private boolean powerUp;
    
    public void loadSpiller(){
        
        JSONParser sParse = new JSONParser();
        
        try {
            Object sObj = sParse.parse(new FileReader("Spiller.json"));
            JSONObject sJson = (JSONObject) sObj;
            hp = (double) (float) (long) sJson.get("Hp");
            score = (int) (long) sJson.get("Score");
            posX = Double.valueOf((long) sJson.get("posX"));
            posY =  Double.valueOf((long) sJson.get("posY"));
            powerUp = (boolean) sJson.get("PUp");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkLoad(){
        try {
            JSONParser testParse = new JSONParser();
            Object testObj = testParse.parse(new FileReader("Spiller.json"));
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException | ParseException ex) {
            return false;
        } 
    }
    
    public double getHp(){
        return hp;
    }
    
    public int getScore(){
        return score;
    }
    
    public double getPosX(){
        return posX;
    }
    
    public double getPosY(){
        return posY;
    }
    
    public boolean getPowerup(){
        return powerUp;
    }
}
