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
@Table(name = "CITIZEN_CERTIFICATE")
public class CitizenCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME", length = 100)
    private String name;
    
    @Column(name = "FH_NAME", length = 100)
    private String fhName;
    
    @Column(name = "FH_TYPE", length = 4)
    private String fhType;
    
    @Column(name = "LANG", length = 4)
    private String lang;
    
    @Column(name = "MOTHER_NAME", length = 100)
    private String motherName;
    
    @Column(name = "PRESENT_ADDR")
    private String presentAddr;
    
    @Column(name = "PERMANENT_ADDR")
    private String permanentAddr;
    
    
    @Column(name = "TRACKING_NO", length=32)
    private String trackingNo;
    
    @Column(name = "NID1_PATH")
    private String nid1Path;
    
    @Column(name = "NID2_PATH")
    private String nid2Path;
    
    
    @Column(name = "PP_PATH")
    private String ppPath;
    
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
    
    @Column(name = "MOBILE", length=20)
    private String mobile;
    
    
    @Column(name = "REFERENCE")
    private String reference;
    
 
    @Transient
    String uuid;
    
  

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



	public String getFhName() {
		return fhName;
	}



	public void setFhName(String fhName) {
		this.fhName = fhName;
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



	public String getPermanentAddr() {
		return permanentAddr;
	}



	public void setPermanentAddr(String permanentAddr) {
		this.permanentAddr = permanentAddr;
	}



	public String getTrackingNo() {
		return trackingNo;
	}



	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
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
	
	public String getNid1Path() {
		return nid1Path;
	}



	public void setNid1Path(String nid1Path) {
		this.nid1Path = nid1Path;
	}



	public String getNid2Path() {
		return nid2Path;
	}



	public void setNid2Path(String nid2Path) {
		this.nid2Path = nid2Path;
	}



	public String getPpPath() {
		return ppPath;
	}



	public void setPpPath(String ppPath) {
		this.ppPath = ppPath;
	}



	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}




	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getReference() {
		return reference;
	}



	public void setReference(String reference) {
		this.reference = reference;
	}



	public String getModifyBy() {
		return modifyBy;
	}



	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}



	public CitizenCertificate() {
		super();
		
	}



	@Override
	public String toString() {
		return "CitizenCertificate [id=" + id + ", name=" + name + ", fhName=" + fhName + ", fhType=" + fhType
				+ ", lang=" + lang + ", motherName=" + motherName + ", presentAddr=" + presentAddr + ", permanentAddr="
				+ permanentAddr + ", trackingNo=" + trackingNo + ", nid1Path=" + nid1Path + ", nid2Path=" + nid2Path
				+ ", ppPath=" + ppPath + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", modifyBy="
				+ modifyBy + ", status=" + status + ", remark=" + remark + ", mobile=" + mobile + ", reference="
				+ reference + ", uuid=" + uuid + "]";
	}






	
	

	


}
