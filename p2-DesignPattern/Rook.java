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
        // System.out.println("curr x: " + arrIndicesOfCurrRook[0]);
        // System.out.println("curr y: " + arrIndicesOfCurrRook[1]);
        Piece currRook = b.getPiece(loc);
        if (currRook != null)
        {
            if (currRook.color() == Color.WHITE)
            {
                //System.out.println("White Rook");
                checkAndAddPossibleMove(b, currRook, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 7, possibleMoveLoc);
            }
            else
            {
                //System.out.println("Black Rook");
                checkAndAddPossibleMove(b, currRook, arrIndicesOfCurrRook[0], arrIndicesOfCurrRook[1], 7, 0,possibleMoveLoc);
            }
        }
        return possibleMoveLoc;
    }

    private void checkAndAddPossibleMove(Board b, Piece p, int locX, int locY, int xConstraint, int yConstraint, List<String> possibleMoves)
    {
        boolean isThereOpponent = false;
        
        /* get all possible move of x-coordinate of current rook */
        for (int nextLocX = 0; nextLocX <= 7; nextLocX++)
        {
            {
                if (nextLocX != locX)
                {
                    isThereOpponent = isTherePieceBlockage(b, p, nextLocX, locY, possibleMoves);
                    if (isThereOpponent)
                    {                    
                        break;
                    }
                }
            }
        }
        
        /* white rook: get all possible move of y-coordinate of current white rook (forward and backward)*/
        if (yConstraint == 7)
        {
            /* check for opponent in front of the current white rook */
            for (int nextLocY = locY; nextLocY <= 7; nextLocY++)
            {
                if (nextLocY != locY)
                {
                    isThereOpponent = isTherePieceBlockage(b, p, locX, nextLocY, possibleMoves);
        
                    /* ensure that current white rook can't move pass the location of its front opponent, if any */
                    if (isThereOpponent)
                    {                    
                        break;
                    }
                }
            }

            /* check for opponent in the back of the current white rook */
            for (int nextLocY = locY; nextLocY >= 0; nextLocY--)
            {
                if (nextLocY != locY)
                {
                    isThereOpponent = isTherePieceBlockage(b, p, locX, nextLocY, possibleMoves);
                    /* ensure that current white rook can't move pass the location of its back opponent, if any */
                    if (isThereOpponent)
                    {                    
                        break;
                    }
                }
            }
        }
        /* black rook: get all possible move of y-coordinate of current black rook (forward and backward) */
        else if (yConstraint == 0)
        {
            /* check for opponent in front of the current black rook */
            for (int nextLocY = locY; nextLocY >= 0; nextLocY--)
            {
                if (nextLocY != locY)
                {
                    isThereOpponent = isTherePieceBlockage(b, p, locX, nextLocY, possibleMoves);

                    /* ensure that current black rook can't move pass the location of its back opponent, if any */
                    if (isThereOpponent)
                    {      
                        break;
                    }
                }
            }

            /* check for opponent in the back of the current black rook */
            for (int nextLocY = locY; nextLocY <= 7; nextLocY++)
            {
                if (nextLocY != locY)
                {
                    isThereOpponent = isTherePieceBlockage(b, p, locX, nextLocY, possibleMoves);
            
                    /* ensure that current black rook can't move pass the location of its front opponent, if any */
                    if (isThereOpponent)
                    {      
                        break;
                    }
                }
            }
        }
    }

    private boolean isTherePieceBlockage(Board b, Piece p, int locX, int locY, List<String> possibleMoves)
    {
        int[] locStrOfPotentialOpponent = new int[] {locX, locY};
        String potentialOpponentLocStr = b.arrIndicesToLocStr(locStrOfPotentialOpponent);
        Piece potentialOpponent = b.getPiece(potentialOpponentLocStr);
        
        /* blocked piece is an opponent of the given piece */
        if ((potentialOpponent != null && potentialOpponent.color() != p.color()))
        {
            possibleMoves.add(potentialOpponentLocStr);
            return true;
        }
        /* blocked piece is NOT an opponent of the given piece */
        else if ((potentialOpponent != null && potentialOpponent.color() == p.color()))
        {
            return true;
        }
        /* not piece blocking the given rook's path (x-coordinate or y-coordinate) */
        else
        {
            possibleMoves.add(potentialOpponentLocStr);
            return false;
        }
    }
}