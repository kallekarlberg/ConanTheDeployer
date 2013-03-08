package com.kkarlberg.conan.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConanRole")
public class ConanRole {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
    private int id;

    @Column(name = "username", nullable = false, insertable = true, updatable = false)
    private String username;

    @Column(name = "role", nullable = false, insertable = true, updatable = false)
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
