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
        String pieceName = (color() == Color.WHITE) ? "w" : "b";
        Piece rook = Piece.createPiece(pieceName.concat("r"));
        Piece bishop = Piece.createPiece(pieceName.concat("b"));

        List<String> rookPossibleMoves = rook.moves(b, loc);
        List<String> bishopPossibleMoves = bishop.moves(b, loc);
        List<String> queenPossibleMoves = new ArrayList<>();
        queenPossibleMoves.addAll(rookPossibleMoves);
        queenPossibleMoves.addAll(bishopPossibleMoves);
        return queenPossibleMoves;
    }

}