package com.aminaventon.blog.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false)
    private String content;

    //optional = false
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Date date = new Date();

    public Post () {
    }

    /*
    public Post(String title, String content, Date date, User user) {
        super();
        this.title = title;
        this.content = content;
        this.date = date;
        this.user = user;
    }

     */

    public Post(String title, String content, Date date) {
        super();
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
