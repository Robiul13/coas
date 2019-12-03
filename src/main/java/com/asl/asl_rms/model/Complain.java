package com.asl.asl_rms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COMPLAIN")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", length = 100)	
    private String name;

    @Column(name = "ADDRESS", length = 200)
    private String address;
   
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "MOBILE", length = 15)
    private String mobile;


    @Column(name = "SUBJECT", length = 200)
    private String subject;
    

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "LANG", length = 4)
    private String lang;

    @Column(name = "PIC1_PATH")
    private String pic1_path;

    @Column(name = "PIC2_PATH")
    private String pic2_path;


    @Column(name = "PIC3_PATH")
    private String pic3_path;
    
   
    @Column(name = "CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    @Temporal(TemporalType.TIMESTAMP)    
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date modifyDate;
    
    @Column(name = "MODIFY_BY", length=50)
    private String modifyBy;

    @Column(name = "STATUS", length=4)
    private String status;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "TRACKING_NO", length=32)
    private String trackingNo;

    @Transient
    String uuid;
    
    public Complain() {
		super();

	}



	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPic1_path() {
		return pic1_path;
	}

	public void setPic1_path(String pic1_path) {
		this.pic1_path = pic1_path;
	}

	public String getPic2_path() {
		return pic2_path;
	}

	public void setPic2_path(String pic2_path) {
		this.pic2_path = pic2_path;
	}

	public String getPic3_path() {
		return pic3_path;
	}

	public void setPic3_path(String pic3_path) {
		this.pic3_path = pic3_path;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Override
	public String toString() {
		return "Complain [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", mobile="
				+ mobile + ", subject=" + subject + ", description=" + description + ", lang=" + lang + ", pic1_path="
				+ pic1_path + ", pic2_path=" + pic2_path + ", pic3_path=" + pic3_path + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + ", status=" + status + ", remark=" + remark
				+ ", trackingNo=" + trackingNo + ", uuid=" + uuid + "]";
	}

	
}
