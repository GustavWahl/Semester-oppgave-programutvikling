import javafx.geometry.Point2D;
import javafx.scene.Node;






    public class Boss extends GameObject{


        private boolean alive = true;
        private int hp;
        private int damage;
        private int decideActiveState;


        public Boss(int hp, int damage,boolean alive, Node view){
            super(view);
            this.hp = hp;
            this.damage = damage;
            this.alive = alive;
        }

        public void FSM (GameObject object , Boss object2) {


            switch (decideActiveState) {

                case 0:
                    StateShoot(object,object2);

                    break;

                case 1:
                    StateWalk(object,object2);

                    break;

                case 2:
                    StandStill(object,object2);
                    break;

                default:
                    StandStill(object,object2);

                    break;
            }
        }


        public void setState(int state) {
            decideActiveState = state;
        }


        public void StateShoot(GameObject object, Boss object2) {

            object2.setVelocity(new Point2D((((object.getX() - object2.getX()) * 0.001)), (((object.getY() - object2.getY()) * 0.001))));



            if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 30 && Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 30 ) {

                setState(1);
            }
        }

        public void StandStill(GameObject object, Boss object2) {

            object2.setVelocity(new Point2D(0, 0));



            if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 150 && Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 150 ) {
                setState(1);
            }
        }

        public void StateWalk(GameObject object, Boss object2) {



            object2.setVelocity(new Point2D(((object.getX() - object2.getX()) * 0.001), ((object.getY() - object2.getY()) * 0.001)));


            if (Math.pow((object.getX() - object2.getX()),2) *0.001 <= 50 && Math.pow((object.getY() - object2.getY()),2)* 0.001 <= 50 ) {
                setState(0);
            }
           else if (Math.pow((object.getX() - object2.getX()),2) *0.001 >= 350 && Math.pow((object.getY() - object2.getY()),2)* 0.001 >= 350 ) {
                setState(2);
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




