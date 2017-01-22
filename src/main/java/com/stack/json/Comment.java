package com.stack.json;

public class Comment {
    private int id;
    private String body;
    private String user;
    private String date;
    private int postid;

    public Comment(com.stack.model.dao.Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.user = comment.getUsername();
        this.date = comment.getCreationDate();
        this.postid = comment.getPostId();
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
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
}
