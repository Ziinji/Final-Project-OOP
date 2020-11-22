import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

enum EntityState{
    MOVING, STANDING
}

enum EFacing{
    FACING_LEFT, FACING_RIGHT
}

public class Character {

    private int dx;
    private int x;
    private int y;
    private int w;
    private int h;
    private int health;
    private Image image;
    EFacing pos = EFacing.FACING_RIGHT;

    public void loadImage(String filename) {

        ImageIcon ii = new ImageIcon(filename);
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {
        x += dx;
        if(x<=-140){
            x=-140;
        }
        if(x>=730){
            x=730;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {

        return h;
    }

    public Image getImage() {

        return image;
    }

    public void moveLeft(int a) {
        dx = -a;
    }

    public void moveRight(int a) {
        dx = a;
    }

    public void stand() {
        dx = 0;
    }

    public void kill(){
    }
}