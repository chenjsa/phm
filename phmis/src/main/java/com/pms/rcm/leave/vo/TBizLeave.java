package com.pms.rcm.leave.vo;
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

import com.pms.rcm.task.vo.ActTransModel;

@Entity
@Table(name="t_biz_leave")
public class TBizLeave  implements java.io.Serializable {

    private static final long serialVersionUID = 475725459008L; 
    public TBizLeave() { 
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
	@Column(name="APPLY_USER_ID")
	private String applyUserId; 
	public String getApplyUserId() {
		return this.applyUserId;
	} 
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	@Column(name="LEAVE_TYPE")
	private String leaveType; 
	public String getLeaveType() {
		return this.leaveType;
	} 
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	@Column(name="APPLY_DATE")
	private String applyDate; 
	public String getApplyDate() {
		return this.applyDate;
	} 
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	@Column(name="START_DATE")
	private String startDate; 
	public String getStartDate() {
		return this.startDate;
	} 
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Column(name="END_DATE")
	private String endDate; 
	public String getEndDate() {
		return this.endDate;
	} 
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Column(name="REASON")
	private String reason; 
	public String getReason() {
		return this.reason;
	} 
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name="MANAGER_ID")
	private String managerId; 
	public String getManagerId() {
		return this.managerId;
	} 
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	@Column(name="REMARKS1")
	private String remarks1; 
	public String getRemarks1() {
		return this.remarks1;
	} 
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	@Column(name="LEADER_ID")
	private String leaderId; 
	public String getLeaderId() {
		return this.leaderId;
	} 
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	@Column(name="REMARKS2")
	private String remarks2; 
	public String getRemarks2() {
		return this.remarks2;
	} 
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	@Column(name="PROCESS_ID")
	private String processId; 
	public String getProcessId() {
		return this.processId;
	} 
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	 
	@Transient  
	private List<ActTransModel> outGoingTransNames;
	public List<ActTransModel> getOutGoingTransNames() {
		return outGoingTransNames;
	}
	public void setOutGoingTransNames(List<ActTransModel> outGoingTransNames) {
		this.outGoingTransNames = outGoingTransNames;
	}
	@Transient  
	private String taskId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Transient  
	private String outcome;
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	
	
	 
	 

	

  





}


