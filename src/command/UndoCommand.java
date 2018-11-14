package command;

import def.StateManager;

import java.util.Stack;

public class UndoCommand extends Command {
    private StateManager stateManager;

    public UndoCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void execute() {
        Stack<UndoableCommand> undoList = stateManager.getUndoList();
        if (undoList.isEmpty()) {
            System.out.println("Nothing to undo");
        }
        UndoableCommand command = undoList.pop();
        stateManager.getRedoList().push(command);
        command.undo();
    }
}
