import java.io.File;
import java.util.Scanner;

public class load {
    
    private Scanner x;
    private double hp;
    private int score;
    private double posX;
    private double posY;
    
    public void apneFil(){
        try{
            x = new Scanner(new File("data.txt"));
        }catch(Exception e){
            System.out.println("finner ikke filen");
        }
    }
    
    public void lesfil(){
        while(x.hasNext()){
            hp = Double.parseDouble(x.next());
            score = Integer.parseInt(x.next());
            posX = Double.parseDouble(x.next());
            posY = Double.parseDouble(x.next());
            
            System.out.printf("%s %s %s %s", score, hp, posX, posY);
        }
    }
    
    public double getHp(){
        return hp;
    }
    
    public int getScore(){
        return score;
    }
    
    public double getX(){
        return posX;
    }
    
    public double getY(){
        return posY;
    }
    
    public void lukkFil(){
        x.close();
    }
}
