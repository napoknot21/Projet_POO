package ig;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class WelcomeWindow extends JFrame {

    private JPanel content;
    private JLabel background;
    private Dimension dim;

    public WelcomeWindow() {

        this.setTitle("Les Colons de Catan!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((dim.width),(dim.height));
        //this.setSize(1920,1080);

        this.content = (JPanel) this.getContentPane();

        ImageIcon fond = new ImageIcon("./images/catan_intro_2.jpg");
        this.background = new JLabel("",fond,JLabel.CENTER);
        this.background.setBounds(0,0,dim.width, dim.height);

        this.content.add(background);

        JPanel boutons = new JPanel();

        boutons.add(new UIButton("Jouer"));
        boutons.add(new UIButton("Instructions"));
        boutons.add(new UIButton("sortir"));

        this.content.add(boutons);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}