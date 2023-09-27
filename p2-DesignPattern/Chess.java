import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Chess {
    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.out.println("Usage: java Chess layout moves");

            /* SD TODO: can we throw an exception here? */
            // throw new ArrayIndexOutOfBoundsException();
        }

        /* SD TODO: un-comment the bottom 7 lines before submission */
        // Piece.registerPiece(new KingFactory());
        // Piece.registerPiece(new QueenFactory());
        // Piece.registerPiece(new KnightFactory());
        // Piece.registerPiece(new BishopFactory());
        // Piece.registerPiece(new RookFactory());
        // Piece.registerPiece(new PawnFactory());
        // Board.theBoard().registerListener(new Logger());

        /* get file names from command line */
        String layoutFileName = args[0]; /* args[0] is the layout file name */
        String moveSeqFileName = args[1]; /* args[1] is the moves file name */
        
        File layoutFile = new File(layoutFileName);
        File moveSeqFile = new File(moveSeqFileName);
    
        /* check for files existency */
        if (layoutFile.exists() && moveSeqFile.exists())
        {
            /* read layout file */
            boolean isLayoutFileInCorrectFormat = readAndProcessFiles(layoutFile, true);
            boolean isMoveSeqFileInCorrectFormat = readAndProcessFiles(moveSeqFile, false);

            if (!isLayoutFileInCorrectFormat)
            {
                System.out.println(String.format("Error: Layout  File {%1$s} is in an incorrect format.", layoutFileName));
                throw new IllegalArgumentException();
            }
            if (!isMoveSeqFileInCorrectFormat)
            {
                System.out.println(String.format("Error: Move File {%1$s} is in an incorrect format.", moveSeqFileName));
                // throw new IllegalArgumentException();
            }
            
        }
        else
        {
            /* give layout file name doesn't exist in current directory */
            if (!layoutFile.exists())
            {
                System.out.println(String.format("Error: {%1$s} file does not exist in current directory.", layoutFileName));
            }
            
            /* give move sequence file name doesn't exist in current directory */
            if (!moveSeqFile.exists())
            {
                System.out.println(String.format("Error: {%1$s} file does not exist in current directory.", moveSeqFileName));
            }
        }

        // // Leave the following code at the end of the simulation:
        /* SD TODO: un-comment the bottom 2 lines before submission */
        // System.out.println("Final board:");
        // Board.theBoard().iterate(new BoardPrinter());
    }

    private static boolean readAndProcessFiles(File layoutFile, boolean isLayoutFile)
    {
        boolean isFileInCorrectFormat = false;
        try
        {
            /* check if layout file is an empty file */
            if (layoutFile.length() != 0)
            {
                FileReader fileReader = new FileReader(layoutFile);
                try (BufferedReader textReader = new BufferedReader(fileReader))
                {
                    String currentLineContent = "";
   
                    while ((currentLineContent = textReader.readLine()) != null)
                    {
                        /* check if there exist the character "#" in the current line */
                        if (currentLineContent.contains("#"))
                        {
                            /* get the index of the character "#" from the current line */
                            int commentIndex = currentLineContent.indexOf("#");
   
                            if (commentIndex != -1)
                            {
                                /* remove everything after "#" and keep only the part before it */
                                currentLineContent = currentLineContent.substring(0, commentIndex);
                            }
                        }

                        /* skip current line and move to the next one b/c current line is a comment */
                        if (currentLineContent.trim().isEmpty())
                        {
                            continue;
                        }

                        /* check what file we're performing the check format on (layout file or move sequence file) */
                        if (isLayoutFile)
                        {
                            
                            char xCoord = currentLineContent.charAt(0);
                            char yCoord = currentLineContent.charAt(1);
                            char equalSign = currentLineContent.charAt(2);
                            char pieceColor = currentLineContent.charAt(3);
                            char pieceType = currentLineContent.charAt(4);
                            
                            isFileInCorrectFormat = verifyLayoutFormat(xCoord, yCoord, equalSign, pieceColor, pieceType);
                            
                        }
                        else
                        {
                            char currXCoord = currentLineContent.charAt(0);
                            char currYCoord = currentLineContent.charAt(1);
                            char dashSign = currentLineContent.charAt(2);
                            char newXCoord = currentLineContent.charAt(3);
                            char newYCoord = currentLineContent.charAt(4);
                            
                            isFileInCorrectFormat = verifyMoveSeqFormat(currXCoord, currYCoord, newXCoord, newYCoord, dashSign);
                        }

                        if (isFileInCorrectFormat)
                        {

                        }
                        else
                        {
                            fileReader.close();
                            return false;
                        }
                    }
                }
                fileReader.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return isFileInCorrectFormat;
    }

    private static boolean verifyLayoutFormat(char xCoord, char yCoord, char equalSign, char pieceColor, char pieceType)
    {
        /* check y-coordinate (1-8) */
        boolean isValidXCoord = (xCoord >= 97 && xCoord <= 104);

        /* check y-coordinate (1-8) */
        boolean isValidYCoord = (yCoord >= 49 && yCoord <= 56);


        /* check for equal sign */
        boolean isEqualSign = (equalSign == 61);

        
        /* check color (b or w) */
        boolean isValidPieceColor = (pieceColor == 98 || pieceColor == 119);


        /* check piece's type (k, q, n, b, r, or p) */
        boolean isValidPieceType = (pieceType == 107 || pieceType == 113 || pieceType == 110 ||
                                    pieceType == 98 || pieceType == 114 || pieceType == 112);

        
    
        /* return true if all conditions are met, otherwise return false */
        return isValidXCoord && isValidYCoord && isEqualSign && isValidPieceColor && isValidPieceType;
        
    } 

    private static boolean verifyMoveSeqFormat(char currXCoord, char currYCoord, char newXCoord, char newYCoord, char dashSign)
    {
        /* Check current position of the chess piece in x-coordinate (a-h) */
        boolean validXCoord = (currXCoord >= 97 && currXCoord <= 104);

        /* Check current position of the chess piece in y-coordinate (1-8) */
        boolean validYCoord = (currYCoord >= 49 && currYCoord <= 56);

        /* Check new position of the chess piece in x-coordinate (a-h) */
        boolean validNewXCoord = (newXCoord >= 97 && newXCoord <= 104);

        /* Check new position of the chess piece in y-coordinate (1-8) */
        boolean validNewYCoord = (newYCoord >= 49 && newYCoord <= 56);

        /* Check equal sign (-) */
        boolean validDashSign = (dashSign == 45);

        /* Check if all conditions are met */
        return validXCoord && validYCoord && validNewXCoord && validNewYCoord && validDashSign;
    } 

}