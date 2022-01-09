package board;

import board.pieces.Chemin;
import board.pieces.Colonie;
import board.pieces.Structure;
import game.Joueur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

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
    public Tile[][] getTuiles () { return this.tuiles; }
    public Chemin[][][] getChemins () { return this.chemins; }

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

    public ArrayList<Tile> getTuilesAdjacentesStructure (VertexLocation location) {
        ArrayList<Tile> tuiles = new ArrayList<>();
        if (location.getOrientation() == 0) {
            Tile a = this.tuiles[location.getX()][location.getY()];
            if (a.getType() != null) {
                tuiles.add(a);
            }
            Tile b = this.tuiles[location.getX()][location.getY()+1];
            if (b.getType() != null) {
                tuiles.add(b);
            }
            Tile c = this.tuiles[location.getX()+1][location.getY()+1];
            if (c.getType() != null) {
                tuiles.add(c);
            }
        } else {
            Tile a = this.tuiles[location.getX()][location.getY()];
            if (a.getType() != null) {
                tuiles.add(a);
            }
            Tile b = this.tuiles[location.getX()][location.getY()-1];
            if (b.getType() != null) {
                tuiles.add(b);
            }
            Tile c = this.tuiles[location.getX()-1][location.getY()-1];
            if (c.getType() != null) {
                tuiles.add(c);
            }
        }
        return tuiles;
    }

    @SuppressWarnings("unchecked")
    public int trouverLeCheminLePlusLong (Joueur player) {
        ArrayList<Chemin> cheminsList = (ArrayList<Chemin>) player.getChemins().clone();
        int maxChemins = 1; //Debut du chemin

        while (cheminsList.size() > 0) {
            ArrayList<Chemin> cheminsConnectes =  new ArrayList<>();
            cheminsConnectes.add(cheminsList.remove(0));
            for (int i = 0; i <= cheminsConnectes.size(); i++) {
                ArrayList<Chemin> cheminsAdjacents = this.trouverCheminsAdjacents(cheminsConnectes.get(i).getLocation());
                for (int j = 0; j <= cheminsAdjacents.size(); j++) {
                    int index = cheminsList.indexOf(cheminsAdjacents.get(j));
                    if (index >= 0) {
                        cheminsConnectes.add(cheminsList.remove(index));
                    }
                }
            }

            if (this.endPoint == null) {
                this.endPoint = cheminsConnectes.get (0);
                if (this.endPoint.getLocation().getOrientation() == 0 || this.endPoint.getLocation().getOrientation() == 1) {
                    this.startSide = this.structures[this.endPoint.getLocation().getX()][this.endPoint.getLocation().getY()][0].getLocation();
                } else {
                    this.startSide = this.structures[this.endPoint.getLocation().getX()+1][this.endPoint.getLocation().getY()+1][0].getLocation();
                }
            }

            Stack<Chemin> s = new Stack<>();
            Stack<VertexLocation> entreeCotes = new Stack<>();
            s.push(this.endPoint);

            entreeCotes.push(this.startSide);
            int count = 1;

            while (!s.empty()) {
                s.peek().visiter();
                ArrayList<Chemin> cheminsEnfats = this.trouverCheminsAdjacentsDFS(s.peek(),entreeCotes.peek());
                for (int i = 0; i < cheminsEnfats.size(); i++) {
                    if (cheminsEnfats.get(i).estVisite()) {
                        cheminsEnfats.remove(i);
                        i--;
                    }
                }
                if (cheminsEnfats.size() <= 0) {
                    s.pop();
                    entreeCotes.pop();
                    if (count >= maxChemins) {
                        maxChemins = count;
                        count--;
                    }
                } else {
                    count++;
                    entreeCotes.push(this.cheminsConnectesAAutre(s.peek(),cheminsEnfats.get(0)));
                    s.push(cheminsEnfats.get(0));
                }
            }

            for (int i = 0; i < cheminsConnectes.size(); i++) {
                cheminsConnectes.get(i).ResetVisite();
            }

        }

        this.endPoint = null;
        this.startSide = null;
        return maxChemins;


    }


    private ArrayList<Chemin> trouverCheminsAdjacents (EdgeLocation location) {
        Chemin ch = this.chemins[location.getX()][location.getY()][0];
        ArrayList<Chemin> result = new ArrayList<>();
        Joueur player = ch.getPlayer();
        int x = location.getX();
        int y = location.getY();
        int o = location.getOrientation();

        if (o == 0) {
            if (player.equals(this.structures[x][y + 1][1].getJoueur()) || this.structures[x][y + 1][1].getJoueur() == null) {

                if (!player.equals(this.chemins[x - 1][y][1].getPlayer()) && !player.equals(this.chemins[x - 1][y][2].getPlayer())) {
                    this.startSide = this.structures[x][y + 1][1].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x - 1][y][1].getPlayer())) {
                        result.add(this.chemins[x - 1][y][1]);
                    }
                    if (player.equals(this.chemins[x - 1][y][2].getPlayer())) {
                        result.add(this.chemins[x - 1][y][2]);
                    }
                }
            }
            if (player.equals(this.structures[x][y][0].getJoueur()) || this.structures[x][y][0].getJoueur() == null) {
                if (!player.equals(this.chemins[x][y + 1][2].getPlayer()) && !player.equals(this.chemins[x][y][1].getPlayer())) {
                    this.startSide = this.structures[x][y][0].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x][y + 1][2].getPlayer())) {
                        result.add(this.chemins[x][y + 1][2]);
                    }
                    if (player.equals(this.chemins[x][y][1].getPlayer())) {
                        result.add(this.chemins[x][y][1]);
                    }
                }
            }
        } else if (o == 1) {

            if (player.equals(this.structures[x+1][y+1][1].getJoueur()) || this.structures[x+1][y+1][1].getJoueur() == null) {
                if (!player.equals(this.chemins[x+1][y][0].getPlayer()) && !player.equals(this.chemins[x][y][2].getPlayer())) {
                    this.startSide = this.structures[x+1][y+1][1].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x+1][y][0].getPlayer())) {
                        result.add(this.chemins[x+1][y][0]);
                    }
                    if (player.equals(this.chemins[x][y][2].getPlayer())) {
                        result.add(this.chemins[x][y][2]);
                    }
                }
            }

            if (player.equals(this.structures[x][y][0].getJoueur()) || this.structures[x][y][0].getJoueur() == null) {
                if (!player.equals(this.chemins[x][y + 1][2].getPlayer()) && !player.equals(this.chemins[x][y][0].getPlayer())) {
                    this.startSide = this.structures[x][y][0].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x][y + 1][2].getPlayer())) {
                        result.add(this.chemins[x][y + 1][2]);
                    }
                    if (player.equals(this.chemins[x][y][0].getPlayer())) {
                        result.add(this.chemins[x][y][0]);
                    }
                }
            }

        } else {

            if (player.equals(this.structures[x+1][y+1][1].getJoueur()) || this.structures[x+1][y+1][1].getJoueur() == null) {
                if (!player.equals(this.chemins[x+1][y][0].getPlayer()) && !player.equals(this.chemins[x][y][1].getPlayer())) {
                    this.startSide = this.structures[x+1][y+1][1].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x+1][y][0].getPlayer())) {
                        result.add(this.chemins[x+1][y][0]);
                    }
                    if (player.equals(this.chemins[x][y][1].getPlayer())) {
                        result.add(this.chemins[x][y][1]);
                    }
                }
            }

            if (player.equals(this.structures[x][y-1][0].getJoueur()) || this.structures[x][y-1][0].getJoueur() == null) {
                if (!player.equals(this.chemins[x][y - 1][1].getPlayer()) && !player.equals(this.chemins[x][y-1][0].getPlayer())) {
                    this.startSide = this.structures[x][y-1][0].getLocation();
                    this.endPoint = ch;
                } else {
                    if (player.equals(this.chemins[x][y - 1][1].getPlayer())) {
                        result.add(this.chemins[x][y - 1][1]);
                    }
                    if (player.equals(this.chemins[x][y-1][0].getPlayer())) {
                        result.add(this.chemins[x][y-1][0]);
                    }
                }
            }

        }
        return result;
    }


    public ArrayList<Chemin> trouverCheminsAdjacentsDFS (Chemin route, VertexLocation entreeCote) {
        ArrayList<Chemin> check = new ArrayList<>();
        Structure s = this.structures[entreeCote.getX()][entreeCote.getY()][entreeCote.getOrientation()];
        Joueur p = route.getPlayer();
        int x = route.getLocation().getX();
        int y = route.getLocation().getY();
        int o = route.getLocation().getOrientation();

        if (o == 0) {

            if (entreeCote.getOrientation() == 0 && (p.equals(s.getJoueur())) || s.getJoueur() == null) {
                check.add(this.chemins[x-1][y][2]);
                check.add(this.chemins[x-1][y][1]);
            } else if (p.equals(s.getJoueur()) || s.getJoueur() == null) {
                check.add(this.chemins[x][y][1]);
                check.add(this.chemins[x][y+1][2]);
            }

        } else if (o == 1) {

            if (entreeCote.getOrientation() == 0 && (p.equals(s.getJoueur()) || s.getJoueur() == null)) {
                check.add(this.chemins[x][y][2]);
                check.add(this.chemins[x+1][y][0]);
            } else if (p.equals(s.getJoueur()) || s.getJoueur() == null) {
                check.add(this.chemins[x][y][0]);
                check.add(this.chemins[x][y+1][2]);
            }

        } else if (o == 2) {

            if (entreeCote.getOrientation() == 0 && (p.equals(s.getJoueur()) || s.getJoueur() == null)) {
                check.add(this.chemins[x+1][y][0]);
                check.add(this.chemins[x][y][1]);
            } else if (p.equals(s.getJoueur()) || s.getJoueur() == null) {
                check.add(this.chemins[x][y-1][1]);
                check.add(this.chemins[x][y-1][0]);
            }

        }

        for (int i = 0; i < check.size(); i++) {
            if (p.equals(check.get(i).getPlayer()));
            else {
                check.remove(i);
                i--;
            }
        }
        return check;
    }

    public VertexLocation cheminsConnectesAAutre (Chemin ch, Chemin autre ) {
        int ch_x = ch.getLocation().getX();
        int ch_y = ch.getLocation().getY();
        int ch_o = ch.getLocation().getOrientation();

        int autre_x = autre.getLocation().getX();
        int autre_y = autre.getLocation().getY();
        int autre_o = autre.getLocation().getOrientation();

        if (ch_o == 0) {
            if (autre_o == 1) {
                if (ch_x == autre_x) {
                    return this.structures[ch_x][ch_y][0].getLocation();
                } else {
                    return this.structures[ch_x][ch_y+1][1].getLocation();
                }
            } else {
                if (ch_y + 1 == autre_y) {
                    return this.structures[ch_x][ch_y][0].getLocation();
                } else {
                    return this.structures[ch_x][ch_y+1][1].getLocation();
                }
            }
        } else if (ch_o == 1) {
            if (autre_o == 0) {
                if (ch_x == autre_x) {
                    return this.structures[ch_x][ch_y][0].getLocation();
                } else {
                    return this.structures[ch_x-1][ch_y+1][1].getLocation();
                }
            } else {
                if (ch_y + 1 == autre_y) {
                    return this.structures[ch_x][ch_y][0].getLocation();
                } else {
                    return this.structures[ch_x-1][ch_y][1].getLocation();
                }
            }
        } else {
            if (autre_o == 0) {
                if (ch_x == autre_x) {
                    return this.structures[ch_x][ch_y-1][0].getLocation();
                } else {
                    return this.structures[ch_x+1][ch_y+1][1].getLocation();
                }
            } else {
                if (ch_y == autre_y) {
                    return this.structures[ch_x+1][ch_y+1][1].getLocation();
                } else {
                    return this.structures[ch_x][ch_y-1][0].getLocation();
                }
            }
        }
    }

    /*
    int portTag if port, -1 if not
	 *  			  0 = general
					  1 = Argile
					  2 = Mouton
					  3 = Pierre
					  4 = Blé
					  5 = Bois
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