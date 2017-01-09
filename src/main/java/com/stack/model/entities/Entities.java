package com.stack.model.entities;

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

    Entities(Class<?> c) {
        value = c;
    }
}
