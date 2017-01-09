package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by grzesiek on 2017-01-09.
 */
@Entity
@Table(name = "posts", schema = "public", catalog = "de23md1m4q7ru7")
public class PostsEntity {
    private int id;
    private String posttype;
    private Timestamp creationdate;
    private Integer score;
    private String body;
    private Timestamp lasteditdate;
    private String title;
    private String tags;
    private Timestamp closeddate;
    private String closedreason;
    private Collection<CommentariesEntity> commentariesById;
    private Collection<PostlinksEntity> postlinksesById;
    private Collection<PostlinksEntity> postlinksesById_0;
    private PostsEntity postsByAcceptedanswerid;
    private Collection<PostsEntity> postsesById;
    private PostsEntity postsByParentid;
    private Collection<PostsEntity> postsesById_0;
    private UsersEntity usersByOwneruserid;
    private UsersEntity usersByLasteditoruserid;
    private Collection<PosttagsEntity> posttagsesById;
    private Collection<VotesEntity> votesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "posttype")
    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
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
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "lasteditdate")
    public Timestamp getLasteditdate() {
        return lasteditdate;
    }

    public void setLasteditdate(Timestamp lasteditdate) {
        this.lasteditdate = lasteditdate;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "closeddate")
    public Timestamp getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
    }

    @Basic
    @Column(name = "closedreason")
    public String getClosedreason() {
        return closedreason;
    }

    public void setClosedreason(String closedreason) {
        this.closedreason = closedreason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostsEntity that = (PostsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (posttype != null ? posttype.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (lasteditdate != null ? lasteditdate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (closeddate != null ? closeddate.hashCode() : 0);
        result = 31 * result + (closedreason != null ? closedreason.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "postsByPostid")
    public Collection<CommentariesEntity> getCommentariesById() {
        return commentariesById;
    }

    public void setCommentariesById(Collection<CommentariesEntity> commentariesById) {
        this.commentariesById = commentariesById;
    }

    @OneToMany(mappedBy = "postsByPostid")
    public Collection<PostlinksEntity> getPostlinksesById() {
        return postlinksesById;
    }

    public void setPostlinksesById(Collection<PostlinksEntity> postlinksesById) {
        this.postlinksesById = postlinksesById;
    }

    @OneToMany(mappedBy = "postsByRelatedpostid")
    public Collection<PostlinksEntity> getPostlinksesById_0() {
        return postlinksesById_0;
    }

    public void setPostlinksesById_0(Collection<PostlinksEntity> postlinksesById_0) {
        this.postlinksesById_0 = postlinksesById_0;
    }

    @ManyToOne
    @JoinColumn(name = "acceptedanswerid", referencedColumnName = "id")
    public PostsEntity getPostsByAcceptedanswerid() {
        return postsByAcceptedanswerid;
    }

    public void setPostsByAcceptedanswerid(PostsEntity postsByAcceptedanswerid) {
        this.postsByAcceptedanswerid = postsByAcceptedanswerid;
    }

    @OneToMany(mappedBy = "postsByAcceptedanswerid")
    public Collection<PostsEntity> getPostsesById() {
        return postsesById;
    }

    public void setPostsesById(Collection<PostsEntity> postsesById) {
        this.postsesById = postsesById;
    }

    @ManyToOne
    @JoinColumn(name = "parentid", referencedColumnName = "id")
    public PostsEntity getPostsByParentid() {
        return postsByParentid;
    }

    public void setPostsByParentid(PostsEntity postsByParentid) {
        this.postsByParentid = postsByParentid;
    }

    @OneToMany(mappedBy = "postsByParentid")
    public Collection<PostsEntity> getPostsesById_0() {
        return postsesById_0;
    }

    public void setPostsesById_0(Collection<PostsEntity> postsesById_0) {
        this.postsesById_0 = postsesById_0;
    }

    @ManyToOne
    @JoinColumn(name = "owneruserid", referencedColumnName = "id")
    public UsersEntity getUsersByOwneruserid() {
        return usersByOwneruserid;
    }

    public void setUsersByOwneruserid(UsersEntity usersByOwneruserid) {
        this.usersByOwneruserid = usersByOwneruserid;
    }

    @ManyToOne
    @JoinColumn(name = "lasteditoruserid", referencedColumnName = "id")
    public UsersEntity getUsersByLasteditoruserid() {
        return usersByLasteditoruserid;
    }

    public void setUsersByLasteditoruserid(UsersEntity usersByLasteditoruserid) {
        this.usersByLasteditoruserid = usersByLasteditoruserid;
    }

    @OneToMany(mappedBy = "postsByPostid")
    public Collection<PosttagsEntity> getPosttagsesById() {
        return posttagsesById;
    }

    public void setPosttagsesById(Collection<PosttagsEntity> posttagsesById) {
        this.posttagsesById = posttagsesById;
    }

    @OneToMany(mappedBy = "postsByPostid")
    public Collection<VotesEntity> getVotesById() {
        return votesById;
    }

    public void setVotesById(Collection<VotesEntity> votesById) {
        this.votesById = votesById;
    }
}
