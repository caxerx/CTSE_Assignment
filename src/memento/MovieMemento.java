package memento;

import dataobject.DVD;
import dataobject.Movie;

public class MovieMemento implements DVDMemento {
    private int numAvailable;
    private String director;
    private Movie origin;

    public MovieMemento(Movie movie) {
        director = movie.getDirector();
        numAvailable = movie.getNumAvailable();
        origin = movie;
    }

    @Override
    public DVD getOrigin() {
        return origin;
    }

    @Override
    public void restore() {
        origin.setDirector(director);
        origin.setNumAvailable(numAvailable);
    }
}
