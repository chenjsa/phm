package com.pms.rcm.soft.vo;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pms.rcm.radar.vo.ProvinceInfo;

import javax.persistence.GenerationType;
@Entity
@Table(name="software_version_info")
public class SoftwareVersionInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 924158691741L; 
    public SoftwareVersionInfo() { 
    } 
   
    @Id
    @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
  	@Column(name="id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Software_type_id")
	private String softwareTypeId; 
    @ManyToOne(optional = true)
	@JoinColumn(name = "Software_type_id",insertable = false, updatable = false) 
	private SoftwareTypeInfo softwareTypeInfo;
    
	public SoftwareTypeInfo getSoftwareTypeInfo() {
		return softwareTypeInfo;
	}
	public void setSoftwareTypeInfo(SoftwareTypeInfo softwareTypeInfo) {
		this.softwareTypeInfo = softwareTypeInfo;
	}
	public String getSoftwareTypeId() {
		return this.softwareTypeId;
	} 
	public void setSoftwareTypeId(String softwareTypeId) {
		this.softwareTypeId = softwareTypeId;
	} 
	@Column(name="Version_number")
	private String versionNumber; 
	public String getVersionNumber() {
		return this.versionNumber;
	} 
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	} 
	@Column(name="Version_description")
	private String versionDescription; 
	public String getVersionDescription() {
		return this.versionDescription;
	} 
	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	} 
	@Column(name="PHM_task_id")
	private String phmTaskId; 
	public String getPhmTaskId() {
		return this.phmTaskId;
	} 
	public void setPhmTaskId(String phmTaskId) {
		this.phmTaskId = phmTaskId;
	} 
	@Column(name="User_id")
	private String userId; 
	public String getUserId() {
		return this.userId;
	} 
	public void setUserId(String userId) {
		this.userId = userId;
	} 
	@Column(name="Release_time")
	private java.sql.Date releaseTime; 
	public java.sql.Date getReleaseTime() {
		return this.releaseTime;
	} 
	public void setReleaseTime(java.sql.Date releaseTime) {
		this.releaseTime = releaseTime;
	} 
	@Column(name="Software_URL")
	private String softwareUrl; 
	public String getSoftwareUrl() {
		return this.softwareUrl;
	} 
	public void setSoftwareUrl(String softwareUrl) {
		this.softwareUrl = softwareUrl;
	} 
	@Column(name="File_size")
	private Integer fileSize; 
	public Integer getFileSize() {
		return this.fileSize;
	} 
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	} 

	

	

  



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("软件类型ID:" + this.softwareTypeId + "；"); 
		sb.append("版本号:" + this.versionNumber + "；"); 
		sb.append("版本说明:" + this.versionDescription + "；"); 
		sb.append("应用ID:" + this.phmTaskId + "；"); 
		sb.append("发布人ID:" + this.userId + "；"); 
		sb.append("发布时间:" + this.releaseTime + "；"); 
		sb.append("软件URL:" + this.softwareUrl + "；"); 
		sb.append("文件大小:" + this.fileSize + "；"); 

		return sb.toString();
	}
	

}


