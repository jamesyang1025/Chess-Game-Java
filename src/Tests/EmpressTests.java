package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * All tests for Empress piece
 */
class EmpressTests {

    /**
     * Tests when moving the Empress to an invalid space (off the board)
     */
    @Test
    void EmpressMoveOutOfBound() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 1, 5, 1);
        board.getBoard()[1][5] = empress;
        board.movePiece(empress, -1, 5);
    }

    /**
     * Tests when moving the Empress up to an empty space on the board
     */
    @Test
    void EmpressMoveUp() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 3, 4, 1);

        board.getBoard()[3][4] = empress;

        board.movePiece(empress, 3, 2);

        Assertions.assertEquals(empress, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[3][4]);
    }

    /**
     * Test when moving the Empress up to a place where the path is blocked
     */
    @Test
    void EmpressMoveUpBlocked() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 4, 4, 1);
        Piece empress2 = new Empress(board, 4, 2, 1);

        board.getBoard()[4][4] = empress1;
        board.getBoard()[4][2] = empress2;

        board.movePiece(empress1, 4, 1);

        Assertions.assertEquals(empress1, board.getBoard()[4][4]);
        Assertions.assertEquals(empress2, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[4][1]);
    }

    /**
     * Tests when moving the Empress down to an empty space on the board
     */
    @Test
    void EmpressMoveDown() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 3, 2, 1);

        board.getBoard()[3][2] = empress;

        board.movePiece(empress, 3, 7);

        Assertions.assertEquals(empress, board.getBoard()[3][7]);
        Assertions.assertNull(board.getBoard()[3][2]);
    }

    /**
     * Test when moving the Empress down to a place where the path is blocked
     */
    @Test
    void EmpressMoveDownBlocked() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 2, 1, 1);
        Piece empress2 = new Empress(board, 2, 4, 1);

        board.getBoard()[2][1] = empress1;
        board.getBoard()[2][4] = empress2;

        board.movePiece(empress1, 2, 7);

        Assertions.assertEquals(empress1, board.getBoard()[2][1]);
        Assertions.assertEquals(empress2, board.getBoard()[2][4]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Empress left to an empty space on the board
     */
    @Test
    void EmpressMoveLeft() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 7, 6, 1);

        board.getBoard()[7][6] = empress;

        board.movePiece(empress, 1, 6);

        Assertions.assertEquals(empress, board.getBoard()[1][6]);
        Assertions.assertNull(board.getBoard()[7][6]);
    }

    /**
     * Test when moving the Empress left to a place where the path is blocked
     */
    @Test
    void EmpressMoveLeftBlocked() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 6, 5, 1);
        Piece empress2 = new Empress(board, 4, 5, 1);

        board.getBoard()[6][5] = empress1;
        board.getBoard()[4][5] = empress2;

        board.movePiece(empress1, 1, 5);

        Assertions.assertEquals(empress1, board.getBoard()[6][5]);
        Assertions.assertEquals(empress2, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[1][5]);
    }

    /**
     * Tests when moving the Empress right to an empty space on the board
     */
    @Test
    void EmpressMoveRight() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 3, 5, 1);

        board.getBoard()[3][5] = empress;

        board.movePiece(empress, 7, 5);

        Assertions.assertEquals(empress, board.getBoard()[7][5]);
        Assertions.assertNull(board.getBoard()[3][5]);
    }

    /**
     * Test when moving the Empress right to a place where the path is blocked
     */
    @Test
    void EmpressMoveRightBlocked() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 2, 7, 1);
        Piece empress2 = new Empress(board, 6, 7, 1);

        board.getBoard()[2][7] = empress1;
        board.getBoard()[6][7] = empress2;

        board.movePiece(empress1, 7, 7);

        Assertions.assertEquals(empress1, board.getBoard()[2][7]);
        Assertions.assertEquals(empress2, board.getBoard()[6][7]);
        Assertions.assertNull(board.getBoard()[7][7]);
    }

    /**
     * Tests when moving the Empress left 2 squares and up 1 square
     */
    @Test
    void EmpressMoveLeft2Up1() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 0, 4);

        Assertions.assertEquals(empress, board.getBoard()[0][4]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress left 1 squares and up 2 squares
     */
    @Test
    void EmpressMoveLeft1Up2() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 1, 3);

        Assertions.assertEquals(empress, board.getBoard()[1][3]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress right 2 squares and up 1 square
     */
    @Test
    void EmpressMoveRight2Up1() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 4, 4);

        Assertions.assertEquals(empress, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress right 1 squares and up 2 squares
     */
    @Test
    void EmpressMoveRight1Up2() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 3, 3);

        Assertions.assertEquals(empress, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress left 2 squares and down 1 square
     */
    @Test
    void EmpressMoveLeft2Down1() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 0, 6);

        Assertions.assertEquals(empress, board.getBoard()[0][6]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress left 1 square and down 2 squares
     */
    @Test
    void EmpressMoveLeft1Down2() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 1, 7);

        Assertions.assertEquals(empress, board.getBoard()[1][7]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress right 2 squares and down 1 square
     */
    @Test
    void EmpressMoveRight2Down1() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 4, 6);

        Assertions.assertEquals(empress, board.getBoard()[4][6]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress right 1 square and down 2 squares
     */
    @Test
    void EmpressMoveRight1Down2() {
        Board board = new Board(8, 8);
        Piece empress = new Empress(board, 2, 5, 1);

        board.getBoard()[2][5] = empress;

        board.movePiece(empress, 3, 7);

        Assertions.assertEquals(empress, board.getBoard()[3][7]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Empress like Rook to a space already containing one of his/her pieces
     */
    @Test
    void EmpressRookMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 6, 3, 1);
        Piece empress2 = new Empress(board, 6, 6, 1);

        board.getBoard()[6][3] = empress1;
        board.getBoard()[6][6] = empress2;

        board.movePiece(empress1, 6, 6);

        Assertions.assertEquals(empress1, board.getBoard()[6][3]);
        Assertions.assertEquals(empress2, board.getBoard()[6][6]);
    }

    /**
     * Tests when the Empress tries to move as a Rook and capture an opponent piece
     */
    @Test
    void EmpressRookCapture() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 3, 2, 1);
        Piece empress2 = new Empress(board, 6, 2, 2);

        board.getBoard()[3][2] = empress1;
        board.getBoard()[6][2] = empress2;

        board.movePiece(empress1, 6, 2);

        Assertions.assertEquals(empress1, board.getBoard()[6][2]);
        Assertions.assertNull(board.getBoard()[3][2]);
    }

    /**
     * Tests when moving the Empress as a Knight to a space already containing one of his/her pieces
     */
    @Test
    void EmpressMoveToFriendlyOccupied() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 0, 6, 1);
        Piece pawn = new Pawn(board, 1, 4, 1);

        board.getBoard()[0][6] = empress1;
        board.getBoard()[1][4] = pawn;

        board.movePiece(empress1, 1, 4);

        Assertions.assertEquals(empress1, board.getBoard()[0][6]);
        Assertions.assertEquals(pawn, board.getBoard()[1][4]);
    }

    /**
     * Tests when the Empress tries to move as a Knight and capture an opponent piece
     */
    @Test
    void EmpressCapture() {
        Board board = new Board(8, 8);
        Piece empress1 = new Empress(board, 1, 5, 1);
        Piece empress2 = new Empress(board, 3, 4, 2);

        board.getBoard()[1][5] = empress1;
        board.getBoard()[3][4] = empress2;


        board.movePiece(empress1, 3, 4);

        Assertions.assertEquals(empress1, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[1][5]);
    }
    
}
