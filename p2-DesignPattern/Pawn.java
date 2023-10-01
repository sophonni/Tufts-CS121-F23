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
                /* handle the ability for white pawn to move two space up */
                if (currLocYCoor == 1)
                {
                    // int[] forward1LocStr = new int[] {forward1LocXCoor, forward1LocYCoor};
                    // pieceAtForward1LocStr = b.arrIndicesToLocStr(forward1LocStr);
                    // //System.out.println("Forward 1: " + pieceAtForward1LocStr);
                    // pieceAtForward1Loc = b.getPiece(pieceAtForward1LocStr);
                    // if (pieceAtForward1Loc == null)
                    // {
                    //     possibleMoveLoc.add(pieceAtForward1LocStr);
                    // }
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                    
                    // int[] forward2LocStr = new int[] {forward2LocXCoor, forward2LocYCoor};
                    // pieceAtForward2LocStr = b.arrIndicesToLocStr(forward2LocStr);
                    // //System.out.println("Forward 2: " + pieceAtForward2LocStr);
                    // pieceAtForward2Loc = b.getPiece(pieceAtForward2LocStr);
                    // if (pieceAtForward2Loc == null)
                    // {
                    //     possibleMoveLoc.add(pieceAtForward2LocStr);
                    // }
                    checkAndAddPossibleMove(b, currPawn, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc);

                }
                else
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                }

                /* if the current pawn is not in first column and last column, there a
                possibility that there are 2 opponents the current pawn can take */
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

                /* if the current pawn is in first column, there a chance
                that there is 1 opponent (right diagonal) it can take */
                else if (currLocXCoor == 0)
                {
                    rightDiagonalLocXCoor = currLocXCoor + 1;
                    rightDiagonalLocYCoor = currLocYCoor + 1;
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                }

                /* if the current pawn is in las† column, there a chance
                that there is 1 opponent (left diagonal) it can take */
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
                if (currLocYCoor == 6)
                {
                    // int[] forward1LocStr = new int[] {forward1LocXCoor, forward1LocYCoor};
                    // pieceAtForward1LocStr = b.arrIndicesToLocStr(forward1LocStr);
                    // //System.out.println("Forward 1: " + pieceAtForward1LocStr);
                    // pieceAtForward1Loc = b.getPiece(pieceAtForward1LocStr);
                    // if (pieceAtForward1Loc == null)
                    // {
                    //     possibleMoveLoc.add(pieceAtForward1LocStr);
                    // }
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);

                    
                    // int[] forward2LocStr = new int[] {forward2LocXCoor, forward2LocYCoor};
                    // pieceAtForward2LocStr = b.arrIndicesToLocStr(forward2LocStr);
                    // //System.out.println("Forward 2: " + pieceAtForward2LocStr);
                    // pieceAtForward2Loc = b.getPiece(pieceAtForward2LocStr);
                    // if (pieceAtForward2Loc == null)
                    // {
                    //     possibleMoveLoc.add(pieceAtForward2LocStr);
                    // }
                    checkAndAddPossibleMove(b, currPawn, forward2LocXCoor, forward2LocYCoor, possibleMoveLoc);

                }
                else
                {
                    checkAndAddPossibleMove(b, currPawn, forward1LocXCoor, forward1LocYCoor, possibleMoveLoc);
                }

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
                    //assert rightDiagonalOpponate != null;

                }

                /* if the current pawn is in first column, there a chance
                that there is 1 opponent (right diagonal) it can take */
                else if (currLocXCoor == 0)
                {
                    rightDiagonalLocXCoor = currLocXCoor - 1;
                    rightDiagonalLocYCoor = currLocYCoor - 1;
                    checkAndAddPossibleMove(b, currPawn, rightDiagonalLocXCoor, rightDiagonalLocYCoor, possibleMoveLoc);
                }

                /* if the current pawn is in las† column, there a chance
                that there is 1 opponent (left diagonal) it can take */
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
        if (potentialOpponentPiece != null && potentialOpponentPiece.color() != p.color())
        {
            possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        }
        else if (potentialOpponentPiece == null)
        {
            possibleMoves.add(b.arrIndicesToLocStr(locStrOfPotentialOpponent));
        }
    }

    // private Piece blackPawnRightDiagonalOpponent(Board b, Piece blackPawn)
    // {
    //     int[] indicesLocOfPawn = b.locStrToArrIndices(blackPawn.toString());
    //     int currLocX = indicesLocOfPawn[0];
    //     int currLocY = indicesLocOfPawn[1];

    //     int rightDiagonalLocX = currLocX - 1;
    //     int rightDiagonalLocY = currLocY + 1;
    //     int[] potentialLeftDiagonalOpponentStrLoc = new int[] {rightDiagonalLocX, rightDiagonalLocY};
    //     String diagonalLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
    //     return b.getPiece(diagonalLocStr);
    // }

    // private Piece whitePawnRightDiagonalOpponent(Board b, Piece whitePawn)
    // {
    //     int[] indicesLocOfPawn = b.locStrToArrIndices(whitePawn.toString());
    //     int currLocX = indicesLocOfPawn[0];
    //     int currLocY = indicesLocOfPawn[1];

    //     int rightDiagonalLocX = currLocX + 1;
    //     int rightDiagonalLocY = currLocY + 1;
    //     int[] potentialLeftDiagonalOpponentStrLoc = new int[] {rightDiagonalLocX, rightDiagonalLocY};
    //     String diagonalLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
    //     return b.getPiece(diagonalLocStr);
    // }

    // private Piece blackPawnLeftDiagonalOpponent(Board b, Piece blackPawn)
    // {
    //     int[] indicesLocOfPawn = b.locStrToArrIndices(blackPawn.toString());
    //     int currLocX = indicesLocOfPawn[0];
    //     int currLocY = indicesLocOfPawn[1];

    //     int leftDiagonalLocX = currLocX - 1;
    //     int leftDiagonalLocY = currLocY - 1;
    //     int[] potentialLeftDiagonalOpponentStrLoc = new int[] {leftDiagonalLocX, leftDiagonalLocY};
    //     String diagonalLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
    //     return b.getPiece(diagonalLocStr);
    // }

    // private Piece whitePawnLeftDiagonalOpponent(Board b, Piece whitePawn)
    // {
    //     int[] indicesLocOfPawn = b.locStrToArrIndices(whitePawn.toString());
    //     int currLocX = indicesLocOfPawn[0];
    //     int currLocY = indicesLocOfPawn[1];

    //     int leftDiagonalLocX = currLocX - 1;
    //     int leftDiagonalLocY = currLocY + 1;
    //     int[] potentialLeftDiagonalOpponentStrLoc = new int[] {leftDiagonalLocX, leftDiagonalLocY};
    //     String diagonalLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
    //     return b.getPiece(diagonalLocStr);
    // }
}