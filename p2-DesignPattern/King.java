import java.util.*;

public class King extends Piece {
    public King(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String kingName= (color == Color.BLACK) ? "b" : "w";
        return kingName + "k";
    }

    public List<String> moves(Board b, String loc)
    {

        List<String> possibleMoves = new ArrayList<String>();
        int[] arrIndicesOfCurrKing = b.locStrToArrIndices(loc);

        if (color() == Color.WHITE)
        {
            checkDownDiagonal(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, 0, possibleMoves);
            checkDownDiagonal(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, 0, possibleMoves);

            checkUpDiagonal(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, 7, possibleMoves);
            checkUpDiagonal(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, 7, possibleMoves);

            checkLeftRight(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, possibleMoves);
            checkLeftRight(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, possibleMoves);

            checkUpDown(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, possibleMoves);
            checkUpDown(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, possibleMoves);
        }
        else
        {
            checkDownDiagonal(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, 7, possibleMoves);
            checkDownDiagonal(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, 7, possibleMoves);

            checkUpDiagonal(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, 0, possibleMoves);
            checkUpDiagonal(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, 0, possibleMoves);

            checkLeftRight(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, possibleMoves);
            checkLeftRight(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, possibleMoves);

            checkUpDown(b, true, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 0, possibleMoves);
            checkUpDown(b, false, pieceLocStr, color(), arrIndicesOfCurrKing[0], arrIndicesOfCurrKing[1], 7, possibleMoves);
        }
        /*
        WHITE KING
            check front (x, y+1)                        x-constraint = 7    y-constraint = 7
            check back (x, y-1)                         x-constraint = 7    y-constraint = 0
            check left (x-1, y)                         x-constraint = 0    y-constraint = 7
            check right (x+1, y)                        x-constraint = 7    y-constraint = 7

            check forward left diagonal(x-1, y+1)       x-constraint = 0    y-constraint = 7
            check forward right diagonal(x+1, y+1)      x-constraint = 7    y-constraint = 7
            check backward left diagonal(x-1, y-1)      x-constraint = 0    y-constraint = 0
            check backward right diagonal(x+1, y-1)     x-constraint = 7    y-constraint = 0
        BLACK KING
            check front (x, y-1)                        x-constraint = 7    y-constraint = 0
            check back (x, y+1)                         x-constraint = 7    y-constraint = 7
            check left (x+1, y)                         x-constraint = 7    y-constraint = 0
            check right (x-1, y)                        x-constraint = 0    y-constraint = 0

            check forward left diagonal(x+1, y-1)       x-constraint = 7    y-constraint = 0
            check forward right diagonal(x-1, y-1)      x-constraint = 0    y-constraint = 0
            check backward left diagonal(x+1, y+1)      x-constraint = 7    y-constraint = 7
            check backward right diagonal(x-1, y+1)     x-constraint = 0    y-constraint = 7
        */
        return possibleMoves;
    }

