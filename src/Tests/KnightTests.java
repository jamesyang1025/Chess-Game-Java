package Tests;
import Main.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class KnightTests {

    /**
     * Tests when moving the Knight to an invalid space (off the board)
     * @throws Exception
     */
    @Test
    public void KnightMoveOutOfBound() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, -1, 8);
    }

    /**
     * Tests when moving the Knight left 2 squares and up 1 square
     * @throws Exception
     */
    @Test
    public void KnightMoveLeft2Up1() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 0, 4);

        Assertions.assertEquals(knight, board.getBoard()[0][4]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight left 1 squares and up 2 squares
     * @throws Exception
     */
    @Test
    public void KnightMoveLeft1Up2() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 1, 3);

        Assertions.assertEquals(knight, board.getBoard()[1][3]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight right 2 squares and up 1 square
     * @throws Exception
     */
    @Test
    public void KnightMoveRight2Up1() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 4, 4);

        Assertions.assertEquals(knight, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight right 1 squares and up 2 squares
     * @throws Exception
     */
    @Test
    public void KnightMoveRight1Up2() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 3, 3);

        Assertions.assertEquals(knight, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight left 2 squares and down 1 square
     * @throws Exception
     */
    @Test
    public void KnightMoveLeft2Down1() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 0, 6);

        Assertions.assertEquals(knight, board.getBoard()[0][6]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight left 1 square and down 2 squares
     * @throws Exception
     */
    @Test
    public void KnightMoveLeft1Down2() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 1, 7);

        Assertions.assertEquals(knight, board.getBoard()[1][7]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight right 2 squares and down 1 square
     * @throws Exception
     */
    @Test
    public void KnightMoveRight2Down1() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 4, 6);

        Assertions.assertEquals(knight, board.getBoard()[4][6]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight right 1 square and down 2 squares
     * @throws Exception
     */
    @Test
    public void KnightMoveRight1Down2() throws Exception{
        Board board = new Board(8, 8);
        Piece knight = new Knight(board, 2, 5, 1);

        board.getBoard()[2][5] = knight;

        board.MovePiece(knight, 3, 7);

        Assertions.assertEquals(knight, board.getBoard()[3][7]);
        Assertions.assertNull(board.getBoard()[2][5]);
    }

    /**
     * Tests when moving the Knight to a space already containing one of his/her pieces
     * @throws Exception
     */
    @Test
    public void KnightMoveToFriendlyOccupied() throws Exception{
        Board board = new Board(8, 8);
        Piece knight1 = new Knight(board, 1, 7, 1);
        Piece pawn = new Pawn(board, 2, 5, 1);

        board.getBoard()[1][7] = knight1;
        board.getBoard()[2][5] = pawn;

        board.MovePiece(knight1, 2, 5);

        Assertions.assertEquals(knight1, board.getBoard()[1][7]);
        Assertions.assertEquals(pawn, board.getBoard()[2][5]);
    }

    /**
     * Tests when the Knight tries to capture an opponent piece
     * @throws Exception
     */
    @Test
    public void KnightCapture() throws Exception{
        Board board = new Board(8, 8);
        Piece knight1 = new Knight(board, 0, 4, 1);
        Piece knight2 = new Knight(board, 2, 3, 2);

        board.getBoard()[0][4] = knight1;
        board.getBoard()[2][3] = knight2;


        board.MovePiece(knight1, 2, 3);

        Assertions.assertEquals(knight1, board.getBoard()[2][3]);
        Assertions.assertNull(board.getBoard()[0][4]);
    }

}
