package ig;

import javax.swing.*;
import java.awt.*;

public class ConfigurationWindow extends JFrame {



    private JLabel background;
    public boolean status = false;
    private ImageIcon img;


    public ConfigurationWindow () {

        this.setTitle("Paramètres");
        this.setSize(500, 500);
        this.setBackground(new Color(208, 199, 182));
        this.setVisible(status);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel bouton = new JPanel();
        bouton.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(bouton,BorderLayout.SOUTH);

        bouton.add(new UIButton("Jouer !!!"));


    }


    public void setStatus (boolean status) {
        this.setVisible(status);
    }

}
