package GUI.MVC;

import java.util.Stack;

/**
 * Command Manager class
 * Reference: https://gamedevelopment.tutsplus.com/tutorials/let-your-players-undo-their-in-game-mistakes-with-the-command-pattern--gamedev-1391
 */
class CommandManager {
    private Stack<Command> undos = new Stack<>();

    boolean executeCommand(Command c) {
        boolean ret = c.execute();
        if(ret)
            undos.push(c);
        return ret;
    }

    boolean isUndoAvailable() {
        return !undos.empty();
    }

    int undo() {
        assert(!undos.empty());
        Command command = undos.pop();
        return command.undo();
    }

}
