import java.util.List;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    public static void test1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("bp");
        b.addPiece(p, "a3");
        assert b.getPiece("a3") == p;
    }

/**************************
* FILE HANDLER TESTING    *
***************************/

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

/**********************************
* PIECE INITIALIZATION TESTING    *
**********************************/
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

/************************
* PIECE FORMAT TESTING  *
************************/
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

    public static void addAndGetPieceInCorrectFormatTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteP = Piece.createPiece("wp");
        assert whiteP != null;

        b.addPiece(whiteP, "h8");

        Piece verifyWiteP = b.getPiece("h8");
        assert verifyWiteP != null;
        assert verifyWiteP.color == Color.WHITE;
        assert verifyWiteP.toString().equals("wp");
    }

/***************************
* AVAILABLE MOVES TESTING  *
***************************/
    public static void justPawnOnBoardTesting()
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
        List<String> possibleMoveLoc = whiteP.moves(b, "a2");
        // for (String s : possibleMoveLoc)
        // {
        //     System.out.println(s);
        // }
        assert possibleMoveLoc.size() == 2;
    }

    public static void whitePawnWithOpponentOnBoardTesting()
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

    public static void blackPawnWithOpponentOnBoardTesting1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece blackP = Piece.createPiece("bp");
        Piece whitePLeft = Piece.createPiece("wp");
        Piece whitePRight = Piece.createPiece("wp");
        Piece whitePFront = Piece.createPiece("wp");

        b.addPiece(blackP, "d4");
        b.addPiece(whitePLeft, "c3");
        b.addPiece(whitePRight, "e3");
        b.addPiece(whitePFront, "d3");

        List possibleMoveLoc = blackP.moves(b, "d4");
        assert possibleMoveLoc.size() == 3;
        assert possibleMoveLoc.contains("c3") == true;
        assert possibleMoveLoc.contains("e3") == true;
        assert possibleMoveLoc.contains("d3") == true;
        assert possibleMoveLoc.contains("d5") == false;
    }

    public static void blackPawnWithOpponentOnBoardTesting2()
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

    public static void withPawnWithTeamAndOpponentPieceTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteP1 = Piece.createPiece("wp");
        Piece whiteP2 = Piece.createPiece("wp");
        Piece blackPR = Piece.createPiece("bp");
        Piece blackPL = Piece.createPiece("bp");

        b.addPiece(whiteP1, "c2");
        b.addPiece(whiteP2, "c4");
        b.addPiece(blackPL, "d3");
        b.addPiece(blackPR, "b3");

        List possibleMoveLoc = whiteP1.moves(b, "c2");
        assert possibleMoveLoc.size() == 3;
        assert possibleMoveLoc.contains("b3") == true;
        assert possibleMoveLoc.contains("d3") == true;
        assert possibleMoveLoc.contains("c3") == true;
        assert possibleMoveLoc.contains("c4") == false;
    }

    public static void blackAndWhitePawnOnBoardTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteR = Piece.createPiece("wr");
        b.addPiece(whiteR, "a1");
        List<String> possibleMoveLocWhiteRook = whiteR.moves(b, "a1");
        //System.out.println("White: " + possibleMoveLocWhiteRook.size());
        assert possibleMoveLocWhiteRook.size() == 14;

        Piece blackR = Piece.createPiece("br");
        b.addPiece(blackR, "a8");
        List<String> possibleMoveLocBlackRook = blackR.moves(b, "a8");
        //System.out.println("Black: " + possibleMoveLocBlackRook.size());
        assert possibleMoveLocBlackRook.size() == 14;
    }

    public static void blackRookWithOpponentOnBoardTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece blackR = Piece.createPiece("br");
        Piece whiteP = Piece.createPiece("wp");


        b.addPiece(blackR, "d6");
        b.addPiece(whiteP, "d3");


        List<String> possibleMoveLoc = blackR.moves(b, "d6");
        assert possibleMoveLoc.size() == 12;
        assert possibleMoveLoc.contains("d5") == true;
        assert possibleMoveLoc.contains("d4") == true;
        assert possibleMoveLoc.contains("d3") == true;
        assert possibleMoveLoc.contains("d2") == false;
        assert possibleMoveLoc.contains("d1") == false;
        assert possibleMoveLoc.contains("d9") == false;

    }

    public static void blackRookWithOpponentOnBoardTest2()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece blackR = Piece.createPiece("br");
        Piece whiteP1 = Piece.createPiece("wp");
        Piece whiteP2 = Piece.createPiece("wp");


        b.addPiece(blackR, "d6");
        b.addPiece(whiteP1, "d3");
        b.addPiece(whiteP2, "d7");


        List<String> possibleMoveLoc = blackR.moves(b, "d6");
        // for (String s : possibleMoveLoc)
        // {
        //     System.out.println(s);
        // }
        //System.out.println("size: " + possibleMoveLoc.size());
        assert possibleMoveLoc.size() == 11;
        assert possibleMoveLoc.contains("d5") == true;
        assert possibleMoveLoc.contains("d4") == true;
        assert possibleMoveLoc.contains("d3") == true;
        assert possibleMoveLoc.contains("d2") == false;
        assert possibleMoveLoc.contains("d1") == false;
        assert possibleMoveLoc.contains("d9") == false;
        assert possibleMoveLoc.contains("d8") == false;
        assert possibleMoveLoc.contains("d7") == true;
    }

    public static void whiteRookWithOpponentOnBoardTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteR = Piece.createPiece("wr");
        Piece blackP1 = Piece.createPiece("bp");
        Piece blackP2 = Piece.createPiece("bp");


        b.addPiece(whiteR, "d3");
        b.addPiece(blackP1, "d6");
        b.addPiece(blackP2, "d2");


        List<String> possibleMoveLoc = whiteR.moves(b, "d3");
        // for (String s : possibleMoveLoc)
        // {
        //     System.out.println(s);
        // }
        //System.out.println("size: " + possibleMoveLoc.size());
        assert possibleMoveLoc.size() == 11;
    }

    public static void whiteRookWithOpponentOnBoardTest2()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteB1 = Piece.createPiece("wb");
        b.addPiece(whiteB1, "c3");

        List<String> possibleMoves = whiteB1.moves(b, "c3");
        // for (String s : possibleMoves)
        // {
        //     System.out.println(s);
        // }
        assert possibleMoves.size() == 11;
    }

    public static void bishopAndRookOpponentTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteB1 = Piece.createPiece("wb");
        b.addPiece(whiteB1, "c3");

        Piece blackB1 = Piece.createPiece("bb");
        b.addPiece(blackB1, "h8");

        List<String> possibleMoves1 = whiteB1.moves(b, "c3");
        // for (String s : possibleMoves)
        // {
        //     System.out.println(s);
        // }
        assert possibleMoves1.size() == 11;

        List<String> possibleMoves2 = blackB1.moves(b, "h8");
        assert possibleMoves2.size() == 5;
    }

    public static void bishopAndRookOpponentTest2()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteB1 = Piece.createPiece("wb");
        b.addPiece(whiteB1, "f2");

        Piece blackR1 = Piece.createPiece("br");
        b.addPiece(blackR1, "h4");

        List<String> possibleMoves1 = whiteB1.moves(b, "f2");
        assert possibleMoves1.size() == 9;
        
        List<String> possibleMoves2 = blackR1.moves(b, "h4");
        //System.out.println(possibleMoves2.size());
        assert possibleMoves2.size() == 14;

        List<String> possibleMoves3 = blackR1.moves(b, "h2");
        //System.out.println(possibleMoves3.size());
        assert possibleMoves3.size() == 9;
    }

    public static void rookTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteR1 = Piece.createPiece("wr");
        b.addPiece(whiteR1, "f2");

        List<String> possibleMoves1 = whiteR1.moves(b, "f2");
        assert possibleMoves1.size() == 14;
    }

    public static void bishopTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece blackB = Piece.createPiece("bb");
        b.addPiece(blackB, "a8");

        List<String> possibleMoves = blackB.moves(b, "a8");
        // for (String s : possibleMoves)
        // {
        //     System.out.println(s);
        // }
        // System.out.println(possibleMoves.size());
        assert possibleMoves.size() == 7;
    }

    public static void main(String[] args)
    {
	    test1();
        // filesParsing_NotEnoughArgument();
        // filesParsingTest_TooManyArguments();
        // filesParsingTest_CorrectFilesAndFormat();
        // filesParsingTest_InCorrectLayoutFileFormat();
        // filesParsingTest_InCorrectMoveFileFormat();
        // filesParsingTest_InCorrectFilesFormat();
        // filesParsingTest_NonExistanceFileName();
        // filesParsingTest_NonExistanceFileNames();
        // filesParsingTest_CommentAtEndAndMid();

        pawnColorTest();
        registerPieceTest();
        createKnownPiecesTest();
        createUnknownPiecesTest();
        // pieceLocationCorrectLocFormatTest();
        // pieceLocationIncorrectLocFormatTest();
        // addAndGetPieceInCorrectFormatTest();

        justPawnOnBoardTesting();
        blackPawnWithOpponentOnBoardTesting1();
        blackPawnWithOpponentOnBoardTesting2();
        withPawnWithTeamAndOpponentPieceTest();
        blackAndWhitePawnOnBoardTest();
        blackRookWithOpponentOnBoardTest1();
        blackRookWithOpponentOnBoardTest2();
        whiteRookWithOpponentOnBoardTest1();
        whiteRookWithOpponentOnBoardTest2();
        bishopAndRookOpponentTest1();
        bishopAndRookOpponentTest2();
        rookTest1();
        bishopTest1();
    }

}