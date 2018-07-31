package com.pms.rcm.task.vo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="act_hi_taskinst")
public class ActHiTaskinst  implements java.io.Serializable {

    private static final long serialVersionUID = 943873586578L; 
    public ActHiTaskinst() { 
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
	@Column(name="PROC_DEF_ID_")
	private String procDefId; 
	public String getProcDefId() {
		return this.procDefId;
	} 
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	@Column(name="TASK_DEF_KEY_")
	private String taskDefKey; 
	public String getTaskDefKey() {
		return this.taskDefKey;
	} 
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
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
	@Column(name="NAME_")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="PARENT_TASK_ID_")
	private String parentTaskId; 
	public String getParentTaskId() {
		return this.parentTaskId;
	} 
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	@Column(name="DESCRIPTION_")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="OWNER_")
	private String owner; 
	public String getOwner() {
		return this.owner;
	} 
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Column(name="ASSIGNEE_")
	private String assignee; 
	public String getAssignee() {
		return this.assignee;
	} 
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Column(name="START_TIME_")
	private java.sql.Timestamp startTime; 
	public java.sql.Timestamp getStartTime() {
		return this.startTime;
	} 
	public void setStartTime(java.sql.Timestamp startTime) {
		this.startTime = startTime;
	}
	@Column(name="CLAIM_TIME_")
	private java.sql.Timestamp claimTime; 
	public java.sql.Timestamp getClaimTime() {
		return this.claimTime;
	} 
	public void setClaimTime(java.sql.Timestamp claimTime) {
		this.claimTime = claimTime;
	}
	@Column(name="END_TIME_")
	private java.sql.Timestamp endTime; 
	public java.sql.Timestamp getEndTime() {
		return this.endTime;
	} 
	public void setEndTime(java.sql.Timestamp endTime) {
		this.endTime = endTime;
	}
	@Column(name="DURATION_")
	private Integer duration; 
	public Integer getDuration() {
		return this.duration;
	} 
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	@Column(name="DELETE_REASON_")
	private String deleteReason; 
	public String getDeleteReason() {
		return this.deleteReason;
	} 
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	@Column(name="PRIORITY_")
	private Integer priority; 
	public Integer getPriority() {
		return this.priority;
	} 
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(name="DUE_DATE_")
	private java.sql.Timestamp dueDate; 
	public java.sql.Timestamp getDueDate() {
		return this.dueDate;
	} 
	public void setDueDate(java.sql.Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	@Column(name="FORM_KEY_")
	private String formKey; 
	public String getFormKey() {
		return this.formKey;
	} 
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	@Column(name="CATEGORY_")
	private String category; 
	public String getCategory() {
		return this.category;
	} 
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="TENANT_ID_")
	private String tenantId; 
	public String getTenantId() {
		return this.tenantId;
	} 
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Transient  
	private String startTimeStr;
	@Transient  
	private String endTimeStr;
	@Transient  
	private String claimTimeStr;
	
	
	public String getStartTimeStr() {
		 String tsStr = "";  
	        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        try {  
	            //方法一 
	        	if(startTime!=null)
	        		tsStr = sdf.format(startTime);    
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		return tsStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		 String tsStr = "";  
	        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        try {  
	            //方法一 
	        	if(endTime!=null)
	        		tsStr = sdf.format(endTime);    
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		return tsStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public String getClaimTimeStr() {
		 String tsStr = "";  
	        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        try {  
	            //方法一 
	        	if(claimTime!=null)
	        		tsStr = sdf.format(claimTime);    
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		return tsStr;
	}
	public void setClaimTimeStr(String claimTimeStr) {
		this.claimTimeStr = claimTimeStr;
	}

	@Transient  
    private ActTaskExtend actTaskExtend;
	public ActTaskExtend getActTaskExtend() {
		return actTaskExtend;
	}
	public void setActTaskExtend(ActTaskExtend actTaskExtend) {
		this.actTaskExtend = actTaskExtend;
	}
 
  





}


