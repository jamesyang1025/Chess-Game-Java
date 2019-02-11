package Main;

public class Pawn extends Piece {


    /**
     * Constructs a Main.Pawn piece
     * @param board the chessboard
     * @param x the x coordinate
     * @param y the y coordinate
     * @param player the player it belongs to
     */
    public Pawn(Board board, int x, int y, int player){
        super(board, x, y, player);
    }


    /**
     * Check whether the Main.Pawn can move or not
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the Main.Pawn can move, false otherwise
     */
    public boolean canMove(int destX, int destY){
        if(!checkOutOfBound(destX, destY)){
            if(player == 1) {

                if(destY < y) {

                    if (pawnCanMove(destX, destY)) return true;

                }else{
                    return false;
                }

            }else{
                if(destY > y) {

                    if (pawnCanMove(destX, destY)) return true;

                }else{
                    return false;
                }
            }


        }
        return false;
    }

    private boolean pawnCanMove(int destX, int destY) {
        if (canMoveTwo(destX, destY)) {
            return true;
        }

        if (canMoveForward(destX, destY)) {
            return true;
        }

        if (pawnCanCapture(destX, destY)) {
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
            int num = board.getTurns();
            if(num == 1){
                if(destY < y){
                    if(!checkOccupied(destX, y - 1) && !checkOccupied(destX, destY)){
                        board.setTurns(num+1);
                        return true;
                    }
                }else{
                    if(!checkOccupied(destX, y + 1) && !checkOccupied(destX, destY)){
                        board.setTurns(num+1);
                        return true;
                    }
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
            if(!checkOccupied(destX, destY)){
                return true;
            }
        }
        return false;
    }

    public boolean pawnCanCapture(int destX, int destY){
        int diffY = Math.abs(destY - y);
        int diffX = Math.abs(destX - x);
        if(diffX == 1 && diffY == 1){
            if(checkOccupied(destX, destY)){
                if(canCapture(destX, destY)){
                    return true;
                }
            }
        }
        return false;
    }
}
