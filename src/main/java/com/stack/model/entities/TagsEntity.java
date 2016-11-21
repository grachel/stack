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
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = -1)
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

        if (id != that.id) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
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
