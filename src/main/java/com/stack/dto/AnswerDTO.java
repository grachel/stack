package com.stack.dto;

import java.sql.Timestamp;
import java.util.Collection;

public class AnswerDTO {

    private int id;
    private Timestamp creationDate;
    private Integer score = 0;
    private String body;
    private UserDTO user;
    private PostDTO post;
    private Collection<CommentDTO> comments;
    private Collection<VoteDTO> votes;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public Collection<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentDTO> comments) {
        this.comments = comments;
    }

    public Collection<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(Collection<VoteDTO> votes) {
        this.votes = votes;
    }
}
