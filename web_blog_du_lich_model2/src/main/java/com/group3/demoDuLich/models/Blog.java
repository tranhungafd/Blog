package com.group3.demoDuLich.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 9000000)
    private String content;
    private String title;
    private String summary;
    private int view;
    private int rating;
    private Date date = new Date(System.currentTimeMillis());
    @ManyToOne
    private Category category;

    @OneToMany
    private Set<Image> images;

    @OneToMany
    private Set<Comment> comments;

    @ManyToOne
    private User user;
}
