package com.asl.asl_rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HEIR")
public class Heir {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "HEIR_CERTIFICATE_ID", nullable = false)
	private HeirCertificate heirCertificate;

	@Column(name = "NAME", length = 100)
	public String name;

	@Column(name = "AGE", length = 3)
	public String age;

	@Column(name = "RELATION", length = 20)
	public String relation;

	@Column(name = "REMARK")
	public String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HeirCertificate getHeirCertificate() {
		return heirCertificate;
	}

	public void setHeirCertificate(HeirCertificate heirCertificate) {
		this.heirCertificate = heirCertificate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Heir() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Heir [id=" + id + ", heirCertificate=" + heirCertificate + ", name=" + name + ", age=" + age
				+ ", relation=" + relation + ", remark=" + remark + "]";
	}
	
	

}
