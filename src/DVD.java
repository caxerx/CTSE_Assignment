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
