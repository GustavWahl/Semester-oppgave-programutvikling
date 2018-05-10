import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
    
    public void saveFiende(List<Fiender> fiender){
        
        JSONObject[] arrayFiender = new JSONObject[fiender.size()];
        
        JSONObject jsonFiender = new JSONObject();
        
        for(int i = 0; i < fiender.size(); i++){
            try {
                arrayFiender[i] = new JSONObject();
                
                arrayFiender[i].put("Hp", fiender.get(i).getHp());
                arrayFiender[i].put("PosX", fiender.get(i).getPosX());
                arrayFiender[i].put("PosY", fiender.get(i).getPosY());
                jsonFiender.put(Integer.toString(i), arrayFiender[i]);
                if(i == fiender.size()-1){
                    jsonFiender.put("Ant", i+1);
                }
            } catch (JSONException ex) {
                Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try(FileWriter file = new FileWriter("Fiender.json")){
            file.append(jsonFiender.toString());
            file.flush();
        }catch(IOException e){
            System.out.println("SHIT!");
        }
    }
    
    public void saveBoss(List<Boss> boss){
        
        JSONObject[] arrayBoss = new JSONObject[boss.size()];
        
        JSONObject jsonBoss = new JSONObject();
        
        for(int i = 0; i < boss.size(); i++){
            try {
                arrayBoss[i] = new JSONObject();
                
                arrayBoss[i].put("Hp", boss.get(i).getHp());
                arrayBoss[i].put("PosX", boss.get(i).getPosX());
                arrayBoss[i].put("PosY", boss.get(i).getPosY());
                jsonBoss.put(Integer.toString(i), arrayBoss[i]);
                if(i == boss.size()-1){
                    jsonBoss.put("Ant", i+1);
                }
            } catch (JSONException ex) {
                Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try(FileWriter file = new FileWriter("Boss.json")){
            file.append(jsonBoss.toString());
            file.flush();
        }catch(IOException e){
            System.out.println("SHIT!");
        }
    }
}
