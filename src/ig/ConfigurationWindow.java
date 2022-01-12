package ig;

import javax.swing.*;
import java.awt.*;

public class ConfigurationWindow extends JFrame {


    private WelcomeWindow ww;
    private GameWindow gw;
    private JLabel background;
    public boolean status = false;
    private ImageIcon img;


    public ConfigurationWindow (WelcomeWindow ww) {

        this.ww = ww;

        this.setTitle("Paramètres");
        this.setSize(500, 500);
        this.setBackground(new Color(208, 199, 182));
        this.setVisible(status);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        JPanel content = (JPanel) this.getContentPane();

        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JPanel player3 = new JPanel();
        JPanel player4 = new JPanel();

        JLabel name = new JLabel("Rentrez le nom du joueur");
        player1.add(name);
        player2.add(name);
        player3.add(name);
        player4.add(name);
        String name_player = name.getText();


        this.add(player1,BorderLayout.NORTH);
        this.add(player2,BorderLayout.EAST);
        this.add(player3,BorderLayout.CENTER);
        this.add(player4,BorderLayout.SOUTH);






        JPanel bouton = new JPanel();
        bouton.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(bouton,BorderLayout.SOUTH);
        JButton play = new UIButton("Jouer !!!");
        bouton.add(play);
        play.addActionListener(event -> {
            this.ww.setVisible(false);
            this.setStatus(false);
            new GameWindow().setVisible(true);
            System.out.println("Jouer !!!");
        });



    }


    public void setStatus (boolean status) {
        this.setVisible(status);
    }

}
