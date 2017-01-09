package com.stack.model.entities;

import javax.persistence.*;

/**
 * Created by grzesiek on 2017-01-09.
 */
@Entity
@Table(name = "posttags", schema = "public", catalog = "de23md1m4q7ru7")
public class PosttagsEntity {
    private String id;
    private PostsEntity postsByPostid;
    private TagsEntity tagsByTagid;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosttagsEntity that = (PosttagsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
