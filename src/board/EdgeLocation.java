package board;

public class EdgeLocation extends Location {

    private int orientation; // 0 = left and 1 = rigth

    public EdgeLocation (int x, int y, int orientation ) {
        super(x,y);
        this.orientation = orientation;
    }

    public int getOrientation () { return this.orientation; }


}
