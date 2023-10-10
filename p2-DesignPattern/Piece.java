import java.util.*;

abstract public class Piece {
    public Color color;
    protected String pieceLocStr;

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

        /* check color (b or w) */
        boolean isValidPieceColor = (color == 98 || color == 119);


        /* check piece's type (k, q, n, b, r, or p) */
        // boolean isValidPieceType = (pieceType == 107 || pieceType == 113 || pieceType == 110 ||
        //                             pieceType == 98 || pieceType == 114 || pieceType == 112);
        // boolean isValidColorAndType = isValidPieceColor && isValidPieceType;
        String errorMessage;

        /* ensure valid color of piece (black or white) */
        if (isValidPieceColor)
        {
            /* get corresponding piece factor for a specific peice */
            PieceFactory pfOfGivenPiece = symbolToPieceTypeMapping.get(pieceType);
            if (pfOfGivenPiece == null)
            {
                errorMessage = String.format("Error: {%1$s} piece name is not in a correct format.", name);
                throw new IllegalArgumentException(errorMessage);
            }
            else
            {
                /* use the corresponding piece factory method to create a specific piece */
                Color colorOfGivenPiece = (color.equals('b')) ? Color.BLACK : Color.WHITE;
                return pfOfGivenPiece.create(colorOfGivenPiece);
            }
        }
        else
        {
            errorMessage = String.format("Error: Color {%1$s} of piece {%2$s} is invalid.", color, name);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public Color color()
    {
        return this.color;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);
}