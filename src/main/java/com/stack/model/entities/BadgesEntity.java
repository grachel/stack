package com.stack.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "badges", schema = "public", catalog = "de23md1m4q7ru7")
public class BadgesEntity {
    private int id;
    private Integer userid;
    private String title;
    private Timestamp creationdate;
    private UsersEntity usersByUserid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
    @Column(name = "creationdate", nullable = true)
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BadgesEntity that = (BadgesEntity) o;

        if (id != that.id) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    public UsersEntity getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(UsersEntity usersByUserid) {
        this.usersByUserid = usersByUserid;
    }
}
