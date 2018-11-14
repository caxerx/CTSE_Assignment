package command;

/**
 * A command that able to undo and redo
 */
public abstract class UndoableCommand extends Command {
    @Override
    public boolean isUndoCommand() {
        return true;
    }

    /**
     * Redo the action after undo
     */
    abstract void redo();

    /**
     * Undo the action
     */
    abstract void undo();
}