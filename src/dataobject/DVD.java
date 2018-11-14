package dataobject;

import memento.DVDMemento;

public class DVD {
    private int dvdId;
    private String title;
    private int length;
    private int numAvailable;

    public DVD(int dvdId, String title, int length, int numAvailable) {
        this.dvdId = dvdId;
        this.title = title;
        this.length = length;
        this.numAvailable = numAvailable;
    }

    public int getDvdId() {
        return dvdId;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }


    public String toListString() {
        //"ID  Title    Length (mins)   No. available   Other Info"
        return String.format("%s\t%s\t%s\t%s", dvdId, title, length, numAvailable);
    }

    public DVDMemento backup() {
        return null;
    }


    @Override
    public String toString() {
        return "DVD{" +
                "dvdId=" + dvdId +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", numAvailable=" + numAvailable +
                '}';
    }
}
