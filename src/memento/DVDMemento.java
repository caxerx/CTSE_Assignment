package memento;

import dataobject.DVD;

public interface DVDMemento {
    DVD getOrigin();

    void restore();
}
