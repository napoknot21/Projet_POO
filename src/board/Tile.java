package board;

import java.util.concurrent.TimeUnit;

public class Tile {

    private int number = 0; //Nombre aléatoire entre 2 et 12
    private Location location;
    private boolean voleur; //Savoir si le voleur est present ou pas
    private final String type; //Type de tuile

    public Tile (int x, int y, int n, String type, boolean voleur) {
        this.location = new Location(x,y);
        this.number = n;
        this.type = type;
        this.voleur = voleur;
    }

    public Tile (String type) {
        this.type = type;
    }

    public Tile (String type, boolean voleur) {
        this.type = type;
        this.voleur = voleur;
    }

    /*Setters et Getters*/

    public Location getLocation () { return this.location; }
    public int getNumber () { return this.number; }
    public boolean getVoleur () { return this.voleur; }

    public void setLocation(int newX, int newY) { this.location = new Location(newX,newY); }
    public void setNumber (int n) { this.number = n; }
    public void setVoleur (boolean voleur) { this.voleur = voleur; }


}