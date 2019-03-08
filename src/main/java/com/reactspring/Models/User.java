package com.reactspring.Models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public User() {}
