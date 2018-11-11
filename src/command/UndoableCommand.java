package command;

/**
 * A command that able to undo and redo
 */
public interface UndoableCommand extends Command {
    /**
     * Redo the action after undo
     */
    void redo();

    /**
     * Undo the action
     */
    void undo();
}