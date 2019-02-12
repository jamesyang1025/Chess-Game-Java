package Tests;
import Main.Board;
import Main.Piece;
import Main.Rook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * All tests for Rook piece
 */
class RookTests {

    /**
     * Tests when moving the Rook to an invalid space (off the board)
     */
    @Test
    void RookMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece rook = new Rook(board, 1, 5, 1);
        board.getBoard()[1][5] = rook;
        board.movePiece(rook, -1, 5);
    }

    /**
     * Tests when moving the Rook up to an empty space on the board
     */
    @Test
    void RookMoveUp() {
        Board board = new Board(8, 8);
        Piece rook = new Rook(board, 5, 4, 1);

        board.getBoard()[5][4] = rook;

        board.movePiece(rook, 5, 2);

        Assertions.assertEquals(rook, board.getBoard()[5][2]);
        Assertions.assertNull(board.getBoard()[5][4]);
    }

    /**
     * Test when moving the Rook up to a place where the path is blocked
     */
    @Test
    void RookMoveUpBlocked() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 6, 4, 1);
        Piece rook2 = new Rook(board, 6, 2, 1);

        board.getBoard()[6][4] = rook1;
        board.getBoard()[6][2] = rook2;

        board.movePiece(rook1, 6, 1);

        Assertions.assertEquals(rook1, board.getBoard()[6][4]);
        Assertions.assertEquals(rook2, board.getBoard()[6][2]);
        Assertions.assertNull(board.getBoard()[6][1]);
    }

    /**
     * Tests when moving the Rook down to an empty space on the board
     */
    @Test
    void RookMoveDown() {
        Board board = new Board(8, 8);
        Piece rook = new Rook(board, 4, 4, 1);

        board.getBoard()[4][4] = rook;

        board.movePiece(rook, 4, 7);

        Assertions.assertEquals(rook, board.getBoard()[4][7]);
        Assertions.assertNull(board.getBoard()[4][4]);
    }

    /**
     * Test when moving the Rook down to a place where the path is blocked
     */
    @Test
    void RookMoveDownBlocked() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 7, 1, 1);
        Piece rook2 = new Rook(board, 7, 4, 1);

        board.getBoard()[7][1] = rook1;
        board.getBoard()[7][4] = rook2;

        board.movePiece(rook1, 7, 7);

        Assertions.assertEquals(rook1, board.getBoard()[7][1]);
        Assertions.assertEquals(rook2, board.getBoard()[7][4]);
        Assertions.assertNull(board.getBoard()[7][7]);
    }

    /**
     * Tests when moving the Rook left to an empty space on the board
     */
    @Test
    void RookMoveLeft() {
        Board board = new Board(8, 8);
        Piece rook = new Rook(board, 5, 5, 1);

        board.getBoard()[5][5] = rook;

        board.movePiece(rook, 1, 5);

        Assertions.assertEquals(rook, board.getBoard()[1][5]);
        Assertions.assertNull(board.getBoard()[5][5]);
    }

    /**
     * Test when moving the Rook left to a place where the path is blocked
     */
    @Test
    void RookMoveLeftBlocked() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 6, 2, 1);
        Piece rook2 = new Rook(board, 4, 2, 1);

        board.getBoard()[6][2] = rook1;
        board.getBoard()[4][2] = rook2;

        board.movePiece(rook1, 1, 2);

        Assertions.assertEquals(rook1, board.getBoard()[6][2]);
        Assertions.assertEquals(rook2, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[1][2]);
    }

    /**
     * Tests when moving the Rook right to an empty space on the board
     */
    @Test
    void RookMoveRight() {
        Board board = new Board(8, 8);
        Piece rook = new Rook(board, 2, 2, 1);

        board.getBoard()[2][2] = rook;

        board.movePiece(rook, 4, 2);

        Assertions.assertEquals(rook, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[2][2]);
    }

    /**
     * Test when moving the Rook right to a place where the path is blocked
     */
    @Test
    void RookMoveRightBlocked() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 2, 3, 1);
        Piece rook2 = new Rook(board, 6, 3, 1);

        board.getBoard()[2][3] = rook1;
        board.getBoard()[6][3] = rook2;

        board.movePiece(rook1, 7, 3);

        Assertions.assertEquals(rook1, board.getBoard()[2][3]);
        Assertions.assertEquals(rook2, board.getBoard()[6][3]);
        Assertions.assertNull(board.getBoard()[7][3]);
    }

    /**
     * Tests when moving the Rook to a space already containing one of his/her pieces
     */
    @Test
    void RookMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 4, 3, 1);
        Piece rook2 = new Rook(board, 4, 6, 1);

        board.getBoard()[4][3] = rook1;
        board.getBoard()[4][6] = rook2;

        board.movePiece(rook1, 4, 6);

        Assertions.assertEquals(rook1, board.getBoard()[4][3]);
        Assertions.assertEquals(rook2, board.getBoard()[4][6]);
    }

    /**
     * Tests when the Rook tries to capture an opponent piece
     */
    @Test
    void RookCapture() {
        Board board = new Board(8, 8);
        Piece rook1 = new Rook(board, 3, 3, 1);
        Piece rook2 = new Rook(board, 6, 3, 2);

        board.getBoard()[3][3] = rook1;
        board.getBoard()[6][3] = rook2;

        board.movePiece(rook1, 6, 3);

        Assertions.assertEquals(rook1, board.getBoard()[6][3]);
        Assertions.assertNull(board.getBoard()[3][3]);
    }




}
