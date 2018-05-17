package com.stack.dto;

import java.util.Collection;

public class TagDTO {

    private int id;
    private String tag;
    private Collection<PostDTO> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Collection<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(Collection<PostDTO> posts) {
        this.posts = posts;
    }
}
