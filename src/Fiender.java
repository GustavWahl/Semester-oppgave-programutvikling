import javafx.geometry.Point2D;
import javafx.scene.Node;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public class Fiender extends GameObject{


    private boolean alive = true;
    private int hp;
    private int damage;
    private int decideActiveState;


    public Fiender(int hp, int damage,boolean alive, Node view){
        super(view);
        this.hp = hp;
        this.damage = damage;
        this.alive = alive;



    }

    public void FSM (GameObject object , Fiender object2) {


        switch (decideActiveState) {

            case 0:
                StateShoot(object,object2);

                break;

            case 1:
                StateWalk(object,object2);

                break;

            default:
                StateWalk(object,object2);

                break;
        }



    }





    public void setState(int state) {
        decideActiveState = state;
    }


    public void StateShoot(GameObject object, Fiender object2) {

            object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.01)), (((object.getY() - object2.getY()) * 0.01))));



        if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 || Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

            setState(1);

        }
    }

    public void StateWalk(GameObject object, Fiender object2) {



            object2.setVelocity(new Point2D(((object.getX() - object2.getX()) * 0.01), ((object.getY() - object2.getY()) * 0.01)));


        if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 5 || Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 5 ) {
            setState(0);

        }

    }





    public int getDecideActiveState() {
        return decideActiveState;
    }

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
