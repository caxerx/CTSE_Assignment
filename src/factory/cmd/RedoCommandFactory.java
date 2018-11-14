package factory.cmd;

import command.Command;
import command.RedoCommand;
import def.StateManager;

public class RedoCommandFactory implements CommandFactory {
    private StateManager stateManager;

    public RedoCommandFactory(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new RedoCommand(stateManager);
    }

    @Override
    public String getName() {
        return "redo";
    }
}
