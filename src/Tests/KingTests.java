package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class KingTests {

    /**
     * Tests when moving the King to an invalid space (out of range)
     * @throws Exception
     */
    @Test
    public void KingMoveOutOfRange() throws Exception{
        Board board = new Board(8, 8);
        Piece king = new King(board, 6, 6, 1);

        board.getBoard()[6][6] = king;

        board.MovePiece(king, -1, 8);
        board.MovePiece(king, 2, 6);

        Assertions.assertEquals(king, board.getBoard()[6][6]);
        Assertions.assertNull(board.getBoard()[2][6]);
    }

    /**
     * Tests all kinds of movement of king
     * @throws Exception
     */
    @Test
    public void KingMove() throws Exception{
        Board board = new Board(8, 8);
        Piece king = new King(board, 4, 4, 1);

        board.getBoard()[4][4] = king;

        //move left
        board.MovePiece(king, 3, 4);
        Assertions.assertEquals(king, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[4][4]);

        //move up
        board.MovePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[3][4]);

        //move right
        board.MovePiece(king, 4, 3);
        Assertions.assertEquals(king, board.getBoard()[4][3]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //move down
        board.MovePiece(king, 4, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[4][3]);

        //move up-left
        board.MovePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[4][4]);

        //move up-right
        board.MovePiece(king, 4, 2);
        Assertions.assertEquals(king, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //move down-left
        board.MovePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[4][2]);

        //move down-right
        board.MovePiece(king, 4, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //cannot move due to occupied by friendly
        Piece rook = new Rook(board, 5, 4, 1);
        board.getBoard()[5][4] = rook;
        board.MovePiece(king, 5, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertEquals(rook, board.getBoard()[5][4]);

    }

    /**
     * Tests when the King tries to capture opponent's piece
     * @throws Exception
     */
    @Test
    public void KingCapture() throws Exception{
        Board board = new Board(8, 8);
        Piece king = new King(board, 3, 5, 1);
        Piece pawn = new Pawn(board, 3, 4, 2);

        board.getBoard()[3][5] = king;
        board.getBoard()[3][4] = pawn;

        board.MovePiece(king, 3, 4);

        Assertions.assertEquals(king, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[3][5]);
    }

    /**
     * Check checkmate scenario
     * @throws Exception
     */
    @Test
    public void KingCheckmate() throws Exception{
        Board board = new Board(8, 8);
        Piece king = new King(board, 7, 7, 1);
        Piece rook = new Rook(board, 7, 5, 2);
        Piece bishop1 = new Bishop(board, 5, 5, 2);
        Piece bishop2 = new Bishop(board, 4, 5, 2);

        board.getBoard()[7][7] = king;
        board.getBoard()[7][5] = rook;
        board.getBoard()[5][5] = bishop1;
        board.getBoard()[4][5] = bishop2;

        board.player1Pieces.add(king);
        board.player2Pieces.add(rook);
        board.player2Pieces.add(bishop1);
        board.player2Pieces.add(bishop2);


        Assertions.assertTrue(((King) king).checkmate());
    }

}
