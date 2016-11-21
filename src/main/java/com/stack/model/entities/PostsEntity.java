package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "posts", schema = "public", catalog = "de23md1m4q7ru7")
public class PostsEntity {
    private int id;
    private String posttype;
    private Integer acceptedanswerid;
    private Integer parentid;
    private Timestamp creationdate;
    private Integer score;
    private String body;
    private Integer owneruserid;
    private Integer lasteditoruserid;
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
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "posttype", nullable = false, length = -1)
    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    @Basic
    @Column(name = "acceptedanswerid", nullable = true)
    public Integer getAcceptedanswerid() {
        return acceptedanswerid;
    }

    public void setAcceptedanswerid(Integer acceptedanswerid) {
        this.acceptedanswerid = acceptedanswerid;
    }

    @Basic
    @Column(name = "parentid", nullable = true)
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "creationdate", nullable = true)
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "body", nullable = false, length = -1)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "owneruserid", nullable = true)
    public Integer getOwneruserid() {
        return owneruserid;
    }

    public void setOwneruserid(Integer owneruserid) {
        this.owneruserid = owneruserid;
    }

    @Basic
    @Column(name = "lasteditoruserid", nullable = true)
    public Integer getLasteditoruserid() {
        return lasteditoruserid;
    }

    public void setLasteditoruserid(Integer lasteditoruserid) {
        this.lasteditoruserid = lasteditoruserid;
    }

    @Basic
    @Column(name = "lasteditdate", nullable = true)
    public Timestamp getLasteditdate() {
        return lasteditdate;
    }

    public void setLasteditdate(Timestamp lasteditdate) {
        this.lasteditdate = lasteditdate;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "tags", nullable = true, length = -1)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "closeddate", nullable = true)
    public Timestamp getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
    }

    @Basic
    @Column(name = "closedreason", nullable = true, length = -1)
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
        if (posttype != null ? !posttype.equals(that.posttype) : that.posttype != null) return false;
        if (acceptedanswerid != null ? !acceptedanswerid.equals(that.acceptedanswerid) : that.acceptedanswerid != null)
            return false;
        if (parentid != null ? !parentid.equals(that.parentid) : that.parentid != null) return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (owneruserid != null ? !owneruserid.equals(that.owneruserid) : that.owneruserid != null) return false;
        if (lasteditoruserid != null ? !lasteditoruserid.equals(that.lasteditoruserid) : that.lasteditoruserid != null)
            return false;
        if (lasteditdate != null ? !lasteditdate.equals(that.lasteditdate) : that.lasteditdate != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (closeddate != null ? !closeddate.equals(that.closeddate) : that.closeddate != null) return false;
        if (closedreason != null ? !closedreason.equals(that.closedreason) : that.closedreason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (posttype != null ? posttype.hashCode() : 0);
        result = 31 * result + (acceptedanswerid != null ? acceptedanswerid.hashCode() : 0);
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (owneruserid != null ? owneruserid.hashCode() : 0);
        result = 31 * result + (lasteditoruserid != null ? lasteditoruserid.hashCode() : 0);
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
