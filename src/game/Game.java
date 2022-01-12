package game;

import board.Board;
import board.cartes.Paquet;
import ig.WelcomeWindow;

import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class Game {

    private Board board;
    private ArrayList<Joueur> players;
    private Paquet paquet;
    private WelcomeWindow ww;

    public Game (/*ArrayList<Joueur> tousLesJoueurs*/) {
        EventQueue.invokeLater(() -> {
            this.ww = new WelcomeWindow();
            this.ww.setVisible(true);

        });
        /*
        if (tousLesJoueurs.size() <3 || tousLesJoueurs.size() >4) {
            throw new IllegalArgumentException("Le jeu doit être joué pour au moins 3 personnes et au max 4");
        }


         */

    }



}
