package com.pms.rcm.phmtask.vo;
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

import javax.persistence.GenerationType;
@Entity
@Table(name="task_result")
public class TaskResult  implements java.io.Serializable {

    private static final long serialVersionUID = 770670500135L; 
    public TaskResult() { 
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
	@Column(name="Task_id")
	private Integer taskId; 
	public Integer getTaskId() {
		return this.taskId;
	} 
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	} 
	@Column(name="Record_time")
	private java.sql.Timestamp recordTime; 
	public java.sql.Timestamp getRecordTime() {
		return this.recordTime;
	} 
	public void setRecordTime(java.sql.Timestamp recordTime) {
		this.recordTime = recordTime;
	} 
	@Column(name="Result_data")
	private String resultData; 
	public String getResultData() {
		return this.resultData;
	} 
	public void setResultData(String resultData) {
		this.resultData = resultData;
	} 
	@Column(name="remark")
	private String remark; 
	public String getRemark() {
		return this.remark;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	} 

	

	
	@ManyToOne(optional = true)
	@JoinColumn(name = "Task_id",insertable = false, updatable = false) 
	private PhmtaskInfo phmtaskInfo; 

	public PhmtaskInfo getPhmtaskInfo() {
		return phmtaskInfo;
	}
	public void setPhmtaskInfo(PhmtaskInfo phmtaskInfo) {
		this.phmtaskInfo = phmtaskInfo;
	}
   @Transient
   private java.sql.Timestamp recordTimeB; 
   @Transient
   private java.sql.Timestamp recordTimeE; 
   
   
	
	public java.sql.Timestamp getRecordTimeB() {
	return recordTimeB;
}
public void setRecordTimeB(java.sql.Timestamp recordTimeB) {
	this.recordTimeB = recordTimeB;
}
public java.sql.Timestamp getRecordTimeE() {
	return recordTimeE;
}
public void setRecordTimeE(java.sql.Timestamp recordTimeE) {
	this.recordTimeE = recordTimeE;
}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("任务编号:" + this.taskId + "；"); 
		sb.append("记录时间:" + this.recordTime + "；"); 
		sb.append("结果数据:" + this.resultData + "；"); 
		sb.append("备注:" + this.remark + "；"); 

		return sb.toString();
	}
	

}


