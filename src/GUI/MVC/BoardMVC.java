package GUI.MVC;

public class BoardMVC {
    public static void main(String[] args) {
        BoardModel boardModel = new BoardModel();
        BoardView boardView = new BoardView(boardModel);
        BoardController boardController = new BoardController(boardModel, boardView);

        boardView.setVisible(true);
    }
}
