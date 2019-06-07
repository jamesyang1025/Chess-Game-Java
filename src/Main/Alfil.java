package Main;

/**
 * An inherited class from Piece that describes the Alfil piece - a Fairy Chess Piece that jumps two squares diagonally
 */
public class Alfil extends Piece {
    /**
     * Constructs an Alfil piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Alfil(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Alfil can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Alfil can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(checkInBound(destX, destY)){
            int diffX = Math.abs(x - destX);
            int diffY = Math.abs(y - destY);

            if(diffX == 2 && diffY == 2){
                if(!checkOccupied(destX, destY)){
                    return true;
                }else return canCapture(destX, destY);
            }
        }
        return false;
    }

}
