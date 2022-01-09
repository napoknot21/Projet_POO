package ig;

import board.Tile;
import board.pieces.Chemin;
import board.pieces.Structure;
import game.Game;
import game.Joueur;

import javax.swing.*;
import java.util.ArrayList;

public class CatanBoard extends JPanel {

    private int etat = 0;
    private Game game;
    private int boardHeight;
    private int hexagonSide;
    private int heightMargin = 100;
    private int widthMargin;
    private final double carre3div2 = 0.86602540378;
    private final int structureSize = 12;
    private final int cheminSize = 20;

    private ArrayList<Joueur> players;
    private Tile[][] tuiles;
    private Chemin[][][] chemins;
    private Structure[][][] structures;

    private int index;
    private boolean capitol = false;

    public CatanBoard (ArrayList<Joueur> joueurs) {

        this.game = new Game();
        this.players = joueurs;


    }





}
