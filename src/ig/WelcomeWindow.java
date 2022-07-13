package ig;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeWindow extends JFrame {

    private JLabel background;
    private InstructionsWndow iw;
    private ConfigurationWindow cw;
    private ImageIcon fond;
    public boolean status = true;

    public WelcomeWindow() {

        this.fond = new ImageIcon("./images/catan_intro_2.jpg");

        //On initialise les valeurs de base de la fenêtre
        this.setTitle("Les Colons de Catan!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(this.fond.getIconWidth(),this.fond.getIconHeight());

        //L'arrière-plan avec une image
        //this.background = new JLabel(this.fond);
        this.background = new JLabel("",fond,JLabel.CENTER);
        this.background.setBounds(0,0,fond.getIconWidth(), fond.getIconHeight());
        this.add(this.background);

        //On crée un JPanel pour y mettre les boutons
        JPanel allButtoms = new JPanel();
        allButtoms.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.add(allButtoms,BorderLayout.CENTER);

        //On initialise les "valeurs" des boutons
        this.iw = new InstructionsWndow();
        this.cw = new ConfigurationWindow(this);

        JPanel bouton1 = new JPanel();
        JPanel bouton2 = new JPanel();
        JPanel bouton3 = new JPanel();


        JButton jouer = new UIButton("Jouer");
        jouer.addActionListener(event -> this.cw.setStatus(true));
        //GridBagConstraints constraints = new GridBagConstraints();

        JButton instru = new UIButton("Comment jouer?");
        instru.addActionListener(event -> this.iw.setStatus(true));
        JButton exit = new UIButton("Sortir");
        exit.addActionListener(event-> System.exit(0));


        bouton2.add(jouer, BorderLayout.CENTER);
        bouton2.add(instru,BorderLayout.CENTER);
        bouton2.add(exit,BorderLayout.CENTER);

        this.add(/*jouer*/bouton1,BorderLayout.NORTH);
        this.add(/*instruµ*/bouton2,BorderLayout.CENTER);
        this.add(/*exit*/bouton3,BorderLayout.SOUTH);

        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(status);

    }

    public void setStatus (boolean status) { this.status = status; }


}
