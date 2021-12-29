import java.awt.*;

public class CarteDeveleppement extends Carte {

    private final Color color;
    private final String carteType;
    /*
    On peut avoir les couleurs suivantes:
    -> Bleu : Knight
    -> Vert : Lieux (Bibliothèque, Librairie, etc)
    -> Jaune : Point de victoire
    */

    /*
    On peut avoir les types suivants:
    -> Knight : Defense du voleur
    -> Progress : Permet de construir de ponts
    -> Victory Point: Augment d'un point le nb de points
     */


    public CarteDeveleppement (String titre, String description, Color color, String carteType) {
        super(titre,description);
        this.carteType = carteType;
        this.color = color;
    }

    public Color getColor () {
        return this.color;
    }

    public String getCarteType () {
        return this.carteType;
    }



}
