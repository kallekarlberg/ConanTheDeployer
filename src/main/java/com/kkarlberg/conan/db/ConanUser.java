package com.kkarlberg.conan.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConanUser")
public class ConanUser {

    @Id
    @Column(name = "username", nullable = false, insertable = true, updatable = false)
    private String username;

    @Column(name = "password", nullable = false, insertable = true, updatable = true)
    private String password;

    @Column(name = "salt", nullable = false, insertable = true, updatable = true)
    private String salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String sa) {
        salt = sa;
    }
}
