import java.awt.event.KeyEvent;

public class Player extends Character implements ComboState{

    public Player(){
        super.loadImage("images/Sprites/Player/sprite_19.png");
        super.setX(100);
        super.setY(325);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            super.moveLeft(5);
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.moveRight(5);
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
