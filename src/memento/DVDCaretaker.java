package memento;

import java.util.Stack;

public class DVDCaretaker {
    private Stack<DVDMemento> undoList;
    private Stack<DVDMemento> redoList;

    public DVDCaretaker() {
        this.undoList = new Stack<>();
        this.redoList = new Stack<>();
    }

    public void backup(DVDMemento memento) {
        redoList.clear();
        undoList.push(memento);
    }

    public void undo() {
        if (undoList.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        DVDMemento dvdMemento = undoList.pop();
        redoList.push(dvdMemento.getOrigin().backup());
        dvdMemento.restore();
    }

    public void redo() {
        DVDMemento dvdMemento = redoList.pop();
        undoList.push(dvdMemento.getOrigin().backup());
        dvdMemento.restore();
    }
}
