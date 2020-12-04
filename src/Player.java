import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

enum Combo {
    NONE, Z, ZZ, ZZX, X, XZ, XXZ
}

public class Player extends Character implements AttackState{

    public boolean attacking = true;
    public boolean zPressed = false;
    public boolean xPressed = false;
    Combo combo_state = Combo.NONE;

    public Combo getCombo_state() {
        return combo_state;
    }

    public Player(){
        loadImg();
        super.setHealth(200);
        super.setX(100);
        super.setY(325);
        Timer timerPlayer = new Timer();
        timerPlayer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Player.this.attacked();
            }
        }, 0, 900);
        timerPlayer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Player.this.comboPhase();
            }
        }, 0, 1000);
    }

    public void loadImg() {

        switch (state){
            case STANDING:
                super.loadImage("images/Sprites/Player/idle.gif");
                break;
            case ATTACKING:
                if (combo_state == Combo.NONE && zPressed) {
                    super.loadImage("images/Sprites/Player/atkZ1.png");
                } else if (combo_state == Combo.NONE && xPressed){
                    super.loadImage("images/Sprites/Player/atkX1.png");
                } else if (combo_state == Combo.Z && zPressed){
                    super.loadImage("images/Sprites/Player/atkZ2.png");
                } else if (combo_state == Combo.ZZ && zPressed){
                    super.loadImage("images/Sprites/Player/atkZ3.png");
                } else if (combo_state == Combo.X && xPressed) {
                    super.loadImage("images/Sprites/Player/atkX2.png");
                } else if (combo_state == Combo.ZZ && xPressed) {
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

    public void attacked(){
        if (super.state == EntityState.ATTACKED){
            super.state = EntityState.STANDING;
        }
    }

    public void comboPhase() {
        getCombo_state();
        combo_state = Combo.NONE;
    }

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

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z){
            zPressed = false;
            attacking = true;
            super.state = EntityState.STANDING;
        }

        if (key == KeyEvent.VK_X){
            xPressed = false;
            attacking = true;
            super.state = EntityState.STANDING;
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
}
