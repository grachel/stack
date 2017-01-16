package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public", catalog = "de23md1m4q7ru7")
public class UsersEntity {
    private int id;
    private Integer reputation;
    private Timestamp creationdate;
    private String displayname;
    private String login;
    private String password;
    private Timestamp lastaccessdate;
    private String websiteurl;
    private String aboutme;
    private Integer upvotes;
    private Integer downvotes;
    private String email;
    private Collection<BadgesEntity> badgesById;
    private Collection<CommentariesEntity> commentariesById;
    private Collection<PostsEntity> postsesById;
    private Collection<PostsEntity> postsesById_0;
    private Collection<VotesEntity> votesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reputation")
    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    @Basic
    @Column(name = "creationdate")
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Basic
    @Column(name = "displayname")
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "lastaccessdate")
    public Timestamp getLastaccessdate() {
        return lastaccessdate;
    }

    public void setLastaccessdate(Timestamp lastaccessdate) {
        this.lastaccessdate = lastaccessdate;
    }

    @Basic
    @Column(name = "websiteurl")
    public String getWebsiteurl() {
        return websiteurl;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }

    @Basic
    @Column(name = "aboutme")
    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    @Basic
    @Column(name = "upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @Basic
    @Column(name = "downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (reputation != null ? !reputation.equals(that.reputation) : that.reputation != null) return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;
        if (displayname != null ? !displayname.equals(that.displayname) : that.displayname != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastaccessdate != null ? !lastaccessdate.equals(that.lastaccessdate) : that.lastaccessdate != null)
            return false;
        if (websiteurl != null ? !websiteurl.equals(that.websiteurl) : that.websiteurl != null) return false;
        if (aboutme != null ? !aboutme.equals(that.aboutme) : that.aboutme != null) return false;
        if (upvotes != null ? !upvotes.equals(that.upvotes) : that.upvotes != null) return false;
        if (downvotes != null ? !downvotes.equals(that.downvotes) : that.downvotes != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (reputation != null ? reputation.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        result = 31 * result + (displayname != null ? displayname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastaccessdate != null ? lastaccessdate.hashCode() : 0);
        result = 31 * result + (websiteurl != null ? websiteurl.hashCode() : 0);
        result = 31 * result + (aboutme != null ? aboutme.hashCode() : 0);
        result = 31 * result + (upvotes != null ? upvotes.hashCode() : 0);
        result = 31 * result + (downvotes != null ? downvotes.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUserid")
    public Collection<BadgesEntity> getBadgesById() {
        return badgesById;
    }

    public void setBadgesById(Collection<BadgesEntity> badgesById) {
        this.badgesById = badgesById;
    }

    @OneToMany(mappedBy = "usersByUserid")
    public Collection<CommentariesEntity> getCommentariesById() {
        return commentariesById;
    }

    public void setCommentariesById(Collection<CommentariesEntity> commentariesById) {
        this.commentariesById = commentariesById;
    }

    @OneToMany(mappedBy = "usersByOwneruserid", cascade = {CascadeType.ALL})
    public Collection<PostsEntity> getPostsesById() {
        return postsesById;
    }

    public void setPostsesById(Collection<PostsEntity> postsesById) {
        this.postsesById = postsesById;
    }

    @OneToMany(mappedBy = "usersByLasteditoruserid")
    public Collection<PostsEntity> getPostsesById_0() {
        return postsesById_0;
    }

    public void setPostsesById_0(Collection<PostsEntity> postsesById_0) {
        this.postsesById_0 = postsesById_0;
    }

    @OneToMany(mappedBy = "usersByUserid")
    public Collection<VotesEntity> getVotesById() {
        return votesById;
    }

    public void setVotesById(Collection<VotesEntity> votesById) {
        this.votesById = votesById;
    }
}
