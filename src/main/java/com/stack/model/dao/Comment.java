package com.stack.model.dao;

import com.stack.model.DomainContext;
import com.stack.model.entities.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Comment extends Common {

    CommentariesEntity entity;

    public Comment() {
        session = DomainContext.openSession();
        this.entity = new CommentariesEntity();
    }

    public Comment(Session session){
        this.entity = new CommentariesEntity();
        this.session = session;
    }

    public Comment(CommentariesEntity comment) {
        this.entity = comment;
    }

    public void findById(String s) {
        entity = session.get(CommentariesEntity.class, Integer.parseInt(s));
    }

    public void save() {
        try {
            session.persist(entity);
            session.flush();
        } finally {
            DomainContext.closeSession(session);
        }
    }

    public User getUser(){
        return new User(entity.getUsersByUserid(), session);
    }

    public Integer getScore() {
        return entity.getScore();
    }

    public List<Vote> getVotes() {
        List<Vote> result = new ArrayList<>();
        Query query = session.createQuery("from VotesEntity where commentid=:id");
        query.setParameter("id", entity.getId());
        List<?> votes = query.list();
        if (votes != null) {
            for (Object vote : votes) {
                result.add(new Vote((VotesEntity) vote));
            }
        }
        return result;
    }

    public void incScore() {
        entity.setScore(entity.getScore() + 1);
    }

    public String getCreationDate(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(entity.getCreationdate());
    }

    public String getBody(){
        return entity.getBody();
    }


    public String getUsername(){
        return entity.getUsersByUserid().getDisplayname();
    }
}