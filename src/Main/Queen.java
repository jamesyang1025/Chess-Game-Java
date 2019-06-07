package Main;

/**
 * An inherited class from Piece that describes the Queen piece
 */
public class Queen extends Piece {

    /**
     * Constructs a Main.Queen piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Queen(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Main.Queen can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Queen can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(checkInBound(destX, destY)){

            if(checkParallelCanMove(destX, destY))  return true;

            return checkDiagonalCanMove(destX, destY);
        }
        return false;
    }

}
