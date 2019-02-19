package GUI.MVC;

import Main.*;

import java.util.Vector;

class BoardModel {

    private Board board;

    private boolean start;

    private int player1Score;

    private int player2Score;

    BoardModel(){
        initializeBoard();
        player1Score = 0;
        player2Score = 0;
        start = false;
    }

    Board getBoard(){
        return board;
    }

    Piece getPiece(int x, int y){
        return board.getBoard()[x][y];
    }

    boolean getStart(){
        return start;
    }

    int getPlayer(){
        if(board.getTurns() % 2 == 1)
            return 1;
        else
            return 2;
    }

    int getOpponent(){
        if(board.getTurns() % 2 == 1)
            return 2;
        else
            return 1;
    }

    void setWinner(int player){
        if(player == 1)
            player1Score++;
        else
            player2Score++;
    }

    void setStart(boolean start){
        this.start = start;
    }

    int getPlayer1Score(){
        return player1Score;
    }

    int getPlayer2Score(){
        return player2Score;
    }

    boolean checkmate(int player){
        Vector<Piece> playerPieces;
        if(player == 1)
            playerPieces = board.player1Pieces;
        else
            playerPieces = board.player2Pieces;

        for(int i = 0; i < playerPieces.size(); i++){
            Piece piece = playerPieces.elementAt(i);
            if(piece instanceof King){
                return ((King) piece).checkmate();
            }
        }

        return false;
    }

    boolean kingChecked(int player){
        Vector<Piece> playerPieces;
        if(player == 1)
            playerPieces = board.player1Pieces;
        else
            playerPieces = board.player2Pieces;

        for(int i = 0; i < playerPieces.size(); i++){
            Piece piece = playerPieces.elementAt(i);
            if(piece instanceof King){
                return ((King) piece).isChecked(piece.getX(), piece.getY());
            }
        }

        return false;
    }


    boolean canSelect(Object object){
        Piece piece = (Piece) object;
        if((piece.getPlayer() == 1 && board.getTurns() % 2 == 1)
                || (piece.getPlayer() == 2 && board.getTurns() % 2 == 0)){
            return true;
        }
        return false;
    }

    boolean canMove(Object object, int destX, int destY){
        Piece piece = (Piece) object;
        if(board.movePiece(piece, destX, destY)){
            return true;
        }
        return false;
    }

    /**
     * Helper function to initialize the chessboard
     */
    void initializeBoard(){

        board = new Board(8, 8);

        board.getBoard()[0][0] = new Rook(board, 0, 0, 2);
        board.getBoard()[1][0] = new Knight(board, 1, 0, 2);
        board.getBoard()[2][0] = new Bishop(board, 2, 0, 2);
        board.getBoard()[3][0] = new Queen(board, 3, 0, 2);
        board.getBoard()[4][0] = new King(board, 4, 0, 2);
        board.getBoard()[5][0] = new Bishop(board, 5, 0, 2);
        board.getBoard()[6][0] = new Knight(board, 6, 0, 2);
        board.getBoard()[7][0] = new Rook(board, 7, 0, 2);
        for(int i = 0; i < 8; i++){
            board.getBoard()[i][1] = new Pawn(board, i, 1, 2);
        }
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 8; x++){
                board.player2Pieces.add(board.getBoard()[x][y]);
            }
        }

        board.getBoard()[0][7] = new Rook(board, 0, 7, 1);
        board.getBoard()[1][7] = new Knight(board, 1, 7, 1);
        board.getBoard()[2][7] = new Bishop(board, 2, 7, 1);
        board.getBoard()[3][7] = new Queen(board, 3, 7, 1);
        board.getBoard()[4][7] = new King(board, 4, 7, 1);
        board.getBoard()[5][7] = new Bishop(board, 5, 7, 1);
        board.getBoard()[6][7] = new Knight(board, 6, 7, 1);
        board.getBoard()[7][7] = new Rook(board, 7, 7, 1);
        for(int i = 0; i < 8; i++){
            board.getBoard()[i][6] = new Pawn(board, i, 6, 1);
        }
        for(int y = 6; y < 8; y++){
            for(int x = 0; x < 8; x++){
                board.player1Pieces.add(board.getBoard()[x][y]);
            }
        }

    }
}
