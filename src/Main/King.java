package Main;

public class King extends Piece {

    /**
     * Constructs a Main.King piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public King(Board board, int x, int y, int player){
        super(board, x, y, player);
    }

    /**
     * Check whether the Main.King can move or not
     * @param destX the new x coordinate
     * @param destY the new y coordinate
     * @return true if the Main.King can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(!checkOutOfBound(destX, destY)){

            int diffX = Math.abs(x - destX);
            int diffY = Math.abs(y - destY);

            if(diffX <= 1 && diffY <= 1) {

                if (!checkOccupied(destX, destY)) {

                    return true;

                } else if (canCapture(destX, destY)) {

                    return true;

                }
            }
        }
        return false;
    }
}
