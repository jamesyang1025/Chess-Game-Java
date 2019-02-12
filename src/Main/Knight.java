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
            return checkKnightCanMove(destX, destY);
        }
        return false;
    }
}
