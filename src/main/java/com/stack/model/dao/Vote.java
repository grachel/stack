package com.stack.model.dao;

import com.stack.model.DomainContext;
import com.stack.model.entities.*;

public class Vote extends Common {

    VotesEntity entity;

    public Vote() {
        session = DomainContext.openSession();
        this.entity = new VotesEntity();
    }

    Vote(VotesEntity vote) {
        session = DomainContext.openSession();
        this.entity = vote;
    }

    public User getUser(){
        return new User(entity.getUsersByUserid(), session);
    }

    public void setUser(User user) {
        this.entity.setUsersByUserid(user.entity);
    }

    public void setComment(Comment comment){
        this.entity.setCommentsByCommentid(comment.entity);
    }

    public void save() {
        try{
            session.persist(entity);
            session.flush();
        }
        finally {
            DomainContext.closeSession(session);
        }
    }
}