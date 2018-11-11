package factory.dvd;

import dataobject.DVD;

public interface DVDFactory {
    DVD createDVD(int dvdID, String title, int length, int numAvaliable);
}
