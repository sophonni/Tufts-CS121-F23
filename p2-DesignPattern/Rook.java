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
            moveLeftRightAndAddedPossibleLoc(loc,b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, true, color());
            moveLeftRightAndAddedPossibleLoc(loc,b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 0, 7, possibleMoveLoc, false, color());
        }
        else
        {

            /* black rook up-down movement */
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 0, possibleMoveLoc, true, color());
            moveUpDownAndAddedPossibleLoc(b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, false, color());
    
            /* black rook left-right movement */
            moveLeftRightAndAddedPossibleLoc(loc,b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc, true, color());
            moveLeftRightAndAddedPossibleLoc(loc,b, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 0, 7, possibleMoveLoc, false, color());
        }
        
        return possibleMoveLoc;
    }

    /**
    * Generates and adds valid up-down moves to the list of possible moves for a rook.
    *
    * @param b               The chessboard.
    * @param locXToCheckForPossibleMoves            The X-coordinate of the rook's location.
    * @param locYToCheckForPossibleMoves            The Y-coordinate of the rook's location.
    * @param xConstraint     The X-coordinate constraint.
    * @param yConstraint     The Y-coordinate constraint.
    * @param possibleMoves   The list of possible moves to be updated.
    * @param isMoveUp        A flag indicating whether the rook moves up (true) or down (false).
    * @param thisPieceColor   The color of the rook.
    **/
    public void moveUpDownAndAddedPossibleLoc(Board b, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int xConstraint, int yConstraint, List<String> possibleMoves, boolean isMoveUp, Color thisPieceColor) {
        boolean isTherePieceBlockage = false;

        if (isMoveUp) {
            /* iterate upward */
            for (int nextlocY = (thisPieceColor == Color.WHITE) ? locYToCheckForPossibleMoves + 1 : locYToCheckForPossibleMoves - 1;
            (thisPieceColor == Color.WHITE) ? nextlocY <= yConstraint : nextlocY >= yConstraint;
            nextlocY += (thisPieceColor == Color.WHITE) ? 1 : -1) {
                /* check for piece blockage */
                isTherePieceBlockage = isTherePieceBlockage(b, pieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, nextlocY, possibleMoves);
                if (isTherePieceBlockage) {
                    break;
                }
            }
        } else {
            /* iterate downward */
            for (int nextlocY = (thisPieceColor == Color.WHITE) ? locYToCheckForPossibleMoves - 1 : locYToCheckForPossibleMoves + 1;
                (thisPieceColor == Color.WHITE) ? nextlocY >= yConstraint : nextlocY <= yConstraint;
                nextlocY += (thisPieceColor == Color.WHITE) ? -1 : 1) {
                /* check for piece blockage */
                isTherePieceBlockage = isTherePieceBlockage(b, pieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, nextlocY, possibleMoves);
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
    * @param locXToCheckForPossibleMoves            The X-coordinate of the rook's location.
    * @param locYToCheckForPossibleMoves            The Y-coordinate of the rook's location.
    * @param xConstraint     The X-coordinate constraint.
    * @param yConstraint     The Y-coordinate constraint.
    * @param possibleMoves   The list of possible moves to be updated.
    * @param isMoveRight     A flag indicating whether the rook moves right (true) or left (false).
    * @param thisPieceColor   The color of the rook.
    */
    public void moveLeftRightAndAddedPossibleLoc(String locStrToSearchForPossibleMove, Board b, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int xConstraint, int yConstraint, List<String> possibleMoves, boolean isMoveRight, Color thisPieceColor) {
        boolean isTherePieceBlockage = false;
        
        /* iterate left or right */
        for (int nextLocXToCheckForPossibleMoves = (isMoveRight) ? locXToCheckForPossibleMoves + 1 : locXToCheckForPossibleMoves - 1;
            (isMoveRight) ? nextLocXToCheckForPossibleMoves <= xConstraint : nextLocXToCheckForPossibleMoves >= xConstraint;
            nextLocXToCheckForPossibleMoves += (isMoveRight) ? 1 : -1) {
            /* check for piece blockage */
            isTherePieceBlockage = isTherePieceBlockage(b, locStrToSearchForPossibleMove, thisPieceColor, nextLocXToCheckForPossibleMoves, locYToCheckForPossibleMoves, possibleMoves);
            if (isTherePieceBlockage) {
                break;
            }
        }
    }

    /**
    * Checks if there is a piece blocking the path of a rook.
    *
    * @param b               The chessboard.
    * @param thisPieceLocStr The location string of the current piece.
    * @param thisPieceColor  The color of the current piece.
    * @param locXToCheckForPossibleMoves            The X-coordinate of the potential opponent's location.
    * @param locYToCheckForPossibleMoves            The Y-coordinate of the potential opponent's location.
    * @param possibleMoves   The list of possible moves to be updated.
    * @return                True if there is a piece blocking the path, false otherwise.
    **/
    public static boolean isTherePieceBlockage(Board b, String thisPieceLocStr, Color thisPieceColor, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, List<String> possibleMoves) {
        int[] locStrOfPotentialOpponent = new int[] { locXToCheckForPossibleMoves, locYToCheckForPossibleMoves };
        String potentialOpponentLocStr = b.arrIndicesToLocStr(locStrOfPotentialOpponent);
        Piece potentialOpponent = b.getPiece(potentialOpponentLocStr);
        //System.out.println("Given: " + thisPieceLocStr);

        /* blocked piece is an opponent of the given piece */
        if (potentialOpponent != null && potentialOpponent.color() != thisPieceColor) {
            //System.out.println("Opponent: " + potentialOpponentLocStr);
            possibleMoves.add(potentialOpponentLocStr);
            return true;
        }
        /* blocked piece is NOT an opponent of the given piece */
        else if (potentialOpponent != null && potentialOpponent.color() == thisPieceColor && !b.arrIndicesToLocStr(locStrOfPotentialOpponent).equals(thisPieceLocStr)) {
            //System.out.println("Non-Opponent: " + potentialOpponentLocStr);
            return true;
        }
        /* no piece blocking the given rook's path (x-coordinate or y-coordinate) */
        else if (potentialOpponent == null) {
            //System.out.println("Non-Occupie: " + potentialOpponentLocStr);
            possibleMoves.add(potentialOpponentLocStr);
            return false;
        } else {
            //System.out.println("Else: " + potentialOpponentLocStr);
            possibleMoves.add(potentialOpponentLocStr);
            return false;
        }
    }
}