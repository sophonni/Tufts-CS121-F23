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

    public List<String> moves(Board b, String loc)
    {
        List<String> possibleMoveLoc = new ArrayList<>();

        int[] arrIndicesOfCurrRook = b.locStrToArrIndices(loc);

        if (color() == Color.WHITE)
        {
            /* white rook up-down movement */
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, true, color());
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 0, possibleMoveLoc, false, color());
    
            /* white rook left-right movement */
            moveLeftRightAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, true, color());
            moveLeftRightAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 0, 7, possibleMoveLoc, false, color());
        }
        else
        {

            /* black rook up-down movement */
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 0, possibleMoveLoc, true, color());
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, false, color());
    
            /* black rook left-right movement */
            moveLeftRightAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, true, color());
            moveLeftRightAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 0, 7, possibleMoveLoc, false, color());
        }
        
        return possibleMoveLoc;
    }

    /**
    * Generates and adds valid up-down moves to the list of possible moves for a rook.
    *
    * @param b               The chessboard.
    * @param locX            The X-coordinate of the rook's location.
    * @param locY            The Y-coordinate of the rook's location.
    * @param xConstraint     The X-coordinate constraint.
    * @param yConstraint     The Y-coordinate constraint.
    * @param possibleMoves   The list of possible moves to be updated.
    * @param isMoveUp        A flag indicating whether the rook moves up (true) or down (false).
    * @param currRookColor   The color of the rook.
    **/
    public void moveUpDownAndAddedPossibleLoc(Board b, int locX, int locY, int xConstraint, int yConstraint, List<String> possibleMoves, boolean isMoveUp, Color currRookColor) {
        boolean isTherePieceBlockage = false;

        if (isMoveUp) {
            /* iterate upward */
            for (int nextLocY = (currRookColor == Color.WHITE) ? locY + 1 : locY - 1;
                (currRookColor == Color.WHITE) ? nextLocY <= yConstraint : nextLocY >= yConstraint;
                nextLocY += (currRookColor == Color.WHITE) ? 1 : -1) {
                /* check for piece blockage */
                isTherePieceBlockage = isTherePieceBlockage(b, pieceLocStr, currRookColor, locX, nextLocY, possibleMoves);
                if (isTherePieceBlockage) {
                    break;
                }
            }
        } else {
            /* iterate downward */
            for (int nextLocY = (currRookColor == Color.WHITE) ? locY - 1 : locY + 1;
                (currRookColor == Color.WHITE) ? nextLocY >= yConstraint : nextLocY <= yConstraint;
                nextLocY += (currRookColor == Color.WHITE) ? -1 : 1) {
                /* check for piece blockage */
                isTherePieceBlockage = isTherePieceBlockage(b, pieceLocStr, currRookColor, locX, nextLocY, possibleMoves);
                if (isTherePieceBlockage) {
                    break;
                }
            }
        }
    }


    /**
    * Generates and adds valid left-right moves to the list of possible moves for a rook.
    *
    * @param b               The chessboard.
    * @param locX            The X-coordinate of the rook's location.
    * @param locY            The Y-coordinate of the rook's location.
    * @param xConstraint     The X-coordinate constraint.
    * @param yConstraint     The Y-coordinate constraint.
    * @param possibleMoves   The list of possible moves to be updated.
    * @param isMoveRight     A flag indicating whether the rook moves right (true) or left (false).
    * @param currRookColor   The color of the rook.
    */
    public void moveLeftRightAndAddedPossibleLoc(Board b, int locX, int locY, int xConstraint, int yConstraint, List<String> possibleMoves, boolean isMoveRight, Color currRookColor) {
        boolean isTherePieceBlockage = false;
        
        /* iterate left or right */
        for (int nextLocX = (isMoveRight) ? locX + 1 : locX - 1;
            (isMoveRight) ? nextLocX <= xConstraint : nextLocX >= xConstraint;
            nextLocX += (isMoveRight) ? 1 : -1) {
            /* check for piece blockage */
            isTherePieceBlockage = isTherePieceBlockage(b, pieceLocStr, currRookColor, nextLocX, locY, possibleMoves);
            if (isTherePieceBlockage) {
                break;
            }
        }
    }

    /**
    * Checks if there is a piece blocking the path of a rook.
    *
    * @param b               The chessboard.
    * @param currPieceLocStr The location string of the current piece.
    * @param currPieceColor  The color of the current piece.
    * @param locX            The X-coordinate of the potential opponent's location.
    * @param locY            The Y-coordinate of the potential opponent's location.
    * @param possibleMoves   The list of possible moves to be updated.
    * @return                True if there is a piece blocking the path, false otherwise.
    **/
    public static boolean isTherePieceBlockage(Board b, String currPieceLocStr, Color currPieceColor, int locX, int locY, List<String> possibleMoves) {
        int[] locStrOfPotentialOpponent = new int[] { locX, locY };
        String potentialOpponentLocStr = b.arrIndicesToLocStr(locStrOfPotentialOpponent);
        Piece potentialOpponent = b.getPiece(potentialOpponentLocStr);

        /* blocked piece is an opponent of the given piece */
        if (potentialOpponent != null && potentialOpponent.color() != currPieceColor) {
            possibleMoves.add(potentialOpponentLocStr);
            return true;
        }
        /* blocked piece is NOT an opponent of the given piece */
        else if (potentialOpponent != null && potentialOpponent.color() == currPieceColor && !potentialOpponent.toString().equals(currPieceLocStr)) {
            possibleMoves.add(potentialOpponentLocStr);
            return false;
        }
        /* no piece blocking the given rook's path (x-coordinate or y-coordinate) */
        else if (potentialOpponent == null) {
            possibleMoves.add(potentialOpponentLocStr);
            return false;
        } else {
            return true;
        }
    }
}