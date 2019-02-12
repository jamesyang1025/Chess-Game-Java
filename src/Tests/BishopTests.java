package Tests;
import Main.Bishop;
import Main.Board;
import Main.Piece;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * All tests for Bishop piece
 */
class BishopTests {

    /**
     * Tests when moving the Bishop to an invalid space (off the board)
     */
    @Test
    void BishopMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 0, 2, 1);

        board.getBoard()[0][2] = bishop;

        board.movePiece(bishop, 2, 8);

    }

    /**
     * Tests when moving the Bishop to a top-left empty space on the board
     */
    @Test
    void BishopMoveTopLeft() {
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 7, 1);

        board.getBoard()[2][7] = bishop;

        board.movePiece(bishop, 1, 6);

        Assertions.assertEquals(bishop, board.getBoard()[1][6]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Bishop to a top-left empty space where the path is blocked
     */
    @Test
    void BishopMoveTopLeftBlocked() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 5, 7, 1);
        Piece bishop2 = new Bishop(board, 3, 5, 1);

        board.getBoard()[5][7] = bishop1;
        board.getBoard()[3][5] = bishop2;


        board.movePiece(bishop1, 0, 2);

        Assertions.assertEquals(bishop1, board.getBoard()[5][7]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][5]);
        Assertions.assertNull(board.getBoard()[0][2]);
    }

    /**
     * Tests when moving the Bishop to a top-right empty space on the board
     */
    @Test
    void BishopMoveTopRight() {
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 7, 1);

        board.getBoard()[2][7] = bishop;

        board.movePiece(bishop, 4, 5);

        Assertions.assertEquals(bishop, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Bishop to a top-right empty space where the path is blocked
     */
    @Test
    void BishopMoveTopRightBlocked() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 7, 1);
        Piece bishop2 = new Bishop(board, 4, 5, 1);

        board.getBoard()[2][7] = bishop1;
        board.getBoard()[4][5] = bishop2;


        board.movePiece(bishop1, 6, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][7]);
        Assertions.assertEquals(bishop2, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[6][3]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space on the board
     */
    @Test
    void BishopMoveBottomLeft() {
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 5, 0, 1);

        board.getBoard()[5][0] = bishop;

        board.movePiece(bishop, 3, 2);

        Assertions.assertEquals(bishop, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[5][0]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space where the path is blocked
     */
    @Test
    void BishopMoveBottomLeftBlocked() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 5, 0, 1);
        Piece bishop2 = new Bishop(board, 3, 2, 1);

        board.getBoard()[5][0] = bishop1;
        board.getBoard()[3][2] = bishop2;


        board.movePiece(bishop1, 2, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[5][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[2][3]);
    }

    /**
     * Tests when moving the Bishop to a bottom-right empty space on the board
     */
    @Test
    void BishopMoveBottomRight() {
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 0, 1);

        board.getBoard()[2][0] = bishop;

        board.movePiece(bishop, 3, 1);

        Assertions.assertEquals(bishop, board.getBoard()[3][1]);
        Assertions.assertNull(board.getBoard()[2][0]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space where the path is blocked
     */
    @Test
    void BishopMoveBottomRightBlocked() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 3, 1, 1);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[3][1] = bishop2;


        board.movePiece(bishop1, 5, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][1]);
        Assertions.assertNull(board.getBoard()[5][3]);
    }

    /**
     * Tests when moving the Bishop to a space already containing one of his/her pieces
     */
    @Test
    void BishopMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 5, 3, 1);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[5][3] = bishop2;


        board.movePiece(bishop1, 5, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[5][3]);

    }

    /**
     * Tests when the Bishop tries to capture an opponent piece
     */
    @Test
    void BishopCapture() {
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 6, 4, 2);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[6][4] = bishop2;


        board.movePiece(bishop1, 6, 4);

        Assertions.assertEquals(bishop1, board.getBoard()[6][4]);
        Assertions.assertNull(board.getBoard()[2][0]);
    }

}
