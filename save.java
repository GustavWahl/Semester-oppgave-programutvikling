import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * save saves the needed attributes from a Spiller, Fiender, Boss or Destroyer object onto a JSON-file.
 * This data can later be retrived from SpillerLoad, FeindeLoad, BossLoad or DestroyerLoad, depending on what kind of 
 * object that was originaly saved.
 * 
 * @author Gustav Wehn
 */
public class save {
    
    /**
     * saves the attributes from a Spiller object to a JSON-file
     * @param hp the Spiller object's hp value
     * @param score the Spiller object's score value
     * @param posX the Spiller object's X coordinate
     * @param posY the Spiller object's Y coordinate
     * @param powerUp the Spiller object's powerup value
     */
    public void saveSpiller(double hp, int score, double posX, double posY, boolean powerUp){
        JSONObject jsonSpiller = new JSONObject();
        
        try {
            jsonSpiller.put("Hp", hp);
            jsonSpiller.put("Score", score);
            jsonSpiller.put("posX", Math.round(posX));
            jsonSpiller.put("posY", Math.round(posY));
            jsonSpiller.put("PUp", powerUp);
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
    
    /**
     * saves the attributes from all Fiender objects in a list to a JSON-file
     * @param fiender the linked list with all the Fiender objects
     */
    public void saveFiende(List<Fiender> fiender){
        
        JSONObject[] arrayFiender = new JSONObject[fiender.size()];
        
        JSONObject jsonFiender = new JSONObject();
        
        for(int i = 0; i < fiender.size(); i++){
            try {
                arrayFiender[i] = new JSONObject();
                
                arrayFiender[i].put("Hp", fiender.get(i).getHp());
                arrayFiender[i].put("PosX", Math.round(fiender.get(i).getPosX()));
                arrayFiender[i].put("PosY", Math.round(fiender.get(i).getPosY()));
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
    
    /**
     * saves the attributes from all Boss objects in a list to a JSON-file
     * @param boss the linked list with all the Boss objects
     */
    public void saveBoss(List<Boss> boss){
        
        JSONObject[] arrayBoss = new JSONObject[boss.size()];
        
        JSONObject jsonBoss = new JSONObject();
        
        for(int i = 0; i < boss.size(); i++){
            try {
                arrayBoss[i] = new JSONObject();
                
                arrayBoss[i].put("Hp", boss.get(i).getHp());
                arrayBoss[i].put("PosX", Math.round(boss.get(i).getPosX()));
                arrayBoss[i].put("PosY", Math.round(boss.get(i).getPosY()));
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
    
    /**
     * saves the attributes from all Destroyer objects in a list to a JSON-file
     * @param dest the linked list with all the Destroyer objects
     */
    public void saveDestroyer(List<Destroyer> dest){
        JSONObject[] arrayDest = new JSONObject[dest.size()];
        
        JSONObject jsonDest = new JSONObject();
        
        for(int i = 0; i < dest.size(); i++){
            try {
                arrayDest[i] = new JSONObject();
                
                arrayDest[i].put("Hp", dest.get(i).getHp());
                arrayDest[i].put("PosX", Math.round(dest.get(i).getPosX()));
                arrayDest[i].put("PosY", Math.round(dest.get(i).getPosY()));
                jsonDest.put(Integer.toString(i), arrayDest[i]);
                if(i == dest.size()-1){
                    jsonDest.put("Ant", i+1);
                }
            } catch (JSONException ex) {
                Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try(FileWriter file = new FileWriter("Destroyer.json")){
            file.append(jsonDest.toString());
            file.flush();
        }catch(IOException e){
            System.out.println("SHIT!");
        }
    }
}
