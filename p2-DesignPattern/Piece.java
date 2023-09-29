import java.util.*;

abstract public class Piece {
    public Color color;

    private static Map<Character, PieceFactory> symbolToPieceTypeMapping = new HashMap<>();
    public static void registerPiece(PieceFactory pf)
    {
        char symbol = pf.symbol();
        symbolToPieceTypeMapping.put(symbol, pf);
    }

    public static Piece createPiece(String name)
    {
        Character color = name.charAt(0);
        Character pieceType = name.charAt(1);
        String errorMessage;

        PieceFactory pfOfGivenPiece = symbolToPieceTypeMapping.get(pieceType);
        if (pfOfGivenPiece == null)
        {
            errorMessage = String.format("Error: {%1$s} piece name is not in a correct.", name);
            throw new IllegalArgumentException(errorMessage);
        }
        else
        {
            Color colorOfGivenPiece = (color.equals('b')) ? Color.BLACK : Color.WHITE;
            return pfOfGivenPiece.create(colorOfGivenPiece);
        }
    }

    public Color color()
    {
        return this.color;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);
}