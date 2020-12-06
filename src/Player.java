import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

enum Combo {
    NONE, Z, ZZ, ZZZ, X, XX, ZZX
}

public class Player extends Character implements AttackState{

    public boolean attacking = true;
    Combo combo_state = Combo.NONE;
    private Timer timerPlayer = new Timer();

    public Combo getCombo_state() {
        return combo_state;
    }

    public Player(){
        loadImg();
        super.setHealth(10);
        super.setX(100);
        super.setY(325);
    }

    public void loadImg() {

        switch (state){
            case STANDING:
                super.loadImage("images/Sprites/Player/idle.gif");
                break;
            case ATTACKING:
                switch (combo_state) {
                    case Z:
                        super.loadImage("images/Sprites/Player/atkZ1.gif");
                        break;
                    case X:
                        super.loadImage("images/Sprites/Player/atkX1.png");
                        break;
                    case ZZ:
                        super.loadImage("images/Sprites/Player/atkZ2.gif");
                        break;
                    case ZZZ:
                        super.loadImage("images/Sprites/Player/atkZ3.png");
                        break;
                    case XX:
                    case ZZX:
                        super.loadImage("images/Sprites/Player/atkX2.png");
                        break;
                }
                break;
            case MOVING:
                super.loadImage("images/Sprites/Player/run.gif");
                break;
            case ATTACKED:
                super.loadImage("images/Sprites/Player/HitStun.png");
                break;
            case START:
                super.loadImage("images/Sprites/Player/standing.gif");
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.state = EntityState.MOVING;
            super.pos = EFacing.FACING_LEFT;
            super.moveLeft(4);
            combo_state = Combo.NONE;
        }

        if (key == KeyEvent.VK_RIGHT) {
            this.state = EntityState.MOVING;
            super.pos = EFacing.FACING_RIGHT;
            super.moveRight(4);
            combo_state = Combo.NONE;
        }
    }

    public void holdBeforeRelease(Combo now) {
        attacking = true;
        if (combo_state == Combo.ZZZ) {
            combo_state = Combo.NONE;
        } else if (combo_state == Combo.XX) {
            combo_state = Combo.NONE;
        }
        if (state == EntityState.MOVING) {
            super.state = EntityState.MOVING;
        } else {
            super.state = EntityState.STANDING;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z){
            timerPlayer.schedule(new TimerTask() {
                @Override
                public void run() {
                    holdBeforeRelease(Combo.ZZZ);
                }
            }, 150);
        }

        if (key == KeyEvent.VK_X){
            timerPlayer.schedule(new TimerTask() {
                @Override
                public void run() {
                    holdBeforeRelease(Combo.XX);
                }
            }, 150);
        }

        if (key == KeyEvent.VK_LEFT) {
            super.stand();
            super.state = EntityState.STANDING;
            attacking = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.stand();
            super.state = EntityState.STANDING;
            attacking = true;
        }
    }

    public void doneAttack(Character monster){
        timerPlayer.schedule(new TimerTask() {
            @Override
            public void run() {
                Player.super.doneAttack(monster);
            }
        }, 150);
    }

}
