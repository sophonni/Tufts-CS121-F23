import java.util.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private static Map<Character, Integer> xLocStrToArrIndexMapping = new HashMap<>();
    private static Map<Integer, Character> arrIndicesToLocStrMapping = new HashMap<>();


    private static void initializeArrIndicesAndNameFormatMapping()
    {
        /* perform character a-h to 1-8 mapping and 1-8 to a-h mapping of the chess board coordinates */
        int i = 0;
        for (char c = 'a'; c <= 104; c++)
        {
            xLocStrToArrIndexMapping.put(c, i);
            arrIndicesToLocStrMapping.put(i, c);
            i++;
        }
    }
    public int[] locStrToArrIndices(String loc)
    {
        /* verify that the given location in valid */
        verifyLocStrFormat(loc);

        /* get the x-coordinate of the given position from the mapping */
        char xCoorChar = loc.charAt(0);

        int xCoord = xLocStrToArrIndexMapping.get(xCoorChar);

        /* convert the y-coordinate of the given position from integer in char to integer in int */
        int yCoor = (int)loc.charAt(1) - '0';

        /* returns the x and y indices positon of the 2d array using the given position*/
        return new int[] {xCoord, yCoor - 1}; 
    }

    public String arrIndicesToLocStr(int[] loc)
    {
        /* verify that the given indices is valid for the 2D array of the board */
        verifyArrIndices(loc);
        
        char xCoor = arrIndicesToLocStrMapping.get(loc[0]);
        
        char yCoor = (char)('0' + (loc[1] + 1));

        String indicesLocIntoStrFormat = String.valueOf(xCoor) + String.valueOf(yCoor);

        return indicesLocIntoStrFormat; 
    }

    public boolean verifyArrIndices(int[] loc)
    {
        String errorMessage;
        if (loc.length == 2)
        {
            int xPosOfPiece = loc[0];
            int yPosOfPiece = loc[1];

            boolean isXIndicesValid = (xPosOfPiece >= 0 && xPosOfPiece <= 7);
            boolean isYIndicesValid = (yPosOfPiece >= 0 && yPosOfPiece <= 7);

            return isXIndicesValid && isYIndicesValid;
        }
        else
        {
            errorMessage = "Error: Location of piece should contain {x, y} postion.";
            throw new IllegalArgumentException(errorMessage);
        }
    }


    public boolean verifyLocStrFormat(String loc)
    {
        String errorMessage;

        /* verify that the string only has 2 characters (x-coordinate, y-coordinate) */
        if (loc.length() == 2)
        {
            char xCoordChar = loc.charAt(0);
            char yCoordChar = loc.charAt(1);

            /* check x-coordinate of the given position to ensure its range a-h */
            boolean isValidXCoord = (xCoordChar >= 97 && xCoordChar <= 104);
    
            /* check y-coordinate of the given position to ensure its range 1-8 */
            boolean isValidYCoord = (yCoordChar >= 49 && yCoordChar <= 56);
    
            return isValidXCoord && isValidYCoord;
        }
        else
        {
            errorMessage = String.format("Error: Location {%1$s} is in an incorrect format.", loc);
            throw new IllegalArgumentException(errorMessage);
        }
    }


    private Board() { }
    
    public static Board theBoard() {
        initializeArrIndicesAndNameFormatMapping();
        return new Board();
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {
        /* ensure that given location is valid */
        verifyLocStrFormat(loc);

        /* get 2d array indices (x, y) from the given location (y-coordinate if offset by 1 for array indexing) */
        int[] indicesOfPiece = locStrToArrIndices(loc);
        int xCoor = indicesOfPiece[0];
        int yCoor = indicesOfPiece[1];

        /* check if the location is empty before return the piece at the given location */
        if (pieces[xCoor][yCoor] != null)
        {
            return pieces[xCoor][yCoor];
        }
        else
        {
            return null;
        }
    }

    public void addPiece(Piece p, String loc) {
        String errorMessage;

        /* ensure that given location is valid */
        verifyLocStrFormat(loc);

        /* get 2d array indices (x, y) from the given location */
        int[] indicesOfPiece = locStrToArrIndices(loc);
        int xCoor = indicesOfPiece[0];
        int yCoor = indicesOfPiece[1];

        /* check if the location is empty before adding the given piece at the given location */
        if (pieces[xCoor][yCoor] == null)
        {
            pieces[xCoor][yCoor] = p;
        }

        /* throw exception since there's already a piece exist at the given location */
        else
        {
            errorMessage = String.format("Error: Location {%1$s} is already occupied by a piece.", loc);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void movePiece(String from, String to) {
        String errorMessage;

        /* ensure that given location is valid */
        verifyLocStrFormat(from);

        Piece pieceToMove = getPiece(from);
        Piece pieceAtTargetPos = getPiece(to);

        if (pieceToMove != null && pieceAtTargetPos == null)
        {
            /* get 2d array indices (x, y) from the given location */
            int[] indicesOfPieceCurrPos = locStrToArrIndices(from);
            int xCoorCurr = indicesOfPieceCurrPos[0];
            int yCoorCurr = indicesOfPieceCurrPos[1];

            /* SD TODO: to completed */

        }
        else
        {
            errorMessage = String.format("Error: Location {%1$s} does not contain a piece to move or there already exist a piece at target position {%1$s}.", from, to);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public boolean areTwoPieceOnTheSameTeam(Piece currPiece, Piece potentialOpponent)
    {
        if (currPiece != null && potentialOpponent != null)
        {
            if (currPiece.color() != potentialOpponent.color())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public void clear() {
	throw new UnsupportedOperationException();
    }

    public void registerListener(BoardListener bl) {
	throw new UnsupportedOperationException();
    }

    public void removeListener(BoardListener bl) {
	throw new UnsupportedOperationException();
    }

    public void removeAllListeners() {
	throw new UnsupportedOperationException();
    }

    public void iterate(BoardInternalIterator bi) {
	throw new UnsupportedOperationException();
    }
}