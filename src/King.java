public class King extends Piece {

    /**
     * Constructs a King piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public King(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the King can move or not
     * @param destX the new x coordinate
     * @param destY the new y coordinate
     * @return true if the King can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(!checkOutOfBound(destX, destY)){

            if(!checkOccupied(destX, destY)){

                return true;

            }else if(canCapture(destX, destY)){

                return true;

            }
        }
        return false;
    }

    /**
     * Check whether the king is checkmated
     * @return true if the king is checkmated, false otherwise
     */
    public boolean checkmate(){
        return false;
    }
}
