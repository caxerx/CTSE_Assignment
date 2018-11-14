package factory.cmd;

import command.Command;
import command.UndoCommand;
import def.StateManager;

public class UndoCommandFactory implements CommandFactory {
    private StateManager stateManager;

    public UndoCommandFactory(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new UndoCommand(stateManager);
    }

    @Override
    public String getName() {
        return "redo";
    }
}
