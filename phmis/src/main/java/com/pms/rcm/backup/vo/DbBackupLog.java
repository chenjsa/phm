package com.pms.rcm.backup.vo;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GenerationType;
@Entity
@Table(name="db_backup_log")
public class DbBackupLog  implements java.io.Serializable {

    private static final long serialVersionUID = 499114787484L; 
    public DbBackupLog() { 
    } 
   
    @Id
    @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
  	@Column(name="ID")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="backup_time")
	private java.sql.Timestamp backupTime; 
	public java.sql.Timestamp getBackupTime() {
		return this.backupTime;
	} 
	public void setBackupTime(java.sql.Timestamp backupTime) {
		this.backupTime = backupTime;
	} 
	@Column(name="backup_name")
	private String backupName; 
	public String getBackupName() {
		return this.backupName;
	} 
	public void setBackupName(String backupName) {
		this.backupName = backupName;
	} 
	@Column(name="backup_type")
	private String backupType; 
	public String getBackupType() {
		return this.backupType;
	} 
	public void setBackupType(String backupType) {
		this.backupType = backupType;
	} 
	@Column(name="backup_model")
	private String backupModel; 
	public String getBackupModel() {
		return this.backupModel;
	} 
	public void setBackupModel(String backupModel) {
		this.backupModel = backupModel;
	} 
	@Column(name="file_size")
	private String fileSize; 
	public String getFileSize() {
		return this.fileSize;
	} 
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	} 
	@Column(name="store_path")
	private String storePath; 
	public String getStorePath() {
		return this.storePath;
	} 
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	} 
	@Column(name="backup_status")
	private String backupStatus; 
	public String getBackupStatus() {
		return this.backupStatus;
	} 
	public void setBackupStatus(String backupStatus) {
		this.backupStatus = backupStatus;
	} 

	

	

  



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("备份时间:" + this.backupTime + "；"); 
		sb.append("backup_name:" + this.backupName + "；"); 
		sb.append("备份类型:" + this.backupType + "；"); 
		sb.append("备份方式:" + this.backupModel + "；"); 
		sb.append("文件大小:" + this.fileSize + "；"); 
		sb.append("存放路径:" + this.storePath + "；"); 
		sb.append("执行状态:" + this.backupStatus + "；"); 

		return sb.toString();
	}
	@Transient
	private java.sql.Timestamp backupTimeB; 
	@Transient
	private java.sql.Timestamp backupTimeE;
	public java.sql.Timestamp getBackupTimeB() {
		return backupTimeB;
	}
	public void setBackupTimeB(java.sql.Timestamp backupTimeB) {
		this.backupTimeB = backupTimeB;
	}
	public java.sql.Timestamp getBackupTimeE() {
		return backupTimeE;
	}
	public void setBackupTimeE(java.sql.Timestamp backupTimeE) {
		this.backupTimeE = backupTimeE;
	} 
	
}


