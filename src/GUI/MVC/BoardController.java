package GUI.MVC;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class
 */
class BoardController {
    private BoardModel model;

    private BoardView view;

    /**
     * Constructor for the controller class
     * @param boardModel the boardModel class
     * @param boardView the boardView class
     */
    BoardController(BoardModel boardModel, BoardView boardView){
        model = boardModel;
        view = boardView;



        view.addStartListener(new StartListener());

        view.addSquareListener(new SquareListener());

        view.addForfeitListener(new ForfeitListener());

        view.addRestartListener(new RestartListener());

        view.addUndoListener(new UndoListener());

        view.addCustomPiecesListener(new CustomPiecesListener());

    }

    /**
     * ActionListener of when button is pressed
     */
    class SquareListener implements ActionListener {

        /**
         * When users pressed any square on the chessboard
         * @param e the source button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!model.getStart())   return;

            if(view.selectMove(e.getSource()))
                return;

            if(view.cancelSelection(e.getSource()))
                return;

            if(view.moveToSquare(e.getSource())){
                //check checkmate
                if(model.checkmate(model.getPlayer())){
                    System.out.println("player " + model.getPlayer());
                    System.out.println("turn " + model.getBoard().getTurns());
                    model.setStart(false);
                    model.setWinner(model.getOpponent());
                    view.checkmate(model.getOpponent());

                    return;
                }

                //check if your king is checked
                if(model.kingChecked(model.getOpponent())){
                    view.kingChecked(model.getOpponent());

                }

            }



        }
    }

    /**
     * ActionListener of when the start button is pressed
     */
    class StartListener implements ActionListener{

        /**
         * when user pressed the start button
         * @param e the start button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!model.getStart()){
                if(view.confirmStart()){
                    view.addSquareListener(new SquareListener());
                    model.setStart(true);
                }
            }
        }
    }

    /**
     * ActionListener of when the forfeit button is pressed
     */
    class ForfeitListener implements ActionListener{

        /**
         * When the user pressed the forfeit button
         * @param e the forfeit button
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(!model.getStart()) return;

            if(view.forfeit())
                model.setStart(false);
            else
                model.setStart(true);
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
            if(!model.getStart())   return;

            view.restart();


        }
    }

    /**
     * ActionListener of when the undo button is pressed
     */
    public class UndoListener implements ActionListener{

        /**
         * When the user pressed the undo button
         * @param e the undo button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!model.getStart()) return;

            if(!model.canUndo()){
                System.out.println("No undo available");
                return;
            }

            System.out.println("Undo");
            int coordinate = model.undo();
            view.undo(coordinate);


        }
    }

    /**
     * ActionListener of when the use custom pieces button is pressed
     */
    public class CustomPiecesListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!model.getStart()){
                if(view.useCustomPieces()){
                    view.addSquareListener(new SquareListener());
                    model.setStart(true);
                }
            }
        }
    }

}
