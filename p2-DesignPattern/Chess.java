import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        String errorMessage;
    
        /* check for files existency */
        if (layoutFile.exists() && moveSeqFile.exists())
        {
            /* read layout file */
            readAndProcessFiles(layoutFile, true);
            readAndProcessFiles(moveSeqFile, false);
        }
        else
        {
            /* give layout file name doesn't exist in current directory */
            if (!layoutFile.exists())
            {
                errorMessage = String.format("Error: {%1$s} file does not exist in current directory.", layoutFileName);
                throw new IllegalArgumentException(errorMessage);

            }
            
            /* give move sequence file name doesn't exist in current directory */
            if (!moveSeqFile.exists())
            {
                errorMessage = String.format("Error: {%1$s} file does not exist in current directory.", moveSeqFileName);
                throw new IllegalArgumentException(errorMessage);
            }
        }

        // // Leave the following code at the end of the simulation:
        /* SD TODO: un-comment the bottom 2 lines before submission */
        // System.out.println("Final board:");
        // Board.theBoard().iterate(new BoardPrinter());
    }

    private static void readAndProcessFiles(File layoutFile, boolean isLayoutFile)
    {
        boolean isFileInCorrectFormat = false;
        String errorMessage;
        try
        {
            /* ensure given file is not empty */
            if (layoutFile.length() != 0)
            {
                FileReader fileReader = new FileReader(layoutFile);
                try (BufferedReader textReader = new BufferedReader(fileReader))
                {
                    String currentLineContent = "";
                    
                    while ((currentLineContent = textReader.readLine()) != null)
                    {
                        /* check for commented portion of the currrent line */
                        if (currentLineContent.contains("#"))
                        {
                            /* get the index of the character "#" from the current line */
                            int commentIndex = currentLineContent.indexOf("#");
                            
                            if (commentIndex != -1)
                            {
                                /* remove commented out section of the current line */
                                currentLineContent = currentLineContent.substring(0, commentIndex);
                            }
                        }

                        /* the whole line is commented out */
                        if (currentLineContent.trim().isEmpty())
                        {
                            continue;
                        }
                        
                        /* ensure correct format of the given layout file */
                        if (isLayoutFile)
                        {
                            if (currentLineContent.length() >= 5)
                            {
                                char xCoord = currentLineContent.charAt(0);
                                char yCoord = currentLineContent.charAt(1);
                                char equalSign = currentLineContent.charAt(2);
                                char pieceColor = currentLineContent.charAt(3);
                                char pieceType = currentLineContent.charAt(4);
                                isFileInCorrectFormat = verifyLayoutFormat(xCoord, yCoord, equalSign, pieceColor, pieceType);

                                /* throw exception if line content doesn't meet required content */
                                if (!isFileInCorrectFormat)
                                {
                                    fileReader.close();
                                    errorMessage = String.format("Error: Layout File {%1$s} with content {%2$s} is not in correct format.", layoutFile.getName(), currentLineContent);
                                    throw new IllegalArgumentException(errorMessage);
                                }
                            }
                            else
                            {
                                fileReader.close();
                                errorMessage = String.format("Error: Layout File {%1$s} line {%2$s} is not in correct format.", layoutFile.getName(), currentLineContent);
                                throw new IllegalArgumentException(errorMessage);
                            }

                        }
                        /* ensure correct format of the give move file */
                        else
                        {
                            /* ensure each line has at least 5 characters */
                            if (currentLineContent.length() >= 5)
                            {
                                char currXCoord = currentLineContent.charAt(0);
                                char currYCoord = currentLineContent.charAt(1);
                                char dashSign = currentLineContent.charAt(2);
                                char newXCoord = currentLineContent.charAt(3);
                                char newYCoord = currentLineContent.charAt(4);
                                isFileInCorrectFormat = verifyMoveSeqFormat(currXCoord, currYCoord, newXCoord, newYCoord, dashSign);

                                /* throw exception if line content doesn't meet required content */
                                if (!isFileInCorrectFormat)
                                {
                                    fileReader.close();
                                    errorMessage = String.format("Error: Layout File {%1$s} line {%2$s} is not in correct format.", layoutFile.getName(), currentLineContent);
                                    throw new IllegalArgumentException(errorMessage);
                                }
                            }
                            else
                            {
                                fileReader.close();
                                errorMessage = String.format("Error: Layout File {%1$s} line {%2$s} is not in correct format.", layoutFile.getName(), currentLineContent);
                                throw new IllegalArgumentException(errorMessage);
                            }
                            
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