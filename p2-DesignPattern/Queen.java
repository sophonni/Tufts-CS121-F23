import java.util.*;

public class Queen extends Piece {
    public Queen(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String queenName= (color == Color.BLACK) ? "b" : "w";
        return queenName + "q";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}