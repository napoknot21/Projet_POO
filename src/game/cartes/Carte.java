package game.cartes;

public abstract class Carte {

    private final String titre;
    private final String description;

    public Carte (String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre () {
        return this.titre;
    }

    public String getDescription () {
        return this.description;
    }

}