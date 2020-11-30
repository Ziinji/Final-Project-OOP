import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

enum Combo {
    NONE, Z, ZX, ZZX, X, XZ, XXZ
}

public class Player extends Character implements AttackState{

    public boolean attacking = true;
    Combo combo_state = Combo.NONE;

    public Player(){
        loadImg();
        super.setHealth(200);
        super.setX(100);
        super.setY(325);
    }

    public void loadImg() {
        if (state == EntityState.STANDING) {
            super.loadImage("images/Sprites/Player/idle.gif");
        } else if (state == EntityState.ATTACKING) {
            super.loadImage("images/Sprites/Player/Sprite-atk-001.png");
        } else if (state == EntityState.MOVING){
            super.loadImage("images/Sprites/Player/run.gif");
        }else{
            super.loadImage("images/Sprites/Player/standing.gif");
        }
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

        if ((key == KeyEvent.VK_Z) || (key == KeyEvent.VK_X)){
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
