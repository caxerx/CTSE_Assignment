public class MV extends DVD {
    private String singer;

    public MV(int dvdId, String title, int length, int numAvailable, String singer) {
        super(dvdId, title, length, numAvailable);
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSinger: " + singer;
    }

    @Override
    public String toListString() {
        return super.toListString() + singer;
    }

    public DVDMemento backup() {
        return new MVMemento(this);
    }
}
