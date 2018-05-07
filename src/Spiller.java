import javafx.scene.Node;


public class Spiller extends GameObject{

    private double hp;
    private int damage;
    private int score;
    private String name;




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

    public int getDamage() {
        return damage;
    }

    public void setScore() {
        score++;
    }

    public int getScore(){
        return score;
    }
}
