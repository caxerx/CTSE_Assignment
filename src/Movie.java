public class Movie extends DVD {
    private String director;

    public Movie(int dvdId, String title, int length, int numAvailable, String director) {
        super(dvdId, title, length, numAvailable);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "director='" + director + '\'' +
                '}';
    }
}
