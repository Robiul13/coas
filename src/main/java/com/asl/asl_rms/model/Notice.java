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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NOTICE")
public class Notice {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TRACKING_NO", length = 32)
	private String trackingNo;

	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "TITLE")
	private String title;
	
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "TITLE_BN")
	private String titleBn;

	@NotNull
	@Column(name = "PUBLISH_DATE", length = 10)
	private String publishDate;

	@Column(name = "URL")
	private String url;

	@JsonIgnore
	@Column(name = "ACTIVE", length = 1)
	private boolean active;
	
	@JsonIgnore
	@Column(name = "CREATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDate;

	@JsonIgnore
	@Column(name = "MODIFY_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modifyDate;

	@JsonIgnore
	@Column(name = "CREATE_BY", length = 50)
	public String createBy;

	@JsonIgnore
	@Column(name = "MODIFY_BY", length = 50)
	public String modifyBy;
	
	
	@Transient
	MultipartFile pdf;

	public Notice() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public MultipartFile getPdf() {
		return pdf;
	}

	public void setPdf(MultipartFile pdf) {
		this.pdf = pdf;
	}
	
	

	public String getTitleBn() {
		return titleBn;
	}

	public void setTitleBn(String titleBn) {
		this.titleBn = titleBn;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", trackingNo=" + trackingNo + ", title=" + title + ", titleBn=" + titleBn
				+ ", publishDate=" + publishDate + ", url=" + url + ", active=" + active + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", createBy=" + createBy + ", modifyBy=" + modifyBy + ", pdf=" + pdf.getOriginalFilename()
				+ "]";
	}

	

	

}
