package com.kkarlberg.conan.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConanPermission")
public class ConanPermission {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
    private int id;

    @Column(name = "role", nullable = false, insertable = true, updatable = false)
    private String role;

    @Column(name = "permission", nullable = false, insertable = true, updatable = false)
    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String per) {
        this.permission = per;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
