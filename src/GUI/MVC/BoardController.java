package GUI.MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoardController {
    private BoardModel model;

    private BoardView view;

    BoardController(BoardModel boardModel, BoardView boardView){
        model = boardModel;
        view = boardView;



        view.addStartListener(new StartListener());

        view.addSquareListener(new SquareListener());

        view.addForfeitListener(new ForfeitListener());

        view.addRestartListener(new RestartListener());

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

            view.moveToSquare(e.getSource());



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

}
