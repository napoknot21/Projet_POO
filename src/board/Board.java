package board;

import board.pieces.Chemin;
import board.pieces.Colonie;
import board.pieces.Structure;
import game.Joueur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Board {

    private Tile[][] tuiles;
    private Structure[][][] structures;
    private Chemin[][][] chemins;
    private Location voleurLocation;
    private Chemin endPoint;
    private VertexLocation startSide;

    public Board () {
        this.tuiles = new Tile[7][7];
        this.structures = new Structure[7][7][2];
        this.chemins = new Chemin[7][7][3];

        Tile desert = new Tile("Desert",true);

        //Creation d'une arrayList où on met les tuiles possibles
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(new Tile("Forêt")); //resource: Bois
        tileList.add(new Tile("Forêt"));
        tileList.add(new Tile("Forêt"));
        tileList.add(new Tile("Forêt"));
        tileList.add(new Tile("Colline")); //Resource : Argile
        tileList.add(new Tile("Colline"));
        tileList.add(new Tile("Colline"));
        tileList.add(new Tile("Champ")); //Resource: Blé
        tileList.add(new Tile("Champ"));
        tileList.add(new Tile("Champ"));
        tileList.add(new Tile("Champ"));
        tileList.add(new Tile("Pré")); //Resource : Laine (Moutons)
        tileList.add(new Tile("Pré"));
        tileList.add(new Tile("Pré"));
        tileList.add(new Tile("Pré"));
        tileList.add(new Tile("Montagne")); //Resource : Minerai (Pierre)
        tileList.add(new Tile("Montagne"));
        tileList.add(new Tile("Montagne"));
        tileList.add(desert);

        //On melange l'ordre des tuiles précédentes ci-dessus
        Collections.shuffle(tileList);

        //On place les tuiles dans le board
        int count = 0;
        for (int row = 1; row < 6; row++) {
            switch (row) {
                case 1:
                    for (int col = 1; col < 4; col++) {
                        this.tuiles[col][row] = tileList.get(count);
                        this.tuiles[col][row].setLocation(col, row);
                        count++;
                    }
                    break;
                case 2:
                    for (int col = 1; col < 5; col++) {
                        this.tuiles[col][row] = tileList.get(count);
                        this.tuiles[col][row].setLocation(col, row);
                        count++;
                    }
                    break;
                case 3:
                    for (int col = 1; col < 6; col++) {
                        this.tuiles[col][row] = tileList.get(count);
                        this.tuiles[col][row].setLocation(col, row);
                        count++;
                    }
                    break;
                case 4:
                    for (int col = 2; col < 6; col++) {
                        this.tuiles[col][row] = tileList.get(count);
                        this.tuiles[col][row].setLocation(col, row);
                        count++;
                    }
                    break;
                case 5:
                    for (int col = 3; col < 6; col++) {
                        this.tuiles[col][row] = tileList.get(count);
                        this.tuiles[col][row].setLocation(col, row);
                        count++;
                    }
                    break;
            }
            this.voleurLocation = desert.getLocation();
        }

        //L'ordre des nombres assignés aux tuiles, suivi d'un in qui va être utilisé comme index
        int[] nbOrdre = {5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11};
        int nbTuile = 0;

        //Le pairs x, y pour proceder en spiral
        int[] tuileOrdre = {3,5, 2,4, 1,3, 1,2, 1,1, 2,1, 3,1, 4,2, 5,3, 5,4, 5,5, 4,5, 3,4, 2,3, 2,2, 3,2, 4,3, 4,4, 3,3};

        //On asigne les valeurs du tab nbOrdre aux tuiles sur le board, en procedant en spiral
        for (int i = 0; i < tuileOrdre.length -1; i+=2) {
            if (nbTuile == 18) {
                break;
            }

            if (this.tuiles[tuileOrdre[i]][tuileOrdre[i+1]].getType().equals("Desert")) {
                //On fait rien
            } else {
                this.tuiles[tuileOrdre[i]][tuileOrdre[i + 1]].setNumber(nbOrdre[nbTuile]);
                nbTuile++;
            }
        }

        //On place les tuiles vides dans le board
        for (int i = 0; i < this.tuiles.length; i++) {
            for (int j = 0; j < this.tuiles[0].length; j++) {
                if (this.tuiles[i][j] == null) { //On verifie qu'elle est bien nulle
                    this.tuiles[i][j] = new Tile(i,j,0,null); //On met les coordonées
                }
            }
        }

        //On place tous les structures dans le board
        for (int row = 0; row < this.structures.length; row++) {
            for (int col = 0; col < this.structures[0].length; col++) {
                for (int ori = 0; ori < this.structures[0][0].length; ori++) {
                    this.structures[col][row][ori] = new Colonie(col,row,ori);
                }
            }
        }

        //On place les chemins dans le board
        for (int row = 0; row < this.chemins.length; row++) {
            for (int col = 0; col < this.chemins[0].length; col++) {
                for (int ori = 0; ori < this.chemins[0][0].length; ori++) {
                    this.chemins[col][row][ori] = new Chemin(col,row,ori);
                }
            }
        }

    }

    /***********Getters**********/
    public Structure[][][] getStructures () { return this.structures; }
    public Chemin getChemin (EdgeLocation location) {
        return this.chemins[location.getX()][location.getY()][location.getOrientation()];
    }
    public Location getVoleurLocation () { return this.voleurLocation; }

    public void setVoleurLocation (Location location) {
        this.voleurLocation = location;
    }
    public Tile getTuile (Location location) {
        return this.tuiles[location.getX()][location.getY()];
    }

    //La fonction donne les resources aux joueurs avec un "structure" qui est frontiere avec le nombre des dés
    public void distributionResources (int des) {
        ArrayList<Tile> desTuiles = this.getTuilesAvecNumero(des);
        for (Tile t : desTuiles) {
            if (t.getVoleur() || t.getType().equals("Desert")) {
                continue;
            }
            ArrayList<Structure> desStructures = new ArrayList<>();
            Location location = t.getLocation();

            //On ajoute toutes les 6 structures dans l'arrayList
            desStructures.add(this.structures[location.getX()][location.getY()][0]);
            desStructures.add(this.structures[location.getX()][location.getY()][1]);
            desStructures.add(this.structures[location.getX()-1][location.getY()+1][1]);
            desStructures.add(this.structures[location.getX()-1][location.getY()-1][0]);
            desStructures.add(this.structures[location.getX()][location.getY()-1][0]);

            for (Structure s : desStructures) {
                if (s.getJoueur() != null) {
                    s.donnerResources(t.getType());
                }
            }
        }

    }


    //La fonction cherche dans le board les tuiles avec la valeur passé en parametre et ça renvoie une ArrayList
    public ArrayList<Tile> getTuilesAvecNumero (int nb) {
        ArrayList<Tile> list = new ArrayList<>();
        for (int i = 1; i < this.tuiles.length; i++) {
            for (int j = 1; j < this.tuiles[i].length; j++) {
                if (this.tuiles[i][j].getNumber() == nb) {
                    list.add(this.tuiles[i][j]);
                }
            }
        }
        return list;
    }

    public Structure getStructure (VertexLocation location) {
        return this.structures[location.getX()][location.getY()][location.getOrientation()];
    }

    public void setStructure (VertexLocation location, Structure s) {
        this.structures[location.getX()][location.getY()][location.getOrientation()] = s;
    }

    public boolean setStructureSansChemin (VertexLocation location, Joueur player) {
        if (this.structures[location.getX()][location.getY()][location.getOrientation()] != null) { //Vertex est deja occupé
            return false;
        }

        if (location.getOrientation() == 0) {
            if (this.structures[location.getX()][location.getY()+1][1].getJoueur() == null &&
                this.structures[location.getX()-1][location.getY()+1][1].getJoueur() == null &&
                !(location.getY()+2 <= 6 && !(this.structures[location.getX()+1][location.getY()+2][1].getJoueur() == null))) {

                if (this.checkPort(location) != 1) {
                    player.addPort(checkPort(location));
                }
                this.structures[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;

            } else {
                return false;
            }
        } else {
            if (this.structures[location.getX()][location.getY() - 1][0].getJoueur() == null &&
                    this.structures[location.getX() - 1][location.getY() - 1][0].getJoueur() == null &&
                    !(location.getY() - 2 >= 0 && !(this.structures[location.getX() - 1][location.getY() - 2][0].getJoueur() == null))) {

                if (this.checkPort(location) != 1) {
                    player.addPort(checkPort(location));
                }
                this.structures[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;

            } else {
                return false;
            }

        }

    }

    public boolean placerStructure (VertexLocation location, Joueur player) {
        if (this.structures[location.getX()][location.getY()][location.getOrientation()] != null) { //Vertex est deja occupé
            return false;
        }

        if (location.getOrientation() == 0) {

            if ((player.equals(this.chemins[location.getX()][location.getY()][0].getPlayer()) ||
               player.equals(this.chemins[location.getX()][location.getY()][1].getPlayer()) ||
               player.equals(this.chemins[location.getX()][location.getY() + 1][2].getPlayer())) &&
               (this.structures[location.getX()][location.getY()+1][1].getJoueur() == null &&
               this.structures[location.getX()+1][location.getY()+1][1].getJoueur() == null &&
               !(location.getY() + 2 <=6 && !(this.structures[location.getX()+1][location.getY()+2][1].getJoueur() == null)))) {

                if (this.checkPort(location) != 1) {
                    player.addPort(checkPort(location));
                }
                this.structures[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;


            } else {
                return false;
            }

        } else {

            if ((player.equals(this.chemins[location.getX()][location.getY()-1][0].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY()-1][1].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY() - 1][2].getPlayer())) &&
                (this.structures[location.getX()][location.getY()-1][1].getJoueur() == null &&
                this.structures[location.getX()+1][location.getY()-1][1].getJoueur() == null &&
                !(location.getY() - 2 >= 0 && !(this.structures[location.getX()-1][location.getY()-2][0].getJoueur() == null)))) {

                if (this.checkPort(location) != 1) {
                    player.addPort(checkPort(location));
                }
                this.structures[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;

            } else {
                return false;
            }

        }
    }

    //Verifie la position pour assigner le chemin au joueur
    public boolean placerChemin (EdgeLocation location, Joueur player) {
        if (this.chemins[location.getX()][location.getY()][location.getOrientation()].getPlayer() != null) { //Le vertex est déjà pris !
            return false;
        }

        if (location.getOrientation() == 0) {

            if (player.equals(this.structures[location.getX()][location.getY()+1][1].getJoueur()) ||
                player.equals(this.structures[location.getX()][location.getY()][0].getJoueur()) ||
                player.equals(this.chemins[location.getX()-1][location.getY()][1].getPlayer()) ||
                player.equals(this.chemins[location.getX()-1][location.getY()][2].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY()+1][2].getPlayer())||
                player.equals(this.chemins[location.getX()][location.getY()][1].getPlayer())) {

                this.chemins[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;
            } else {
                return false;
            }


        } else if (location.getOrientation() == 1) {

            if (player.equals(this.structures[location.getX()][location.getY()][0].getJoueur()) ||
                player.equals(this.structures[location.getX()+1][location.getY()+1][1].getJoueur()) ||
                player.equals(this.chemins[location.getX()][location.getY()][0].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY()+1][2].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY()][2].getPlayer())||
                player.equals(this.chemins[location.getX()+1][location.getY()][0].getPlayer())) {

                this.chemins[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;
            } else {
                return false;
            }

        } else {
            if (player.equals(this.structures[location.getX()][location.getY()-1][0].getJoueur()) ||
                player.equals(this.structures[location.getX()+1][location.getY()+1][1].getJoueur()) ||
                player.equals(this.chemins[location.getX()][location.getY()][1].getPlayer()) ||
                player.equals(this.chemins[location.getX()+1][location.getY()][0].getPlayer()) ||
                player.equals(this.chemins[location.getX()][location.getY()-1][0].getPlayer())||
                player.equals(this.chemins[location.getX()][location.getY()-1][1].getPlayer())) {

                this.chemins[location.getX()][location.getY()][location.getOrientation()].setPlayer(player);
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean placeVille (VertexLocation location, Joueur player) {
        if (player.equals(this.structures[location.getX()][location.getY()][location.getOrientation()].getJoueur()) &&
            this.structures[location.getX()][location.getY()][location.getOrientation()].getType() == 0) { //0 si c'est un colonie et 1 si c'est une ville
            this.structures[location.getX()][location.getY()][location.getOrientation()].setType(1);
            return true;
        }
        return false;
    }

    //
    public boolean moveVoleur (Location location) {
        Location tmp = this.getVoleurLocation();
        if (tmp.getX() == location.getX() && tmp.getY() == location.getY()) {
            return false;
        }
        this.tuiles[tmp.getX()][tmp.getY()].setVoleur(false);
        this.setVoleurLocation(tmp);
        this.tuiles[location.getX()][location.getY()].setVoleur(true);
        this.setVoleurLocation(location);
        return true;
    }


    public ArrayList<Joueur> getJoueursAffectesParVoleur () {
        Location voleurTmp = this.getVoleurLocation();
        ArrayList<Structure> pieces = new ArrayList<>();
        ArrayList<Joueur> players = new ArrayList<>();
        pieces.add(this.structures[voleurTmp.getX()][voleurTmp.getY()][0]);
        pieces.add(this.structures[voleurTmp.getX()+1][voleurTmp.getY()+1][1]);
        pieces.add(this.structures[voleurTmp.getX()][voleurTmp.getY()-1][0]);
        pieces.add(this.structures[voleurTmp.getX()][voleurTmp.getY()][1]);
        pieces.add(this.structures[voleurTmp.getX()-1][voleurTmp.getY()-1][0]);
        pieces.add(this.structures[voleurTmp.getX()][voleurTmp.getY()+1][1]);

        for (Structure s : pieces) {
            if (s.getJoueur() != null) {
                if (Collections.frequency(players,s.getJoueur())<1) { //On verifie qu'il est pas dans la liste pour l'ajouter
                    players.add(s.getJoueur());
                }
            }
        }
        return players;
    }

    /*
    CODE PAS FINI
     */

    public int checkPort (VertexLocation location) {
        int x = location.getX();
        int y = location.getY();
        int o = location.getOrientation();

        if ( (x == 4 && y == 1 && o == 0) || (x == 4 && y == 2 && o ==1)) {
            return 1;
        } else if ((x == 4 && y == 5 && o == 0) || (x == 5 && y == 6 && o == 1)) {
            return 2;
        } else if ((x == 1 && y == 3 && o == 0) || (x == 2 && y == 5 && o == 1)) {
            return 3;
        } else if ((x == 0 && y == 1 && o == 0) || (x == 1 && y == 3 && o == 1)) {
            return 4;
        } else if ((x == 2 && y == 0 && o == 0) || (x == 2 && y == 1 && o == 1)) {
            return 5;
        } else if ((x == 0 && y == 0 && o == 0) ||
                (x == 1 && y == 1 && o == 1) ||
                (x == 5 && y == 2 && o == 0) ||
                (x == 6 && y == 4 && o == 1) ||
                (x == 5 && y == 4 && o == 0) ||
                (x == 6 && y == 5 && o == 1) ||
                (x == 3 && y == 5 && o == 0) ||
                (x == 3 && y == 6 && o == 1)) {
            return 0;
        } else {
            return -1;
        }
    }



}
