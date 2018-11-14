package command;

public abstract class Command implements CommandInterface {
    @Override
    public boolean isUndoCommand() {
        return false;
    }
}
