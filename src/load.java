import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class load {
    
    private double hp;
    private int score;
    private double posX;
    private double posY;
    
    public void loadSpiller(){
        
        JSONParser sParse = new JSONParser();
        
        try {
            Object sObj = sParse.parse(new FileReader("Spiller.json"));
            JSONObject sJson = (JSONObject) sObj;
            hp = (double) (float) (long) sJson.get("Hp");
            score = (int) (long) sJson.get("Score");
            posX = (double) sJson.get("posX");
            posY = (double) sJson.get("posY");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
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
}
