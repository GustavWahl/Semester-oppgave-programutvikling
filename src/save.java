import java.util.Formatter;


public class save {
    
    private Formatter x;
    
    public void apneFil(){
        try{
            x = new Formatter("data.txt");
        }catch(Exception e){
            System.out.println("funka ikke");
        }
    }
    
    public void lagreData(double hp, int score, double posX, double posY){
        x.format("%s %s %s %s", hp, score, posX, posY);
    }
    
    public void lukkFil(){
        x.close();
    }
}
