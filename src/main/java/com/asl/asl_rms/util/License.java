package com.asl.asl_rms.util;


import java.io.Serializable;

public class License
  implements Serializable
{
  private static final long serialVersionUID = 8798384776386259183L;
  private String licNo;
  private String clientNo;
  private String expDate;
  private String validDay;
  private String createDate;
  private String cpuId;
  private String diskId;
  private String biosId;
  private boolean expLimitFlag;
  private boolean machInfoLimitFlag;

  public String getLicNo()
  {
    return this.licNo;
  }
  public void setLicNo(String licNo) {
    this.licNo = licNo;
  }
  public String getClientNo() {
    return this.clientNo;
  }
  public void setClientNo(String clientNo) {
    this.clientNo = clientNo;
  }
 
  public String getExpDate() {
    return this.expDate;
  }
  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }
  public String getCreateDate() {
    return this.createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getCpuId() {
    return this.cpuId;
  }
  public void setCpuId(String cpuId) {
    this.cpuId = cpuId;
  }
  public String getDiskId() {
    return this.diskId;
  }
  public void setDiskId(String diskId) {
    this.diskId = diskId;
  }
  public String getBiosId() {
    return this.biosId;
  }
  public void setBiosId(String biosId) {
    this.biosId = biosId;
  }
  public String getValidDay() {
    return this.validDay;
  }
  public void setValidDay(String validDay) {
    this.validDay = validDay;
  }

  public boolean isExpLimitFlag() {
    return this.expLimitFlag;
  }
  public void setExpLimitFlag(boolean expLimitFlag) {
    this.expLimitFlag = expLimitFlag;
  }
  public boolean isMachInfoLimitFlag() {
    return this.machInfoLimitFlag;
  }
  public void setMachInfoLimitFlag(boolean machInfoLimitFlag) {
    this.machInfoLimitFlag = machInfoLimitFlag;
  }
}