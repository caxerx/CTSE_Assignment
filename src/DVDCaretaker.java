import java.util.Stack;

/**
 * Caretaker of dvd memento
 */
public class DVDCaretaker {
    private Stack<DVDMemento> undoList;
    private Stack<DVDMemento> redoList;

    public DVDCaretaker() {
        this.undoList = new Stack<>();
        this.redoList = new Stack<>();
    }

    /**
     * Consume a DVD memento when a action was done
     *
     * @param memento the dvd memento
     */
    public void backup(DVDMemento memento) {
        redoList.clear();
        undoList.push(memento);
    }

    /**
     * Restore last memento
     */
    public void undo() {
        if (undoList.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        DVDMemento dvdMemento = undoList.pop();
        redoList.push(dvdMemento.getOrigin().backup());
        dvdMemento.restore();
    }

    /**
     * Redo the restoration of last memento
     */
    public void redo() {
        if (redoList.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        DVDMemento dvdMemento = redoList.pop();
        undoList.push(dvdMemento.getOrigin().backup());
        dvdMemento.restore();
    }
}
