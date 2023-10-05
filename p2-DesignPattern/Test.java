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

        List<String> possibleMoveLoc = whiteP.moves(b, "c2");
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

        List<String> possibleMoveLoc = blackP.moves(b, "d4");
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

        List<String> possibleMoveLoc = blackP.moves(b, "c6");
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

        List<String> possibleMoveLoc = whiteP1.moves(b, "c2");
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
        // for (String s : possibleMoves3)
        // {
        //     System.out.println(s);
        // }
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

    public static void rookAndPawnOpponentTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteR1 = Piece.createPiece("wr");
        b.addPiece(whiteR1, "d4");

        Piece blackP1 = Piece.createPiece("bp");
        b.addPiece(blackP1, "e3");

        // List<String> whiteRookPossibleMoves1 = whiteR1.moves(b, "d4");
        // assert whiteRookPossibleMoves1.size() == 14;
        // List<String> whiteRookPossibleMoves4 = whiteR1.moves(b, "d1");
        // assert whiteRookPossibleMoves4.size() == 14;
        List<String> whiteRookPossibleMoves2 = whiteR1.moves(b, "d3");
        //System.out.println(whiteRookPossibleMoves2.size());
        // for (String s : whiteRookPossibleMoves2)
        // {
        //     System.out.println(s);
        // }
        assert whiteRookPossibleMoves2.size() == 11;

        List<String> whiteRookPossibleMoves3 = whiteR1.moves(b, "f3");
        assert whiteRookPossibleMoves3.size() == 10;

        List<String> possibleMoves3 = blackP1.moves(b, "e3");
        assert possibleMoves3.size() == 1;
        List<String> possibleMoves4 = blackP1.moves(b, "e5");
        assert possibleMoves4.size() == 2;
    }

    public static void rookPawnBishopOpponentTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteR1 = Piece.createPiece("wr");
        b.addPiece(whiteR1, "d4");

        Piece whiteR2 = Piece.createPiece("wr");
        b.addPiece(whiteR2, "e4");

        List<String> whiteRook = whiteR1.moves(b, "d4");
        //System.out.println(whiteRook.size());
        // for (String s : whiteRook)
        // {
        //     System.out.println(s);
        // }
        assert whiteRook.size() == 10;

        Piece blackP1 = Piece.createPiece("bp");
        b.addPiece(blackP1, "e5");
        
        List<String> blackPawn1 = blackP1.moves(b, "e5");
        assert blackPawn1.size() == 2;
        
        Piece blackB2 = Piece.createPiece("bb");
        b.addPiece(blackB2, "f5");
        // List<String> blackPawn2 = blackP1.moves(b, "e5");
        // assert blackPawn2.size() == 1;

        List<String> blackBishop1 = blackB2.moves(b, "f5");
        assert blackBishop1.size() == 8;

        List<String> blackBishop2 = blackB2.moves(b, "f6");
        // System.out.println(blackBishop2.size());
        // for (String s : blackBishop2)
        // {
        //     System.out.println(s);
        // }
        assert blackBishop2.size() == 6;
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

    public static void whiteQueenTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteQ = Piece.createPiece("wq");
        b.addPiece(whiteQ, "d4");

        List<String> possibleMoves1 = whiteQ.moves(b, "d4");
        assert possibleMoves1.size() == 27;

        List<String> possibleMoves2 = whiteQ.moves(b, "h1");
        assert possibleMoves2.size() == 21;

        Piece whiteP = Piece.createPiece("wp");
        b.addPiece(whiteP, "h4");

        List<String> possibleMoves3 = whiteQ.moves(b, "d4");
        assert possibleMoves3.size() == 26;

        List<String> possibleMoves4 = whiteQ.moves(b, "h1");
        assert possibleMoves4.size() == 16;

        // for (String s : possibleMoves)
        // {
        //     System.out.println(s);
        // }
        // System.out.println(possibleMoves.size());
    }

    public static void whiteKnightTest()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece whiteKnight1 = Piece.createPiece("wn");
        b.addPiece(whiteKnight1, "d3");

        Piece whiteKnight2 = Piece.createPiece("wn");
        b.addPiece(whiteKnight2, "f4");

        Piece blackBishop1 = Piece.createPiece("bb");
        b.addPiece(blackBishop1, "b5");
        //printBoard(b);

        List<String> whiteKnightPossibleMoves1 = whiteKnight1.moves(b, "d3");
        // for (String s : whiteKnightPossibleMoves1)
        // {
        //     System.out.println(s);
        // }
        // System.out.println(whiteKnightPossibleMoves1.size());
        assert whiteKnightPossibleMoves1.size() == 7;

        try
        {
            List<String> whiteKnightPossibleMoves2 = whiteKnight1.moves(b, "b5");
        }
        catch(Exception e)
        {
            assert e instanceof Exception;
        }

        List<String> blackBishopPossibleMoves1 = blackBishop1.moves(b, "b5");
        System.out.println(blackBishopPossibleMoves1.size());
        assert blackBishopPossibleMoves1.size() == 7;


        List<String> whiteKnightPossibleMoves3 = whiteKnight1.moves(b, "a7");
        //System.out.println(whiteKnightPossibleMoves3.size());
        assert whiteKnightPossibleMoves3.size() == 3;

        Piece blackRook1 = Piece.createPiece("br");
        b.addPiece(blackRook1, "b3");

        Piece whitePawn = Piece.createPiece("wp");
        b.addPiece(whitePawn, "b2");
        //printBoard(b);

        List<String> blackRookPossibleMoves = blackRook1.moves(b, "b3");
        assert blackRookPossibleMoves.size() == 5;

        Piece blackKnight1 = Piece.createPiece("bn");
        b.addPiece(blackKnight1, "h8");
        //printBoard(b);
        List<String> blackKnightPossibleMoves1 = blackKnight1.moves(b, "h8");
        // System.out.println(blackKnightPossibleMoves1.size());
        // for (String s : blackKnightPossibleMoves1)
        // {
        //     System.out.println(s);
        // }
        assert blackKnightPossibleMoves1.size() == 2;

        List<String> blackKnightPossibleMoves2 = blackKnight1.moves(b, "a1");
        assert blackKnightPossibleMoves2.size() == 1;

        List<String> blackKnightPossibleMoves3 = blackKnight1.moves(b, "d1");
        // System.out.println(blackKnightPossibleMoves3.size());
        // for (String s : blackKnightPossibleMoves3)
        // {
        //     System.out.println(s);
        // }
        assert blackKnightPossibleMoves3.size() == 4;


        // System.out.println(possibleMoves.size());
        // for (String s : possibleMoves)
        // {
        //     System.out.println(s);
        // }
    }

    public static void printBoard(Board board) 
    {
        System.out.println("   a  b  c  d  e  f  g  h");
        System.out.println("  +------------------------");

        int[] test = new int[] {0, 0};
        for(int row = 7; row >= 0; row--) 
        { 
            System.out.print((row + 1) + " |"); 

            for(int col = 0; col < 8; col++) 
            {
                test[0] = col;
                test[1] = row;
                String loc = board.arrIndicesToLocStr(test);
                Piece piece = board.getPiece(loc);

                if(piece != null) 
                {
                    System.out.printf("%-3s", piece);
                } else //hi sophonni
                {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }
    }

    public static void whiteKingTest1()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Piece whiteK = Piece.createPiece("wk");
        b.addPiece(whiteK, "d4");
        //printBoard(b);
        
        List<String> possibleMoves1 = whiteK.moves(b, "d4");
        // System.out.println(possibleMoves1.size());
        // for (String s : possibleMoves1)
        // {
        //     System.out.println(s);
        // }
        assert possibleMoves1.size() == 8;

        List<String> whiteKingPossibleMoves1 = whiteK.moves(b, "d1");
        assert whiteKingPossibleMoves1.size() == 5;

        Piece whiteKnight = Piece.createPiece("wn");
        b.addPiece(whiteKnight, "d5");

        Piece blackBishop = Piece.createPiece("bb");
        b.addPiece(blackBishop, "f6");
        //printBoard(b);

        List<String> whiteKnightPossibleMoves = whiteKnight.moves(b, "d5");
        assert whiteKnightPossibleMoves.size() == 8;

        List<String> blackBishopPossibleMoves = blackBishop.moves(b, "f6");
        assert blackBishopPossibleMoves.size() == 8;

        List<String> whiteKingPossibleMoves2 = whiteK.moves(b, "d1");
        assert whiteKingPossibleMoves2.size() == 5;

        List<String> whiteKingPossibleMoves3 = whiteK.moves(b, "a1");
        assert whiteKingPossibleMoves3.size() == 3;

        List<String> whiteKingPossibleMoves4 = whiteK.moves(b, "e6");
        assert whiteKingPossibleMoves4.size() == 7;

        Piece blackKing = Piece.createPiece("bk");
        b.addPiece(blackKing, "h8");
        printBoard(b);

        List<String> blackKingPossibleMoves1 = blackKing.moves(b, "h8");
        assert blackKingPossibleMoves1.size() == 3;

        List<String> blackKingPossibleMoves2 = blackKing.moves(b, "g7");
        assert blackKingPossibleMoves2.size() == 7;
    }

/*****************
* Board TESTING  *
*****************/
    public static void clearBoard()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece piece1 = Piece.createPiece("wq");
        b.addPiece(piece1, "d4");
        Piece piece2 = Piece.createPiece("bk");
        b.addPiece(piece2, "e7");
        Piece piece3 = Piece.createPiece("wp");
        b.addPiece(piece3, "b3");
        Piece piece4 = Piece.createPiece("br");
        b.addPiece(piece4, "a1");
        Piece piece5 = Piece.createPiece("wb");
        b.addPiece(piece5, "h8");

        b.clear();

        assert b.getPiece("d4") == null;
        assert b.getPiece("e7") == null;
        assert b.getPiece("b3") == null;
        assert b.getPiece("a1") == null;
        assert b.getPiece("h8") == null;
    }

