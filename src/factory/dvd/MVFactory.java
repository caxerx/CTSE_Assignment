package factory.dvd;

import dataobject.DVD;
import dataobject.MV;

public class MVFactory implements DVDFactory {
    @Override
    public DVD createDVD(int dvdID, String title, int length, int numAvaliable) {
        return new MV(dvdID, title, length, numAvaliable, "");
    }
}
