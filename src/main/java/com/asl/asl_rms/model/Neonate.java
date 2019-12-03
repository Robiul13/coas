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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NEONATE")
public class Neonate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(name = "FATHER", length = 100)
	public String father;

	@Column(name = "MOTHER", length = 100)
	public String mother;

	@Column(name = "PRESENT_ADDR")
	public String presentAddr;
	
	@Column(name = "PERMANENT_ADDR")
	public String permanentAddr;

	@Column(name = "LANG", length = 4)
	public String lang;

	@Column(name = "GENDER", length = 6)
	public String gender;

	@Column(name = "HOSPITAL")
	public String hospital;

	@Column(name = "MOBILE", length = 20)
	public String mobile;
	
	@Column(name = "TRACKING_NO", length=32)
    private String trackingNo;
	
	@Column(name = "DOB", length=10)
    private String dob;
	

	@Column(name = "CREATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDate;

	@Column(name = "MODIFY_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modifyDate;

	@Column(name = "MODIFY_BY", length = 50)
	public String modifyBy;

	@Column(name = "STATUS", length = 4)
	public String status;

	@Column(name = "REMARK")
	public String remark;

	public Neonate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}



	public String getPresentAddr() {
		return presentAddr;
	}

	public void setPresentAddr(String presentAddr) {
		this.presentAddr = presentAddr;
	}

	public String getPermanentAddr() {
		return permanentAddr;
	}

	public void setPermanentAddr(String permanentAddr) {
		this.permanentAddr = permanentAddr;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
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

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	@Override
	public String toString() {
		return "Neonate [id=" + id + ", father=" + father + ", mother=" + mother + ", presentAddr=" + presentAddr
				+ ", permanentAddr=" + permanentAddr + ", lang=" + lang + ", gender=" + gender + ", hospital="
				+ hospital + ", mobile=" + mobile + ", trackingNo=" + trackingNo + ", dob=" + dob + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + ", status=" + status
				+ ", remark=" + remark + "]";
	}

	
	

}
