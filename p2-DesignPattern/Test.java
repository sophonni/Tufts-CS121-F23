import java.util.List;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    // public static void test1()
    // {
    //     Board b = Board.theBoard();
    //     Piece.registerPiece(new PawnFactory());
    //     Piece p = Piece.createPiece("bp");
    //     b.addPiece(p, "a3");
    //     assert b.getPiece("a3") == p;
    // }

    public static void filesParsing_NotEnoughArgument()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[1];
        fileNames[0] = "move1";
        
        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_TooManyArguments()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[3];
        fileNames[0] = "layout1";
        fileNames[1] = "moves1";
        fileNames[2] = "moves1";

        
        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_CorrectFilesAndFormat()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "layout1";
        fileNames[1] = "moves1";
        
        Chess.main(fileNames);
    }

    public static void filesParsingTest_InCorrectLayoutFileFormat()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "incorrectLayoutFileFormat.txt";
        fileNames[1] = "moves1";

        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_InCorrectMoveFileFormat()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "layout1";
        fileNames[1] = "incorrectMoveFileFormat.txt";

        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_InCorrectFilesFormat()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "incorrectLayoutFileFormat.txt";
        fileNames[1] = "incorrectMoveFileFormat.txt";

        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_NonExistanceFileName()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "layout";
        fileNames[1] = "moves1";

        
        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }
    public static void filesParsingTest_NonExistanceFileNames()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "layout";
        fileNames[1] = "moves";

        
        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void filesParsingTest_CommentAtEndAndMid()
    {
        //Chess c = new Chess();
        String[] fileNames = new String[2];
        fileNames[0] = "commentAtEndAndMid.txt";
        fileNames[1] = "moves1";

        
        try
        {
            Chess.main(fileNames);
        }
        catch (Exception e)
        {
            assert e instanceof Exception == true;
        }
    }

    public static void pawnColorTest()
    {
        Piece p1 = new Pawn(Color.BLACK);
        Piece p2 = new Pawn(Color.WHITE);

        String name1 = p1.toString();
        String name2 = p2.toString();

        /* content comparision of the two strings */
        assert name1.equals("bp");
        assert name2.equals("wp");

        /* address in memory comparision of the two strings */
        assert name1 != "bp";
        assert name2 != "wp";

    }

    public static void registerPieceTest()
    {
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
    }

    public static void createKnownPiecesTest()
    {
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteQ = Piece.createPiece("wq");
        assert whiteQ != null;
        assert whiteQ.color == Color.WHITE;
        assert whiteQ.color != Color.BLACK;
        assert whiteQ.toString().equals("wq");
    }

    public static void createUnknownPiecesTest()
    {
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        try
        {
            Piece unknownPiece = Piece.createPiece("unknown");
        }
        catch (Exception e)
        {
            assert e instanceof Exception;
        }
    }

    // public static void pieceLocationCorrectLocFormatTest()
    // {
    //     Board b = Board.theBoard();
    //     int[] i = b.strLocToArrIndices("a5");
    //     assert i[0] == 0;
    //     assert i[1] == 4;
    // }
    // public static void pieceLocationIncorrectLocFormatTest()
    // {
    //     try
    //     {
    //         Board b = Board.theBoard();
    //         int[] i = b.strLocToArrIndices("a9");
    //         assert i[0] == 0;
    //         assert i[1] == 4;
    //     }
    //     catch (Exception e)
    //     {
    //         assert e instanceof Exception;
    //     }
    // }

    // public static void addAndGetPieceInCorrectFormatTest()
    // {
    //     Board b = Board.theBoard();
    //     Piece.registerPiece(new KingFactory());
    //     Piece.registerPiece(new QueenFactory());
    //     Piece.registerPiece(new KnightFactory());
    //     Piece.registerPiece(new BishopFactory());
    //     Piece.registerPiece(new RookFactory());
    //     Piece.registerPiece(new PawnFactory());

    //     Piece whiteP = Piece.createPiece("wp");
    //     assert whiteP != null;

    //     b.addPiece(whiteQ, "h8");

    //     Piece verifyWiteP = b.getPiece("h8");
    //     assert verifyWiteP != null;
    //     assert verifyWiteP.color == Color.WHITE;
    //     assert verifyWiteP.toString().equals("wp");
    // }

    public static void availableListForPawnMoveWithoutOponentNearByTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteP = Piece.createPiece("wp");

        b.addPiece(whiteP, "a2");
        List t = whiteP.moves(b, "a2");
        assert t.size() == 2;
    }

    public static void whitePawnPossibleMoveWithOpponentsNearByTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteP = Piece.createPiece("wp");
        Piece blackPR = Piece.createPiece("bp");
        Piece blackPL = Piece.createPiece("bp");

        b.addPiece(blackPL, "d3");
        b.addPiece(whiteP, "c2");
        b.addPiece(blackPR, "b3");

        List possibleMoveLoc = whiteP.moves(b, "c2");
        assert possibleMoveLoc.size() == 4;
        assert possibleMoveLoc.contains("b3") == true;
        assert possibleMoveLoc.contains("d3") == true;
        assert possibleMoveLoc.contains("c3") == true;
        assert possibleMoveLoc.contains("c4") == true;
    }

    public static void blackPawnPossibleMoveWithOpponentsNearByTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece blackP = Piece.createPiece("bp");
        Piece whitePRight = Piece.createPiece("wp");
        Piece whitePLeft = Piece.createPiece("wp");

        b.addPiece(whitePLeft, "d5");
        b.addPiece(blackP, "c6");
        b.addPiece(whitePRight, "b5");

        List possibleMoveLoc = blackP.moves(b, "c6");
        assert possibleMoveLoc.size() == 3;
        assert possibleMoveLoc.contains("b5") == true;
        assert possibleMoveLoc.contains("d5") == true;
        assert possibleMoveLoc.contains("c5") == true;
    }

    public static void main(String[] args)
    {
	    //test1();
        //filesParsing_NotEnoughArgument();
        //filesParsingTest_TooManyArguments();
        //filesParsingTest_CorrectFilesAndFormat();
        //filesParsingTest_InCorrectLayoutFileFormat();
        //filesParsingTest_InCorrectMoveFileFormat();
        //filesParsingTest_InCorrectFilesFormat();
        //filesParsingTest_NonExistanceFileName();
        //filesParsingTest_NonExistanceFileNames();
        //filesParsingTest_CommentAtEndAndMid();

        //pawnColorTest();
        //registerPieceTest();
        //createKnownPiecesTest();
        //createUnknownPiecesTest();

        //pieceLocationCorrectLocFormatTest();
        //pieceLocationIncorrectLocFormatTest();

        //addAndGetPieceInCorrectFormatTest();

        //availableListForPawnMoveWithoutOponentNearByTest();
        whitePawnPossibleMoveWithOpponentsNearByTest();
        blackPawnPossibleMoveWithOpponentsNearByTest();
    }

}