package com.kkarlberg.conan.db;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.kkarlberg.conan.utils.DateAdapter;

@Entity
@Table(name = "DeployedApp")
@XmlAccessorType(XmlAccessType.NONE) 
@XmlRootElement(name="deployedApp")
public class DeployedApp {

	@XmlElement(name = "id", required = true) 
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
    private long id;

    @XmlElement(name = "name", required = true) 
    @Column(name = "name", nullable = false, updatable=false)
    private String name;

    @XmlElement(name = "who", required = true) 
    @Column(name = "who", nullable = false, updatable=false)
    private String who;
    
    @XmlElement(name = "when", required = true) 
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "when", nullable = false, updatable=false)
    private Date when;
    
    @XmlElement(name = "host", required = true) 
    @Column(name = "host", nullable = false, insertable = true)
    private String host;

    @XmlElement(name = "version", required = true) 
    @Column(name = "version", nullable = false, insertable = true)
    private String version;

    @XmlElement(name = "environment", required = true) 
    @Column(name = "environment", nullable = false, insertable = true)
    private String environment;

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
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}
