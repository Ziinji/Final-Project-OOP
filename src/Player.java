import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Player extends Character implements ComboState{

    private Image img;

    public Player(){
        super.loadImage("images/Sprites/Player/sprite_19.png");
        super.setX(100);
        super.setY(325);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            super.pos = EFacing.FACING_LEFT;
            super.moveLeft(4);
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.pos = EFacing.FACING_RIGHT;
            super.moveRight(4);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            super.stand();
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.stand();
        }
    }
}
