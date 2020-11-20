import java.awt.EventQueue;
import javax.swing.JFrame;

public class CresserMain extends JFrame {

    public CresserMain() {
        initUI();
    }

    private void initUI() {

        add(new Drawable());

        setTitle("Moving sprite");
        setSize(960, 575);

        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CresserMain ex = new CresserMain();
            ex.setVisible(true);
        });
    }
}