    private void checkUpDiagonal(Board b, boolean isLeft, String thisPieceLocStr, Color thisPieceColor, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int xConstraint, int yConstraint, List<String> possibleMoves)
    {
        int diagonalLocX;
        int diagonalLocY;
        

        if (color() == Color.WHITE)
        {
            if (isLeft)
            {
                diagonalLocX = locXToCheckForPossibleMoves - 1;
                diagonalLocY = locYToCheckForPossibleMoves + 1;

                /* upper left diagonal */
                if (diagonalLocX >= xConstraint && diagonalLocY <= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
            else
            {
                diagonalLocX = locXToCheckForPossibleMoves + 1;
                diagonalLocY = locYToCheckForPossibleMoves + 1;
                
                /* upper right diagonal */
                if (diagonalLocX <= xConstraint && diagonalLocY <= yConstraint)
                {
                Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
        }
        else
        {
            if(isLeft)
            {
                diagonalLocX = locXToCheckForPossibleMoves + 1;
                diagonalLocY = locYToCheckForPossibleMoves - 1;
             
                /* upper left diagonal */
                if (diagonalLocX <= xConstraint && diagonalLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
            else
            {
                diagonalLocX = locXToCheckForPossibleMoves - 1;
                diagonalLocY = locYToCheckForPossibleMoves - 1;
             
                /* upper right diagonal */
                if (diagonalLocX >= xConstraint && diagonalLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
        }
    }
    private void checkDownDiagonal(Board b, boolean isLeft, String thisPieceLocStr, Color thisPieceColor, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int xConstraint, int yConstraint, List<String> possibleMoves)
    {
        int diagonalLocX;
        int diagonalLocY;

        if (color() == Color.WHITE)
        {
            if (isLeft)
            {
                diagonalLocX = locXToCheckForPossibleMoves - 1;
                diagonalLocY = locYToCheckForPossibleMoves - 1;

                /* down left diagonal */
                if (diagonalLocX >= xConstraint && diagonalLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
            else
            {
                diagonalLocX = locXToCheckForPossibleMoves + 1;
                diagonalLocY = locYToCheckForPossibleMoves - 1;
                
                /* down right diagonal */
                if (diagonalLocX <= xConstraint && diagonalLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
        }
        else
        {
            if(isLeft)
            {
                diagonalLocX = locXToCheckForPossibleMoves + 1;
                diagonalLocY = locYToCheckForPossibleMoves + 1;
             
                /* down left diagonal */
                if (diagonalLocX <= xConstraint && diagonalLocY <= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
            else
            {
                diagonalLocX = locXToCheckForPossibleMoves - 1;
                diagonalLocY = locYToCheckForPossibleMoves + 1;
             
                /* down right diagonal */
                if (diagonalLocX >= xConstraint && diagonalLocY <= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
                }
            }
        }
    }

    private void checkLeftRight(Board b, boolean isLeft, String thisPieceLocStr, Color thisPieceColor, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int xConstraint, List<String> possibleMoves)
    {
        int leftOrRightLocX;

        if (color() == Color.WHITE)
        {
            if (isLeft)
            {
                leftOrRightLocX = locXToCheckForPossibleMoves - 1;

                /* left horizontal */
                if (leftOrRightLocX >= xConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, leftOrRightLocX, locYToCheckForPossibleMoves, possibleMoves);
                }
            }
            else
            {
                leftOrRightLocX = locXToCheckForPossibleMoves + 1;
                
                /* right horizontal */
                if (leftOrRightLocX <= xConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, leftOrRightLocX, locYToCheckForPossibleMoves, possibleMoves);
                }
            }
        }
        else
        {
            if (isLeft)
            {
                leftOrRightLocX = locXToCheckForPossibleMoves + 1;

                /* left horizontal */
                if (leftOrRightLocX <= xConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, leftOrRightLocX, locYToCheckForPossibleMoves, possibleMoves);
                }
            }
            else
            {
                leftOrRightLocX = locXToCheckForPossibleMoves - 1;
                
                /* right horizontal */
                if (leftOrRightLocX >= xConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, leftOrRightLocX, locYToCheckForPossibleMoves, possibleMoves);
                }
            }
        }
    }

    private void checkUpDown(Board b, boolean isUp, String thisPieceLocStr, Color thisPieceColor, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, int yConstraint, List<String> possibleMoves)
    {
        int upOrDownLocY;

        if (color() == Color.WHITE)
        {
            if (isUp)
            {
                upOrDownLocY = locYToCheckForPossibleMoves + 1;

                /* up vertical */
                if (upOrDownLocY <= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, upOrDownLocY, possibleMoves);
                }
            }
            else
            {
                upOrDownLocY = locYToCheckForPossibleMoves - 1;
                
                /* down vertical */
                if (upOrDownLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, upOrDownLocY, possibleMoves);
                }
            }
        }
        else
        {
            if (isUp)
            {
                upOrDownLocY = locYToCheckForPossibleMoves - 1;

                /* up vertical */
                if (upOrDownLocY >= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, upOrDownLocY, possibleMoves);
                }
            }
            else
            {
                upOrDownLocY = locYToCheckForPossibleMoves + 1;
                
                /* down vertical */
                if (upOrDownLocY <= yConstraint)
                {
                    Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, locXToCheckForPossibleMoves, upOrDownLocY, possibleMoves);
                }
            }
        }
    }
        // {
        //     if(isUpLeft)
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves - 1;
        //         diagonalLocY = locYToCheckForPossibleMoves + 1;
                
        //         /* upper left diagonal */
        //         if (diagonalLocX >= xConstraint && diagonalLocY <= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }
        //     else
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves + 1;
        //         diagonalLocY = locYToCheckForPossibleMoves + 1;
                
        //         /* upper right diagonal */
        //         if (diagonalLocX <= xConstraint && diagonalLocY <= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }

        //     if(isDownLeft)
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves - 1;
        //         diagonalLocY = locYToCheckForPossibleMoves - 1;
                
        //         /* down left diagonal */
        //         if (diagonalLocX >= xConstraint && diagonalLocY >= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }

        //     }
        //     else
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves + 1;
        //         diagonalLocY = locYToCheckForPossibleMoves - 1;
                
        //         /* down right diagonal */
        //         if (diagonalLocX <= xConstraint && diagonalLocY >= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }
        // }
        // else
        // {
        //     if(isUpLeft)
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves + 1;
        //         diagonalLocY = locYToCheckForPossibleMoves - 1;
                
        //         /* upper left diagonal */
        //         if (diagonalLocX <= xConstraint && diagonalLocY >= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }
        //     else
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves - 1;
        //         diagonalLocY = locYToCheckForPossibleMoves - 1;
                
        //         /* upper right diagonal */
        //         if (diagonalLocX >= xConstraint && diagonalLocY >= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }

        //     if(isDownLeft)
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves + 1;
        //         diagonalLocY = locYToCheckForPossibleMoves + 1;
                
        //         /* down left diagonal */
        //         if (diagonalLocX <= xConstraint && diagonalLocY <= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }

        //     }
        //     else
        //     {
        //         diagonalLocX = locXToCheckForPossibleMoves - 1;
        //         diagonalLocY = locYToCheckForPossibleMoves + 1;
                
        //         /* down right diagonal */
        //         if (diagonalLocX >= xConstraint && diagonalLocY <= yConstraint)
        //         {
        //             Rook.isTherePieceBlockage(b, thisPieceLocStr, thisPieceColor, diagonalLocX, diagonalLocY, possibleMoves);
        //         }
        //     }
        // }

}