package com.App.Entity;

import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", created_at=" + createdAt +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Todo(int id, User user, String name, String description, short priority, short status, Date created_at) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = created_at;
    }

    private String name;
    private String description;
    private short priority; //1=high 2=low
    private short status; // 1 =done 0=pending
    private Date createdAt;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getPriority() {
        return priority;
    }

    public void setPriority(short priority) {
        this.priority = priority;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }

    public Todo(String name, String description, short priority, short status, Date created_at) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = created_at;
    }

    public Todo() {
    }

    public Todo(int id, String name, String description, short priority, short status, Date created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = created_at;
    }
}
