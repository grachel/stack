package com.stack.dto;

import java.sql.Timestamp;
import java.util.Collection;

public class CommentDTO {

    private int id;
    private Integer score = 0;
    private String body;
    private Timestamp creationDate;
    private PostDTO post;
    private AnswerDTO answer;
    private UserDTO user;
    private Collection<VoteDTO> votes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public AnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDTO answer) {
        this.answer = answer;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Collection<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(Collection<VoteDTO> votes) {
        this.votes = votes;
    }
}
