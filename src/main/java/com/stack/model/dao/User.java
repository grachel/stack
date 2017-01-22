package com.stack.model.dao;

import com.stack.model.DomainContext;
import com.stack.model.entities.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User extends Common {

    UsersEntity entity;

    public User(){
        this.entity = new UsersEntity();
        this.session = DomainContext.openSession();
    }

    public User(Session session){
        this.entity = new UsersEntity();
        this.session = session;
    }

    User(UsersEntity entity, Session session){
        this.entity = entity;
        this.session = session;
    }

    public void findById(String s) {
        entity = session.get(UsersEntity.class, s);
    }

    public static User getCurrentUser(Session session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = new User(session);
        currentUser.findByLogin(auth.getName());
        return currentUser;
    }

    public static User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = new User();
        currentUser.findByLogin(auth.getName());
        return currentUser;
    }

    public void findByLogin(String s) {
        Query query = session.createQuery("from UsersEntity where login=:login");
        query.setParameter("login", s);
        entity = (UsersEntity) query.uniqueResult();
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

    public Collection<Post> getPosts() {
        List<Post> result = new ArrayList<>();
        for (PostsEntity postsEntity : entity.getPostsesById()) {
            result.add(new Post(postsEntity, session));
         }
        return result;
    }

    public Collection<PostsEntity> getEditedPosts() {
        return entity.getPostsesById_0();
    }

    public Collection<VotesEntity> getVotes() {
        return entity.getVotesById() ;
    }

}
