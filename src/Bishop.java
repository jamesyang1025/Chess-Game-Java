public class Bishop extends Piece{

    /**
     * Constructs a Bishop piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Bishop(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Bishop can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Bishop can move, false otherwise
     */
    public Boolean canMove(int destX, int destY){
        if(!checkOutOfBound(destX, destY) && !checkDiagonalBlocked(destX, destY)){

            if(!checkOccupied(destX, destY)){

                return true;

            }else if(canCapture(destX, destY)){

                return true;

            }
        }
        return false;
    }
}
