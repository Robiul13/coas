package com.asl.asl_rms.model;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "HEIR_CERTIFICATE")
public class HeirCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    @Column(name = "NAME", length = 100)
    public String dName;
    
    @Column(name = "RELATION", length = 20)
    public String dRelation;
    
    @Column(name = "GENDER", length = 20)
    public String dGender;
    
    @Column(name = "FATHER_NAME",  length = 100)
    public String dFatherName;
    
    @Column(name = "MOTHER_NAME",  length = 100)
    public String dMotherName;
    
    @Column(name = "PRESENT_ADDR")
    public String dPresentAddr;
    
    @Column(name = "PERMANENT_ADDR")
    public String dPermanentAddr;
    
    @Column(name = "M_STATUS", length = 10)
    public String mStatus;
    
    @Column(name = "DOD", length = 10)
    public String dod;
    
    @Column(name = "LANG", length = 4)
    public String lang;

	@Column(name = "HUSBAND_NAME",  length = 100)
    public String dHusbandName;
	
	@Column(name = "APPLICANT_NAME", length=100)
    public String applicantName;
	
	@Column(name = "APPLICANT_ADDR")
    public String applicantAddr;
	
	@Column(name = "APPLICANT_MOBILE", length=15)
    public String applicantMobile;
	
	@Column(name = "APPLICANT_NID_NO", length=20)
    public String applicantNidNo;
	
    
    @Column(name = "TRACKING_NO", length=32)
    public String trackingNo;
    
    @Column(name = "IMG1_PATH")
    public String img1Path;
    
    @Column(name = "IMG2_PATH")
    public String img2Path;
    
    @Column(name = "IMG3_PATH")
    public String img3Path;
     
    
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date createDate;
    
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date modifyDate;
    
    @Column(name = "MODIFY_BY", length=50)
    public String modifyBy;
    
    @Column(name = "STATUS", length=4)
    public String status;
    
    @Column(name = "REMARK")
    public String remark;
    
  
    @Transient
    private List<String> heirName;
    
    @Transient
    private List<String> heirRelation;
    
    @Transient
    private List<String> heirAge;
    
    @Transient
    private List<String> heirRemark;
    
  

	public Long getId() {
		return id;
	}



	public String getdName() {
		return dName;
	}



	public void setdName(String dName) {
		this.dName = dName;
	}



	public String getdRelation() {
		return dRelation;
	}



	public void setdRelation(String dRelation) {
		this.dRelation = dRelation;
	}



	public String getdGender() {
		return dGender;
	}



	public void setdGender(String dGender) {
		this.dGender = dGender;
	}



	public String getdFatherName() {
		return dFatherName;
	}



	public void setdFatherName(String dFatherName) {
		this.dFatherName = dFatherName;
	}



	public String getdMotherName() {
		return dMotherName;
	}



	public void setdMotherName(String dMotherName) {
		this.dMotherName = dMotherName;
	}



	public String getdPresentAddr() {
		return dPresentAddr;
	}



	public void setdPresentAddr(String dPresentAddr) {
		this.dPresentAddr = dPresentAddr;
	}



	public String getdPermanentAddr() {
		return dPermanentAddr;
	}



	public void setdPermanentAddr(String dPermanentAddr) {
		this.dPermanentAddr = dPermanentAddr;
	}



	public String getmStatus() {
		return mStatus;
	}



	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}



	public String getDod() {
		return dod;
	}



	public void setDod(String dod) {
		this.dod = dod;
	}



	public String getdHusbandName() {
		return dHusbandName;
	}



	public void setdHusbandName(String dHusbandName) {
		this.dHusbandName = dHusbandName;
	}



	public String getTrackingNo() {
		return trackingNo;
	}



	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}



	public String getImg1Path() {
		return img1Path;
	}



	public void setImg1Path(String img1Path) {
		this.img1Path = img1Path;
	}



	public String getImg2Path() {
		return img2Path;
	}



	public void setImg2Path(String img2Path) {
		this.img2Path = img2Path;
	}



	public String getImg3Path() {
		return img3Path;
	}



	public void setImg3Path(String img3Path) {
		this.img3Path = img3Path;
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



	public List<String> getHeirName() {
		return heirName;
	}



	public void setHeirName(List<String> heirName) {
		this.heirName = heirName;
	}



	public List<String> getHeirRelation() {
		return heirRelation;
	}



	public void setHeirRelation(List<String> heirRelation) {
		this.heirRelation = heirRelation;
	}



	public List<String> getHeirAge() {
		return heirAge;
	}



	public void setHeirAge(List<String> heirAge) {
		this.heirAge = heirAge;
	}



	public List<String> getHeirRemark() {
		return heirRemark;
	}



	public void setHeirRemark(List<String> heirRemark) {
		this.heirRemark = heirRemark;
	}



	public void setId(Long id) {
		this.id = id;
	}






	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}



	public String getApplicantName() {
		return applicantName;
	}



	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}



	public String getApplicantAddr() {
		return applicantAddr;
	}



	public void setApplicantAddr(String applicantAddr) {
		this.applicantAddr = applicantAddr;
	}



	public String getApplicantMobile() {
		return applicantMobile;
	}



	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}



	public String getApplicantNidNo() {
		return applicantNidNo;
	}



	public void setApplicantNidNo(String applicantNidNo) {
		this.applicantNidNo = applicantNidNo;
	}
	
	


	public String getModifyBy() {
		return modifyBy;
	}



	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}



	public HeirCertificate() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "HeirCertificate [id=" + id + ", dName=" + dName + ", dRelation=" + dRelation + ", dGender=" + dGender
				+ ", dFatherName=" + dFatherName + ", dMotherName=" + dMotherName + ", dPresentAddr=" + dPresentAddr
				+ ", dPermanentAddr=" + dPermanentAddr + ", mStatus=" + mStatus + ", dod=" + dod + ", lang=" + lang
				+ ", dHusbandName=" + dHusbandName + ", applicantName=" + applicantName + ", applicantAddr="
				+ applicantAddr + ", applicantMobile=" + applicantMobile + ", applicantNidNo=" + applicantNidNo
				+ ", trackingNo=" + trackingNo + ", img1Path=" + img1Path + ", img2Path=" + img2Path + ", img3Path="
				+ img3Path + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy
				+ ", status=" + status + ", remark=" + remark + ", heirName=" + heirName + ", heirRelation="
				+ heirRelation + ", heirAge=" + heirAge + ", heirRemark=" + heirRemark + "]";
	}
	




	


}
