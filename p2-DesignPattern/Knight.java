import java.util.*;

public class Knight extends Piece {
    public Knight(Color c)
    {
        super.color = c;
    }
    // implement appropriate methods

    public String toString()
    {
        Color color = color();
        String pawnName= (color == Color.BLACK) ? "b" : "w";
        return pawnName + "n";
    }

    public List<String> moves(Board b, String loc) {
        // String errorMessage;
        int[] arrIndicesOfCurrKnight = b.locStrToArrIndices(loc);
        List<String> possibleMoves = new ArrayList<>();
    
        /* WHITE KNIGHT */
        if (color() == Color.WHITE)
        {
            /* move up 2 squares, right 1 square */
            move2UpDownAnd1LeftRight(b, true, true, 7, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
            
            /* move up 2 squares, left 1 square */
            move2UpDownAnd1LeftRight(b, true, false, 0, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
    
            /* move down 2 squares, right 1 square */
            move2UpDownAnd1LeftRight(b, false, true, 7, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
            
            /* move down 2 squares, left 1 square */
            move2UpDownAnd1LeftRight(b, false, false, 0, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
    
            /* move right 2 squares, up 1 square */
            move2LeftRightAnd1UpDown(b, false, false, 7, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
            
            /* move right 2 squares, down 1 square */
            move2LeftRightAnd1UpDown(b, false, true, 7, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
    
            /* move left 2 squares, down 1 square */
            move2LeftRightAnd1UpDown(b, true, true, 0, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
            
            /* move left 2 squares, up 1 square */
            move2LeftRightAnd1UpDown(b, true, false, 0, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
        }
        else
        {
            /* BLACK KNIGHT */
             /* move up 2 squares, right 1 square */
             move2UpDownAnd1LeftRight(b, true, true, 0, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
            
             /* move up 2 squares, left 1 square */
             move2UpDownAnd1LeftRight(b, true, false, 7, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
     
             /* move down 2 squares, right 1 square */
             move2UpDownAnd1LeftRight(b, false, true, 0, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
             
             /* move down 2 squares, left 1 square */
             move2UpDownAnd1LeftRight(b, false, false, 7, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
     
             /* move right 2 squares, up 1 square */
             move2LeftRightAnd1UpDown(b, false, false, 0, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
             
             /* move right 2 squares, down 1 square */
             move2LeftRightAnd1UpDown(b, false, true, 0, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
     
             /* move left 2 squares, down 1 square */
             move2LeftRightAnd1UpDown(b, true, true, 7, 7, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
             
             /* move left 2 squares, up 1 square */
             move2LeftRightAnd1UpDown(b, true, false, 7, 0, arrIndicesOfCurrKnight[0], arrIndicesOfCurrKnight[1], possibleMoves);
        }

        
        return possibleMoves;
    }
    /*
    For both White
        - check up 2, right 1
            - x constraints: x + 1 && x = 7
            - y constraints:  y + 2 and y = 7
        - check up 2, left 1
            - x constraints: x - 1 && x = 0
            - y constraints:  y + 2 and y = 7

        - check down 2, right 1
            - x constraints: x + 1 && x = 7
            - y constraints:  y - 2 and y = 0
        - check down 2, left 1
            - x constraints: x - 1 && x = 0
            - y constraints:  y - 2 and y = 0

        - check left 2, up 1
            - x constraints: x - 2 && x = 0
            - y constraints:  y + 1 and y = 7
        - check left 2, down 1
            - x constraints: x - 2 && x = 0
            - y constraints:  y - 1 and y = 0

        - check right 2, up 1
            - x constraints: x + 2 && x = 7
            - y constraints:  y + 1 and y = 7
        - check right 2, down 1
            - x constraints: x + 2 && x = 7
            - y constraints:  y - 1 and y = 0

    For both Black
        - check up 2, right 1
            - x constraints: x + 1 && x = 7
            - y constraints:  y - 2 and y = 0
        - check up 2, left 1
            - x constraints: x - 1 && x = 0
            - y constraints:  y - 2 and y = 0

        - check down 2, right 1
            - x constraints: x + 1 && x = 7
            - y constraints:  y + 2 and y = 7
        - check down 2, left 1
            - x constraints: x - 1 && x = 0
            - y constraints:  y + 2 and y = 7

        - check left 2, up 1
            - x constraints: x - 2 && x = 0
            - y constraints:  y - 1 and y = 0
        - check left 2, down 1
            - x constraints: x - 2 && x = 0
            - y constraints:  y + 1 and y = 7

        - check right 2, up 1
            - x constraints: x + 2 && x = 7
            - y constraints:  y - 1 and y = 0
        - check right 2, down 1
            - x constraints: x + 2 && x = 7
            - y constraints:  y + 1 and y = 7
    */

    // private boolean checkKnightMove(Board b, int locX, int locY, int xConstraint, int yConstraint, List<String> possibleMoves, boolean isMoveUp, boolean inMoveRight, Color color, String pieceLocStr) {
    //     int xDirection = inMoveRight ? 1 : -1;
    //     int yDirection = isMoveUp ? 1 : -1;
    
    //     int possibleMoveLocX = locX + (xDirection * 1);
    //     int possibleMoveLocY = locY + (yDirection * 2);
    
    //     if (possibleMoveLocX >= 0 && possibleMoveLocX <= xConstraint &&
    //         possibleMoveLocY >= 0 && possibleMoveLocY <= yConstraint) {
            
    //         return Rook.isTherePieceBlockage(b, pieceLocStr, color, locX, locY, possibleMoves);
    //     }
        
    //     return false;
    // }

    // private void upDownLeftRight(Board b, boolean isMoveUp, boolean inMoveRight, int xConstraint, int yConstraint, int locX, int locY, List<String> possibleMoves) {
    //     boolean isTherePieceBlockage = checkKnightMove(b, locX, locY, xConstraint, yConstraint, possibleMoves, isMoveUp, inMoveRight, color, pieceLocStr);
    // }

    private void move2UpDownAnd1LeftRight(Board b, boolean isMoveUp, boolean isMoveRight, int xConstraint, int yConstraint, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, List<String> possibleMoves)
    {
        int possibleMoveLocX;
        int possibleMoveLocY;

        if (color() == Color.WHITE)
        {
            possibleMoveLocX = (isMoveRight) ? locXToCheckForPossibleMoves + 1 :  locXToCheckForPossibleMoves - 1;
            possibleMoveLocY = (isMoveUp) ? locYToCheckForPossibleMoves + 2 :  locYToCheckForPossibleMoves - 2;
        }
        else
        {
            possibleMoveLocX = (isMoveRight) ? locXToCheckForPossibleMoves - 1 :  locXToCheckForPossibleMoves + 1;
            possibleMoveLocY = (isMoveUp) ? locYToCheckForPossibleMoves - 2 :  locYToCheckForPossibleMoves + 2;
        }

        if (color() == Color.WHITE)
        {
            if (isMoveUp)
            {
                if (isMoveRight)
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
            else
            {
                if (isMoveRight)
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
        }
        else
        {
            if (isMoveUp)
            {
                if (isMoveRight)
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
            else
            {
                if (isMoveRight)
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
        }


        /* ensure to stay within the 2D 8x8 chess board */
        // if (((possibleMoveLocX <= xConstraint) || possibleMoveLocX >= 0) && 
        //     ((possibleMoveLocY <= yConstraint) || possibleMoveLocY >= 0))
        // {
        //     System.out.println("2 x: " + possibleMoveLocX);
        //     System.out.println("2 y: " + possibleMoveLocY);
        //     Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
        // }
    }

    private void move2LeftRightAnd1UpDown(Board b, boolean isMoveLeft, boolean isMoveDown, int xConstraint, int yConstraint, int locXToCheckForPossibleMoves, int locYToCheckForPossibleMoves, List<String> possibleMoves)
    {
        int possibleMoveLocX;
        int possibleMoveLocY;

        if (color() == Color.WHITE)
        {
            possibleMoveLocX = (isMoveLeft) ? locXToCheckForPossibleMoves - 2 :  locXToCheckForPossibleMoves + 2;
            possibleMoveLocY = (isMoveDown) ? locYToCheckForPossibleMoves - 1 :  locYToCheckForPossibleMoves + 1;
        }
        else
        {
            possibleMoveLocX = (isMoveLeft) ? locXToCheckForPossibleMoves + 2 :  locXToCheckForPossibleMoves - 2;
            possibleMoveLocY = (isMoveDown) ? locYToCheckForPossibleMoves + 1 :  locYToCheckForPossibleMoves - 1;
        }

        if (color() == Color.WHITE)
        {
            if (isMoveLeft)
            {
                if (isMoveDown)
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
            else
            {
                if (isMoveDown)
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        boolean test = Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
        }
        else
        {
            if (isMoveLeft)
            {
                if (isMoveDown)
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX <= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
            else
            {
                if (isMoveDown)
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY <= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
                else
                {
                    if(possibleMoveLocX >= xConstraint && possibleMoveLocY >= yConstraint)
                    {
                        Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
                    }
                }
            }
        }

        // /* ensure to stay within the 2D 8x8 chess board */
        // if (((possibleMoveLocX <= xConstraint) || possibleMoveLocX >= 0) && 
        //     ((possibleMoveLocY <= yConstraint) || possibleMoveLocY >= 0))
        // {
        //     // System.out.println("1 x: " + possibleMoveLocX);
        //     // System.out.println("1 y: " + possibleMoveLocY);
        
        //     Rook.isTherePieceBlockage(b, pieceLocStr, color, possibleMoveLocX, possibleMoveLocY, possibleMoves);
        // }
    }
       

    /*
    * upDownRight
    * upDownLeft
    * leftRightUp
    * leftRightDown
    */
}