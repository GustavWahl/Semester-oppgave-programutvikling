import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class save {
    
    public void saveSpiller(double hp, int score, double posX, double posY){
        JSONObject jsonSpiller = new JSONObject();
        
        try {
            jsonSpiller.put("Hp", hp);
            jsonSpiller.put("Score", score);
            jsonSpiller.put("posX", posX);
            jsonSpiller.put("posY", posY);
        } catch (JSONException ex) {
            Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try(FileWriter file = new FileWriter("Spiller.json")){
            file.append(jsonSpiller.toString());
            file.flush();
        }catch(IOException e){
            System.out.println("SHIT!");
        }
    }
}
