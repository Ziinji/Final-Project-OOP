import java.awt.Image;
import javax.swing.ImageIcon;

public class Map {

    private int x = 0;
    private int y = 0;
    private int w;
    private int h;
    private Image image;

    public Map() {
        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("images/Sprites/Forest.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
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
}