/**********************
* Move Piece TESTING  *
**********************/
    public static void movePiece()
    {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece piece1 = Piece.createPiece("wq");
        b.addPiece(piece1, "d4");
        Piece piece2 = Piece.createPiece("bk");
        b.addPiece(piece2, "e7");
        Piece piece3 = Piece.createPiece("wp");
        b.addPiece(piece3, "b3");
        Piece piece4 = Piece.createPiece("br");
        b.addPiece(piece4, "a1");
        Piece piece5 = Piece.createPiece("wb");
        b.addPiece(piece5, "h8");
        Piece piece6 = Piece.createPiece("bb");
        b.addPiece(piece6, "c4");

        //printBoard(b);

        try
        {
            b.movePiece("b3", "c8");
        }
        catch (Exception e)
        {
            System.out.println("Fail on Purpose: " + e.toString());
            assert e instanceof Exception;
        }

        b.movePiece("b3", "c4");
        assert b.getPiece("b3") == null;
        //printBoard(b);

        try
        {
            b.movePiece("c4", "d4");
        }
        catch (Exception e)
        {
            System.out.println("Fail on Purpose: " + e.toString());
            assert e instanceof Exception;
        }
        //printBoard(b);

        try
        {
            b.movePiece("c4", "c6");
        }
        catch (Exception e)
        {
            System.out.println("Fail on Purpose: " + e.toString());
            assert e instanceof Exception;
        }
        //printBoard(b);
        
        b.movePiece("c4", "c5");
        //printBoard(b);
        
        b.movePiece("a1", "d1");
        printBoard(b);
        
        List<String> blackRookPossibleMoves = piece4.moves(b, "d1");
        assert blackRookPossibleMoves.size() == 10;
        
        try
        {
            b.movePiece("k0", "T6");
        }
        catch (Exception e)
        {
            System.out.println("Fail on Purpose: " + e.toString());
            assert e instanceof Exception;
        }
    }

    public static void main(String[] args)
    {
	    // test1();
        // filesParsing_NotEnoughArgument();
        // filesParsingTest_TooManyArguments();
        // filesParsingTest_CorrectFilesAndFormat();
        // filesParsingTest_InCorrectLayoutFileFormat();
        // filesParsingTest_InCorrectMoveFileFormat();
        // filesParsingTest_InCorrectFilesFormat();
        // filesParsingTest_NonExistanceFileName();
        // filesParsingTest_NonExistanceFileNames();
        // filesParsingTest_CommentAtEndAndMid();

        //// pieceLocationCorrectLocFormatTest();
        //// pieceLocationIncorrectLocFormatTest();
        //// addAndGetPieceInCorrectFormatTest();
        pawnColorTest();
        registerPieceTest();
        createKnownPiecesTest();
        createUnknownPiecesTest();
        
        justPawnOnBoardTesting();
        blackPawnWithOpponentOnBoardTesting1();
        blackPawnWithOpponentOnBoardTesting2();
        withPawnWithTeamAndOpponentPieceTest();
        blackRookWithOpponentOnBoardTest1();
        blackRookWithOpponentOnBoardTest2();
        whiteRookWithOpponentOnBoardTest1();
        whiteRookWithOpponentOnBoardTest2();
        bishopAndRookOpponentTest1();
        bishopTest1();
        rookTest1();
        rookPawnBishopOpponentTest();
        
        rookAndPawnOpponentTest();
        blackAndWhitePawnOnBoardTest();
        bishopAndRookOpponentTest2();

        whiteQueenTest1();

        clearBoard();
        whiteKnightTest();

        whiteKingTest1();

        movePiece();
    }
}