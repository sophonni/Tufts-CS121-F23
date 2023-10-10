import java.util.*;

public class Bishop extends Piece
{
    public Bishop(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String bishopName= (color == Color.BLACK) ? "b" : "w";
        return bishopName + "b";
    }

    public List<String> moves(Board b, String loc)
    {
        List<String> possibleMoveLoc = new ArrayList<>();

        int[] arrIndicesOfCurrBishop = b.locStrToArrIndices(loc);

        /* calculate possible moves for the bishop in various directions */

        /* diagonally move backward left for white bishop */
        backwardMove(b, false, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 7, possibleMoveLoc);

        /* diagonally move backward right for white bishop */
        backwardMove(b, true, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 7, possibleMoveLoc);

        /* diagonally move backward left for black bishop */
        backwardMove(b, false, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 0, possibleMoveLoc);

        /* diagonally move backward right for black bishop */
        backwardMove(b, true, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 0, possibleMoveLoc);

        /************************************************************************************************************************************************ */

        /* diagonally move forward left for white bishop */
        forwardMove(b, false, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 7, possibleMoveLoc);

        /* diagonally move forward right for white bishop */
        forwardMove(b, true, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 7, possibleMoveLoc);

        /* diagonally move forward left for black bishop */
        forwardMove(b, false, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 0, possibleMoveLoc);

        /* diagonally move forward right for black bishop */
        forwardMove(b, true, arrIndicesOfCurrBishop[0], arrIndicesOfCurrBishop[1], 7, 0, possibleMoveLoc);

        /* return the list of valid move locations for the bishop */
        return possibleMoveLoc;
    }

    public void forwardMove(Board b, boolean isMoveForwardRight, int xLoc, int yLoc, int xConstraint, int yConstraint, List<String> possibleMoveList)
    {
        Piece currBackDiagonalPiece;
        int startXLoc;
        boolean isTherePieceBlockage = false;

        startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, xLoc);

        /* iterate through possible forward moves */
        for (int startYLoc = (color() == Color.WHITE) ? yLoc + 1 : yLoc - 1;
            ((color() == Color.WHITE) ? startYLoc <= yConstraint : startYLoc >= yConstraint);
            startYLoc += (color() == Color.WHITE) ? 1 : -1)
        {
            /* ensure staying within the 8x8 2D array during the iteration */
            if ((color() == Color.WHITE) && startXLoc <= xConstraint)
            {
                if (startXLoc >= 0)
                {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                }
                else
                {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }
                
                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);
                
                if (isTherePieceBlockage)
                {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, startXLoc);
            }
            else if ((color() == Color.BLACK) && startXLoc <= xConstraint)
            {
                if (startXLoc >= 0)
                {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                }
                else
                {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }
                
                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);
                
                if (isTherePieceBlockage)
                {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, startXLoc);
            }
            else
            {
                /* Bishop reached the edge of the board, exit the loop */
                break;
            }
        }
    }


    private int computeXCoorBasedOnColorAndDirection(Color c, boolean isMoveRightDiagonal, int xLoc)
    {
        int startXLoc;
        if (c == Color.WHITE)
        {
            if (isMoveRightDiagonal)
            {
                startXLoc = xLoc + 1;
            }
            else
            {
                startXLoc = xLoc - 1;
            }
        }
        else
        {
            if (isMoveRightDiagonal)
            {
                startXLoc = xLoc - 1;
            }
            else
            {
                startXLoc = xLoc + 1;
            }
        }
        return startXLoc;
    }

    public void backwardMove(Board b, boolean isMoveBackwardRight, int xLoc, int yLoc, int xConstraint, int yConstraint, List<String> possibleMoveList)
    {
        Piece currBackDiagonalPiece;
        int startXLoc;
        boolean isTherePieceBlockage = false;
        startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, xLoc);

        /* iterate through possible backward moves */
        for (int startYLoc = (color() == Color.WHITE) ? yLoc - 1 : yLoc + 1;
            ((color() == Color.WHITE) ? startYLoc >= yConstraint : startYLoc <= yConstraint);
            startYLoc += (color() == Color.WHITE) ? -1 : 1)
            {
            /* ensure staying within the 8x8 2D array during the iteration */
            if (color() == Color.WHITE && startXLoc <= xConstraint)
            {
                if (startXLoc >= 0)
                {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                }
                else
                {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }

                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);

                if (isTherePieceBlockage)
                {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, startXLoc);
            }
            else if ((color() == Color.BLACK) && startXLoc <= xConstraint)
            {
                if (startXLoc >= 0)
                {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                }
                else
                {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }

                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);

                if (isTherePieceBlockage)
                {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, startXLoc);
            }
            else
            {
                /* Bishop reached the edge of the board, exit the loop */
                break;
            }
        }
    }

    private Piece getPieceViaArrIndices(Board b, int xLoc, int yLoc)
    {
        /* convert 2D array indices to a location string */
        int[] locStrInArrIndices = new int[] {xLoc, yLoc};
        // System.out.println("x: " + xLoc + " y: " + yLoc);
        String wantedPieceLocStr = b.arrIndicesToLocStr(locStrInArrIndices);

        /* return the piece (or null) at the location string */
        return b.getPiece(wantedPieceLocStr);
    }
}