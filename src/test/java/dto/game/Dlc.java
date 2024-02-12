package dto.game;

public class Dlc {
    private String description = "Test";
    private String dlcName = "Test";
    private boolean isDlcFree = true;
    private int price = 0;
    private int rating = 0;
    private SimilarDlc similarDlc = new SimilarDlc();

    public Dlc() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDlcName() {
        return dlcName;
    }

    public void setDlcName(String dlcName) {
        this.dlcName = dlcName;
    }

    public boolean isDlcFree() {
        return isDlcFree;
    }

    public void setDlcFree(boolean dlcFree) {
        isDlcFree = dlcFree;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public SimilarDlc getSimilarDlc() {
        return similarDlc;
    }

    public void setSimilarDlc(SimilarDlc similarDlc) {
        this.similarDlc = similarDlc;
    }
}
