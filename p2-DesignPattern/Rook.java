import java.util.*;

public class Rook extends Piece {
    public Rook(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String rookName= (color == Color.BLACK) ? "b" : "w";
        return rookName + "r";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}