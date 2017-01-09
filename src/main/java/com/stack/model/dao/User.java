package com.stack.model.dao;

import com.stack.model.entities.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User extends Common {

    private UsersEntity entity;

    public User(Session session){
        this.entity = new UsersEntity();
        this.session = session;
    }

    private User(UsersEntity entity, Session session){
        this.entity = entity;
        this.session = session;
    }

    public static User findById(String s, Session session) {
        return new User(session.get(UsersEntity.class, s), session);
    }

    public static User findByLogin(String s, Session session) {
        Query query = session.createQuery("from UsersEntity where login=:login");
        query.setParameter("login", s);
        UsersEntity user = (UsersEntity) query.uniqueResult();
        return new User(user, session);
    }

    public void save() {
        session.persist(entity);
    }

    public void delete() {
        session.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public static List<User> findAll(Session session) {
        List<User> result = new ArrayList<>();
        List<UsersEntity> entities = (List<UsersEntity>) session.createQuery("from UsersEntity").list();
        for(UsersEntity entity : entities){
            result.add(new User(entity, session));
        }
        return result;
    }
    public int getId() {
        return entity.getId();
    }

    public Integer getReputation() {
        return entity.getReputation();
    }

    public void setReputation(Integer reputation) {
        entity.setReputation(reputation);
    }

    public Timestamp getCreationDate() {
        return entity.getCreationdate();
    }

    public void setCreationDate(Timestamp creationdate) {
        entity.setCreationdate(creationdate);
    }

    public String getDisplayName() {
        return entity.getDisplayname();
    }

    public void setDisplayName(String displayname) {
        entity.setDisplayname(displayname);
    }

    public String getLogin() {
        return entity.getLogin();
    }

    public void setLogin(String login) {
        entity.setLogin(login);
    }


    public String getPassword() {
        return entity.getPassword();
    }

    public void setPassword(String password) {
        entity.setPassword(password);
    }

    public Timestamp getLastAccessDate() {
        return entity.getLastaccessdate();
    }

    public void setLastAccessDate(Timestamp lastaccessdate) {
        entity.setLastaccessdate(lastaccessdate);
    }

    public String getWebsiteURL() {
        return entity.getWebsiteurl();
    }

    public void setWebsiteURL(String websiteurl) {
        entity.setWebsiteurl(websiteurl);
    }

    public String getAboutMe() {
        return entity.getAboutme();
    }

    public void setAboutMe(String aboutme) {
        entity.setAboutme(aboutme);
    }

    public Integer getUpVotes() {
        return entity.getUpvotes();
    }

    public void setUpVotes(Integer upvotes) {
        entity.setUpvotes(upvotes);
    }

    public Integer getDownVotes() {
        return entity.getDownvotes();
    }

    public void setDownVotes(Integer downvotes) {
        entity.setDownvotes(downvotes);
    }

    public String getEmail() {
        return entity.getEmail();
    }

    public void setEmail(String email) {
        entity.setEmail(email);
    }

    public Collection<BadgesEntity> getBadges() {
        return entity.getBadgesById();
    }

    public Collection<CommentariesEntity> getCommentaries() {
        return entity.getCommentariesById();
    }

    public Collection<PostsEntity> getPosts() {
        return entity.getPostsesById();
    }

    public Collection<PostsEntity> getEditedPosts() {
        return entity.getPostsesById_0();
    }

    public Collection<VotesEntity> getVotes() {
        return entity.getVotesById() ;
    }

}
