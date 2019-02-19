package GUI.MVC;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class BoardView extends JFrame {
    private BoardModel model;

    private BoardPanel boardPanel;

    private JLabel player1Label = new JLabel();

    private JLabel player2Label = new JLabel();

    JButton[][] squares = new JButton[8][8];

    private JButton selected;
    private Color prevColor;
    private JButton currSquare;

    JButton forfeitButton = new JButton("Forfeit");
    JButton restartButton = new JButton("Restart");
    JButton undoButton = new JButton("Undo");
    JButton startButton = new JButton("Start");

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
     * The chessboard panel
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
            setPreferredSize(new Dimension(160, 640));
            setBorder(new EmptyBorder(new Insets(80, 20, 150, 20)));


            player1Label = new JLabel();
            player2Label = new JLabel();
            player1Label.setText("Player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());

            add(player2Label);
            add(Box.createRigidArea(new Dimension(0, 180)));
            add(startButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(undoButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(forfeitButton);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(restartButton);
            add(Box.createRigidArea(new Dimension(0, 150)));
            add(player1Label);


        }
    }

    void addSquareListener(ActionListener squareListener){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[i][j].addActionListener(squareListener);
            }
        }
    }

    void addStartListener(ActionListener startListener){
        startButton.addActionListener(startListener);
    }

    void addUndoListener(ActionListener undoListener){
        undoButton.addActionListener(undoListener);
    }

    void addForfeitListener(ActionListener forfeitListener){
        forfeitButton.addActionListener(forfeitListener);
    }

    void addRestartListener(ActionListener restartListener){
        restartButton.addActionListener(restartListener);
    }

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

    boolean moveToSquare(Object square){
        currSquare = (JButton) square;
        if(selected != null){
            int destX = (int) currSquare.getClientProperty("x");
            int destY = (int) currSquare.getClientProperty("y");
            if(model.canMove(selected.getClientProperty("piece"), destX, destY)){
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

    boolean confirmStart(){

        int response = JOptionPane.showConfirmDialog(null, "Start a new game?");
        //user responds with yes
        if(response == 0) {
            ResetBoard();
            player1Label.setText("player Alpha:     " + model.getPlayer1Score());
            player2Label.setText("player Echo:     " + model.getPlayer2Score());
            return true;
        }
        return false;
    }

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

    boolean restart(){
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
                ResetBoard();
                player1Label.setText("player Alpha:     " + model.getPlayer1Score());
                player2Label.setText("player Echo:     " + model.getPlayer2Score());
                return true;
            }else{
                model.setStart(true);
            }
        }

        return false;
    }

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

    void kingChecked(int player){
        JOptionPane.showMessageDialog(null, "Warning! Your king is being checked!");
    }


    /**
     * Helper function to reset the chessboard
     */
    void ResetBoard(){
        model.initializeBoard();

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
    ImageIcon getImage(int i, int j){
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
