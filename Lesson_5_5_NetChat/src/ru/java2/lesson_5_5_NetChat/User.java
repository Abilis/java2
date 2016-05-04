package ru.java2.lesson_5_5_NetChat;

/**
 * Created by Abilis on 04.05.2016.
 */
public class User {

    private int id;
    private String oldNick;
    private String currentNick;
    private boolean isLogin;

    public User(int id, String oldNick, String currentNick) {
        this.id = id;
        this.currentNick = currentNick;
    }

    public int getId() {
        return id;
    }

    public String getOldNick() {
        return oldNick;
    }

    public String getCurrentNick() {
        return currentNick;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin() {
        isLogin = true;
    }

    public void setCurrentNick(String currentNick) {
        this.currentNick = currentNick;
    }

    public void setOldNick(String oldNick) {
        this.oldNick = oldNick;
    }
}
