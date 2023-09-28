import java.util.*;

public class King extends Piece {
    public King(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String kingName= (color == Color.BLACK) ? "b" : "w";
        return kingName + "k";
    }

    public List<String> moves(Board b, String loc) {
	throw new UnsupportedOperationException();
    }

}