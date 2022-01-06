package board.pieces;

import board.VertexLocation;
import board.cartes.CarteResource;

public class Ville extends Structure {

    public Ville (VertexLocation location) {
        super.setLocation(location);
        super.setType(1);
    }

    public void donnerResources (String type) {
        super.getJoueur().setNombreResourcesType(type,super.getJoueur().getNombreResourcesType(type)+2);
    }

}
