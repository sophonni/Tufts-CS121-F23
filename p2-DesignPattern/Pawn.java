import java.util.*;

public class Pawn extends Piece {
    public Pawn(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String pawnName= (color == Color.BLACK) ? "b" : "w";
        return pawnName + "p";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}