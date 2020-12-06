import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.Timer;

enum GameState{
    PAUSED, PLAYING, GAME_OVER
}

public class Drawable extends JPanel implements ActionListener {

    private Timer timer;
    private Map background;
    private Player alysse;
    private Enemy monster;
    private final int DELAY = 10;
    private int killed;
    GameState gameState = GameState.PLAYING;

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
        if(gameState == GameState.PLAYING) {
            drawUI(g);
            if (monster.getHealth() > 0) {
                drawEnemy(g);
            } else {
                killed++;
                monster.spawn();
            }
            if (alysse.getHealth() > 0) {
                drawPlayer(g);
            } else {
                gameState = GameState.GAME_OVER;
            }
        }
        if(gameState == GameState.PAUSED){
            drawUI(g);
            drawPaused(g);
        }
        if(gameState == GameState.GAME_OVER){
            drawGO(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawBG(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background.getImage(), background.getX(), background.getY(), this);
    }

    public void drawUI(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.drawString("PLAYER HEALTH",10,40);
        g2d.setFont(new Font("Default", Font.PLAIN,20));
        g2d.drawString("KILLS :",800,40);
        g2d.drawString(Integer.toString(killed),880,40);
        repaint();
        g2d.setColor(Color.RED);
        g2d.fillRect(10, 50, 200, 20);
        g2d.setColor(Color.CYAN);
        g2d.fillRect(10, 50, alysse.getHealth(), 20);
        repaint(10, 50, alysse.getHealth(), 20);
    }

    public void drawGO(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Times New Roman", Font.PLAIN,75));
        g2d.drawString("GAME OVER",260,200);
        g2d.drawString("Enemies Killed:",245,280);
        g2d.drawString(Integer.toString(killed),455,360);
    }


    private void drawPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (alysse.pos == EFacing.FACING_RIGHT) {
            g2d.drawImage(alysse.getImage(), alysse.getX(), alysse.getY(), this);
        } else {
            g2d.drawImage(alysse.getImage(), alysse.getX() + alysse.getWidth(), alysse.getY(), -alysse.getWidth(), alysse.getHeight(), (ImageObserver) this);
        }
    }

    private void drawEnemy(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        monster.MaxHealthBar(g);
        monster.HealthBar(g);
        if (monster.pos == EFacing.FACING_RIGHT) {
            g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
        } else {
            g2d.drawImage(monster.getImage(), monster.getX() + monster.getWidth(), monster.getY(), -monster.getWidth(), monster.getHeight(), (ImageObserver) this);
        }
    }

    public void drawPaused(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawEnemy(g);
        drawPlayer(g);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Times New Roman", Font.PLAIN,75));
        g2d.drawString("PAUSED",330,280);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.PLAYING) {
            step();
            if ((monster.getHealth() > 0) && (alysse.state != EntityState.START)) {
                enemyStep();
            }
        }
    }

    private void step() {
        alysse.move();
        alysse.loadImg();
        if (gameState == GameState.PLAYING) {
            repaint(alysse.getX() - 1, alysse.getY() - 1, alysse.getWidth() + 2, alysse.getHeight() + 2);
        }
    }

    private void enemyStep(){
        if (monster.state != EntityState.ATTACKED){
            monster.chase(alysse);
            monster.move();
        }
        monster.loadImg();
        repaint(monster.getX()-1, monster.getY()-1, monster.getWidth()+2, monster.getHeight()+2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (gameState == GameState.PLAYING) {
                int key = e.getKeyCode();
                alysse.keyReleased(e);
                if(key == KeyEvent.VK_Z || key == KeyEvent.VK_X){
                    alysse.doneAttack(monster);
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE){
                if(gameState == GameState.PAUSED){
                    gameState = GameState.PLAYING;
                } else if (gameState == GameState.PLAYING) {
                    gameState = GameState.PAUSED;
                }
            }
            if (gameState == GameState.PLAYING) {
                if (alysse.attacking) {
                    alysse.state = EntityState.ATTACKING;
                    alysse.stand();
                    alysse.attacking = false;

                    if(key == KeyEvent.VK_Z){
                        alysse.zPressed = true;
                        switch(alysse.combo_state){
                            case NONE:
                            case X:
                                alysse.attack(monster, 20);
                                // Delay here. Jadi pas attack dia state NONE, abis attack state Z
                                // Delay on animation. Abis pencet tombol, tahan PNG sekitar 200MS
                                alysse.combo_state = Combo.Z;
                                break;
                            case Z:
                                alysse.attack(monster, 30);
                                alysse.combo_state = Combo.ZZ;
                                break;
                            case ZZ:
                                alysse.attack(monster, 40);
                                alysse.combo_state = Combo.ZZZ;
                                break;
                        }
                    } else if (key == KeyEvent.VK_X){
                        alysse.xPressed = true;
                        switch (alysse.combo_state) {
                            case NONE:
                            case Z:
                                alysse.attack(monster, 35);
                                alysse.combo_state = Combo.X;
                                break;
                            case X:
                            case ZZ:
                                alysse.attack(monster, 15);
                                alysse.combo_state = Combo.NONE;
                                alysse.heal(10);
                                alysse.combo_state = Combo.XX;
                                break;
                        }
                    }

                }
                alysse.keyPressed(e);
            }
        }
    }
}