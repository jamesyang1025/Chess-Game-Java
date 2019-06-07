package Main;

/**
 * An abstract Piece class that covers the basics of a general piece
 */
public abstract class Piece {
    int x;
    int y;
    int player;

    /**
     * The chessboard
     */
    protected Board board;

    /**
     * Constructs a general chess piece
     * @param board the chessboard
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @param player which player does the piece belong to (player1 or player2)
     */
    public Piece(Board board, int x, int y, int player){
        this.board = board;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    /**
     * Get the player the piece belongs to
     * @return the player
     */
    public int getPlayer(){
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * Get x coordinate
     * @return x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Get y coordinate
     * @return y coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * An abstract method that check whether the piece can move to the destination
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if can, false otherwise
     */
    public abstract boolean canMove(int destX, int destY);

    /**
     * Check if the destination if out of the bound
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if out of bound, false otherwise
     */
    boolean checkInBound(int destX, int destY){

        return destX >= 0 && destX <= board.getWidth() - 1 && destY >= 0 && destY <= board.getHeight() - 1;
    }

    /**
     * Check whether the Main.Piece will be blocked horizontally or vertically
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if blocked, false otherwise
     */
    private boolean checkParallelBlocked(int destX, int destY){

        if(this.x == destX && this.y != destY){
            //move vertically
            for(int j = Math.min(this.y, destY) + 1; j < Math.max(this.y, destY); j++){
                if(board.getBoard()[destX][j] != null){
                    return true;
                }
            }

        }

        if(this.x != destX && this.y == destY){
            //move horizontally
            for(int i = Math.min(this.x, destX) + 1; i < Math.max(this.x, destX); i++){
                if(board.getBoard()[i][destY] != null){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check whether the Main.Piece will be blocked diagonally
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if blocked, false otherwise
     */
    private boolean checkDiagonalBlocked(int destX, int destY){

        //the absolute difference between x and destX
        int diffX = Math.abs(x - destX);
        int diffY = Math.abs(y - destY);

        if(diffX != 0&& diffX == diffY){

            //the piece move diagonally
            if(destX < x && destY < y){

                //destination is at the upper-left of the piece
                if (iterateTopLeftToBottomRight(diffX, destX, destY)) return true;

            }

            if(destX < x && destY > y){

                //destination is at the bottom-left of the piece
                if (iterateBottomLeftToTopRight(diffX, destX, destY)) return true;

            }

            if(destX > x && destY < y){

                //destination is at the upper-right of the piece
                if (iterateBottomLeftToTopRight(diffX, x, y)) return true;
            }

            if(destX > x && destY > y){

                //destination is at the bottom-right of the piece
                return iterateTopLeftToBottomRight(diffX, x, y);
            }


        }
        return false;
    }

    /**
     * Iterate diagonally from bottom-left to top-right and check if there is any piece blocked
     * @param diffX the absolute difference between between the x coordinate
     * @param minX the x coordinate of the bottom-left location
     * @param minY the y coordinate of the bottom-left location
     * @return true if the path is blocked, false otherwise
     */
    private boolean iterateBottomLeftToTopRight(int diffX, int minX, int minY) {
        for (int i = 1; i < diffX; i++) {
            if (board.getBoard()[minX + i][minY - i] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterate diagonally from Top-left to Bottom-right and check if there is any piece blocked
     * @param diffX the absolute difference between between the x coordinate
     * @param minX the x coordinate of the Top-left location
     * @param minY the y coordinate of the Top-left location
     * @return true if the path is blocked, false otherwise
     */
    private boolean iterateTopLeftToBottomRight(int diffX, int minX, int minY) {
        for (int i = 1; i < diffX; i++) {
            if (board.getBoard()[minX + i][minY + i] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the destination is occupied by another piece
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the destination is occupied, false otherwise
     */
    boolean checkOccupied(int destX, int destY){

        return board.getBoard()[destX][destY] != null;
    }

    /**
     * capture other pieces
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return  true if the piece can capture the other piece at the destination, false otherwise
     */
    boolean canCapture(int destX, int destY) {

        return board.getBoard()[destX][destY].player != player;
    }

    /**
     * check if the piece can move parallel to the destination
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if can, false otherwise
     */
    boolean checkParallelCanMove(int destX, int destY){
        if((x == destX && y != destY) || (x != destX && y == destY)) {

            if(!checkParallelBlocked(destX, destY)) {


                if (!checkOccupied(destX, destY)) {

                    return true;

                } else return canCapture(destX, destY);
            }
        }
        return false;
    }

    /**
     * check if the piece can move diagonally to the destination
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if can, false otherwise
     */
    boolean checkDiagonalCanMove(int destX, int destY){
        int diffX = Math.abs(x - destX);
        int diffY = Math.abs(y - destY);

        if(diffX != 0&& diffX == diffY) {

            if(!checkDiagonalBlocked(destX, destY)) {

                if (!checkOccupied(destX, destY)) {

                    return true;

                } else return canCapture(destX, destY);
            }
        }
        return false;
    }

    /**
     * check if the piece can move as a Knight to the destination
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if can, false otherwise
     */
    boolean checkKnightCanMove(int destX, int destY){
        int diffX = Math.abs(x - destX);
        int diffY = Math.abs(y - destY);

        if((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)){
            if(!checkOccupied(destX, destY)){
                return true;
            }else return canCapture(destX, destY);
        }
        return false;
    }

}
