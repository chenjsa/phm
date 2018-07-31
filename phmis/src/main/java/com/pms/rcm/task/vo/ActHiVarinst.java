package com.pms.rcm.task.vo;
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

@Entity
@Table(name="act_hi_varinst")
public class ActHiVarinst  implements java.io.Serializable {

    private static final long serialVersionUID = 167466785093L; 
    public ActHiVarinst() { 
    } 
   
    @Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid") 
	@Column(name="ID_")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="PROC_INST_ID_")
	private String procInstId; 
	public String getProcInstId() {
		return this.procInstId;
	} 
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	@Column(name="EXECUTION_ID_")
	private String executionId; 
	public String getExecutionId() {
		return this.executionId;
	} 
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	@Column(name="TASK_ID_")
	private String taskId; 
	public String getTaskId() {
		return this.taskId;
	} 
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="NAME_")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="VAR_TYPE_")
	private String varType; 
	public String getVarType() {
		return this.varType;
	} 
	public void setVarType(String varType) {
		this.varType = varType;
	}
	@Column(name="REV_")
	private Integer rev; 
	public Integer getRev() {
		return this.rev;
	} 
	public void setRev(Integer rev) {
		this.rev = rev;
	}
	@Column(name="BYTEARRAY_ID_")
	private String bytearrayId; 
	public String getBytearrayId() {
		return this.bytearrayId;
	} 
	public void setBytearrayId(String bytearrayId) {
		this.bytearrayId = bytearrayId;
	}
	 
	@Column(name="TEXT_")
	private String text; 
	public String getText() {
		return this.text;
	} 
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="TEXT2_")
	private String text2; 
	public String getText2() {
		return this.text2;
	} 
	public void setText2(String text2) {
		this.text2 = text2;
	}
	@Column(name="CREATE_TIME_")
	private java.sql.Timestamp createTime; 
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	} 
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name="LAST_UPDATED_TIME_")
	private java.sql.Timestamp lastUpdatedTime; 
	public java.sql.Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	} 
	public void setLastUpdatedTime(java.sql.Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	 
	

	

  





}


