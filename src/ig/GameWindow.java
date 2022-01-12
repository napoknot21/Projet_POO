package ig;

import board.Board;
import game.Game;

import javax.swing.*;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    public boolean status = false;
    private ImageIcon fond;
    private JLabel background;
    private Game game;
    private Board board;

    public GameWindow () {
        this.fond = new ImageIcon("./images/catan_intro_2.jpg");

        this.setTitle("Colons de Catan");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(this.fond.getIconWidth(),this.fond.getIconHeight());
        this.setLocationRelativeTo(null);
        this.background = new JLabel("",this.fond,JLabel.CENTER);
        this.background.setBounds(0,0,this.fond.getIconWidth(),this.fond.getIconHeight());
        this.add(this.background);
    }


}
