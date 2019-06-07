package Main;

/**
 * An inherited class from Piece that describes the Rook piece
 */
public class Rook extends Piece {

    /**
     * Constructs a Main.Rook piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Rook(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Main.Rook can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Rook can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(checkInBound(destX, destY)){

            return checkParallelCanMove(destX, destY);
        }
        return false;
    }

}
