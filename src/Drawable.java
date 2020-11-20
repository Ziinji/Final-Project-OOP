import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Drawable extends JPanel implements ActionListener {

    private Timer timer;
    private Map background;
    private Player alysse;
    private Enemy monster;
    private final int DELAY = 10;
    private BufferedImage image;

    public Drawable() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        background = new Map();
        setFocusable(true);

        alysse = new Player();
        monster = new Enemy();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBG(g);
        drawEnemy(g);
        drawPlayer(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawBG(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background.getImage(), background.getX(), background.getY(), this);
    }

    private void drawPlayer(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(alysse.getImage(), alysse.getX(), alysse.getY(), this);
    }

    private void drawEnemy(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
        enemyStep();
    }

    private void step() {
        alysse.move();
        repaint(alysse.getX()-1, alysse.getY()-1, alysse.getWidth()+2, alysse.getHeight()+2);
    }

    private void enemyStep(){
        monster.chase(alysse);
        monster.move();
        repaint(monster.getX()-1, monster.getY()-1, monster.getWidth()+2, monster.getHeight()+2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            alysse.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            alysse.keyPressed(e);
        }
    }
}