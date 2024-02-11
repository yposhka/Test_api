package dto;

import java.util.ArrayList;


public class UserReg {

    private ArrayList<Object> games = new ArrayList<>();
    private String login;
    private String pass;

    public UserReg(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public ArrayList<Object> getGames() {
        return games;
    }

    public void setGames(ArrayList<Object> games) {
        this.games = games;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
