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

    public List<String> moves(Board b, String loc)
    {
        List<String> possibleMoveLoc = new ArrayList<>();

        /* get array indices of the current paw */
        int[] indicesOfPiece = b.locStrToArrIndices(loc);
        int currLocXCoor = indicesOfPiece[0];
        int currLocYCoor = indicesOfPiece[1];
        // System.out.println("curr x: " + currLocXCoor);
        // System.out.println("curr y: " + currLocYCoor);

        int leftDiagonalLocXCoor;
        int leftDiagonalLocYCoor;
        int rightDiagonalLocXCoor;
        int rightDiagonalLocYCoor;

        int forward1LocXCoor = currLocXCoor;
        int forward1LocYCoor;
        int forward2LocXCoor = currLocXCoor;
        int forward2LocYCoor;

        Piece currPawn = b.getPiece(loc);

        if (currPawn != null)
        {
    
            if (currPawn.color() == Color.WHITE)
            {
                forward1LocYCoor = currLocYCoor + 1;
                forward2LocYCoor = currLocYCoor + 2;

                /* white pawn can move 2 space up */
                if (currLocYCoor == 1)
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                    checkAndAddPossibleMove(b, currPawn, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc);
                }

                /* white pawn can move 1 space up */
                else
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                }

                /* possible to have 2 pieces that white pawn can take */
                if (currLocXCoor > 0 && currLocXCoor < 7)
                {
                    /* get the piece in the left diagonal location of the current pawn, if any */
                    leftDiagonalLocXCoor = currLocXCoor - 1;
                    leftDiagonalLocYCoor = currLocYCoor + 1;
                    // System.out.println("L x: " + leftDiagonalLocXCoor);
                    // System.out.println("L y: " + leftDiagonalLocYCoor);
                    checkAndAddPossibleMove(b, currPawn, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc);
                    
                    /* get the piece in the right diagonal locationof the current pawn, if any */
                    rightDiagonalLocXCoor = currLocXCoor + 1;
                    rightDiagonalLocYCoor = currLocYCoor + 1;
                    // System.out.println("R x: " + rightDiagonalLocXCoor);
                    // System.out.println("R y: " + rightDiagonalLocYCoor);
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                    //assert rightDiagonalOpponate != null;

                }

                /* white pawn can only take the piece located at the diagonal right square (from white team POV) */
                else if (currLocXCoor == 0)
                {
                    rightDiagonalLocXCoor = currLocXCoor + 1;
                    rightDiagonalLocYCoor = currLocYCoor + 1;
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                }

                /* white pawn can only take the piece located at the diagonal left square (from white team POV) */
                else
                {
                    leftDiagonalLocXCoor = currLocXCoor - 1;
                    leftDiagonalLocYCoor = currLocYCoor + 1;
                    checkAndAddPossibleMove(b, currPawn, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc);

                }
            }
            else
            {
                forward1LocYCoor = currLocYCoor - 1;
                forward2LocYCoor = currLocYCoor - 2;

                /* black pawn can move 2 space up */
                if (currLocYCoor == 6)
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                    checkAndAddPossibleMove(b, currPawn, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc);
                }
                /* black pawn can move 1 space up */
                else
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                }

                /* possible to have 2 pieces that black pawn can take */
                if (currLocXCoor > 0 && currLocXCoor < 7)
                {
                    /* get the piece in the left diagonal location of the current pawn, if any */
                    leftDiagonalLocXCoor = currLocXCoor + 1;
                    leftDiagonalLocYCoor = currLocYCoor - 1;
                    // System.out.println("L x: " + leftDiagonalLocXCoor);
                    // System.out.println("L y: " + leftDiagonalLocYCoor);
                    checkAndAddPossibleMove(b, currPawn, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc);
                    
                    /* get the piece in the right diagonal locationof the current pawn, if any */
                    rightDiagonalLocXCoor = currLocXCoor - 1;
                    rightDiagonalLocYCoor = currLocYCoor - 1;
                    // System.out.println("R x: " + rightDiagonalLocXCoor);
                    // System.out.println("R y: " + rightDiagonalLocYCoor);
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                }
                /* black pawn can only take the piece located at the diagonal right square (from black team POV) */
                else if (currLocXCoor == 0)
                {
                    rightDiagonalLocXCoor = currLocXCoor - 1;
                    rightDiagonalLocYCoor = currLocYCoor - 1;
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                }
                /* black pawn can only take the piece located at the diagonal left square (from black team POV) */
                else
                {
                    leftDiagonalLocXCoor = currLocXCoor + 1;
                    leftDiagonalLocYCoor = currLocYCoor - 1;
                    checkAndAddPossibleMove(b, currPawn, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc);
                }
            }
        }
        return possibleMoveLoc;
    }

    private void checkAndAddPossibleMove(Board b, Piece p, int locX, int locY, List<String> possibleMoves)
    {
        int[] locStrOfPotentialOpponent = new int[] {locX, locY};
        String diagonalLocStr = b.arrIndicesToLocStr(locStrOfPotentialOpponent);
        Piece potentialOpponentPiece = b.getPiece(diagonalLocStr);

        /* ensure that the piece in the diagonal belongs to the opponent */
        if (potentialOpponentPiece != null && potentialOpponentPiece.color() != p.color())
        {
            possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        }
        /* ensure that not same color piece is blocking and ensure that it can't move off the board */
        else if (potentialOpponentPiece == null && (locX != 7 || locX != 0))
        {
            possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        }
    }
}