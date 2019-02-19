package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * All tests for Alfil piece
 */
class AlfilTests {

    /**
     * Tests when moving the Alfil to an invalid space (off the board)
     */
    @Test
    void AlfilMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece alfil = new Alfil(board, 2, 5, 1);

        board.getBoard()[2][5] = alfil;

        board.movePiece(alfil, -1, 8);
    }

    /**
     * Tests when moving the Alfil two square diagonally to upper left
     */
    @Test
    void AlfilJumpTwoSquaresUpLeft() {
        Board board = new Board(8, 8);
        Piece alfil = new Alfil(board, 4, 5, 1);

        board.getBoard()[4][5] = alfil;

        board.movePiece(alfil, 2, 3);

        Assertions.assertEquals(alfil.getX(), 2);
        Assertions.assertEquals(alfil.getY(), 3);
        Assertions.assertEquals(alfil.getPlayer(), 1);
        alfil.setPlayer(2);
        Assertions.assertEquals(alfil.getPlayer(), 2);
        Assertions.assertEquals(alfil, board.getBoard()[2][3]);
        Assertions.assertNull(board.getBoard()[4][5]);
    }

    /**
     * Tests when moving the Alfil two square diagonally to bottom left
     */
    @Test
    void AlfilJumpTwoSquaresBottomLeft() {
        Board board = new Board(8, 8);
        Piece alfil = new Alfil(board, 4, 5, 1);

        board.getBoard()[4][5] = alfil;

        board.movePiece(alfil, 2, 7);


        Assertions.assertEquals(alfil, board.getBoard()[2][7]);
        Assertions.assertNull(board.getBoard()[4][5]);
    }

    /**
     * Tests when moving the Alfil two square diagonally to upper right
     */
    @Test
    void AlfilJumpTwoSquaresUpRight() {
        Board board = new Board(8, 8);
        Piece alfil = new Alfil(board, 4, 5, 1);

        board.getBoard()[4][5] = alfil;

        board.movePiece(alfil, 6, 3);


        Assertions.assertEquals(alfil, board.getBoard()[6][3]);
        Assertions.assertNull(board.getBoard()[4][5]);
    }

    /**
     * Tests when moving the Alfil two square diagonally to bottom right
     */
    @Test
    void AlfilJumpTwoSquaresBottomRight() {
        Board board = new Board(8, 8);
        Piece alfil = new Alfil(board, 4, 5, 1);

        board.getBoard()[4][5] = alfil;

        board.movePiece(alfil, 6, 7);


        Assertions.assertEquals(alfil, board.getBoard()[6][7]);
        Assertions.assertNull(board.getBoard()[4][5]);
    }

    /**
     * Tests when moving the Alfil to a space already containing one of his/her pieces
     */
    @Test
    void AlfilMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece alfil1 = new Alfil(board, 2, 3, 1);
        Piece pawn = new Pawn(board, 4, 1, 1);

        board.getBoard()[2][3] = alfil1;
        board.getBoard()[4][1] = pawn;

        board.movePiece(alfil1, 4, 1);

        Assertions.assertEquals(alfil1, board.getBoard()[2][3]);
        Assertions.assertEquals(pawn, board.getBoard()[4][1]);
    }

    /**
     * Tests when the Alfil tries to capture an opponent piece
     */
    @Test
    void AlfilCapture() {
        Board board = new Board(8, 8);
        Piece alfil1 = new Alfil(board, 0, 5, 1);
        Piece alfil2 = new Alfil(board, 2, 3, 2);

        board.getBoard()[0][5] = alfil1;
        board.getBoard()[2][3] = alfil2;


        board.movePiece(alfil1, 2, 3);

        Assertions.assertEquals(alfil1, board.getBoard()[2][3]);
        Assertions.assertNull(board.getBoard()[0][5]);
    }


}
