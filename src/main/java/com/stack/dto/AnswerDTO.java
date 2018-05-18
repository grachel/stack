package com.stack.dto;

import java.sql.Timestamp;
import java.util.Collection;

public class AnswerDTO {

    private int id;
    private Integer score = 0;
    private String body;
    private String user;
    private Timestamp creationDate;
    private Collection<CommentDTO> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Collection<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentDTO> comments) {
        this.comments = comments;
    }
}
