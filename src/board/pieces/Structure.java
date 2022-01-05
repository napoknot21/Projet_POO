package board.pieces;

import board.VertexLocation;
import game.Joueur;

public abstract class Structure {

    private Joueur player = null;
    private VertexLocation location;
    private int type; //0 si c'est une colonnie et 1 si c'est une ville

    /*
    Cette methode va donner les resources au joueur qui possède la structure
    */
    public abstract void donnerResources (String resType);

    public void setPlayer (Joueur joueur) {
        if (this.player == null) this.player = joueur;
    }

    public void setLocation (VertexLocation location) {
        this.location = location;
    }

    public void setType (int type) {
        this.type = type;
    }

    public Joueur getJoueur () { return this.player; }
    public VertexLocation getLocation () { return this.location; }
    public int getType () { return this.type; }





}
