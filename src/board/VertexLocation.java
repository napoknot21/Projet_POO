package board;

public class VertexLocation extends Location {

    private int orientation;

    public VertexLocation (int x, int y, int o) {
        super(x,y);
        this.orientation = o;
    }

    public int getOrientation () { return this.orientation; }




}
