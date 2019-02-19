package Main;

/**
 * An inherited class from Piece that describes the Pawn piece
 */
public class Pawn extends Piece {

    public int moves;

    /**
     * Constructs a Main.Pawn piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Pawn(Board board, int x, int y, int player){
        super(board, x, y, player);
        moves = 1;
    }


    /**
     * Check whether the Main.Pawn can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Pawn can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(checkInBound(destX, destY)){
            if(player == 1) {

                if(destY < y) {

                    return pawnCanMove(destX, destY);

                }else{
                    return false;
                }

            }else{
                if(destY > y) {

                    return pawnCanMove(destX, destY);

                }else{
                    return false;
                }
            }


        }
        return false;
    }

    private boolean pawnCanMove(int destX, int destY) {
        if (canMoveTwo(destX, destY)) {
            moves++;
            return true;
        }

        if (canMoveForward(destX, destY)) {
            moves++;
            return true;
        }

        if(pawnCanCapture(destX, destY)) {
            moves++;
            return true;
        }

        return false;
    }

    /**
     * Check whether the Main.Pawn can advance two squares
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Pawn can move two squares, false otherwise
     */
    private boolean canMoveTwo(int destX, int destY){
        int diffY = Math.abs(destY - y);
        if(destX == x && diffY == 2){
            if(moves == 1){
                if(destY < y){
                    return !checkOccupied(destX, y - 1) && !checkOccupied(destX, destY);
                }else{
                    return !checkOccupied(destX, y + 1) && !checkOccupied(destX, destY);
                }
            }
        }
        return false;
    }

    /**
     * Check whether the Main.Pawn can move forward to the unoccupied square in front of it
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Pawn can move forward one square, false otherwise
     */
    private boolean canMoveForward(int destX, int destY){
        int diffY = Math.abs(destY - y);

        if(destX == x && diffY == 1){
            return !checkOccupied(destX, destY);
        }
        return false;
    }

    private boolean pawnCanCapture(int destX, int destY){
        int diffY = Math.abs(destY - y);
        int diffX = Math.abs(destX - x);
        if(diffX == 1 && diffY == 1){
            if(checkOccupied(destX, destY)){
                return canCapture(destX, destY);
            }
        }
        return false;
    }
}
