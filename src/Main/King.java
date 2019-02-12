package Main;

import java.util.Vector;

/**
 * An inherited class from Piece that describes the King piece
 */
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
     * Check whether the King can move or not
     * @param destX the new x coordinate
     * @param destY the new y coordinate
     * @return true if the Main.King can move, false otherwise
     */
    public boolean canMove(int destX, int destY){

        if(checkInBound(destX, destY)){

            int diffX = Math.abs(x - destX);
            int diffY = Math.abs(y - destY);

            if(diffX <= 1 && diffY <= 1) {

                if (!checkOccupied(destX, destY)) {

                    return SafeThisTurn(destX, destY);

                } else if (canCapture(destX, destY)) {

                    return SafeThisTurn(destX, destY);

                }
            }
        }
        return false;
    }

    /**
     * check if the king is safe this turn
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if safe, false otherwise
     */
    private boolean SafeThisTurn(int destX, int destY) {
        if ((board.getTurns() % 2 == 1 && player == 1) || (board.getTurns() % 2 == 0 && player == 2)) {

            return !isChecked(destX, destY);
        }
        return false;
    }

    /**
     * Iterate through the opponent's vector and check if the king is being checked by an opponent piece
     * @param x the x coordinate of the king
     * @param y the y coordinate of the king
     * @return true if the king is checked, false otherwise
     */
    private boolean isChecked(int x, int y){
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
            if (!kingCanMove()){
                return !friendlyCanSave();
            }
            return true;
        }

        return false;
    }

    /**
     * check if the king can move at all
     * @return true if the king can move, false otherwise
     */
    private boolean kingCanMove() {
        return canMove(x - 1, y) || canMove(x - 1, y - 1) || canMove(x - 1, y + 1)
                || canMove(x, y - 1) || canMove(x, y + 1) || canMove(x + 1, y)
                || canMove(x + 1, y - 1) || canMove(x + 1, y + 1);
    }

    /**
     * Check if the king is stalemated
     * @return true if the king is stalemated, false otherwise
     */
    public boolean stalemate(){
        if(!isChecked(x, y)){
            if (!kingCanMove()){
                return !friendlyCanMove();
            }
            return true;
        }

        return false;
    }

    /**
     * check if the friendly piece can move
     * @return true if can, false otherwise
     */
    private boolean friendlyCanMove(){

        Vector<Piece> friendlyPieces;
        if(player == 1)
            friendlyPieces = board.player1Pieces;
        else
            friendlyPieces = board.player2Pieces;

        for(int i = 0; i < friendlyPieces.size(); i++){
            Piece piece = friendlyPieces.elementAt(i);

            if(piece != null){

                //Rook
                if(piece instanceof Rook){
                    if(checkAnyParallelCanMove(piece)) return true;
                }

                //Bishop
                if(piece instanceof Bishop){
                    if(checkAnyParallelCanMove(piece)) return true;
                }

                //Queen
                if(piece instanceof Queen){
                    if(checkAnyParallelCanMove(piece)) return true;

                    if(checkAnyDiagonalCanMove(piece)) return true;
                }

                //Knight
                if(piece instanceof Knight){
                    if(piece.canMove(piece.x-2, piece.y-1))  return true;

                    if(piece.canMove(piece.x-1, piece.y-2))  return true;

                    if(piece.canMove(piece.x-2, piece.y+1))  return true;

                    if(piece.canMove(piece.x-1, piece.y+2))  return true;

                    if(piece.canMove(piece.x+2, piece.y-1))  return true;

                    if(piece.canMove(piece.x+1, piece.y-2))  return true;

                    if(piece.canMove(piece.x+2, piece.y+1))  return true;

                    if(piece.canMove(piece.x+1, piece.y+2))  return true;
                }

                //Pawn
                if(piece instanceof Pawn){
                    //player 1
                    if(piece.canMove(piece.x, piece.y-1))    return true;

                    if(piece.canMove(piece.x-1, piece.y-1))    return true;

                    if(piece.canMove(piece.x+1, piece.y-1))    return true;

                    //player 2
                    if(piece.canMove(piece.x, piece.y+1))    return true;

                    if(piece.canMove(piece.x-1, piece.y+1))    return true;

                    if(piece.canMove(piece.x+1, piece.y+1))    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if the friendly pieces can save the king
     * @return true if can, false otherwise
     */
    private boolean friendlyCanSave(){
        Vector<Piece> friendlyPieces;
        if(player == 1)
            friendlyPieces = board.player1Pieces;
        else
            friendlyPieces = board.player2Pieces;

        for(int i = 0; i < friendlyPieces.size(); i++){
            Piece piece = friendlyPieces.elementAt(i);
            if(piece != null){

                //Rook
                if(piece instanceof Rook){
                    if (checkCanSaveAllParallelMoves(piece)) return true;

                }

                //Bishop
                if(piece instanceof Bishop){

                    //check diagonal moves
                    if (checkCanSaveAllDiagonalMoves(piece)) return true;
                }

                //Queen
                if(piece instanceof Queen){

                    //check horizontal moves
                    if (checkCanSaveAllParallelMoves(piece)) return true;

                    //check diagonal moves
                    if (checkCanSaveAllDiagonalMoves(piece)) return true;

                }

                //Knight
                if(piece instanceof Knight){
                    if(canSaveHelper(piece, piece.x-2, piece.y-1))  return true;

                    if(canSaveHelper(piece, piece.x-1, piece.y-2))  return true;

                    if(canSaveHelper(piece, piece.x-2, piece.y+1))  return true;

                    if(canSaveHelper(piece, piece.x-1, piece.y+2))  return true;

                    if(canSaveHelper(piece, piece.x+2, piece.y-1))  return true;

                    if(canSaveHelper(piece, piece.x+1, piece.y-2))  return true;

                    if(canSaveHelper(piece, piece.x+2, piece.y+1))  return true;

                    if(canSaveHelper(piece, piece.x+1, piece.y+2))  return true;
                }

                //Pawn
                if(piece instanceof Pawn){
                    //player 1
                    if(canSaveHelper(piece, piece.x, piece.y-1))    return true;

                    if(canSaveHelper(piece, piece.x-1, piece.y-1))    return true;

                    if(canSaveHelper(piece, piece.x+1, piece.y-1))    return true;

                    //player 2
                    if(canSaveHelper(piece, piece.x, piece.y+1))    return true;

                    if(canSaveHelper(piece, piece.x-1, piece.y+1))    return true;

                    if(canSaveHelper(piece, piece.x+1, piece.y+1))    return true;
                }




            }
        }

        return false;
    }

    /**
     * check horizontal and vertical moves that can save the king
     * @param piece the piece to move
     * @return true if can, false otherwise
     */
    private boolean checkCanSaveAllParallelMoves(Piece piece) {
        //check horizontal moves
        for (int j = 0; j < board.getWidth(); j++) {
            if (j != piece.x && canSaveHelper(piece, j, piece.y))
                return true;
        }

        //check vertical moves
        for (int j = 0; j < board.getHeight(); j++) {
            if (j != piece.y && canSaveHelper(piece, piece.x, j))
                return true;
        }
        return false;
    }

    /**
     * check if the piece can move horizontally or vertically
     * @param piece the piece to move
     * @return true if can, false otherwise
     */
    private boolean checkAnyParallelCanMove(Piece piece) {
        //check horizontal moves
        for (int j = 0; j < board.getWidth(); j++) {
            if (j != piece.x && piece.canMove(j, piece.y))
                return true;
        }

        //check vertical moves
        for (int j = 0; j < board.getHeight(); j++) {
            if (j != piece.y && piece.canMove(piece.x, j))
                return true;
        }
        return false;
    }

    /**
     * check diagonal moves that can save the king
     * @param piece the piece to move
     * @return true if can, false otherwise
     */
    private boolean checkCanSaveAllDiagonalMoves(Piece piece) {
        int j = piece.x;
        int k = piece.y;

        while (j >= 0 && k >= 0) {
            if (j != piece.x && canSaveHelper(piece, j, k))
                return true;
            j--;
            k--;
        }
        j = piece.x;
        k = piece.y;

        while (j >= 0 && k <= board.getHeight()) {
            if (j != piece.x && canSaveHelper(piece, j, k))
                return true;
            j--;
            k++;
        }

        j = piece.x;
        k = piece.y;

        while (j <= board.getWidth() && k >= 0) {
            if (j != piece.x && canSaveHelper(piece, j, k))
                return true;
            j++;
            k--;
        }

        j = piece.x;
        k = piece.y;

        while (j <= board.getWidth() && k <= board.getHeight()) {
            if (j != piece.x && canSaveHelper(piece, j, k))
                return true;
            j++;
            k++;
        }
        return false;
    }

    /**
     * check if the piece can move diagonally
     * @param piece the piece to move
     * @return true if can, false otherwise
     */
    private boolean checkAnyDiagonalCanMove(Piece piece){
        int j = piece.x;
        int k = piece.y;

        while (j >= 0 && k >= 0) {
            if (j != piece.x && piece.canMove(j, k))
                return true;
            j--;
            k--;
        }
        j = piece.x;
        k = piece.y;

        while (j >= 0 && k <= board.getHeight()) {
            if (j != piece.x && piece.canMove(j, k))
                return true;
            j--;
            k++;
        }

        j = piece.x;
        k = piece.y;

        while (j <= board.getWidth() && k >= 0) {
            if (j != piece.x && piece.canMove(j, k))
                return true;
            j++;
            k--;
        }

        j = piece.x;
        k = piece.y;

        while (j <= board.getWidth() && k <= board.getHeight()) {
            if (j != piece.x && piece.canMove(j, k))
                return true;
            j++;
            k++;
        }
        return false;
    }

    /**
     * Check if the piece can save the king by moving to the destination
     * @param piece the piece to move
     * @param destX the destination x coordinate
     * @param destY the destination y coordinate
     * @return true if the patient can save the king by moving to the destination, false otherwise
     */
    private boolean canSaveHelper(Piece piece, int destX, int destY){
        int prevX = piece.x;
        int prevY = piece.y;
        if(board.movePiece(piece, destX, destY)){
            board.setTurns(board.getTurns()-1);
            if(kingCanMove()){
                board.undoMovePiece(piece, prevX, prevY);
                return true;
            }
            board.undoMovePiece(piece, prevX, prevY);
        }
        return false;
    }



}
