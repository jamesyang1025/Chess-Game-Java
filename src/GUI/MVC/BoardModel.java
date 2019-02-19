package GUI.MVC;

import Main.*;

import java.util.Vector;

/**
 * Model class
 */
class BoardModel {

    private Board board;

    private boolean start;

    private int player1Score;

    private int player2Score;

    /**
     * Constructor for the boardModel class
     */
    BoardModel(){
        initializeBoard();
        player1Score = 0;
        player2Score = 0;
        start = false;
    }

    /**
     * getter function to get the board
     * @return the chessboard
     */
    Board getBoard(){
        return board;
    }

    /**
     * getter function to get the piece at the given location
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the piece at the given location
     */
    Piece getPiece(int x, int y){
        return board.getBoard()[x][y];
    }

    /**
     * getter function to get the game start status
     * @return true if game started, false otherwise
     */
    boolean getStart(){
        return start;
    }

    /**
     * getter function to get the current player
     * @return the player id
     */
    int getPlayer(){
        if(board.getTurns() % 2 == 1)
            return 1;
        else
            return 2;
    }

    /**
     * getter function to get the opponent
     * @return the opponent id
     */
    int getOpponent(){
        if(board.getTurns() % 2 == 1)
            return 2;
        else
            return 1;
    }

    /**
     * setter function to set the winner for this round of the game
     * @param player the winner id
     */
    void setWinner(int player){
        if(player == 1)
            player1Score++;
        else
            player2Score++;
    }

    /**
     * setter function to set the game start status
     * @param start the game start status
     */
    void setStart(boolean start){
        this.start = start;
    }

    /**
     * getter function to get the player 1's score
     * @return player 1's score
     */
    int getPlayer1Score(){
        return player1Score;
    }

    /**
     * getter function to get the player 2's score
     * @return player 2's score
     */
    int getPlayer2Score(){
        return player2Score;
    }

    /**
     * check if the player is in checkmate
     * @param player the player to check
     * @return true if the player is in checkmate, false otherwise
     */
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

    /**
     * check if the player's king is being checked
     * @param player the player to check
     * @return true if the player's king is being checked, false otherwise
     */
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


    /**
     * check if the player can select the piece to move
     * @param object the piece to select
     * @return true if can, false otherwise
     */
    boolean canSelect(Object object){
        Piece piece = (Piece) object;
        return (piece.getPlayer() == 1 && board.getTurns() % 2 == 1)
                || (piece.getPlayer() == 2 && board.getTurns() % 2 == 0);
    }

    boolean canMove(Object object, int destX, int destY){
        Piece piece = (Piece) object;
        return board.movePiece(piece, destX, destY);
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
        setPawns(1, 2);
        setPlayerPieces(0, 2, board.player2Pieces);

        board.getBoard()[0][7] = new Rook(board, 0, 7, 1);
        board.getBoard()[1][7] = new Knight(board, 1, 7, 1);
        board.getBoard()[2][7] = new Bishop(board, 2, 7, 1);
        board.getBoard()[3][7] = new Queen(board, 3, 7, 1);
        board.getBoard()[4][7] = new King(board, 4, 7, 1);
        board.getBoard()[5][7] = new Bishop(board, 5, 7, 1);
        board.getBoard()[6][7] = new Knight(board, 6, 7, 1);
        board.getBoard()[7][7] = new Rook(board, 7, 7, 1);
        setPawns(6, 1);
        setPlayerPieces(6, 8, board.player1Pieces);

    }

    /**
     * Helper function to initialize the chessboard with custom pieces
     */
    void initializeWithCustomPieces(){
        board = new Board(8, 8);

        board.getBoard()[0][0] = new Rook(board, 0, 0, 2);
        board.getBoard()[1][0] = new Empress(board, 1, 0, 2);
        board.getBoard()[2][0] = new Alfil(board, 2, 0, 2);
        board.getBoard()[3][0] = new Queen(board, 3, 0, 2);
        board.getBoard()[4][0] = new King(board, 4, 0, 2);
        board.getBoard()[5][0] = new Alfil(board, 5, 0, 2);
        board.getBoard()[6][0] = new Empress(board, 6, 0, 2);
        board.getBoard()[7][0] = new Rook(board, 7, 0, 2);

        setPawns(1, 2);
        setPlayerPieces(0, 2, board.player2Pieces);

        board.getBoard()[0][7] = new Rook(board, 0, 7, 1);
        board.getBoard()[1][7] = new Empress(board, 1, 7, 1);
        board.getBoard()[2][7] = new Alfil(board, 2, 7, 1);
        board.getBoard()[3][7] = new Queen(board, 3, 7, 1);
        board.getBoard()[4][7] = new King(board, 4, 7, 1);
        board.getBoard()[5][7] = new Alfil(board, 5, 7, 1);
        board.getBoard()[6][7] = new Empress(board, 6, 7, 1);
        board.getBoard()[7][7] = new Rook(board, 7, 7, 1);
        setPawns(6, 1);

        setPlayerPieces(6, 8, board.player1Pieces);
    }

    private void setPawns(int i2, int i3) {
        for (int i = 0; i < 8; i++) {
            board.getBoard()[i][i2] = new Pawn(board, i, i2, i3);
        }
    }

    private void setPlayerPieces(int i2, int i3, Vector<Piece> player2Pieces) {
        for (int y = i2; y < i3; y++) {
            for (int x = 0; x < 8; x++) {
                player2Pieces.add(board.getBoard()[x][y]);
            }
        }
    }
}
