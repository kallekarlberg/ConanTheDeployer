package com.kkarlberg.conan.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DeployedApp")
@XmlRootElement
public class DeployedApp {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
    private long id;

    @Column(name = "name", nullable = false, updatable=false)
    private String name;

    @Column(name = "host", nullable = false, insertable = true)
    private String host;

    @Column(name = "version", nullable = false, insertable = true)
    private String version;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
