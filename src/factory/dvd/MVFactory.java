package factory.dvd;

import dataobject.DVD;
import dataobject.MV;

public class MVFactory implements DVDFactory {
    @Override
    public DVD createDVD(int dvdID, String title, int length, int numAvailable) {
        return new MV(dvdID, title, length, numAvailable, "");
    }
}
