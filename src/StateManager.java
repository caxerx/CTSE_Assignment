import java.util.Stack;

/**
 * Wrap the state object such as redo list, undo list and caretaker
 */
public class StateManager {
    private DVDCaretaker dvdCaretaker;
    private Stack<UndoableCommand> undoList;
    private Stack<UndoableCommand> redoList;

    public StateManager() {
        dvdCaretaker = new DVDCaretaker();
        undoList = new Stack<>();
        redoList = new Stack<>();
    }

    public DVDCaretaker getDvdCaretaker() {
        return dvdCaretaker;
    }

    public Stack<UndoableCommand> getUndoList() {
        return undoList;
    }

    public Stack<UndoableCommand> getRedoList() {
        return redoList;
    }
}
