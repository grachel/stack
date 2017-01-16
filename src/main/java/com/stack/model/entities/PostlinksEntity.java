package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "postlinks", schema = "public", catalog = "de23md1m4q7ru7")
public class PostlinksEntity {
    private int id;
    private Timestamp creationdate;
    private PostsEntity postsByPostid;
    private PostsEntity postsByRelatedpostid;

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

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = id;
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
