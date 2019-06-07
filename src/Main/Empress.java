package Main;

/**
 * An inherited class from Piece that describes the Empress piece (combines the power of rook and knight)
 */
public class Empress extends Piece {

    /**
     * Constructs a Empress piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Empress(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Empress can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Empress can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(checkInBound(destX, destY)){
            if(checkParallelCanMove(destX, destY)){
                return true;
            }

            return checkKnightCanMove(destX, destY);
        }

        return false;
    }
}
