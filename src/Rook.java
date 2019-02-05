public class Rook extends Piece {

    /**
     * Constructs a Rook piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Rook(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Rook can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Rook can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(!checkOutOfBound(destX, destY) && !checkParallelBlocked(destX, destY)){

            if(!checkOccupied(destX, destY)){

                return true;

            }else if(canCapture(destX, destY)){

                return true;

            }
        }
        return false;
    }

}
