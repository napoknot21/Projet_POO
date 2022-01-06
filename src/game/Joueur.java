package game;

import board.cartes.*;
import board.pieces.Chemin;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

public class Joueur {

    private final String name;
    private final Color color;
    /*
    Couleurs possibles:
        -> Noir
        -> Bleu
        -> Rouge
        -> Jaune
    */

    private HashMap<String, Integer> cartesDeveloppement;
    private HashMap<String,Integer> resources;
    private ArrayList<CarteResource> main;
    private ArrayList<Chemin> chemins;
    private int nbColonie = 2;
    private int nbRoutes = 2;
    private int nbVilles = 0;
    private int pointsVictoire = 2;
    private boolean laPlusLongueArmee;
    //private boolean lePlusLongueChemin;
    private boolean[] ports = {false, false, false, false, false, false};
    // 0 = general
    // 1 = brick
    // 2 = wool
    // 3 = ore
    // 4 = grain
    // 5 = lumber

    public Joueur (String name, Color color) {
        this.name = name;
        this.color = color;

        this.resources = new HashMap<>(5);
        this.resources.put("Blé",0);
        this.resources.put("Mouton",0);
        this.resources.put("Pierre",0);
        this.resources.put("Bois",0);
        this.resources.put("Argile",0);

        /*
        CarteDeveleppement Chevalier = new CarteDeveleppement("Chevalier","Déplacez le pion voleur...",new Color(255,0,255),"Knight",0);
        CarteDeveleppement Biblioteque = new CarteDeveleppement("Bibliothèque","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement PlaceDuMarche = new CarteDeveleppement("Place du Marché","1 point de Victoire", new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Parlement = new CarteDeveleppement("Parlement","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Eglise = new CarteDeveleppement("Eglise","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Universite = new CarteDeveleppement("Université","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1);
        CarteDeveleppement Monopoly = new CarteDeveleppement("Monopoly","description en cours",new Color(0,255,0),"Progress",0);
        CarteDeveleppement Invention = new CarteDeveleppement("Invention","Description en cours",new Color(0,255,0),"Progress",0);
        CarteDeveleppement ConstructionDeRoutes = new CarteDeveleppement("Construction de Routes","Description en cours",new Color(0,255,0),"Progress",0);
         */

        this.cartesDeveloppement = new HashMap<>(9);
        this.cartesDeveloppement.put("Chevalier",0);
        this.cartesDeveloppement.put("Biblioteque",0);
        this.cartesDeveloppement.put("PlaceDuMarche",0);
        this.cartesDeveloppement.put("Parlement",0);
        this.cartesDeveloppement.put("Eglise",0);
        this.cartesDeveloppement.put("Universite",0);
        this.cartesDeveloppement.put("Monopoly",0);
        this.cartesDeveloppement.put("Invention",0);
        this.cartesDeveloppement.put("ConstructionDeRoutes",0);

        this.main = new ArrayList<>();
    }

    public Joueur (String name, Color color, int blé, int mouton, int pierre, int bois, int argile, int pointsVictoire) {
        this(name,color);
        this.setNombreResourcesType("Blé",blé);
        this.setNombreResourcesType("Mouton",mouton);
        this.setNombreResourcesType("Pierre",pierre);
        this.setNombreResourcesType("Bois",bois);
        this.setNombreResourcesType("Argile",argile);
        this.pointsVictoire = pointsVictoire;
    }

    //Getters
    public String getColor () { return this.color.toString(); }
    public String getName () { return this.name; }
    public int getNbColonie() { return this.nbColonie; }
    public int getNbRoutes() { return this.nbRoutes; }
    public int getNbVilles () { return this.nbVilles; }
    public int getPointsVictoire() { return this.pointsVictoire; }
    public ArrayList<Chemin> getChemins () { return this.chemins; }


    /*Getters et setters exlcusif pour les Colletions (Hashmap et ArrayList)*/

     public int getNombreResourcesType (String type) {
        if (type == null || type.equals("Dessert")) {
            return 0;
        } else if (type.equals("Blé") || type.equals("Mouton") || type.equals("Pierre") || type.equals("Bois") || type.equals("Argile")) {
            return this.resources.get(type);
        }
        return 0;
    }

    public int getNombreCarteDeveleppementType (String type) {
        if (type == null) return 0;
        if (type.equals("Chevalier")) {
            return this.getNombreChevaliers();
            //On fait comme ça pour pas avoir des exceptions en cas d'inexistence de la resource
        } else if (type.equals("Biblioteque") || type.equals("PlaceDuMarche") || type.equals("Parlement") ||
                    type.equals("Eglise") || type.equals("Universite") || type.equals("Monopoly") ||
                    type.equals("Invention") || type.equals("ConstructionDeRoutes")) {
            return this.cartesDeveloppement.get(type);
        }
        return 0;
    }

    public void setNombreResourcesType (String type, int n) {
        if (type == null || n < 0) {
            return;
        } else if (type.equals("Blé") || type.equals("Mouton") || type.equals("Pierre") || type.equals("Bois") || type.equals("Argile")) {
            this.resources.put(type,n);
            for (int i = 0 ; i < n; i++) { this.ajouterCarteMain(type); };
        }
    }

    public void setNombreCarteDeveleppementType (String type, int n) {
        if (type == null || n < 0) {
            return;
        } else if (type.equals("Biblioteque") || type.equals("PlaceDuMarche") || type.equals("Parlement") ||
                type.equals("Eglise") || type.equals("Universite") || type.equals("Monopoly") ||
                type.equals("Invention") || type.equals("ConstructionDeRoutes") || type.equals("Chevalier")) {
            this.cartesDeveloppement.put(type,n);
        }
    }

    //Exclusif pour les chevaliers
    public int getNombreChevaliers () {
        return this.cartesDeveloppement.get("Chevalier");
    }

    public void setNombreChevaliers (int n) {
        this.setNombreCarteDeveleppementType("Chevalier",n);
    }

    public void ajouterUnSeulChevalier () {
        this.setNombreChevaliers(this.getNombreChevaliers()+1);
    }


    public void ajouterCarteMain (String type) {
         this.main.add(new CarteResource(type));
    }



    public void setPointsVictoire (int n) {
        this.pointsVictoire = n;
    }


    public boolean winner () { return (this.pointsVictoire >= 10); }

    public static void main (String[] args) {
        Joueur player = new Joueur("Charly",Color.BLUE);
        System.out.println(player.getNombreChevaliers());
        player.ajouterUnSeulChevalier();
        System.out.println(player.getNombreChevaliers());

    }


}