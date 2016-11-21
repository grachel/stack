package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "commentaries", schema = "public", catalog = "de23md1m4q7ru7")
public class CommentariesEntity {
    private int id;
    private Integer postid;
    private Integer score;
    private String body;
    private Timestamp creationdate;
    private Integer userid;
    private PostsEntity postsByPostid;
    private UsersEntity usersByUserid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "postid", nullable = true)
    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "body", nullable = true, length = -1)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "creationdate", nullable = true)
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentariesEntity that = (CommentariesEntity) o;

        if (id != that.id) return false;
        if (postid != null ? !postid.equals(that.postid) : that.postid != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (postid != null ? postid.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "id")
    public PostsEntity getPostsByPostid() {
        return postsByPostid;
    }

    public void setPostsByPostid(PostsEntity postsByPostid) {
        this.postsByPostid = postsByPostid;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    public UsersEntity getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(UsersEntity usersByUserid) {
        this.usersByUserid = usersByUserid;
    }
}
