package com.stack.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "posttags", schema = "public", catalog = "de23md1m4q7ru7")
public class PosttagsEntity {
    private Integer postid;
    private Integer tagid;
    private PostsEntity postsByPostid;
    private TagsEntity tagsByTagid;

    @Basic
    @Column(name = "postid", nullable = true)
    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    @Basic
    @Column(name = "tagid", nullable = true)
    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosttagsEntity that = (PosttagsEntity) o;

        if (postid != null ? !postid.equals(that.postid) : that.postid != null) return false;
        if (tagid != null ? !tagid.equals(that.tagid) : that.tagid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postid != null ? postid.hashCode() : 0;
        result = 31 * result + (tagid != null ? tagid.hashCode() : 0);
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
    @JoinColumn(name = "tagid", referencedColumnName = "id")
    public TagsEntity getTagsByTagid() {
        return tagsByTagid;
    }

    public void setTagsByTagid(TagsEntity tagsByTagid) {
        this.tagsByTagid = tagsByTagid;
    }
}
