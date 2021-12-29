public class CarteResource extends Carte {

    private final String carteType;

    public CarteResource(String carteType) {
        super(null,null);
        this.carteType = carteType;
    }

    public String getCarteType () {
        return this.carteType;
    }






}
