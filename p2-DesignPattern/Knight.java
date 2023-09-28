import java.util.*;

public class Knight extends Piece {
    public Knight(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String pawnName= (color == Color.BLACK) ? "b" : "w";
        return pawnName + "n";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}