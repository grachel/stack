package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "postlinks", schema = "public", catalog = "de23md1m4q7ru7")
public class PostlinksEntity {
    private int id;
    private Integer postid;
    private Integer relatedpostid;
    private Timestamp creationdate;
    private PostsEntity postsByPostid;
    private PostsEntity postsByRelatedpostid;

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
    @Column(name = "relatedpostid", nullable = true)
    public Integer getRelatedpostid() {
        return relatedpostid;
    }

    public void setRelatedpostid(Integer relatedpostid) {
        this.relatedpostid = relatedpostid;
    }

    @Basic
    @Column(name = "creationdate", nullable = true)
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostlinksEntity that = (PostlinksEntity) o;

        if (id != that.id) return false;
        if (postid != null ? !postid.equals(that.postid) : that.postid != null) return false;
        if (relatedpostid != null ? !relatedpostid.equals(that.relatedpostid) : that.relatedpostid != null)
            return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (postid != null ? postid.hashCode() : 0);
        result = 31 * result + (relatedpostid != null ? relatedpostid.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
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
    @JoinColumn(name = "relatedpostid", referencedColumnName = "id")
    public PostsEntity getPostsByRelatedpostid() {
        return postsByRelatedpostid;
    }

    public void setPostsByRelatedpostid(PostsEntity postsByRelatedpostid) {
        this.postsByRelatedpostid = postsByRelatedpostid;
    }
}
