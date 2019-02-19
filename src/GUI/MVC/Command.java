package GUI.MVC;

/**
 * command interface
 * Reference: https://gamedevelopment.tutsplus.com/tutorials/let-your-players-undo-their-in-game-mistakes-with-the-command-pattern--gamedev-1391
 */
public interface Command {

    boolean execute();
    int undo();

}
