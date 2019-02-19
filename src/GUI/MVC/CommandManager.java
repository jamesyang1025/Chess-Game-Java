package GUI.MVC;

import java.util.Stack;

class CommandManager {
    private Stack<Command> undos = new Stack<>();

    boolean executeCommand(Command c) {
        boolean ret = c.execute();
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
