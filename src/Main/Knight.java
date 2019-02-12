package Main;

/**
 * An inherited class from Piece that describes the Knight piece
 */
public class Knight extends Piece {

    /**
     * Constructs a Main.Knight piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Knight(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Main.Knight can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Knight can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(checkInBound(destX, destY)){
            int diffX = Math.abs(x - destX);
            int diffY = Math.abs(y - destY);

            if((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)){
                if(!checkOccupied(destX, destY)){
                    return true;
                }else return canCapture(destX, destY);
            }
        }
        return false;
    }
}
