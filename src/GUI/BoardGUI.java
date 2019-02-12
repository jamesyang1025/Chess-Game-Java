package GUI;
import javax.swing.*;
import java.awt.*;

/**
 * the BoardGUI class to create a GUI for the chessBoard
 */
public class BoardGUI {


    /**
     * Constructor for BoardGUI
     */
    private BoardGUI(){
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(500, 500);
        GridLayout layout = new GridLayout();
        frame.setLayout(layout);
        BoardPanel boardPanel = new BoardPanel();
        frame.add(boardPanel);
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
                    square.setVisible(true);

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
}
