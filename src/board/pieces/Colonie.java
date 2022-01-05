package board.pieces;

import board.VertexLocation;
import board.cartes.CarteResource;

public class Colonie extends Structure {

    public Colonie (int x, int y, int o) {
        super.setLocation(new VertexLocation(x,y,0));
        super.setType(0);
    }

    public void donnerResources(String type) {
        super.getJoueur().setNombreResources(type,super.getJoueur().getNombreResources(type)+1);
    }

    public void donnerResources (CarteResource carte) {
        super.getJoueur().setNombreResources(carte,super.getJoueur().getNombreResources(carte)+1);
    }


}