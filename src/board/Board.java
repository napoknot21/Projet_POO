package board;

import board.pieces.Chemin;
import board.pieces.Structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Board {

    private Tile[][] tuiles;
    private Structure[][] structures;
    private Chemin[][] chemins;
    private Location voleurLocation;
    private Chemin endPoint;
    private VertexLocation startSide;

    public Board () {
        this.tuiles = new Tile[7][7];
        this.structures = new Structure[7][7];
        this.chemins = new Chemin[7][7];

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

        //On melange l'ordre
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


    }


}
