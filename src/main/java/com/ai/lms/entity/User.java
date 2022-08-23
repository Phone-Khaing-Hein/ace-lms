package com.ai.lms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(nullable = false,length = 10,name = "login_id")
    private String loginId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        Admin,
        Teacher,
        Student
    }
}
