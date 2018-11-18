public interface DVDFactory {
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
