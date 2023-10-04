import java.util.*;

public class Bishop extends Piece {
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

    /**
    * Generates a list of possible valid moves for a bishop piece at a given location on the chessboard.
    *
    * @param b   The chessboard.
    * @param loc The location string (e.g., "e4") of the bishop.
    * @return A list of valid move locations for the bishop.
    **/
    public List<String> moves(Board b, String loc) {
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


    /**
    * Calculates possible forward moves for the Bishop.
    *
    * @param b                 The chessboard.
    * @param isMoveForwardRight Whether the move is forward and to the right.
    * @param xLoc              The current x-coordinate of the Bishop.
    * @param yLoc              The current y-coordinate of the Bishop.
    * @param xConstraint       The x-coordinate constraint (0 for forward right, 7 for forward left).
    * @param yConstraint       The y-coordinate constraint (7 for white, 0 for black).
    * @param possibleMoveList  A list to store possible move locations.
    **/
    public void forwardMove(Board b, boolean isMoveForwardRight, int xLoc, int yLoc, int xConstraint, int yConstraint, List<String> possibleMoveList) {
        Piece currBackDiagonalPiece;
        int startXLoc;
        boolean isTherePieceBlockage = false;

        startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, xLoc);

        /* iterate through possible forward moves */
        for (int startYLoc = (color() == Color.WHITE) ? yLoc + 1 : yLoc - 1;
            ((color() == Color.WHITE) ? startYLoc <= yConstraint : startYLoc >= yConstraint);
            startYLoc += (color() == Color.WHITE) ? 1 : -1) {
            /* ensure staying within the 8x8 2D array during the iteration */
            if ((color() == Color.WHITE) && startXLoc <= xConstraint) {
                if (startXLoc >= 0) {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                } else {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }
                
                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);
                
                if (isTherePieceBlockage) {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, startXLoc);
            } else if ((color() == Color.BLACK) && startXLoc <= xConstraint) {
                if (startXLoc >= 0) {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                } else {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }
                
                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);
                
                if (isTherePieceBlockage) {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveForwardRight, startXLoc);
            } else {
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

    /**
    * Calculates possible backward moves for the Bishop.
    *
    * @param b                   The chessboard.
    * @param isMoveBackwardRight Whether the move is backward and to the right.
    * @param xLoc                The current x-coordinate of the Bishop.
    * @param yLoc                The current y-coordinate of the Bishop.
    * @param xConstraint         The x-coordinate constraint (0 for backward right, 7 for backward left).
    * @param yConstraint         The y-coordinate constraint (7 for white, 0 for black).
    * @param possibleMoveList    A list to store possible move locations.
    **/
    public void backwardMove(Board b, boolean isMoveBackwardRight, int xLoc, int yLoc, int xConstraint, int yConstraint, List<String> possibleMoveList) {
        Piece currBackDiagonalPiece;
        int startXLoc;
        boolean isTherePieceBlockage = false;
        startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, xLoc);

        /* iterate through possible backward moves */
        for (int startYLoc = (color() == Color.WHITE) ? yLoc - 1 : yLoc + 1;
            ((color() == Color.WHITE) ? startYLoc >= yConstraint : startYLoc <= yConstraint);
            startYLoc += (color() == Color.WHITE) ? -1 : 1) {
            /* ensure staying within the 8x8 2D array during the iteration */
            if (color() == Color.WHITE && startXLoc <= xConstraint) {
                if (startXLoc >= 0) {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                } else {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }

                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);

                if (isTherePieceBlockage) {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, startXLoc);
            } else if ((color() == Color.BLACK) && startXLoc <= xConstraint) {
                if (startXLoc >= 0) {
                    currBackDiagonalPiece = getPieceViaArrIndices(b, startXLoc, startYLoc);
                } else {
                    /* Bishop moved out of the board, exit the loop */
                    break;
                }

                isTherePieceBlockage = Rook.isTherePieceBlockage(b, pieceLocStr, color(), startXLoc, startYLoc, possibleMoveList);

                if (isTherePieceBlockage) {
                    /* Bishop's path is blocked, exit the loop */
                    break;
                }
                startXLoc = computeXCoorBasedOnColorAndDirection(color(), isMoveBackwardRight, startXLoc);
            } else {
                /* Bishop reached the edge of the board, exit the loop */
                break;
            }
        }
    }


    /**
    * Retrieves the chess piece at a specific location on the chessboard using array indices.
    *
    * @param b    The chessboard.
    * @param xLoc The x-coordinate (array index) of the desired location.
    * @param yLoc The y-coordinate (array index) of the desired location.
    * @return The chess piece at the specified location or null if the location is empty.
    **/
    private Piece getPieceViaArrIndices(Board b, int xLoc, int yLoc) {
        /* convert 2D array indices to a location string */
        int[] locStrInArrIndices = new int[] {xLoc, yLoc};
        // System.out.println("x: " + xLoc + " y: " + yLoc);
        String wantedPieceLocStr = b.arrIndicesToLocStr(locStrInArrIndices);

        /* return the piece (or null) at the location string */
        return b.getPiece(wantedPieceLocStr);
    }
}