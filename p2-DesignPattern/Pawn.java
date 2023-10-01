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

        int leftDiagonalLocXCoor;
        int leftDiagonalLocYCoor;
        int rightDiagonalLocXCoor;
        int rightDiagonalLocYCoor;

        int forward1LocXCoor = currLocXCoor;
        int forward1LocYCoor = currLocYCoor + 1;
        int forward2LocXCoor = currLocXCoor;
        int forward2LocYCoor = currLocYCoor + 2;

        Piece currPawn = b.getPiece(loc);

        Piece pieceAtForward1Loc = null;
        Piece pieceAtForward2Loc = null;

        Piece leftDiagonalOpponate = null;
        Piece rightDiagonalOpponate = null;

        String pieceAtForward1LocStr = null;
        String pieceAtForward2LocStr = null;

        String leftDiagonalPieceLocStr = null;
        String rightDiagonalPieceLocStr = null;

        if (currPawn != null)
        {
    
            if (currPawn.color() == Color.WHITE)
            {
                /* handle the ability for white pawn to move two space up */
                if (currLocYCoor == 1)
                {
                    int[] forward1LocStr = new int[] {forward1LocXCoor, forward1LocYCoor};

                    pieceAtForward1LocStr = b.arrIndicesToLocStr(forward1LocStr);
                    //System.out.println("Forward 1: " + pieceAtForward1LocStr);
                    pieceAtForward1Loc = b.getPiece(pieceAtForward1LocStr);
                    if (pieceAtForward1Loc == null)
                    {
                        possibleMoveLoc.add(pieceAtForward1LocStr);
                    }

                    int[] forward2LocStr = new int[] {forward2LocXCoor, forward2LocYCoor};
                    pieceAtForward2LocStr = b.arrIndicesToLocStr(forward2LocStr);
                    //System.out.println("Forward 2: " + pieceAtForward2LocStr);
                    pieceAtForward2Loc = b.getPiece(pieceAtForward2LocStr);
                    if (pieceAtForward2Loc == null)
                    {
                        possibleMoveLoc.add(pieceAtForward1LocStr);
                    }
                }
                else
                {
                    int[] forward1LocStr = new int[] {forward1LocXCoor, forward1LocYCoor};
                    pieceAtForward1LocStr = b.arrIndicesToLocStr(forward1LocStr);
                    pieceAtForward1Loc = b.getPiece(pieceAtForward1LocStr);
                    if (pieceAtForward1Loc == null)
                    {
                        possibleMoveLoc.add(pieceAtForward1LocStr);
                    }
                }

                /* if the current pawn is not in first column and last column, there a
                possibility that there are 2 opponents the current pawn can take */
                if (currLocXCoor > 0 && currLocXCoor < 7)
                {
                    /* get the piece in the left diagonal location of the current pawn, if any */
                    leftDiagonalLocXCoor = currLocXCoor - 1;

                    leftDiagonalLocYCoor = currLocYCoor + 1;

                    int[] potentialLeftDiagonalOpponentStrLoc = new int[] {leftDiagonalLocXCoor, leftDiagonalLocYCoor};
                    leftDiagonalPieceLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
                    //System.out.println("Left Diagonal: " + leftDiagonalPieceLocStr);
                    leftDiagonalOpponate = b.getPiece(leftDiagonalPieceLocStr);
                    
                    /* get the piece in the right diagonal locationof the current pawn, if any */
                    rightDiagonalLocXCoor = currLocXCoor + 1;

                    rightDiagonalLocYCoor = currLocYCoor + 1;

                    int[] potentialRightDiagonalOpponentStrLoc = new int[] {rightDiagonalLocXCoor, rightDiagonalLocYCoor};
                    rightDiagonalPieceLocStr = b.arrIndicesToLocStr(potentialRightDiagonalOpponentStrLoc);
                    //System.out.println("Right Diagonal: " + rightDiagonalPieceLocStr);

                    rightDiagonalOpponate = b.getPiece(rightDiagonalPieceLocStr);
                    //assert rightDiagonalOpponate != null;

                }

                /* if the current pawn is in first column, there a chance
                that there is 1 opponent (right diagonal) it can take */
                else if (currLocXCoor == 0)
                {
                    rightDiagonalLocXCoor = currLocXCoor;
                    
                    rightDiagonalLocYCoor = currLocYCoor;
                    int[] potentialRightDiagonalOpponentStrLoc = new int[] {rightDiagonalLocXCoor, rightDiagonalLocYCoor};
                    rightDiagonalPieceLocStr = b.arrIndicesToLocStr(potentialRightDiagonalOpponentStrLoc);
                    rightDiagonalOpponate = b.getPiece(rightDiagonalPieceLocStr);                
                }

                /* if the current pawn is in las† column, there a chance
                that there is 1 opponent (left diagonal) it can take */
                else
                {
                    leftDiagonalLocXCoor = currLocXCoor;
                    leftDiagonalLocYCoor = currLocYCoor;
                    int[] potentialLeftDiagonalOpponentStrLoc = new int[] {leftDiagonalLocXCoor, leftDiagonalLocYCoor};
                    leftDiagonalPieceLocStr = b.arrIndicesToLocStr(potentialLeftDiagonalOpponentStrLoc);
                    leftDiagonalOpponate = b.getPiece(leftDiagonalPieceLocStr);
                }
                if (leftDiagonalOpponate != null)
                {
                    //System.out.println("Has left opponent");
                    boolean isLeftDiagonalAnOpponent = b.areTwoPieceOnTheSameTeam(currPawn, leftDiagonalOpponate);
                    if (isLeftDiagonalAnOpponent)
                    {
                        possibleMoveLoc.add(leftDiagonalPieceLocStr);
                    }
                }

                if (rightDiagonalOpponate != null)
                {
                    //System.out.println("Has right opponent");
                    boolean isRightDiagonalAnOpponent = b.areTwoPieceOnTheSameTeam(currPawn, leftDiagonalOpponate);
                    if (isRightDiagonalAnOpponent)
                    {
                        possibleMoveLoc.add(rightDiagonalPieceLocStr);
                    }
                }
            }
        }
        return possibleMoveLoc;
    }
}