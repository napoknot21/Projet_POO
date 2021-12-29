public abstract class Carte {

    private String titre;
    private final String carteType;
    private String description;

    public Carte (String titre, String carteType, String description) {
        this.titre = titre;
        this.carteType = carteType;
        this.description = description;
    }


    public String getCarteType() {
        return this.carteType;
    }


}
