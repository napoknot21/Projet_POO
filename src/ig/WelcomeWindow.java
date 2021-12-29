package ig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        this.setSize(1920,1080);
        this.setTitle("Les Colons de Catan!");
        ImageIcon fond = new ImageIcon("./images/catan_intro_2.jpg");
        JLabel background = new JLabel("",fond,JLabel.CENTER);
        background.setBounds(0,0,1920,1080);
        this.add(background);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        WelcomeWindow w = new WelcomeWindow();
    }
    
    
}
