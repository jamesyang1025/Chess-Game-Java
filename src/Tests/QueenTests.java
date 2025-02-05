package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * All tests for Queen piece
 */
class QueenTests {

    /**
     * Tests when moving the Queen to an invalid space (off the board)
     */
    @Test
    void QueenMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 3, 0, 1);

        board.getBoard()[3][0] = queen;

        board.movePiece(queen, -1, 8);
    }


    /**
     * Tests when moving the Queen up to an empty space on the board
     */
    @Test
    void QueenMoveUp() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 3, 5, 1);

        board.getBoard()[3][5] = queen;

        board.movePiece(queen, 3, 2);

        Assertions.assertEquals(queen, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[3][5]);
    }

    /**
     * Test when moving the Queen up to a place where the path is blocked
     */
    @Test
    void QueenMoveUpBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 3, 6, 1);
        Piece queen2 = new Queen(board, 3, 4, 2);

        board.getBoard()[3][6] = queen1;
        board.getBoard()[3][4] = queen2;

        board.movePiece(queen1, 3, 0);

        Assertions.assertEquals(queen1, board.getBoard()[3][6]);
        Assertions.assertEquals(queen2, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[3][0]);
    }

    /**
     * Tests when moving the Queen down to an empty space on the board
     */
    @Test
    void QueenMoveDown() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 4, 4, 1);

        board.getBoard()[4][4] = queen;

        board.movePiece(queen, 4, 5);

        Assertions.assertEquals(queen, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[4][4]);
    }

    /**
     * Test when moving the Queen down to a place where the path is blocked
     */
    @Test
    void QueenMoveDownBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 7, 3, 1);
        Piece queen2 = new Queen(board, 7, 4, 2);

        board.getBoard()[7][3] = queen1;
        board.getBoard()[7][4] = queen2;

        board.movePiece(queen1, 7, 7);

        Assertions.assertEquals(queen1, board.getBoard()[7][3]);
        Assertions.assertEquals(queen2, board.getBoard()[7][4]);
        Assertions.assertNull(board.getBoard()[7][7]);
    }

    /**
     * Tests when moving the Queen left to an empty space on the board
     */
    @Test
    void QueenMoveLeft() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 5, 5, 1);

        board.getBoard()[5][5] = queen;

        board.movePiece(queen, 0, 5);

        Assertions.assertEquals(queen, board.getBoard()[0][5]);
        Assertions.assertNull(board.getBoard()[5][5]);
    }

    /**
     * Test when moving the Queen left to a place where the path is blocked
     */
    @Test
    void QueenMoveLeftBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 6, 2, 1);
        Piece queen2 = new Queen(board, 4, 2, 2);

        board.getBoard()[6][2] = queen1;
        board.getBoard()[4][2] = queen2;

        board.movePiece(queen1, 0, 2);

        Assertions.assertEquals(queen1, board.getBoard()[6][2]);
        Assertions.assertEquals(queen2, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[0][2]);
    }

    /**
     * Tests when moving the Queen right to an empty space on the board
     */
    @Test
    void QueenMoveRight() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 2, 2, 1);

        board.getBoard()[2][2] = queen;

        board.movePiece(queen, 7, 2);

        Assertions.assertEquals(queen, board.getBoard()[7][2]);
        Assertions.assertNull(board.getBoard()[2][2]);
    }

    /**
     * Test when moving the Queen right to a place where the path is blocked
     */
    @Test
    void QueenMoveRightBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 2, 3, 1);
        Piece queen2 = new Queen(board, 5, 3, 2);

        board.getBoard()[2][3] = queen1;
        board.getBoard()[5][3] = queen2;

        board.movePiece(queen1, 7, 3);

        Assertions.assertEquals(queen1, board.getBoard()[2][3]);
        Assertions.assertEquals(queen2, board.getBoard()[5][3]);
        Assertions.assertNull(board.getBoard()[7][3]);
    }

    /**
     * Tests when moving the Queen to a top-left empty space on the board
     */
    @Test
    void QueenMoveTopLeft() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 2, 7, 1);

        board.getBoard()[2][7] = queen;

        board.movePiece(queen, 1, 6);

        Assertions.assertEquals(queen, board.getBoard()[1][6]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Queen to a top-left empty space where the path is blocked
     */
    @Test
    void QueenMoveTopLeftBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 5, 7, 1);
        Piece queen2 = new Queen(board, 3, 5, 2);

        board.getBoard()[5][7] = queen1;
        board.getBoard()[2][4] = queen2;


        board.movePiece(queen1, 0, 2);

        Assertions.assertEquals(queen1, board.getBoard()[5][7]);
        Assertions.assertEquals(queen2, board.getBoard()[2][4]);
        Assertions.assertNull(board.getBoard()[0][2]);
    }

    /**
     * Tests when moving the Queen to a top-right empty space on the board
     */
    @Test
    void QueenMoveTopRight() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 2, 7, 1);

        board.getBoard()[2][7] = queen;

        board.movePiece(queen, 4, 5);

        Assertions.assertEquals(queen, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Queen to a top-right empty space where the path is blocked
     */
    @Test
    void QueenMoveTopRightBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 2, 7, 1);
        Piece queen2 = new Queen(board, 4, 5, 2);

        board.getBoard()[2][7] = queen1;
        board.getBoard()[5][4] = queen2;


        board.movePiece(queen1, 6, 3);

        Assertions.assertEquals(queen1, board.getBoard()[2][7]);
        Assertions.assertEquals(queen2, board.getBoard()[5][4]);
        Assertions.assertNull(board.getBoard()[6][3]);
    }

    /**
     * Tests when moving the Queen to a bottom-left empty space on the board
     */
    @Test
    void QueenMoveBottomLeft() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 5, 0, 1);

        board.getBoard()[5][0] = queen;

        board.movePiece(queen, 2, 3);

        Assertions.assertEquals(queen, board.getBoard()[2][3]);
        Assertions.assertNull(board.getBoard()[5][0]);
    }

    /**
     * Tests when moving the Queen to a bottom-left empty space where the path is blocked
     */
    @Test
    void QueenMoveBottomLeftBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 5, 0, 1);
        Piece queen2 = new Queen(board, 3, 2, 2);

        board.getBoard()[5][0] = queen1;
        board.getBoard()[2][3] = queen2;


        board.movePiece(queen1, 0, 5);

        Assertions.assertEquals(queen1, board.getBoard()[5][0]);
        Assertions.assertEquals(queen2, board.getBoard()[2][3]);
        Assertions.assertNull(board.getBoard()[0][5]);
    }

    /**
     * Tests when moving the Queen to a bottom-right empty space on the board
     */
    @Test
    void QueenMoveBottomRight() {
        Board board = new Board(8, 8);
        Piece queen = new Queen(board, 3, 0, 1);

        board.getBoard()[3][0] = queen;

        board.movePiece(queen, 4, 1);

        Assertions.assertEquals(queen, board.getBoard()[4][1]);
        Assertions.assertNull(board.getBoard()[3][0]);
    }

    /**
     * Tests when moving the Queen to a bottom-left empty space where the path is blocked
     */
    @Test
    void QueenMoveBottomRightBlocked() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 2, 0, 1);
        Piece queen2 = new Queen(board, 3, 1, 2);

        board.getBoard()[2][0] = queen1;
        board.getBoard()[3][1] = queen2;


        board.movePiece(queen1, 6, 4);

        Assertions.assertEquals(queen1, board.getBoard()[2][0]);
        Assertions.assertEquals(queen2, board.getBoard()[3][1]);
        Assertions.assertNull(board.getBoard()[6][4]);
    }

    /**
     * Tests when moving the Queen to a space already containing one of his/her pieces
     */
    @Test
    void QueenMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 2, 0, 1);
        Piece rook = new Rook(board, 5, 3, 1);

        board.getBoard()[2][0] = queen1;
        board.getBoard()[6][4] = rook;


        board.movePiece(queen1, 7, 5);

        Assertions.assertEquals(queen1, board.getBoard()[2][0]);
        Assertions.assertEquals(rook, board.getBoard()[6][4]);

    }

    /**
     * Tests when the Queen tries to capture an opponent piece
     */
    @Test
    void QueenCapture() {
        Board board = new Board(8, 8);
        Piece queen1 = new Queen(board, 2, 0, 1);
        Piece queen2 = new Queen(board, 6, 4, 2);

        board.getBoard()[2][0] = queen1;
        board.getBoard()[7][5] = queen2;


        board.movePiece(queen1, 7, 5);

        Assertions.assertEquals(queen1, board.getBoard()[7][5]);
        Assertions.assertNull(board.getBoard()[2][0]);
    }
}
