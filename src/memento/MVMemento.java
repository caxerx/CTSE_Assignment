package memento;

import dataobject.DVD;
import dataobject.MV;
import dataobject.Movie;

public class MVMemento implements DVDMemento {
    private int numAvailable;
    private String singer;
    private MV origin;

    public MVMemento(MV movie) {
        singer = movie.getSinger();
        numAvailable = movie.getNumAvailable();
        origin = movie;
    }

    @Override
    public DVD getOrigin() {
        return origin;
    }

    @Override
    public void restore() {
        origin.setSinger(singer);
        origin.setNumAvailable(numAvailable);
    }
}
