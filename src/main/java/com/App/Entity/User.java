package com.App.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class User {
    public User() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private short gendor;
    private String about;
    private Date created_at;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gendor=" + gendor +
                ", about='" + about + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getGendor() {
        return gendor;
    }

    public void setGendor(short gendor) {
        this.gendor = gendor;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }


    public User(String name, String email, String password, short gendor, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gendor = gendor;
        this.about = about;
    }

    public User(int id, String name, String email, String password, short gendor, String about, Date created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gendor = gendor;
        this.about = about;
        this.created_at = created_at;
    }
}
