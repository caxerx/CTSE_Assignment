public interface DVDMemento {
    DVD getOrigin();

    void restore();
}

class MovieMemento implements DVDMemento {
    private int numAvailable;
    private String director;
    private Movie origin;

    public MovieMemento(Movie movie) {
        director = movie.getDirector();
        numAvailable = movie.getNumAvailable();
        this.origin = movie;
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

class MVMemento implements DVDMemento {
    private int numAvailable;
    private String singer;
    private MV origin;

    public MVMemento(MV mv) {
        singer = mv.getSinger();
        numAvailable = mv.getNumAvailable();
        this.origin = mv;
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
