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

    /**************Getters**************/
    public String getColor () { return this.color.toString(); }
    public String getName () { return this.name; }
    public HashMap<String,Integer> getCartesDeveloppement () { return this.cartesDeveloppement; }
    public HashMap<String, Integer> getResources() { return resources; }
    public int getNbColonie() { return this.nbColonie; }
    public int getNbRoutes() { return this.nbRoutes; }
    public int getNbVilles () { return this.nbVilles; }
    public int getPointsVictoire() { return this.pointsVictoire; }
    public ArrayList<Chemin> getChemins () { return this.chemins; }
    public ArrayList<CarteResource> getMain () { return this.main; }
    public boolean isLaPlusLongueArmee () { return this.laPlusLongueArmee; }
    public boolean[] getPorts () { return this.ports; }

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

    public void addChemin (Chemin chemin) {
         this.chemins.add(chemin);
    }


    public void setPointsVictoire (int n) {
        this.pointsVictoire = n;
    }

    public void setLaPlusLongueArmee (boolean b) {
         if (this.laPlusLongueArmee == true && b == false) {
             this.pointsVictoire--;
         } else if (this.laPlusLongueArmee == false && b == true) {
             this.pointsVictoire++;
         }
    }

    public boolean ALesResources (ArrayList<CarteResource> res) {
         int ble = 0,
            mouton = 0,
            pierre = 0,
            bois = 0,
            argile = 0;
         for (CarteResource cr : res) {
             if (cr.getCarteType().equals("Blé")) {
                 ble++;
             } else if (cr.getCarteType().equals("Mouton")) {
                 mouton++;
             } else if (cr.getCarteType().equals("Pierre")) {
                 pierre++;
             } else if (cr.getCarteType().equals("Bois")) {
                 bois++;
             } else if (cr.getCarteType().equals("Argile")) {
                 argile++;
             }
         }

         if (this.getNombreResourcesType("Blé") >= ble && this.getNombreResourcesType("Mouton") >= mouton
                 && this.getNombreResourcesType("Pierre") >= pierre && this.getNombreResourcesType("Bois") >= bois
                 && this.getNombreResourcesType("Argile") >= argile
            ) {
             return true;
         }
         return false;
    }

    public boolean avoirLaCarteMain (String type) {
         for (CarteResource cr : this.main) {
             if (cr.getCarteType().equals(type)) {
                 return true;
             }
         }
         return false;
    }

    public void removeCarteMain (String type) {
        for (CarteResource cr : this.main) {
            if (cr.getCarteType().equals(type)) {
                this.main.remove(cr);
                return;
            }
        }
    }

    public void addPort (int portTag) {
         this.ports[portTag] = true;
    }

    public String toString () {
         return name;
    }

    public void addColonie () { this.nbColonie++;}

    public void upVille () {
         this.nbColonie--;
         this.nbVilles++;
    }

    public void addCheminsComptes () {
         this.nbRoutes++;
    }

    public void removeResource (ArrayList<CarteResource> resources) {
         if (resources.isEmpty()) return;
         for (CarteResource cr : resources) {
             this.setNombreResourcesType(cr.getCarteType(),this.getNombreResourcesType(cr.getCarteType())-1);
         }
    }

    public void addResources (ArrayList<CarteResource> resources) {
         if (resources.isEmpty()) return;
        for (CarteResource cr : resources){
            this.setNombreResourcesType(cr.getCarteType(),this.getNombreResourcesType(cr.getCarteType())+1);
        }
    }

    public int getTotalNumberOfResources () {
         return this.getNombreResourcesType("Blé") +
                 this.getNombreResourcesType("Moutons") +
                 this.getNombreResourcesType("Bois") +
                 this.getNombreResourcesType("Argile") +
                 this.getNombreResourcesType("Pierre");
    }

    public int getNombreDeCarteMainType (String type) {
         int nb = 0;
         for (CarteResource cr : this.main) {
             if (cr.getCarteType().equals(type)) {
                 nb++;
             }
         }
         return nb;
    }

    public boolean winner () { return (this.pointsVictoire >= 10); }



}