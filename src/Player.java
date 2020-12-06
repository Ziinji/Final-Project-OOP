import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

enum Combo {
    NONE, Z, ZZ, ZZZ, X, XX, ZZX
}

public class Player extends Character implements AttackState{

    public boolean attacking = true;
    public boolean zPressed = false;
    public boolean xPressed = false;
    Combo combo_state = Combo.NONE;
    private Timer timerPlayer = new Timer();

    public Combo getCombo_state() {
        return combo_state;
    }

    public Player(){
        loadImg();
        super.setHealth(200);
        super.setX(100);
        super.setY(325);
        /*timerPlayer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Player.this.attacked();
            }
        }, 0, 900);
        timerPlayer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Player.this.comboPhase();
            }
        }, 0, 1000);*/
    }

    public void loadImg() {

        switch (state){
            case STANDING:
                super.loadImage("images/Sprites/Player/idle.gif");
                break;
            case ATTACKING:
                if (combo_state == Combo.Z && zPressed) {
                    super.loadImage("images/Sprites/Player/atkZ1.png");
                } else if (combo_state == Combo.X && xPressed){
                    super.loadImage("images/Sprites/Player/atkX1.png");
                } else if (combo_state == Combo.ZZ && zPressed){
                    super.loadImage("images/Sprites/Player/atkZ2.png");
                } else if (combo_state == Combo.ZZZ && zPressed){
                    super.loadImage("images/Sprites/Player/atkZ3.png");
                } else if (combo_state == Combo.XX && xPressed) {
                    super.loadImage("images/Sprites/Player/atkX2.png");
                } else if (combo_state == Combo.ZZX && xPressed) {
                    super.loadImage("images/Sprites/Player/atkX2.png");
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

    /*public void attacked(){
        if (super.state == EntityState.ATTACKED){
            super.state = EntityState.STANDING;
        }
    }

    public void comboPhase() {
        getCombo_state();
        combo_state = Combo.NONE;
    }*/

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.state = EntityState.MOVING;
            super.pos = EFacing.FACING_LEFT;
            super.moveLeft(4);
        }

        if (key == KeyEvent.VK_RIGHT) {
            this.state = EntityState.MOVING;
            super.pos = EFacing.FACING_RIGHT;
            super.moveRight(4);
        }
    }

    public void holdBeforeRelease(Combo now){
        xPressed = false;
        zPressed = false;
        attacking = true;
        if (combo_state == Combo.ZZZ){
            combo_state = Combo.NONE;
        }
        else if (combo_state == Combo.XX){
            combo_state = Combo.NONE;
        }
        super.state = EntityState.STANDING;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z){
            timerPlayer.schedule(new TimerTask() {
                @Override
                public void run() {
                    holdBeforeRelease(Combo.ZZZ);
                }
            }, 300);
        }

        if (key == KeyEvent.VK_X){
            timerPlayer.schedule(new TimerTask() {
                @Override
                public void run() {
                    holdBeforeRelease(Combo.XX);
                }
            }, 300);
        }

        if (key == KeyEvent.VK_LEFT) {
            super.stand();
            super.state = EntityState.STANDING;
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.stand();
            super.state = EntityState.STANDING;
        }
    }

    public void doneAttack(Character monster){

        timerPlayer.schedule(new TimerTask() {
            @Override
            public void run() {
                Player.super.doneAttack(monster);
            }
        }, 300);
    }

}
