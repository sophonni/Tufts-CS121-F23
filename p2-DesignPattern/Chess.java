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
            throw new ArrayIndexOutOfBoundsException();
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
            filesParsing(layoutFile);
            
            /* read move sequence file */
            filesParsing(moveSeqFile);
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

    private static void filesParsing(File fileToProcess)
    {
        try
        {
            FileReader fileReader = new FileReader(fileToProcess);
            BufferedReader textReader = new BufferedReader(fileReader);
            String currentLineContent = "";

            while ((currentLineContent = textReader.readLine()) != null)
            {
                if (currentLineContent.contains("#"))
                {
                    /* get the index of the character "#" from the current line */
                    int commentIndex = currentLineContent.indexOf("#");

                    /* check if there exist the character "#" in the current line */
                    if (commentIndex != -1) {
                        /* remove everything after "#" and keep only the part before it */
                        currentLineContent = currentLineContent.substring(0, commentIndex);
                    }
                    
                }
                System.out.println(currentLineContent);
            }
            fileReader.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
    }

}