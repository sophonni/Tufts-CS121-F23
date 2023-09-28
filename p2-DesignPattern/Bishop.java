import java.util.*;

public class Bishop extends Piece {
    public Bishop(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String bishopName= (color == Color.BLACK) ? "b" : "w";
        return bishopName + "b";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}