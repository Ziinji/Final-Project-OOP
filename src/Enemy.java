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
        if ((super.getX() - target.getX() < 75) && (super.getX() - target.getX() > -75)) {
            super.stand();
            if(super.getX() < target.getX()){
                super.pos = EFacing.FACING_RIGHT;
            } else {
                super.pos = EFacing.FACING_LEFT;
            }
        } else if (super.getX() < target.getX()){
            super.pos = EFacing.FACING_RIGHT;
            super.moveRight(2);
        } else if (super.getX() > target.getX()){
            super.pos = EFacing.FACING_LEFT;
            super.moveLeft(2);
        } else {
            super.stand();
        }
    }

    public void attack(){
        //if(enemy.x - player.x < 50 || player.x = enemy.x < 50){
            //Attack
            //OnHit
    }
}
