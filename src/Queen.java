public class Queen extends Piece {

    /**
     * Constructs a Queen piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Queen(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Queen can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Queen can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(!checkOutOfBound(destX, destY)){
            if(!checkParallelBlocked(destX, destY)){
                if(!checkOccupied(destX, destY)){
                    return true;
                }else if(canCapture(destX, destY)){
                    return true;
                }
            }else if(checkDiagonalBlocked(destX, destY)){
                if(!checkOccupied(destX, destY)){
                    return true;
                }else if(canCapture(destX, destY)){
                    return true;
                }

            }
        }
        return false;
    }

}
