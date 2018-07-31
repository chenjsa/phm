package com.pms.rcm.task.vo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Table(name="act_hi_actinst")
public class ActHiActinst  implements java.io.Serializable {

    private static final long serialVersionUID = 772001461559L; 
    public ActHiActinst() { 
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
	@Column(name="ACT_ID_")
	private String actId; 
	public String getActId() {
		return this.actId;
	} 
	public void setActId(String actId) {
		this.actId = actId;
	}
	@Column(name="TASK_ID_")
	private String taskId; 
	public String getTaskId() {
		return this.taskId;
	} 
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="CALL_PROC_INST_ID_")
	private String callProcInstId; 
	public String getCallProcInstId() {
		return this.callProcInstId;
	} 
	public void setCallProcInstId(String callProcInstId) {
		this.callProcInstId = callProcInstId;
	}
	@Column(name="ACT_NAME_")
	private String actName; 
	public String getActName() {
		return this.actName;
	} 
	public void setActName(String actName) {
		this.actName = actName;
	}
	@Column(name="ACT_TYPE_")
	private String actType; 
	public String getActType() {
		return this.actType;
	} 
	public void setActType(String actType) {
		this.actType = actType;
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
 
 





}


