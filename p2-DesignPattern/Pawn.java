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
        int[] arrIndicesOfCurrPawn = b.locStrToArrIndices(loc);
        int currLocXCoor = arrIndicesOfCurrPawn[0];
        int currLocYCoor = arrIndicesOfCurrPawn[1];

        boolean checkDiagonal = false;

        int leftDiagonalLocXCoor;
        int leftDiagonalLocYCoor;
        int rightDiagonalLocXCoor;
        int rightDiagonalLocYCoor;

        if (color() == Color.WHITE)
        {
            King.checkUpDown(b, true, pieceLocStr, color, currLocXCoor, currLocYCoor, 7, possibleMoveLoc);
            if (currLocYCoor == 1)
            {                
                King.checkUpDown(b, true, pieceLocStr, color, currLocXCoor, currLocYCoor + 1, 7, possibleMoveLoc);
            }

            /* possible to have 2 pieces that white pawn can take */
            if (currLocXCoor > 0 && currLocXCoor < 7)
            {
                checkDiagonal = true;
                /* get the piece in the left diagonal location of the current pawn, if any */
                leftDiagonalLocXCoor = currLocXCoor - 1;
                leftDiagonalLocYCoor = currLocYCoor + 1;
                // System.out.println("L x: " + leftDiagonalLocXCoor);
                // System.out.println("L y: " + leftDiagonalLocYCoor);
                checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
                    
                /* get the piece in the right diagonal locationof the current pawn, if any */
                rightDiagonalLocXCoor = currLocXCoor + 1;
                rightDiagonalLocYCoor = currLocYCoor + 1;
                // System.out.println("R x: " + rightDiagonalLocXCoor);
                // System.out.println("R y: " + rightDiagonalLocYCoor);
                checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
                //assert rightDiagonalOpponate != null;

            }

            /* white pawn can only take the piece located at the diagonal right square (from white team POV) */
            else if (currLocXCoor == 0)
            {
                checkDiagonal = true;
                rightDiagonalLocXCoor = currLocXCoor + 1;
                rightDiagonalLocYCoor = currLocYCoor + 1;
                checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
            }

            /* white pawn can only take the piece located at the diagonal left square (from white team POV) */
            else
            {
                checkDiagonal = true;
                leftDiagonalLocXCoor = currLocXCoor - 1;
                leftDiagonalLocYCoor = currLocYCoor + 1;
                checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);

            }
        }
        else
        {
            King.checkUpDown(b, true, pieceLocStr, color, currLocXCoor, currLocYCoor, 0, possibleMoveLoc);
            if (currLocYCoor == 6)
            {                
                King.checkUpDown(b, true, pieceLocStr, color, currLocXCoor, currLocYCoor - 1, 0, possibleMoveLoc);
            }
            /* possible to have 2 pieces that black pawn can take */
            if (currLocXCoor > 0 && currLocXCoor < 7)
            {
                checkDiagonal = true;
                /* get the piece in the left diagonal location of the current pawn, if any */
                leftDiagonalLocXCoor = currLocXCoor + 1;
                leftDiagonalLocYCoor = currLocYCoor - 1;
                // System.out.println("L x: " + leftDiagonalLocXCoor);
                // System.out.println("L y: " + leftDiagonalLocYCoor);
                checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
                
                /* get the piece in the right diagonal locationof the current pawn, if any */
                rightDiagonalLocXCoor = currLocXCoor - 1;
                rightDiagonalLocYCoor = currLocYCoor - 1;
                // System.out.println("R x: " + rightDiagonalLocXCoor);
                // System.out.println("R y: " + rightDiagonalLocYCoor);
                checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
            }
            /* black pawn can only take the piece located at the diagonal right square (from black team POV) */
            else if (currLocXCoor == 0)
            {
                checkDiagonal = true;
                leftDiagonalLocXCoor = currLocXCoor + 1;
                leftDiagonalLocYCoor = currLocYCoor - 1;
                checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
            }
            /* black pawn can only take the piece located at the diagonal left square (from black team POV) */
            else
            {
                //checkDiagonal = true;
                rightDiagonalLocXCoor = currLocXCoor - 1;
                rightDiagonalLocYCoor = currLocYCoor - 1;
                checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
            }
        }

        // boolean checkDiagonal = false;

        // int leftDiagonalLocXCoor;
        // int leftDiagonalLocYCoor;
        // int rightDiagonalLocXCoor;
        // int rightDiagonalLocYCoor;

        // int forward1LocXCoor = currLocXCoor;
        // int forward1LocYCoor;
        // int forward2LocXCoor = currLocXCoor;
        // int forward2LocYCoor;

        // if (color() == Color.WHITE)
        // {
        //     forward1LocYCoor = currLocYCoor + 1;
        //     forward2LocYCoor = currLocYCoor + 2;

        //     /* white pawn can move 2 space up */
        //     if (currLocYCoor == 1)
        //     {
        //         checkDiagonal = false;
        //         checkAndAddPossibleMove(b, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc, checkDiagonal);
        //         checkAndAddPossibleMove(b, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc, checkDiagonal);
        //     }

        //     /* white pawn can move 1 space up */
        //     else
        //     {
        //         checkDiagonal = false;
        //         checkAndAddPossibleMove(b, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc, checkDiagonal);
        //     }

        //     /* possible to have 2 pieces that white pawn can take */
        //     if (currLocXCoor > 0 && currLocXCoor < 7)
        //     {
        //         checkDiagonal = true;
        //         /* get the piece in the left diagonal location of the current pawn, if any */
        //         leftDiagonalLocXCoor = currLocXCoor - 1;
        //         leftDiagonalLocYCoor = currLocYCoor + 1;
        //         // System.out.println("L x: " + leftDiagonalLocXCoor);
        //         // System.out.println("L y: " + leftDiagonalLocYCoor);
        //         checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
                
        //         /* get the piece in the right diagonal locationof the current pawn, if any */
        //         rightDiagonalLocXCoor = currLocXCoor + 1;
        //         rightDiagonalLocYCoor = currLocYCoor + 1;
        //         // System.out.println("R x: " + rightDiagonalLocXCoor);
        //         // System.out.println("R y: " + rightDiagonalLocYCoor);
        //         checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
        //         //assert rightDiagonalOpponate != null;

        //     }

        //     /* white pawn can only take the piece located at the diagonal right square (from white team POV) */
        //     else if (currLocXCoor == 0)
        //     {
        //         checkDiagonal = true;
        //         rightDiagonalLocXCoor = currLocXCoor + 1;
        //         rightDiagonalLocYCoor = currLocYCoor + 1;
        //         checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
        //     }

        //     /* white pawn can only take the piece located at the diagonal left square (from white team POV) */
        //     else
        //     {
        //         checkDiagonal = true;
        //         leftDiagonalLocXCoor = currLocXCoor - 1;
        //         leftDiagonalLocYCoor = currLocYCoor + 1;
        //         checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);

        //     }
        // }
        // else
        // {
        //     forward1LocYCoor = currLocYCoor - 1;
        //     forward2LocYCoor = currLocYCoor - 2;

        //     /* black pawn can move 2 space up */
        //     if (currLocYCoor == 6)
        //     {
        //         checkDiagonal = false;
        //         checkAndAddPossibleMove(b, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc, checkDiagonal);
        //         checkAndAddPossibleMove(b, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc, checkDiagonal);
        //     }
        //     /* black pawn can move 1 space up */
        //     else
        //     {
        //         checkDiagonal = false;
        //         checkAndAddPossibleMove(b, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc, checkDiagonal);
        //     }

        //     /* possible to have 2 pieces that black pawn can take */
        //     if (currLocXCoor > 0 && currLocXCoor < 7)
        //     {
        //         checkDiagonal = true;
        //         /* get the piece in the left diagonal location of the current pawn, if any */
        //         leftDiagonalLocXCoor = currLocXCoor + 1;
        //         leftDiagonalLocYCoor = currLocYCoor - 1;
        //         // System.out.println("L x: " + leftDiagonalLocXCoor);
        //         // System.out.println("L y: " + leftDiagonalLocYCoor);
        //         checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
                
        //         /* get the piece in the right diagonal locationof the current pawn, if any */
        //         rightDiagonalLocXCoor = currLocXCoor - 1;
        //         rightDiagonalLocYCoor = currLocYCoor - 1;
        //         // System.out.println("R x: " + rightDiagonalLocXCoor);
        //         // System.out.println("R y: " + rightDiagonalLocYCoor);
        //         checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
        //     }
        //     /* black pawn can only take the piece located at the diagonal right square (from black team POV) */
        //     else if (currLocXCoor == 0)
        //     {
        //         checkDiagonal = true;
        //         rightDiagonalLocXCoor = currLocXCoor - 1;
        //         rightDiagonalLocYCoor = currLocYCoor - 1;
        //         checkAndAddPossibleMove(b, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
        //     }
        //     /* black pawn can only take the piece located at the diagonal left square (from black team POV) */
        //     else
        //     {
        //         checkDiagonal = true;
        //         leftDiagonalLocXCoor = currLocXCoor + 1;
        //         leftDiagonalLocYCoor = currLocYCoor - 1;
        //         checkAndAddPossibleMove(b, leftDiagonalLocXCoor, leftDiagonalLocYCoor, possibleMoveLoc, checkDiagonal);
        //     }
        // }
        return possibleMoveLoc;
    }

    private void checkAndAddPossibleMove(Board b, int locX, int locY, List<String> possibleMoves, boolean checkDiagonal)
    {
        int[] locStrOfPotentialOpponent = new int[] {locX, locY};
        String potentialOpponentLocStr = b.arrIndicesToLocStr(locStrOfPotentialOpponent);
        Piece potentialOpponentPiece = b.getPiece(potentialOpponentLocStr);

        /* ensure that a peice at a given location is an opponent piece */
        if (potentialOpponentPiece != null && potentialOpponentPiece.color() != color())
        {
            possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        }

        // /* checking for opponent piece in the diagonal */
        // if (!checkDiagonal)
        // {
        //     if (potentialOpponentPiece == null)
        //     {
        //         // System.out.println("added 3: " + potentialOpponentLocStr);
        //         possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        //     }

        // }
    }
}