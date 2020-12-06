import javax.swing.*;
import java.awt.*;

enum EntityState{
    MOVING, STANDING, ATTACKING, START, ATTACKED
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
    private boolean visible;
    private Image image;
    EFacing pos = EFacing.FACING_RIGHT;
    EntityState state = EntityState.START;

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w-300, h);
    }

    public void heal(int heal){
        if (health > 195){
            this.health = 200;
        } else {
            this.health = this.health + 5;
        }
    }

    public void attack(Character target, int damage){
        Rectangle rect1 = this.getBounds();
        Rectangle rect2 = target.getBounds();

        if(rect1.intersects(rect2) && (this.state != EntityState.ATTACKED)) {
            target.health = target.health-damage;
            target.state = EntityState.ATTACKED;
            if (this.x < target.x) {
                pos = EFacing.FACING_RIGHT;
                setX(this.x +20);
                target.x = target.x +75;
                this.x = this.x +30;
            } else {
                pos = EFacing.FACING_LEFT;
                setX(this.x -20);
                target.x = target.x -75;
                this.x = this.x -30;
            }
        }
    }

    public void doneAttack(Character target){
        target.state = EntityState.STANDING;
    }
}