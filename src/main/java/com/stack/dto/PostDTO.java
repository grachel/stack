package com.stack.dto;

import java.sql.Timestamp;
import java.util.Collection;

public class PostDTO {

    private int id;
    private Timestamp creationDate;
    private Integer score = 0;
    private String body;
    private String title;
    private UserDTO user;
    private Collection<AnswerDTO> answers;
    private Collection<CommentDTO> comments;
    private Collection<VoteDTO> votes;
    private Collection<TagDTO> tags;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Collection<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<AnswerDTO> answers) {
        this.answers = answers;
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

    public Collection<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Collection<TagDTO> tags) {
        this.tags = tags;
    }
}
