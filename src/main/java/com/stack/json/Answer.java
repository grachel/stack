package com.stack.json;

public class Answer {
    private int id;
    private String body;
    private String user;
    private String date;

    public Answer(int id, String body, String user, String date) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
