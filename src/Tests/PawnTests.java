package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class PawnTests {

    /**
     * Tests when moving the Pawn to an invalid space (off the board)
     */
    @Test
    void PawnMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(board, 6, 6, 1);

        board.getBoard()[2][5] = pawn;

        board.movePiece(pawn, -1, 8);
    }

    /**
     * Tests when moving the pawn forward two squares on the first move
     */
    @Test
    void PawnMoveTwo() {
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(board, 2, 6, 1);

        board.getBoard()[2][6] = pawn;

        board.movePiece(pawn, 2, 4);

        Assertions.assertEquals(pawn, board.getBoard()[2][4]);
        Assertions.assertNull(board.getBoard()[2][6]);
    }

    /**
     * Tests when moving the pawn forward one square
     */
    @Test
    void PawnMoveForward() {
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(board, 4, 6, 1);

        board.getBoard()[4][6] = pawn;

        board.movePiece(pawn, 4, 5);

        Assertions.assertEquals(pawn, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[4][6]);

        board.movePiece(pawn, 4, 4);
        Assertions.assertEquals(pawn, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[4][5]);
    }

    /**
     * Tests when moving the pawn forward one square fail (one piece blocking)
     */
    @Test
    void PawnMoveForwardFail() {
        Board board = new Board(8, 8);
        Piece pawn1 = new Pawn(board, 4, 5, 1);
        Piece pawn2 = new Pawn(board, 4, 4, 2);

        board.getBoard()[4][5] = pawn1;
        board.getBoard()[4][4] = pawn2;

        board.movePiece(pawn1, 4, 4);

        Assertions.assertEquals(pawn1, board.getBoard()[4][5]);
        Assertions.assertEquals(pawn2, board.getBoard()[4][4]);

    }

    /**
     * Tests when moving the pawn forward two squares on the 2nd/3rd/... move
     */
    @Test
    void PawnMoveTwoFail() {
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(board, 2, 6, 1);

        board.getBoard()[2][6] = pawn;

        board.movePiece(pawn, 2, 5);
        board.movePiece(pawn, 2, 3);

        Assertions.assertEquals(pawn, board.getBoard()[2][5]);
        Assertions.assertNull(board.getBoard()[2][6]);
        Assertions.assertNull(board.getBoard()[2][3]);
    }

    /**
     * Tests whether the Pawn can capture the opponent piece at the destination
     */
    @Test
    void canCapture() {
        Board board = new Board(8, 8);
        Piece pawn1 = new Pawn(board, 5, 5, 1);
        Piece pawn2 = new Pawn(board, 4, 4, 2);

        board.getBoard()[5][5] = pawn1;
        board.getBoard()[4][4] = pawn2;

        board.movePiece(pawn1, 4, 4);

        Assertions.assertEquals(pawn1, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[5][5]);
    }

}
