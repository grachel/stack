package com.stack.model.dao;

import com.stack.model.DomainContext;
import com.stack.model.entities.*;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Post extends Common {

    PostsEntity entity;

    public Post(){
        session = DomainContext.openSession();
        this.entity = new PostsEntity();
    }

    public Post(String id){
        session = DomainContext.openSession();
        findById(id);
    }

    public Post(Session session){
        this.session = session;
    }

    Post(PostsEntity entity, Session session){
        this.entity = entity;
        this.session = session;
    }

    public Post(User current) {
        this.entity = new PostsEntity();
        session = current.session;
        setOwner(current);
    }

    public void findById(String s) {
        entity = session.get(PostsEntity.class, Integer.parseInt(s));
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

    public void delete() {
        session.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public static List<Post> findAll(Session session) {
        List<Post> result = new ArrayList<>();
        List<PostsEntity> entities = (List<PostsEntity>) session.createQuery("from PostsEntity").list();
        for(PostsEntity entity : entities){
            result.add(new Post(entity, session));
        }
        return result;
    }

    public int getId() {
        return entity.getId();
    }
    public String getType() {
        return entity.getPosttype();
    }

    public void setType(String posttype) {
        entity.setPosttype(posttype);
    }

    public Timestamp getCreationDate() {
        return entity.getCreationdate();
    }

    public void setCreationDate(Timestamp creationdate) {
        entity.setCreationdate(creationdate);
    }

    public Integer getScore() {
        return entity.getScore();
    }

    public void setScore(Integer score) {
        entity.setScore(score);
    }

    public String getBody() {
        return entity.getBody();
    }

    public void setBody(String body) { entity.setBody(body); }

    public Timestamp getLastEditDate() {
        return entity.getLasteditdate();
    }

    public void setLastEditDate(Timestamp lasteditdate) {
        entity.setLasteditdate(lasteditdate);
    }

    public String getTitle() {
        return entity.getTitle();
    }

    public void setTitle(String title) {
        entity.setTitle(title);
    }

    public Timestamp getCloseDate() {
        return entity.getCloseddate();
    }

    public void setCloseDate(Timestamp closeddate) {
        entity.setCloseddate(closeddate);
    }

    public String getCloseReason() {
        return entity.getClosedreason();
    }

    public void setCloseReason(String closedreason) {
        entity.setClosedreason(closedreason);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post that = (Post) o;

        return entity.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return entity.hashCode();
    }

    public User getOwner() {
        return new User(entity.getUsersByOwneruserid(), session);
    }

    public void setOwner(User user) {
        this.entity.setUsersByOwneruserid(user.entity);
    }

    public Collection<CommentariesEntity> getCommentaries() {
        return entity.getCommentariesById();
    }

    public CommentariesEntity addComment(String body) {
        Collection<CommentariesEntity> comments = entity.getCommentariesById();
        if (comments == null){
            comments = new ArrayList<>();
        }
        CommentariesEntity comment = new CommentariesEntity();
        comment.setBody(body);
        comment.setCreationdate(new Timestamp(System.currentTimeMillis()));
        comment.setPostsByPostid(entity);
        comment.setUsersByUserid(User.getCurrentUser().entity);
        comments.add(comment);

        return comment;
    }
}
