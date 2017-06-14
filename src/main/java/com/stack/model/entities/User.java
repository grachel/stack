package com.stack.model.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public")
public class User implements IEntity {
    private int id;
    private Timestamp creationDate;
    private String login;
    private String password;
    private String email;
    private Collection<Post> posts;
    private Collection<Answer> answers;
    private Collection<Comment> comments;
    private Collection<Vote> votes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creationdate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationdate) {
        this.creationDate = creationdate;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @OrderBy("id")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @OrderBy("id")
    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }
    
}
