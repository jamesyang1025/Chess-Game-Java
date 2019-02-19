package GUI;
import Main.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the BoardGUI class to create a GUI for the chessBoard
 */
public class BoardGUI {

    private Board board;

    private boolean start;

    private JFrame frame;

    private BoardPanel boardPanel;

    private int player1Score;

    private int player2Score;

    private JLabel player1Label;

    private JLabel player2Label;

    /**
     * Constructor for BoardGUI
     */
    private BoardGUI(){
        initializeBoard();
        start = false;
        player1Score = 0;
        player2Score = 0;

        frame = new JFrame("Chess Game");
        boardPanel = new BoardPanel();
        ControlPanel controlPanel = new ControlPanel();
        frame.add(boardPanel, BorderLayout.WEST);
        frame.add(controlPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

    }

    /**
     * Main function where the game set up
     * @param args arguments to take
     */
    public static void main(String[] args){
        new BoardGUI();
    }

    /**
     * The chessboard panel
     */
    public class BoardPanel extends JPanel {
        BoardPanel(){
            super(new GridLayout(8, 8));
            setPreferredSize(new Dimension(640, 640));
            for(int i =0; i < 8; i++){

                for(int j = 0; j < 8; j++){
                    JButton square = new JButton();


                    if((i+j) % 2 == 0) {
                        //set light background
                        square.setBackground(Color.orange);

                        add(square);
                    }
                    else {
                        //set dark background
                        square.setBackground(Color.gray);
                        add(square);
                    }


                    square.setIcon(getImage(i, j));
                    square.putClientProperty("piece", board.getBoard()[j][i]);
                    square.putClientProperty("x", j);
                    square.putClientProperty("y", i);
                    square.setVisible(true);

                    SquareListener button = new SquareListener();
                    square.addActionListener(button);
                }

            }

        }
    }

    /**
     * The control panel where the restart, forfeit, and undo is
     */
    public class ControlPanel extends JPanel{
        ControlPanel(){

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setPreferredSize(new Dimension(160, 640));
            setBorder(new EmptyBorder(new Insets(80, 20, 150, 20)));
            JButton forfeit = new JButton("Forfeit");
            JButton restart = new JButton("Restart");
            JButton undo = new JButton("Undo");
            JButton start = new JButton("Start");

            player1Label = new JLabel();
            player2Label = new JLabel();
            player1Label.setText("player 1:     " + player1Score);
            player2Label.setText("player 2:     " + player2Score);

            add(player2Label);
            add(Box.createRigidArea(new Dimension(0, 180)));
            add(start);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(undo);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(forfeit);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(restart);
            add(Box.createRigidArea(new Dimension(0, 150)));
            add(player1Label);


            StartListener startListener = new StartListener();
            start.addActionListener(startListener);
            ForfeitListener forfeitListener = new ForfeitListener();
            forfeit.addActionListener(forfeitListener);
            RestartListener restartListener = new RestartListener();
            restart.addActionListener(restartListener);


        }
    }

    private JButton selected;
    private Color prevColor;

    /**
     * ActionListener of when button is pressed
     */
    public class SquareListener implements ActionListener{

        /**
         * When users pressed any square on the chessboard
         * @param e the source button
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(!start)  return;

            JButton square = (JButton) e.getSource();
            System.out.println("" + square.getClientProperty("piece"));

            //select a piece to move
            if(selected == null && square.getClientProperty("piece") != null){
                Piece piece = (Piece) square.getClientProperty("piece");
                if((piece.getPlayer() == 1 && board.getTurns() % 2 == 1)
                    || (piece.getPlayer() == 2 && board.getTurns() % 2 == 0)){
                    prevColor = square.getBackground();
                    square.setBackground(Color.green);
                    selected = square;

                    return;
                }

            }

            //cancel selection
            if(selected != null && square == selected){
                selected.setBackground(prevColor);
                selected = null;
                prevColor = null;

                return;
            }

            //move to a new square
            if(selected != null){
                int destX = (int) square.getClientProperty("x");
                int destY = (int) square.getClientProperty("y");
                Piece selectedPiece = (Piece) selected.getClientProperty("piece");
                if(board.movePiece(selectedPiece, destX, destY)){
                    square.putClientProperty("piece", selectedPiece);
                    selected.putClientProperty("piece", null);
                    square.setIcon(selected.getIcon());
                    selected.setIcon(null);
                    selected.setBackground(prevColor);
                    selected = null;
                    prevColor = null;
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Move!");
                }
            }




        }
    }

    /**
     * ActionListener of when the start button is pressed
     */
    public class StartListener implements ActionListener{

        /**
         * when user pressed the start button
         * @param e the start button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!start) {

                int response = JOptionPane.showConfirmDialog(null, "Start the game?");
                //user responds with yes
                if(response == 0) {
                    ResetBoard();
                    player1Label.setText("player 1:     " + player1Score);
                    player2Label.setText("player 2:     " + player2Score);
                    start = true;
                }
            }
        }
    }

    /**
     * ActionListener of when the undo button is pressed
     */
    public class UndoListener implements ActionListener{

        /**
         * when user pressed the undo button
         * @param e the undo button
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * ActionListener of when the forfeit button is pressed
     */
    public class ForfeitListener implements ActionListener{

        /**
         * When the user pressed the forfeit button
         * @param e the forfeit button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit");
            //user responds with yes
            if(response == 0){
                int winner;
                if(board.getTurns() % 2 == 1)
                    winner = 2;
                else
                    winner= 1;
                JOptionPane.showMessageDialog(null, "player " + winner + " Win");
                if(winner == 1)
                    player1Score++;
                else
                    player2Score++;

                player1Label.setText("player 1:     " + player1Score);
                player2Label.setText("player 2:     " + player2Score);

                start = false;
            }
        }
    }

    /**
     * ActionListener of when the restart button is pressed
     */
    public class RestartListener implements ActionListener{

        /**
         * When the user pressed the restart button
         * @param e the restart button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!start)  return;

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart");

            //user responds with yes
            if(response == 0){
                int player;
                if(board.getTurns() % 2 == 1)
                    player = 1;
                else
                    player = 2;

                JOptionPane.showMessageDialog(null, "Awaiting the other player's response");
                start = false;
                int response2 = JOptionPane.showConfirmDialog(null, "Player " + player
                        + " requested to restart. Do you agree?");

                //the opponent agreed
                if(response2 == 0){
                    ResetBoard();
                    player1Label.setText("player 1:     " + player1Score);
                    player2Label.setText("player 2:     " + player2Score);
                }else{
                    start = true;
                }
            }
        }
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

    /**
     * Helper function to initialize the chessboard
     */
    private void initializeBoard(){

        board = new Board(8, 8);

        board.getBoard()[0][0] = new Rook(board, 0, 0, 2);
        board.getBoard()[1][0] = new Knight(board, 1, 0, 2);
        board.getBoard()[2][0] = new Bishop(board, 2, 0, 2);
        board.getBoard()[3][0] = new Queen(board, 3, 0, 2);
        board.getBoard()[4][0] = new King(board, 4, 0, 2);
        board.getBoard()[5][0] = new Bishop(board, 5, 0, 2);
        board.getBoard()[6][0] = new Knight(board, 6, 0, 2);
        board.getBoard()[7][0] = new Rook(board, 7, 0, 2);
        for(int i = 0; i < 8; i++){
            board.getBoard()[i][1] = new Pawn(board, i, 1, 2);
        }
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 8; x++){
                board.player2Pieces.add(board.getBoard()[x][y]);
            }
        }

        board.getBoard()[0][7] = new Rook(board, 0, 7, 1);
        board.getBoard()[1][7] = new Knight(board, 1, 7, 1);
        board.getBoard()[2][7] = new Bishop(board, 2, 7, 1);
        board.getBoard()[3][7] = new Queen(board, 3, 7, 1);
        board.getBoard()[4][7] = new King(board, 4, 7, 1);
        board.getBoard()[5][7] = new Bishop(board, 5, 7, 1);
        board.getBoard()[6][7] = new Knight(board, 6, 7, 1);
        board.getBoard()[7][7] = new Rook(board, 7, 7, 1);
        for(int i = 0; i < 8; i++){
            board.getBoard()[i][6] = new Pawn(board, i, 6, 1);
        }
        for(int y = 6; y < 8; y++){
            for(int x = 0; x < 8; x++){
                board.player1Pieces.add(board.getBoard()[x][y]);
            }
        }

    }

    /**
     * Helper function to reset the chessboard
     */
    private void ResetBoard(){
        initializeBoard();

        boardPanel.removeAll();
        frame.remove(boardPanel);
        boardPanel = new BoardPanel();
        frame.add(boardPanel, BorderLayout.WEST);
        frame.pack();

        selected = null;
        prevColor = null;
    }
}
