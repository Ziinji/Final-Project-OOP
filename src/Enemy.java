import java.util.Random;

public class Enemy extends Character {

    public Enemy(){
        super.loadImage("images/Sprites/Enemy/Sprite-0001.png");
        Random rn = new Random();
        int spawn = rn.nextInt(550) + 1;
        super.setX(spawn);
        super.setY(325);
    }

    public void spawn(){

    }

    public void chase(Player target){
        if (super.getX() < target.getX()){
            super.moveRight(2);
        } else if (super.getX() > target.getX()){
            super.moveLeft(2);
        } else {
            super.stand();
        }
    }

    public void attack(){

    }

    public void kill(){

    }
}
