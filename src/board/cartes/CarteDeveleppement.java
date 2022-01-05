package board.cartes;

import java.awt.*;

public class CarteDeveleppement extends Carte {

    private final Color color;
    private final String carteType;
    private final int pointVictoire;

    /*
    On peut avoir les couleurs suivantes:
    -> Magenta : Knight
    -> Vert : progrès
    -> Jaune : Point de victoire
    */

    /*
    On peut avoir les types suivants:
    -> Knight : Defense du voleur
    -> Progress : Permet de construir de ponts
    -> VictoryPoint: Augment le nb de points de victoire
     */

    public CarteDeveleppement (String titre, String description, Color couleur, String carteType, int pointVictoire) {
        super(titre,description);
        this.carteType = carteType;
        this.color = couleur;
        this.pointVictoire = pointVictoire;
    }

    public CarteDeveleppement (String titre, String description, Color couleur, String carteType) {
        this(titre,description,couleur,carteType,0);
    }

    public Color getColor () { return this.color; }
    public String getCarteType () { return this.carteType; }
    public int getPointVictoire () { return this.pointVictoire; }




}
