public class Game {
    private Board board;

    /**
     * Contructs a Game
     */
    public Game(){
        board = new Board(8, 8);
        setPlayer1Pieces();
        setPlayer2Pieces();

    }

    /**
     * set player1's pieces, which is located at the top half of the chessboard
     */
    private void setPlayer1Pieces(){
        for(int i = 0; i < 8; i++){
            Piece pawn = new Pawn(board, i, 1, 2);
            board.getBoard()[i][1] = pawn;
        }

        //create the pieces
        Piece rook1 = new Rook(board, 0, 0, 2);
        Piece rook2 = new Rook(board, 7, 0, 2);
        Piece knight1 = new Knight(board, 1, 0, 2);
        Piece knight2 = new Knight(board, 6, 0, 2);
        Piece bishop1 = new Bishop(board, 2, 0, 2);
        Piece bishop2 = new Bishop(board, 5, 0, 2);
        Piece queen = new Queen(board, 3, 0, 2);
        Piece king = new King(board, 4, 0, 2);

        //assign the pieces
        board.getBoard()[0][0] = rook1;
        board.getBoard()[7][0] = rook2;
        board.getBoard()[1][0] = knight1;
        board.getBoard()[6][0] = knight2;
        board.getBoard()[2][0] = bishop1;
        board.getBoard()[5][0] = bishop2;
        board.getBoard()[3][0] = queen;
        board.getBoard()[4][0] = king;
    }

    /**
     * set player2's pieces, which is located at the bottom half of the chessboard
     */
    private void setPlayer2Pieces(){
        for(int i = 0; i < 8; i++){
            Piece pawn = new Pawn(board, i, 6, 1);
            board.getBoard()[i][6] = pawn;
        }

        //create the pieces
        Piece rook1 = new Rook(board, 0, 7, 1);
        Piece rook2 = new Rook(board, 7, 7, 1);
        Piece knight1 = new Knight(board, 1, 7, 1);
        Piece knight2 = new Knight(board, 6, 7, 1);
        Piece bishop1 = new Bishop(board, 2, 7, 1);
        Piece bishop2 = new Bishop(board, 5, 7, 1);
        Piece queen = new Queen(board, 3, 7, 1);
        Piece king = new King(board, 4, 7, 1);

        //assign the pieces
        board.getBoard()[0][7] = rook1;
        board.getBoard()[7][7] = rook2;
        board.getBoard()[1][7] = knight1;
        board.getBoard()[6][7] = knight2;
        board.getBoard()[2][7] = bishop1;
        board.getBoard()[5][7] = bishop2;
        board.getBoard()[3][7] = queen;
        board.getBoard()[4][7] = king;

    }

}
