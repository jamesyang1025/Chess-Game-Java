public abstract class Piece {
    protected int x;
    protected int y;
    protected int player;
    protected Board board;

    /**
     * Constructs a general chess piece
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
     * Check if the destination if out of the bound
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if out of bound, false otherwise
     */
    public boolean checkOutOfBound(int destX, int destY){

        if(destX < 0 || destX > board.getWidth() - 1 || destY < 0 || destY > board.getHeight() - 1 ){

            return true;

        }
        return false;
    }

    /**
     * Check whether the Piece will be blocked horizontally or vertically
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if blocked, false otherwise
     */
    public boolean checkParallelBlocked(int destX, int destY){

        if(this.x == destX && this.y != destY){
            //move vertically
            for(int j = Math.min(this.y, destY); j < Math.max(this.y, destY); j++){
                if(board.getBoard()[destX][j] != null){
                    return true;
                }
            }

        }

        if(this.x != destX && this.y == destY){
            //move horizontally
            for(int i = Math.min(this.x, destX); i < Math.max(this.x, destX); i++){
                if(board.getBoard()[i][destY] != null){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check whether the Piece will be blocked diagonally
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if blocked, false otherwise
     */
    public boolean checkDiagonalBlocked(int destX, int destY){

        //the absolute difference between x and destX
        int diffX = Math.abs(x - destX);
        int diffY = Math.abs(y - destY);

        if(diffX != 0 && diffY != 0 && diffX == diffY){

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
                if (iterateTopLeftToBottomRight(diffX, x, y)) return true;
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
        for (int i = 0; i < diffX; i++) {
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
        for (int i = 0; i < diffX; i++) {
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
    public boolean checkOccupied(int destX, int destY){

        if(board.getBoard()[destX][destY] != null){

            return true;

        }
        return false;
    }

    /**
     * capture other pieces
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return  true if the piece can capture the other piece at the destination, false otherwise
     */
    public boolean canCapture(int destX, int destY) {

        if(board.getBoard()[destX][destY].player != player){

            return true;

        }
        return false;
    }

}
