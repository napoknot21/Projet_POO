package board.cartes;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Collections;

public class Paquet {

    private ArrayList<CarteDeveleppement> cartes;

    public Paquet () {

        this.cartes = new ArrayList<>();

        //Cartes Chevaliers
        for (int i = 0; i < 14; i++) {
            this.cartes.add(new CarteDeveleppement("Chevalier","Déplacez le pion voleur...",new Color(255,0,255),"Knight",0));
        }

        //Cartes de points de victoire
        this.cartes.add(new CarteDeveleppement("Bibliothèque","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1));
        this.cartes.add(new CarteDeveleppement("Place du Marché","1 point de Victoire", new Color(255,255,0),"VictoryPoint",1));
        this.cartes.add(new CarteDeveleppement("Parlement","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1));
        this.cartes.add(new CarteDeveleppement("Eglise","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1));
        this.cartes.add(new CarteDeveleppement("Université","1 point de Victoire",new Color(255,255,0),"VictoryPoint",1));

        //Cartes progrès
        for (int i = 0; i < 2; i++) {
            this.cartes.add(new CarteDeveleppement("Monopoly","description en cours",new Color(0,255,0),"Progress",0));
            this.cartes.add(new CarteDeveleppement("Invention","Description en cours",new Color(0,255,0),"Progress",0));
            this.cartes.add(new CarteDeveleppement("Construction de Routes","Description en cours",new Color(0,255,0),"Progress",0));
        }

        //On melanges les cartes aléatoirement
        Collections.shuffle(this.cartes);

    }

    public CarteDeveleppement cartePiochee () {
        if (this.cartes.isEmpty()) {
            return null;
        }
        return this.cartes.remove(this.cartes.size()-1); //On retourne la dernier carte
    }


}