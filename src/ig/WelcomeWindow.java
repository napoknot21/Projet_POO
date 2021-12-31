package ig;
import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JFrame {

    //private Canvas canvas;

    public WelcomeWindow() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((dim.width),(dim.height));
        //this.setSize(1920,1080);
        /*
        ImageIcon fond = new ImageIcon("./images/catan_intro_2.jpg");
        JLabel background = new JLabel("",fond,JLabel.CENTER);
        background.setBounds(0,0,dim.width / 2, dim.height / 2);
        this.add(background);

         */
        this.setTitle("Les Colons de Catan!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(new WelcomePanel());
        //this.setBackground(new Color(255,0,0));
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false); //Pour l'instant
        this.setVisible(true);
    }

}