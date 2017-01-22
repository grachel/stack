package com.stack.model.dao;

import com.stack.model.entities.VotesEntity;
import org.hibernate.Session;

public class Vote extends Common {

    VotesEntity entity;

    public Vote(Session session) {
        this.session = session;
        this.entity = new VotesEntity();
    }

    public Vote(VotesEntity vote, Session session) {
        this.session = session;
        this.entity = vote;
    }

    public User getUser(){
        return new User(entity.getUsersByUserid(), session);
    }

    public void setUser(User user) {
        this.entity.setUsersByUserid(user.entity);
    }

    public void setComment(Comment comment){
        this.entity.setCommentsByPostid(comment.entity);
    }

    public void persist() {
        session.persist(entity);
    }
}