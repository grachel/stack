package com.stack.model.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "public")
public class Post implements IEntity {
    private int id;
    private Timestamp creationDate;
    private Integer score = 0;
    private String body;
    private String title;
    private User user;
    private Collection<Answer> answers;
    private Collection<Comment> comments;
    private Collection<Vote> votes;
    private Set<Tag> tags;

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

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post that = (Post) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "post", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @OrderBy("id")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @OrderBy("id")
    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "posts_tags", joinColumns = {
            @JoinColumn(name = "id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "tag_id",
                    nullable = false, updatable = false) })
    @OrderBy("tag_id")
    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
