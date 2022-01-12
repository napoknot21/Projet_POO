package ig;

import javax.swing.*;
import java.awt.*;

public class InstructionsWndow extends JFrame {

    private boolean status = false;

    public InstructionsWndow () {

        this.setTitle("Instructions");
        this.setSize(600,670);
        this.setBackground(new Color(208, 199, 182));
        this.setVisible(status);
        this.setResizable(false);
        TextArea text = new TextArea();
        text.setSize(new Dimension(390,625));
        text.setFont(new Font("Ariel",Font.PLAIN, 20));
        text.setText("Chaque joueur reçoit une fiche Coûts de construction et toutes les pièces de jeu de sa couleur: \n" +
                "5 Colonies (*), 4 Villes (*) et 15 Routes (*). \n" +
                "Chacun place 2 routes et 2 colonies sur le plateau de jeu. Chaque joueur place devant lui ses pièces de jeu restantes. S’il n’y a que 3 joueurs, les pièces de jeu rouges retournent dans la boîte.\n" +
                "Les fiches spéciales Route la plus longue (*) et Armée la plus puissante (\u001F) sont placées à côté de la zone de jeu, ainsi que les 2 dés.\n" +
                "Les cartes Ressource sont triées en 5 piles, par type et placées face visible dans les compartiments du jeu dédiés à cette f in. Les compartiments sont disposés à proximité de la zone de jeu :");
        this.add(text);
        this.setLocationRelativeTo(null);
    }

    public void setStatus (boolean status) {
        this.setVisible(status);
    }
}
