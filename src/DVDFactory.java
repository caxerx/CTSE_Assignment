/**
 * Abstract Factory to create a DVD object
 */
public interface DVDFactory {
    /**
     * @param dvdID        id of dvd
     * @param title        title of dvd
     * @param length       length of dvd
     * @param numAvailable number of available copie of dvd
     * @return the dvd object with following information
     */
    DVD createDVD(int dvdID, String title, int length, int numAvailable);
}

class MovieFactory implements DVDFactory {
    @Override
    public DVD createDVD(int dvdID, String title, int length, int numAvailable) {
        return new Movie(dvdID, title, length, numAvailable, "");
    }
}

class MVFactory implements DVDFactory {
    @Override
    public DVD createDVD(int dvdID, String title, int length, int numAvailable) {
        return new MV(dvdID, title, length, numAvailable, "");
    }
}
