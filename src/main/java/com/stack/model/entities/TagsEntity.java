package com.stack.model.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tags", schema = "public", catalog = "de23md1m4q7ru7")
public class TagsEntity {
    private int id;
    private String tag;
    private Collection<PosttagsEntity> posttagsesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagsEntity that = (TagsEntity) o;

        return id == that.id && (tag != null ? tag.equals(that.tag) : that.tag == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tagsByTagid")
    public Collection<PosttagsEntity> getPosttagsesById() {
        return posttagsesById;
    }

    public void setPosttagsesById(Collection<PosttagsEntity> posttagsesById) {
        this.posttagsesById = posttagsesById;
    }
}
