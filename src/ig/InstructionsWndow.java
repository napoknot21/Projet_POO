package ig;

import javax.swing.*;
import java.awt.*;

public class InstructionsWndow extends JFrame {

    private boolean status = false;

    public InstructionsWndow () {

        this.setTitle("Instructions");
        this.setSize(400,650);
        this.setBackground(new Color(208, 199, 182));
        this.setVisible(status);

        TextArea text = new TextArea();
        text.setText("Chaque joueur reçoit une fiche Coûts de construction et toutes les pièces de jeu de sa couleur : 5 Colonies (*), 4 Villes (*) et 15 Routes (*). Chacun place 2 routes et 2 colonies sur le plateau de jeu (voir illustration page 1). Chaque joueur place devant lui ses pièces de jeu restantes. S’il n’y a que 3 joueurs, les pièces de jeu rouges retournent dans la boîte.\n" +
                "Les fiches spéciales Route la plus longue (*) et Armée la plus puissante (\u001F) sont placées à côté de la zone de jeu, ainsi que les 2 dés.\n" +
                "Les cartes Ressource sont triées en 5 piles, par type et placées face visible dans les compartiments du jeu dédiés à cette f in. Les compartiments sont disposés à proximité de la zone de jeu :");
        this.add(text);

    }

    public void setStatus (boolean status) {
        this.setVisible(status);
    }
}
