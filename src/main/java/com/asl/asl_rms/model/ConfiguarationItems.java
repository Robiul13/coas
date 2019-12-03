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

@Entity
@Table(name = "CONFIGURATION_ITEMS")
public class ConfiguarationItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
       
    @Column(name = "ITEM_NAME")
    public String itemName;
    
    @Column(name = "ITEM_VALUE", length = 2000)
    public String itemValue;
    
    @Column(name = "LANG", length=4)
    public String lang;
    
    @Column(name = "GROUP_CODE")
    public String groupCode;
    
    @Column(name = "REMARK")
    public String remark;
    
    @Column(name = "OP_RIGHT")
    public String opRight;
    
    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.DATE)
    public Date editDate;
    
    @Column(name = "ACTIVE", length=2)
    public String isActive;


    @Transient
    String clientId;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemValue() {
		return itemValue;
	}


	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}


	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getOpRight() {
		return opRight;
	}


	public void setOpRight(String opRight) {
		this.opRight = opRight;
	}


	public Date getEditDate() {
		return editDate;
	}


	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public ConfiguarationItems() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ConfiguarationItems(String itemName, String itemValue, String lang, String groupCode, String remark,
			String opRight, Date editDate, String isActive) {
		super();
		this.itemName = itemName;
		this.itemValue = itemValue;
		this.lang = lang;
		this.groupCode = groupCode;
		this.remark = remark;
		this.opRight = opRight;
		this.editDate = editDate;
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "ConfiguarationItems [id=" + id + ", itemName=" + itemName + ", itemValue=" + itemValue + ", lang="
				+ lang + ", groupCode=" + groupCode + ", remark=" + remark + ", opRight=" + opRight + ", editDate="
				+ editDate + ", isActive=" + isActive + ", clientId=" + clientId + "]";
	}


	


}
