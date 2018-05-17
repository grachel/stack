package com.stack.dto;

import java.sql.Timestamp;
import java.util.Collection;

public class UserDTO {

    private int id;
    private Timestamp creationDate;
    private String login;
    private String password;
    private String email;
    private Collection<PostDTO> posts;
    private Collection<AnswerDTO> answers;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(Collection<PostDTO> posts) {
        this.posts = posts;
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
}
