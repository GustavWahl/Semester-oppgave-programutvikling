import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.lang.reflect.Method;

public class Fiender extends GameObject{


    private boolean alive = true;
    private int hp;
    private int damage;
    private Void activeState;


    public Fiender(int hp, int damage,boolean alive, Node view){
        super(view);
        this.hp = hp;
        this.damage = damage;
        this.alive = alive;

    }

   /* public void FSM(){
    }

    public void SetState(Void state){
        activeState = state;
    }

    public void Shoot(){

    }

    public void Walk(){

    }

    public void UpdateState(){
        if(activeState != null){
            activeState();
        }

    }*/



    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public boolean isAlive(){
        return  alive;
    }

    public boolean isDead(){
        return !alive;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }




}
