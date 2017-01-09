package com.stack.model.entities;

/**
 * Created by grzesiek on 2017-01-09.
 */
public enum Entities {
    User(UsersEntity.class),
    Badge(BadgesEntity.class),
    Comment(CommentariesEntity.class),
    Postlink(PostlinksEntity.class),
    Post(PostsEntity.class),
    Posttag(PosttagsEntity.class),
    Tag(TagsEntity.class),
    Vote(VotesEntity.class);

    public Class<?> value;

    private Entities(Class<?> c) {
        value = c;
    }
}
