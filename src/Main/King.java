package Main;

import java.util.Vector;

public class King extends Piece {

    private boolean checked = false;

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

                    if(!isChecked(destX, destY)){
                        return true;
                    }

                } else if (canCapture(destX, destY)) {

                    if(!isChecked(destX, destY)){
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * Iterate through the opponent's vector and check if the king is being checked by an opponent piece
     * @param x the x coordinate of the king
     * @param y the y coordinate of the king
     * @return true if the king is checked, false otherwise
     */
    public boolean isChecked(int x, int y){
        Vector<Piece> opponentPieces;
        if(player == 1)
            opponentPieces = board.getPlayer2Pieces();
        else
            opponentPieces = board.getPlayer1Pieces();

        for(int i = 0; i < opponentPieces.size(); i++){
            Piece piece = opponentPieces.elementAt(i);
            if(piece != null) {

                if (board.canMove(piece, x, y)) {

                    if(piece.player != player){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the king is checkmated
     * @return true if the king is checkmated, false otherwise
     */
    public boolean checkmate(){

        if(isChecked(x, y)){


            //the king is already checked
            if(!canMove(x-1, y) && !canMove(x-1, y-1) && !canMove(x-1, y+1)
                && !canMove(x, y-1) && !canMove(x, y+1) && !canMove(x+1, y)
                && !canMove(x+1, y-1) && !canMove(x+1, y+1)){
                return true;
            }
        }

        return false;
    }



}
