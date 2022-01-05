package game.player;

import board.cartes.*;
import java.util.HashMap;
import java.awt.*;

public class Joueur {

    private final String name;
    private final Color color;
    private HashMap<CarteResource,Integer> resources;
    private HashMap<CarteDeveleppement, Integer> cartesDeveloppement;
    private int nbColonie = 2;
    private int nbRoutes = 2;
    private int nbVilles = 0;
    private int pointsVictoire = 2;
    private boolean laPlusLongueArmee;
    private boolean lePlusLongueChemin;
    private boolean[] ports = {false, false, false, false, false, false};
    /*
    Couleurs possibles:
        -> Noir
        -> Bleu
        -> Rouge
        -> Jaune
    */

    public Joueur (String name, Color color) {
        this.name = name;
        this.color = color;

       this.resources = new HashMap<>();
        this.resources.put(new CarteResource("Blé"),0);
        this.resources.put(new CarteResource("Mouton"),0);
        this.resources.put(new CarteResource("Pierre"),0);
        this.resources.put(new CarteResource("Bois"),0);
        this.resources.put(new CarteResource("Argile"),0);

        this.cartesDeveloppement = new HashMap<>();
        CarteDeveleppement Chevalier = new CarteDeveleppement("Chevalier","Déplacez le pion voleur...",new Color(255,0,255),"Knight",0);
        CarteDeveleppement Biblioteque = new CarteDeveleppement("Bibliothèque","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement PlaceDuMarche = new CarteDeveleppement("Place du Marché","1 point de Victoire", new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Parlement = new CarteDeveleppement("Parlement","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Eglise = new CarteDeveleppement("Eglise","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Universite = new CarteDeveleppement("Université","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Monopoly = new CarteDeveleppement("Monopoly","description en cours",new Color(0,255,0),"Progress",0);
        CarteDeveleppement Invention = new CarteDeveleppement("Invention","Description en cours",new Color(0,255,0),"Progress",0);
        CarteDeveleppement ConstructionDeRoutes = new CarteDeveleppement("Construction de Routes","Description en cours",new Color(0,255,0),"Progress",0);


        this.cartesDeveloppement.put(Chevalier,0);
        this.cartesDeveloppement.put(Biblioteque,0);
        this.cartesDeveloppement.put(PlaceDuMarche,0);
        this.cartesDeveloppement.put(Parlement,0);
        this.cartesDeveloppement.put(Eglise,0);
        this.cartesDeveloppement.put(Universite,0);
        this.cartesDeveloppement.put(Monopoly,0);
        this.cartesDeveloppement.put(Invention,0);
        this.cartesDeveloppement.put(ConstructionDeRoutes,0);


    }

    public String getColor () { return this.color.toString(); }
    public String getName () { return this.name; }
    public int getNbColonie() { return this.nbColonie; }
    public int getNbRoutes() { return this.nbRoutes; }
    public int getNbVilles () { return this.nbVilles; }
    public int getPointsVictoire() { return this.pointsVictoire; }

    public int getNombreResources (String type) {
        if (type == null || type.equals("DESSERT")) {
            return 0;
        }
        return this.resources.get(new CarteResource(type));
    }

    public int getNombreResources (CarteResource carte) {
        if (carte == null || carte.getCarteType().equals("DESSERT")) {
            return 0;
        }
        return this.resources.get(carte);
    }

    public void setNombreResources (String type, int n) {
        CarteResource tmp = new CarteResource(type);
        if (type != null && this.resources.containsValue(tmp) && n >= 0) {
            this.resources.put(tmp,n);
        }
    }

    public void setNombreResources (CarteResource carte, int n) {
        if (carte != null && this.resources.containsValue(carte) && n >= 0) {
            this.resources.put(carte,n);
        }
    }



    public boolean winner () { return (this.pointsVictoire >= 10); }

}