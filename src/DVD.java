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
        return String.format("%1$-8s%2$-20s%3$-14s%4$-14s", getDvdId(), getTitle(), getLength(), getNumAvailable());
    }

    public DVDMemento backup() {
        return null;
    }


    @Override
    public String toString() {
        return "ID: " + dvdId + "\n" +
                "Title: " + title + "\n" +
                "Length: " + length + " mins\n" +
                "Number of available copies: " + numAvailable;
    }
}
