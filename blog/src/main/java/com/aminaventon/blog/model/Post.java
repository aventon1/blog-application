package com.aminaventon.blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Post is an object that holds post attributes id, title, content, User, date
 */
@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    //optional = false
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Date date = new Date();

    public Post(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
