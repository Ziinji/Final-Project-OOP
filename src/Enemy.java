import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.*;

public class Enemy extends Character {

    private boolean attacked = true;
    private boolean can_attack = false;

    public Enemy(){
        super.loadImage("images/Sprites/Enemy/Sprite-0001.png");
        spawn();
        super.setY(325);
        Timer timerEnemy = new Timer();
        timerEnemy.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Enemy.this.change_attack_state();
            }
        }, 0, 3000);
    }

    public void change_attack_state(){
        this.can_attack = true;
    }

    public void spawn(){
        super.setHealth(100);
        Random rn = new Random();
        int spawn = rn.nextInt(550) + 1;
        super.setX(spawn);
    }

    public void MaxHealthBar(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(this.getX()+120, this.getY()+10, 100, 10);
    }

    public void HealthBar(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GREEN);
        g2d.fillRect(this.getX()+120, this.getY()+10, super.getHealth(), 10);
    }

    public void chase(Player target){
        if ((Math.abs(super.getX() - target.getX()) < 30) && this.can_attack) {
            super.stand();
            super.attack(target, 20);
            this.can_attack = false;

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
}
