package command;

import def.StateManager;

import java.util.Stack;

public class RedoCommand extends Command {
    private StateManager stateManager;

    public RedoCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void execute() {
        Stack<UndoableCommand> redoList = stateManager.getRedoList();
        if (redoList.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        UndoableCommand command = redoList.pop();
        stateManager.getUndoList().push(command);
        command.redo();
    }
}
