package board.pieces;

import board.EdgeLocation;
import game.Joueur;

public class Chemin {

    private Joueur player = null;
    private final EdgeLocation location;
    private boolean visite = false;

    public Chemin (int x, int y, int orientation) {
        this.location = new EdgeLocation(x,y,orientation);
    }

    public void setPlayer (Joueur player) {
        this.player = player;
    }

    public void visiter () {
        this.visite = true;
    }

    public Joueur getPlayer () { return this.player; }
    public EdgeLocation getLocation () { return this.location; }
    public boolean estVisite () { return this.visite;}

    public void ResetVisite () {
        this.visite = false;
    }




}
