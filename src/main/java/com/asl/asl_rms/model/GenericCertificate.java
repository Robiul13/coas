package com.asl.asl_rms.model;


import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "GENERIC_CERTIFICATE")
public class GenericCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "C_TYPE", length = 50)
    private String certificateType;

    @Column(name = "P_NAME", length = 100)
    private String pName;

    @Column(name = "FH_TYPE", length = 4)
    private String fhType;
    
    @Column(name = "FH_NAME", length = 100)
    private String fhName;

    @Column(name = "MOTHER", length = 100)
    private String motherName;

    @Column(name = "PRESENT_ADDR")
    private String presentAddr;

    @Column(name = "PARMANENT_ADDR")
    private String parmanentAddr;

    @Column(name = "APPLICANT_NAME", length = 100)
    private String applicantName;

    @Column(name = "AFH_TYPE", length = 4)
    private String afhType;
    
    @Column(name = "AFH_NAME", length = 100)
    private String afhName;

    @Column(name = "APPLICANT_ADDR")
    private String applicantAddr;

    @Column(name = "applicantMobile", length = 15)
    private String applicantMobile;

    @Column(name = "LANG", length = 4)
    private String lang;

    @Column(name = "PIC1_PATH")
    private String pic1_path;

    @Column(name = "PIC2_PATH")
    private String pic2_path;

    @Column(name = "CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name = "MODIFY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "STATUS", length=4)
    private String status;
    
    @Column(name = "REMARK")
    private String remark;

    @Column(name = "TRACKING_NO", length=32)
    private String trackingNo;

	@Column(name = "MODIFY_BY", length=50)
	private String modifyBy;
	
    @Transient
    String uuid;

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public GenericCertificate() {
		super();

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getFhType() {
		return fhType;
	}

	public void setFhType(String fhType) {
		this.fhType = fhType;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getPresentAddr() {
		return presentAddr;
	}

	public void setPresentAddr(String presentAddr) {
		this.presentAddr = presentAddr;
	}

	public String getParmanentAddr() {
		return parmanentAddr;
	}

	public void setParmanentAddr(String parmanentAddr) {
		this.parmanentAddr = parmanentAddr;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getAfhType() {
		return afhType;
	}

	public void setAfhType(String afhType) {
		this.afhType = afhType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFhName() {
		return fhName;
	}

	public void setFhName(String fhName) {
		this.fhName = fhName;
	}

	public String getAfhName() {
		return afhName;
	}

	public void setAfhName(String afhName) {
		this.afhName = afhName;
	}

	@Override
	public String toString() {
		return "GenericCertificate [id=" + id + ", certificateType=" + certificateType + ", pName=" + pName
				+ ", fhType=" + fhType + ", fhName=" + fhName + ", motherName=" + motherName + ", presentAddr="
				+ presentAddr + ", parmanentAddr=" + parmanentAddr + ", applicantName=" + applicantName + ", afhType="
				+ afhType + ", afhName=" + afhName + ", applicantAddr=" + applicantAddr + ", applicantMobile="
				+ applicantMobile + ", lang=" + lang + ", pic1_path=" + pic1_path + ", pic2_path=" + pic2_path
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + ", remark="
				+ remark + ", trackingNo=" + trackingNo + ", modifyBy=" + modifyBy + ", uuid=" + uuid + "]";
	}
	
	

}
