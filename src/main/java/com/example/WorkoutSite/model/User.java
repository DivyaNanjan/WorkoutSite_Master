package com.example.WorkoutSite.model;


import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    private interface Table {
        String USERID = "USERID";
        String USERNAME = "USERNAME";
        String PASSWORD = "PASSWORD";
        String EMAILID =  "EMAILID";
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Table.USERID)
    private Integer userId;

    @Column(name = Table.USERNAME)
    private String userName;

    @Column(name = Table.PASSWORD)
    private String password;

    @Column(name = Table.EMAILID, unique = true)
    private String emailId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public User(Integer userId, String userName, String password, String emailId) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", userName=" + userName
                + ", emailId=" + emailId  + "]";
    }
}
