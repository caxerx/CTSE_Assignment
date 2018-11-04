public class MV extends DVD {
    private String singer;

    public MV(int dvdId, String title, int length, int numAvailable) {
        super(dvdId, title, length, numAvailable);
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "MV{" +
                "singer='" + singer + '\'' +
                '}';
    }
}
