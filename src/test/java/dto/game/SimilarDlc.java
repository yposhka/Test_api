package dto.game;

public class SimilarDlc {
    private String dlcNameFromAnotherGame = "Test";
    private boolean isFree = true;

    public SimilarDlc() {

    }

    public String getDlcNameFromAnotherGame() {
        return dlcNameFromAnotherGame;
    }

    public void setDlcNameFromAnotherGame(String dlcNameFromAnotherGame) {
        this.dlcNameFromAnotherGame = dlcNameFromAnotherGame;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
