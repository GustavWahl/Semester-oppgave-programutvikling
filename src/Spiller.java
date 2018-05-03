import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class Spiller extends GameObject{

    private double hp;
    private int damage;
    private int score;
    private String name;
    private int minY;




    public Spiller(double hp, int damage, int score, String name, Node view){
        super(view);
        this.hp = hp;
        this.damage = damage;
        this.score = score;
        this.name = name;
    }

    public void setHp(double hp){
        this.hp = hp;
    }
    public double getHp() {
        return hp;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setScore() {
        score++;
    }

    public int getScore(){
        return score;
    }


}
