package com.kkarlberg.conan.db;

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
@Table(name = "BUNDLES")
@XmlAccessorType(XmlAccessType.NONE) 
@XmlRootElement(name="bundles")
public class Bundle {

	@XmlElement(name = "id", required = true) 
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = false)
	private long id;

		@XmlElement(name = "name", required = true) 
    @Column(name = "release_name", nullable = false, insertable = true)
    private String name;
	
	@XmlElement(name = "createdBy", required = true) 
    @Column(name = "created_By", nullable = false, insertable = true, updatable = false)
	private String createdBy;
	
	@XmlElement(name = "devNotes") 
    @Column(name = "dev_notes", insertable = true)
	private String devNotes;
	
	@XmlElement(name = "csoNotes") 
    @Column(name = "cso_notes", insertable = true)
	private String csoNotes;
	
	@XmlElement(name = "productNotes") 
    @Column(name = "product_notes", insertable = true)
	private String productNotes;
	
	@XmlElement(name = "approvedBy") 
    @Column(name = "approved_by", insertable = true)
	private String approvedBy;
	
	@XmlElement(name = "approved") 
    @Column(name = "appropved",insertable = true)
	private boolean isAppropved;
	
	@XmlElement(name = "createdWhen", required = true) 
    @XmlJavaTypeAdapter(DateAdapter.class)
	@Column(name = "created_when",insertable = true)
	private Date createdWhen;
	
	@XmlElement(name = "approvedWhen", required = true) 
    @XmlJavaTypeAdapter(DateAdapter.class)
	@Column(name = "approvedwhen",insertable = true)
	private Date approvedWhen;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDevNotes() {
		return devNotes;
	}

	public void setDevNotes(String devNotes) {
		this.devNotes = devNotes;
	}

	public String getCsoNotes() {
		return csoNotes;
	}

	public void setCsoNotes(String csoNotes) {
		this.csoNotes = csoNotes;
	}

	public String getProductNotes() {
		return productNotes;
	}

	public void setProductNotes(String productNotes) {
		this.productNotes = productNotes;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isAppropved() {
		return isAppropved;
	}

	public void setAppropved(boolean isAppropved) {
		this.isAppropved = isAppropved;
	}

	public Date getCreatedWhen() {
		return createdWhen;
	}

	public void setCreatedWhen(Date createdWhen) {
		this.createdWhen = createdWhen;
	}

	public Date getApprovedWhen() {
		return approvedWhen;
	}

	public void setApprovedWhen(Date approvedWhen) {
		this.approvedWhen = approvedWhen;
	}
}
