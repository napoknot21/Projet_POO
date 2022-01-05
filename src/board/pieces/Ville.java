package board.pieces;

import board.VertexLocation;
import board.cartes.CarteResource;

public class Ville extends Structure {

    public Ville (VertexLocation location) {
        super.setLocation(location);
        super.setType(1);
    }

    public void donnerResources (String type) {
        super.getJoueur().setNombreResources(type,super.getJoueur().getNombreResources(type)+2);
    }

    public void donnerResources (CarteResource carte) {
        super.getJoueur().setNombreResources(carte,super.getJoueur().getNombreResources(carte)+2);
    }


}
