package Tests;
import Main.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KingTests {

    /**
     * Tests when moving the King to an invalid space (out of range)
     */
    @Test
    void KingMoveOutOfRange() {
        Board board = new Board(8, 8);
        Piece king = new King(board, 6, 6, 1);

        board.getBoard()[6][6] = king;

        board.movePiece(king, -1, 8);
        board.movePiece(king, 2, 6);

        Assertions.assertEquals(king, board.getBoard()[6][6]);
        Assertions.assertNull(board.getBoard()[2][6]);
    }

    /**
     * Tests all kinds of movement of king
     */
    @Test
    void KingMove() {
        Board board = new Board(8, 8);
        Piece king = new King(board, 4, 4, 1);
        Piece rook1 = new Rook(board, 7, 0, 2);

        board.getBoard()[4][4] = king;
        board.getBoard()[7][0] = rook1;

        //move left
        board.movePiece(king, 3, 4);
        Assertions.assertEquals(king, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[4][4]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 1);

        //move up
        board.movePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[3][4]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 2);

        //move right
        board.movePiece(king, 4, 3);
        Assertions.assertEquals(king, board.getBoard()[4][3]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 3);

        //move down
        board.movePiece(king, 4, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[4][3]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 4);

        //move up-left
        board.movePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[4][4]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 5);

        //move up-right
        board.movePiece(king, 4, 2);
        Assertions.assertEquals(king, board.getBoard()[4][2]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 6);

        //move down-left
        board.movePiece(king, 3, 3);
        Assertions.assertEquals(king, board.getBoard()[3][3]);
        Assertions.assertNull(board.getBoard()[4][2]);

        //move rook as player2's turn
        board.movePiece(rook1, 7, 7);

        //move down-right
        board.movePiece(king, 4, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertNull(board.getBoard()[3][3]);

        //cannot move due to occupied by friendly
        Piece rook = new Rook(board, 5, 4, 1);
        board.getBoard()[5][4] = rook;
        board.movePiece(king, 5, 4);
        Assertions.assertEquals(king, board.getBoard()[4][4]);
        Assertions.assertEquals(rook, board.getBoard()[5][4]);

    }

    /**
     * Tests when the King tries to capture opponent's piece
     */
    @Test
    void KingCapture() {
        Board board = new Board(8, 8);
        Piece king = new King(board, 3, 5, 1);
        Piece pawn = new Pawn(board, 3, 4, 2);

        board.getBoard()[3][5] = king;
        board.getBoard()[3][4] = pawn;

        board.movePiece(king, 3, 4);

        Assertions.assertEquals(king, board.getBoard()[3][4]);
        Assertions.assertNull(board.getBoard()[3][5]);
    }

    /**
     * Check checkmate scenario
     */
    @Test
    void KingCheckmate() {
        Board board = new Board(8, 8);
        King king = new King(board, 7, 7, 1);
        Piece king2 = new King(board, 7, 5, 2);
        Piece bishop1 = new Bishop(board, 5, 5, 2);
        Piece bishop2 = new Bishop(board, 4, 5, 2);

        board.getBoard()[7][7] = king;
        board.getBoard()[7][5] = king2;
        board.getBoard()[5][5] = bishop1;
        board.getBoard()[4][5] = bishop2;

        board.player1Pieces.add(king);
        board.player2Pieces.add(king2);
        board.player2Pieces.add(bishop1);
        board.player2Pieces.add(bishop2);


        Assertions.assertTrue(king.checkmate());
    }

    /**
     * checkmate scenario where a friendly piece can save the king
     */
    @Test
    void KingCheckMateFriendlySave() {
        Board board = new Board(8, 8);
        King king = new King(board, 7, 7, 1);
        Piece bishop = new Bishop(board, 3, 6, 1);
        Piece rook1 = new Rook(board, 7, 0, 2);
        Piece rook2 = new Rook(board, 6, 1, 2);

        board.getBoard()[7][7] = king;
        board.getBoard()[3][6] = bishop;
        board.getBoard()[7][0] = rook1;
        board.getBoard()[6][1] = rook2;

        board.player1Pieces.add(king);
        board.player1Pieces.add(bishop);
        board.player2Pieces.add(rook1);
        board.player2Pieces.add(rook2);

        Assertions.assertFalse(king.checkmate());
    }

    /**
     * Chekc stalemate scenario
     */
    @Test
    void KingStalemate() {
        Board board = new Board(8, 8);
        Piece king1 = new King(board, 2, 4, 1);
        King king2 = new King(board, 0, 3, 2);
        Piece queen = new Queen(board, 2, 2, 1);

        board.getBoard()[2][4] = king1;
        board.getBoard()[0][3] = king2;
        board.getBoard()[2][2] = queen;

        board.player1Pieces.add(king1);
        board.player2Pieces.add(king2);
        board.player1Pieces.add(queen);

        Assertions.assertTrue(king2.stalemate());
    }

    /**
     * Friendly scenario where a friendly piece can save the king
     */
    @Test
    void KingStalemateFriendlySave() {
        Board board = new Board(8, 8);
        Piece king1 = new King(board, 2, 4, 1);
        Piece queen = new Queen(board, 2, 2, 1);
        King king2 = new King(board, 0, 3, 2);
        Piece rook = new Rook(board, 7, 3, 2);


        board.getBoard()[2][4] = king1;
        board.getBoard()[0][3] = king2;
        board.getBoard()[2][2] = queen;
        board.getBoard()[7][3] = rook;

        board.player1Pieces.add(king1);
        board.player2Pieces.add(king2);
        board.player1Pieces.add(queen);
        board.player2Pieces.add(rook);

        Assertions.assertFalse(king2.stalemate());
    }



}
