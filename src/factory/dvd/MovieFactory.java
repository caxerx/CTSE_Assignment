package factory.dvd;

import dataobject.DVD;
import dataobject.MV;
import dataobject.Movie;

public class MovieFactory implements DVDFactory {
    @Override
    public DVD createDVD(int dvdID, String title, int length, int numAvaliable) {
        return new Movie(dvdID, title, length, numAvaliable, "");
    }
}
