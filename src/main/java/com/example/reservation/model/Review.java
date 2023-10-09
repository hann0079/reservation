package com.example.reservation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Store store;
    @ManyToOne
    private User user;
    private String content;
    private int rating;
}
