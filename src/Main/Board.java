package Main;

import java.util.Vector;

public class Board {
    private int height;
    private int width;
    private Piece [][] board;
    private int turns;

    public Vector<Piece> player1Pieces;
    public Vector<Piece> player2Pieces;

    /**
     * Constructs a board
     * @param height the height of the board
     * @param width the width of the board
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        board = new Piece[height][width];
        turns = 1;
        player1Pieces = new Vector<Piece>(width * height);
        player2Pieces = new Vector<Piece>(width * height);

    }

    /**
     * Get player1Pieces
     * @return the vector player1Pieces
     */
    public Vector<Piece> getPlayer1Pieces() {
        return player1Pieces;
    }

    /**
     * Get player2Pieces
     * @return the vector player2Pieces
     */
    public Vector<Piece> getPlayer2Pieces() {
        return player2Pieces;
    }

    /**
     * Get the number of turns
     * @return the number of turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Set the number of turns
     * @param n
     */
    public void setTurns(int n) {
        turns = n;
    }

    /**
     * Get the current board
     * @return the board
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * Get the width of the board
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the board
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Check if the piece can move to the destination
     * @param piece the Main.Piece to move
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Piece can move, false otherwise
     */
    public boolean canMove(Piece piece, int destX, int destY) {
        if(piece instanceof King){
            if(((King) piece).canMove(destX, destY)){
                return true;
            }
        }

        if(piece instanceof Rook){
            if(((Rook) piece).canMove(destX, destY)){
                return true;
            }
        }

        if(piece instanceof Bishop){
            if(((Bishop) piece).canMove(destX, destY)){
                return true;
            }

        }

        if(piece instanceof Queen){
            if(((Queen) piece).canMove(destX, destY)){
                return true;
            }
        }

        if(piece instanceof Knight){
            if(((Knight) piece).canMove(destX, destY)){
                return true;
            }
        }

        if(piece instanceof Pawn){
            if(((Pawn) piece).canMove(destX, destY)){
                return true;
            }
        }
        return false;
    }

    /**
     * Move the piece to the destination location
     * @param piece the Main.Piece to be moved
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if moved piece successfully, false otherwise
     */
    public boolean movePiece(Piece piece, int destX, int destY) {
        if(canMove(piece, destX, destY)){
            board[piece.x][piece.y] = null;
            board[destX][destY] = piece;
            piece.x = destX;
            piece.y = destY;
            turns++;
            return true;
        }
        return false;
    }

    /**
     * Undo a move
     * @param piece the piece to undo move
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     */
    public void undoMovePiece(Piece piece, int destX, int destY){
        board[piece.x][piece.y] = null;
        board[destX][destY] = piece;
        piece.x = destX;
        piece.y = destY;
    }



}
