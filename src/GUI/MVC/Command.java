package GUI.MVC;

public interface Command {

    boolean execute();
    int undo();

}
