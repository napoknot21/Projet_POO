import java.awt.*;

public class CarteDeveleppement extends Carte {

    private Color color;
    /*
    On peut avoir les couleurs suivantes:
    -> Bleu : Knight
    -> Vert : Lieux (Bibliothèque, Librairie, etc)
    -> Jaune : Point de victoire
    */

    public CarteDeveleppement (String titre, String carteType, String description,Color color) {
        super(titre,carteType,description);
        this.color = color;
    }


}
