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
        return StringUtils.padLeft(getDvdId() + "", 8) + StringUtils.padLeft(getTitle(), 20) + StringUtils.padLeft(getLength() + "", 14) + StringUtils.padLeft(getNumAvailable() + "", 14);
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
