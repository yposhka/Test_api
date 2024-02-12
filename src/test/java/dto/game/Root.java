package dto.game;

import java.util.ArrayList;

public class Root{
    private String company = "Test";
    private String description  = "Test";
    private ArrayList<Dlc> dlcs = new ArrayList<>();
    private int gameId = 0;
    private String genre = "Test";
    private boolean isFree = true;
    private int price = 0;
    private String publish_date = "2024-02-12T07:29:40.919Z";
    private int rating = 0;
    private boolean requiredAge = true;
    private Requirements requirements = new Requirements();
    private ArrayList<String> tags = new ArrayList<>();
    private String title = "Test";

    public Root() {

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Dlc> getDlcs() {
        return dlcs;
    }

    public void setDlcs(ArrayList<Dlc> dlcs) {
        this.dlcs = dlcs;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(boolean requiredAge) {
        this.requiredAge = requiredAge;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
