package Main;

public class Board {
    private int height;
    private int width;
    private Piece [][] board;
    private int numMove;

    /**
     * Constructs a board
     * @param height the height of the board
     * @param width the width of the board
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        board = new Piece[height][width];
        numMove = 1;
    }

    /**
     * Get the number of moves
     * @return the number of move
     */
    public int getNumMove() {
        return numMove;
    }

    /**
     * Set the number of moves
     * @param n
     */
    public void setNumMove(int n) {
        numMove = n;
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
     */
    public void MovePiece(Piece piece, int destX, int destY) {
        if(canMove(piece, destX, destY)){
            board[piece.x][piece.y] = null;
            board[destX][destY] = piece;
            piece.x = destX;
            piece.y = destY;
            numMove++;
        }

    }

}
