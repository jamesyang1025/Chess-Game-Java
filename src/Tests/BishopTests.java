package Tests;
import Main.Bishop;
import Main.Board;
import Main.Piece;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class BishopTests {

    /**
     * Tests when moving the Bishop to an invalid space (off the board)
     * @throws Exception
     */
    @Test
    public void BishopMoveOutOfBound() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 0, 2, 1);

        board.getBoard()[0][2] = bishop;

        board.MovePiece(bishop, 2, 8);

    }

    /**
     * Tests when moving the Bishop to a top-left empty space on the board
     * @throws Exception
     */
    @Test
    public void BishopMoveTopLeft() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 7, 1);

        board.getBoard()[2][7] = bishop;

        board.MovePiece(bishop, 1, 6);

        Assertions.assertEquals(bishop, board.getBoard()[1][6]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Bishop to a top-left empty space where the path is blocked
     * @throws Exception
     */
    @Test
    public void BishopMoveTopLeftBlocked() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 5, 7, 1);
        Piece bishop2 = new Bishop(board, 3, 5, 1);

        board.getBoard()[5][7] = bishop1;
        board.getBoard()[3][5] = bishop2;


        board.MovePiece(bishop1, 0, 2);

        Assertions.assertEquals(bishop1, board.getBoard()[5][7]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][5]);
        Assertions.assertNull(board.getBoard()[0][2]);
    }

    /**
     * Tests when moving the Bishop to a top-right empty space on the board
     * @throws Exception
     */
    @Test
    public void BishopMoveTopRight() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 7, 1);

        board.getBoard()[2][7] = bishop;

        board.MovePiece(bishop, 4, 5);

        Assertions.assertEquals(bishop, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[2][7]);
    }

    /**
     * Tests when moving the Bishop to a top-right empty space where the path is blocked
     * @throws Exception
     */
    @Test
    public void BishopMoveTopRightBlocked() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 7, 1);
        Piece bishop2 = new Bishop(board, 4, 5, 1);

        board.getBoard()[2][7] = bishop1;
        board.getBoard()[4][5] = bishop2;


        board.MovePiece(bishop1, 6, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][7]);
        Assertions.assertEquals(bishop2, board.getBoard()[4][5]);
        Assertions.assertNull(board.getBoard()[6][3]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space on the board
     * @throws Exception
     */
    @Test
    public void BishopMoveBottomLeft() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 5, 0, 1);

        board.getBoard()[5][0] = bishop;

        board.MovePiece(bishop, 3, 2);

        Assertions.assertEquals(bishop, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[5][0]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space where the path is blocked
     * @throws Exception
     */
    @Test
    public void BishopMoveBottomLeftBlocked() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 5, 0, 1);
        Piece bishop2 = new Bishop(board, 3, 2, 1);

        board.getBoard()[5][0] = bishop1;
        board.getBoard()[3][2] = bishop2;


        board.MovePiece(bishop1, 2, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[5][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][2]);
        Assertions.assertNull(board.getBoard()[2][3]);
    }

    /**
     * Tests when moving the Bishop to a bottom-right empty space on the board
     * @throws Exception
     */
    @Test
    public void BishopMoveBottomRight() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop = new Bishop(board, 2, 0, 1);

        board.getBoard()[2][0] = bishop;

        board.MovePiece(bishop, 3, 1);

        Assertions.assertEquals(bishop, board.getBoard()[3][1]);
        Assertions.assertNull(board.getBoard()[2][0]);
    }

    /**
     * Tests when moving the Bishop to a bottom-left empty space where the path is blocked
     * @throws Exception
     */
    @Test
    public void BishopMoveBottomRightBlocked() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 3, 1, 1);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[3][1] = bishop2;


        board.MovePiece(bishop1, 5, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[3][1]);
        Assertions.assertNull(board.getBoard()[5][3]);
    }

    /**
     * Tests when moving the Bishop to a space already containing one of his/her pieces
     * @throws Exception
     */
    @Test
    public void BishopMoveToFriendlyOccupied() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 5, 3, 1);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[5][3] = bishop2;


        board.MovePiece(bishop1, 5, 3);

        Assertions.assertEquals(bishop1, board.getBoard()[2][0]);
        Assertions.assertEquals(bishop2, board.getBoard()[5][3]);

    }

    /**
     * Tests when the Bishop tries to capture an opponent piece
     * @throws Exception
     */
    @Test
    public void BishopCapture() throws Exception{
        Board board = new Board(8, 8);
        Piece bishop1 = new Bishop(board, 2, 0, 1);
        Piece bishop2 = new Bishop(board, 6, 4, 2);

        board.getBoard()[2][0] = bishop1;
        board.getBoard()[6][4] = bishop2;


        board.MovePiece(bishop1, 6, 4);

        Assertions.assertEquals(bishop1, board.getBoard()[6][4]);
        Assertions.assertNull(board.getBoard()[2][0]);
    }

}
