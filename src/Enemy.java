import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.util.*;

public class Enemy extends Character {

    private boolean can_attack = false;
    private Timer timerEnemyAttack = new Timer();

    public Enemy(){
        loadImg();
        spawn();
        super.setY(325);
        timerEnemyAttack.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Enemy.this.toggle_attack();
            }
        }, 0, 3000);
        timerEnemyAttack.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Enemy.this.done_attack();
            }
        }, 300, 3000);
    }

    public void loadImg() {
        if (state == EntityState.STANDING) {
            super.loadImage("images/Sprites/Enemy/Enemy_Walk.gif");
        } else if (state == EntityState.ATTACKING) {
            super.loadImage("images/Sprites/Enemy/Enemy_Attack.gif");
        } else if (state == EntityState.MOVING){
            super.loadImage("images/Sprites/Enemy/Enemy_Walk.gif");
        } else if (state == EntityState.ATTACKED){
            super.loadImage("images/Sprites/Enemy/Sprite-0001.png");
        }else{
            super.loadImage("images/Sprites/Enemy/Sprite-0001.png");
        }
    }

    public void toggle_attack(){
        if(state != EntityState.ATTACKED){
            super.state = EntityState.ATTACKING;
            this.can_attack = true;
        }
    }

    public void done_attack(){
        if (state != EntityState.ATTACKED){
            super.state = EntityState.STANDING;
        }
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
        if ((Math.abs(super.getX() - target.getX()) < 60) && (this.can_attack) && (state != EntityState.ATTACKED)) {
            super.stand();
            super.attack(target, 20);
            this.can_attack = false;

            if(super.getX() < target.getX()){
                super.pos = EFacing.FACING_RIGHT;
            } else {
                super.pos = EFacing.FACING_LEFT;
            }
        } else if (super.getX() < target.getX()-50){
            super.pos = EFacing.FACING_RIGHT;
            super.moveRight(2);
        } else if (super.getX() > target.getX()+50){
            super.pos = EFacing.FACING_LEFT;
            super.moveLeft(2);
        } else {
            super.stand();
        }
    }

}
