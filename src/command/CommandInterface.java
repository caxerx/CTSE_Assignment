package command;

public interface CommandInterface {
    /**
     * Execute the action of the command
     */
    void execute();

    boolean isUndoCommand();
}
