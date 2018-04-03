import javafx.geometry.Point2D;
import javafx.scene.Node;
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
    private Node face;





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

    public void setScore(int score) {
        this.score = score;
    }






}
