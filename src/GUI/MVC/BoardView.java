package GUI.MVC;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View class
 */
class BoardView extends JFrame {
    private BoardModel model;

    private BoardPanel boardPanel;

    private JLabel player1Label = new JLabel();

    private JLabel player2Label = new JLabel();

    private JButton[][] squares = new JButton[8][8];

    private JButton selected;
    private Color prevColor;
    private JButton currSquare;

    private JButton forfeitButton = new JButton("Forfeit");
    private JButton restartButton = new JButton("Restart");
    private JButton undoButton = new JButton("Undo");
    private JButton startButton = new JButton("Start");
    private JButton useCustomPiecesButton = new JButton("Use Custom Pieces");

    /**
     * Constructor for the boardView class
     * @param boardModel the boardModel class
     */
    BoardView(BoardModel boardModel){
        model = boardModel;
        boardPanel = new BoardPanel();
        ControlPanel controlPanel = new ControlPanel();
        this.add(boardPanel, BorderLayout.WEST);
        this.add(controlPanel, BorderLayout.EAST);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    /**
     * The classic chessboard panel
     */
    class BoardPanel extends JPanel {
        BoardPanel(){
            super(new GridLayout(8, 8));
            setPreferredSize(new Dimension(640, 640));
            for(int i =0; i < 8; i++){

                for(int j = 0; j < 8; j++){
                    squares[i][j] = new JButton();


                    if((i+j) % 2 == 0) {
                        //set light background
                        squares[i][j].setBackground(Color.orange);

                        add(squares[i][j]);
                    }
                    else {
                        //set dark background
                        squares[i][j].setBackground(Color.gray);
                        add(squares[i][j]);
                    }


                    squares[i][j].setIcon(getImage(i, j));
                    squares[i][j].putClientProperty("piece", model.getPiece(j, i));
                    squares[i][j].putClientProperty("x", j);
                    squares[i][j].putClientProperty("y", i);
                    squares[i][j].setVisible(true);
                }

            }

        }
    }




    /**
     * The control panel where the restart, forfeit, and undo is
     */
    class ControlPanel extends JPanel{
        ControlPanel(){

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setPreferredSize(new Dimension(200, 640));
            setBorder(new EmptyBorder(new Insets(80, 20, 150, 20)));


            player1Label = new JLabel();
            player2Label = new JLabel();
            player1Label.setText("Player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());

            add(player2Label);
            add(Box.createRigidArea(new Dimension(0, 150)));
            add(startButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(undoButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(forfeitButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(restartButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(useCustomPiecesButton);
            add(Box.createRigidArea(new Dimension(0, 150)));
            add(player1Label);


        }
    }

    /**
     * helper function to add listener to square buttons
     * @param squareListener the square listener in the controller class
     */
    void addSquareListener(ActionListener squareListener){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[i][j].addActionListener(squareListener);
            }
        }
    }

    /**
     * helper function to add listener to start button
     * @param startListener the start listener in the controller class
     */
    void addStartListener(ActionListener startListener){
        startButton.addActionListener(startListener);
    }

    /**
     * helper function to add listener to undo button
     * @param undoListener the undo listener in the controller class
     */
    void addUndoListener(ActionListener undoListener){
        undoButton.addActionListener(undoListener);
    }

    /**
     * helper function to add listener to forfeit button
     * @param forfeitListener the forfeit listener in the controller class
     */
    void addForfeitListener(ActionListener forfeitListener){
        forfeitButton.addActionListener(forfeitListener);
    }

    /**
     * helper function to add listener to restart button
     * @param restartListener the restart listener in the controller class
     */
    void addRestartListener(ActionListener restartListener){
        restartButton.addActionListener(restartListener);
    }

    /**
     * helper function to add listener to customPieces button
     * @param customPiecesListener the customPieces button in the controller class
     */
    void addCustomPiecesListener(ActionListener customPiecesListener){
        useCustomPiecesButton.addActionListener(customPiecesListener);
    }

    /**
     * cancel current selection
     * @param square the source square
     * @return true if successful, false otherwise
     */
    boolean cancelSelection(Object square){
        currSquare = (JButton) square;
        if(selected != null && currSquare == selected){
            selected.setBackground(prevColor);
            selected = null;
            prevColor = null;
            return true;
        }
        return false;
    }

    /**
     * select the piece to move
     * @param square the source square
     * @return true if successful, false otherwise
     */
    boolean selectMove(Object square){

        currSquare = (JButton) square;
        if(selected == null && currSquare.getClientProperty("piece") != null){
            if(model.canSelect(currSquare.getClientProperty("piece"))){
                prevColor = currSquare.getBackground();
                currSquare.setBackground(Color.green);
                selected = currSquare;
                return true;
            }

        }
        return false;
    }

    /**
     * move the selected piece to the destination square
     * @param square the destination square
     * @return true if successful, false otherwise
     */
    boolean moveToSquare(Object square){
        currSquare = (JButton) square;
        if(selected != null){
            int destX = (int) currSquare.getClientProperty("x");
            int destY = (int) currSquare.getClientProperty("y");


            if(model.movePiece(selected.getClientProperty("piece"), destX, destY)){

                currSquare.putClientProperty("piece", selected.getClientProperty("piece"));
                selected.putClientProperty("piece", null);

                currSquare.setIcon(selected.getIcon());
                selected.setIcon(null);
                selected.setBackground(prevColor);
                selected = null;
                prevColor = null;

                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Move!");
                return false;
            }
        }
        return false;
    }

    /**
     * Update the view for undo
     * @param coordinate the coordinate of the new and previous square
     */
    void undo(int coordinate){
        int prevX = coordinate / 1000 % 10;
        int prevY = coordinate / 100 % 10;
        int x = coordinate / 10 % 10;
        int y = coordinate % 10;

        squares[y][x].putClientProperty("piece", squares[prevY][prevX].getClientProperty("piece"));
        squares[prevY][prevX].putClientProperty("piece", null);
        squares[y][x].setIcon(squares[prevY][prevX].getIcon());
        squares[prevY][prevX].setIcon(null);

        System.out.println("" + squares[prevY][prevX].getClientProperty("piece"));
    }


    /**
     * pop up the confirm dialog for starting the game
     * @return true if successful, false otherwise
     */
    boolean confirmStart(){

        int response = JOptionPane.showConfirmDialog(null, "Start a new game?");
        //user responds with yes
        if(response == 0) {
            ResetBoard(false);
            player1Label.setText("player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());
            return true;
        }
        return false;
    }

    /**
     * pop up the confirm dialog for forfeiting the game
     * @return true if successful, false otherwise
     */
    boolean forfeit(){
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit");
        //user responds with yes
        if(response == 0){
            int winner = model.getOpponent();
            String winnerStr;
            if(winner == 1)
                winnerStr = "Alpha";
            else
                winnerStr = "Echo";
            JOptionPane.showMessageDialog(null, "player " + winnerStr + " Win");
            model.setWinner(winner);

            player1Label.setText("player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());

            return true;
        }
        return false;
    }

    /**
     * pop up the confirm dialog for restarting the game
     * @return true if successful, false otherwise
     */
    void restart(){
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart");

        //user responds with yes
        if(response == 0){
            int player = model.getPlayer();

            JOptionPane.showMessageDialog(null, "Awaiting the other player's response");
            model.setStart(false);
            String playerStr;
            if(player == 1)
                playerStr = "Alpha";
            else
                playerStr = "Echo";

            int response2 = JOptionPane.showConfirmDialog(null, "Player " + playerStr
                    + " requested to restart. Do you agree?");

            //the opponent agreed
            if(response2 == 0){
                ResetBoard(false);
                player1Label.setText("player Alpha:     " + model.getPlayer1Score());
                player2Label.setText("player Echo:     " + model.getPlayer2Score());
            }else{
                model.setStart(true);
            }
        }

    }

    /**
     * initialize the chessboard with custom pieces
     * @return true if successful, false otherwise
     */
    boolean useCustomPieces(){
        int response = JOptionPane.showConfirmDialog(null, "Start a new game with custom pieces?");
        //user responds with yes
        if(response == 0) {

            ResetBoard(true);

            squares[0][1].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Empress_Black.png"));
            squares[0][6].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Empress_Black.png"));
            squares[0][2].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Alfil_Black.png"));
            squares[0][5].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Alfil_Black.png"));


            squares[7][1].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Empress_White.png"));
            squares[7][6].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Empress_White.png"));
            squares[7][2].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Alfil_White.png"));
            squares[7][5].setIcon(new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Alfil_White.png"));

            player1Label.setText("player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());
            return true;
        }
        return false;
    }

    /**
     * pop up the message dialog for checkmate condition
     * @return true if successful, false otherwise
     */
    void checkmate(int player){
        String playerStr;
        if(player == 1)
            playerStr = "Alpha";
        else
            playerStr = "Echo";

        JOptionPane.showMessageDialog(null, "checkmate! Player " + playerStr + " Win!");
        player1Label.setText("player Alpha:     " + model.getPlayer1Score());
        player2Label.setText("player Echo:     " + model.getPlayer2Score());
    }

    /**
     * pop up the message dialog for king checked condition
     * @return true if successful, false otherwise
     */
    void kingChecked(int player){
        JOptionPane.showMessageDialog(null, "Warning! Your king is being checked!");
    }


    /**
     * Helper function to reset the chessboard
     */
    private void ResetBoard(boolean CustomPieces){
        if(!CustomPieces)
            model.initializeBoard();
        else
            model.initializeWithCustomPieces();

        boardPanel.removeAll();
        this.remove(boardPanel);
        boardPanel = new BoardPanel();
        this.add(boardPanel, BorderLayout.WEST);
        this.pack();

        selected = null;
        prevColor = null;

    }

    /**
     * Helper function to get the image for given square/piece
     * @param i the i-index
     * @param j the j-index
     * @return the ImageIcon of the corresponding square/piece
     */
    private ImageIcon getImage(int i, int j){
        ImageIcon image = null;

        //black rook
        if((j == 0 || j == 7) && i == 0){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Rook_Black.png");
        }

        //black knight
        if((j == 1 || j == 6) && i == 0){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Knight_Black.png");
        }

        //black bishop
        if((j == 2 || j == 5) && i == 0){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Bishop_Black.png");
        }

        //black queen
        if(j == 3 && i == 0){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Queen_Black.png");
        }

        //black king
        if(j == 4 && i == 0){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/King_Black.png");
        }

        //black pawn
        if(i == 1){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Pawn_Black.png");
        }

        //white rook
        if((j == 0 || j == 7) && i == 7){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Rook_White.png");
        }

        //white knight
        if((j == 1 || j == 6) && i == 7){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Knight_White.png");
        }

        //white bishop
        if((j == 2 || j == 5) && i == 7){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Bishop_White.png");
        }

        //white queen
        if(j == 3 && i == 7){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Queen_White.png");
        }

        //white king
        if(j == 4 && i == 7){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/King_White.png");
        }

        //white pawn
        if(i == 6){
            image = new ImageIcon("C:/Users/James Yang/Desktop/CS242/cs242-assignment1/src/GUI/Images/Pawn_White.png");
        }

        return image;
    }
}
