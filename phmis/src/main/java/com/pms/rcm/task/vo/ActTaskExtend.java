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
@Table(name="act_task_extend")
public class ActTaskExtend  implements java.io.Serializable {

    private static final long serialVersionUID = 147028257101L; 
    public ActTaskExtend() { 
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
	@Column(name="task_node")
	private String taskNode; 
	public String getTaskNode() {
		return this.taskNode;
	} 
	public void setTaskNode(String taskNode) {
		this.taskNode = taskNode;
	}
	@Column(name="audit_content")
	private String auditContent; 
	public String getAuditContent() {
		return this.auditContent;
	} 
	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}
	@Column(name="out_come_name")
	private String outComeName; 
    
	public String getOutComeName() {
		return outComeName;
	}
	public void setOutComeName(String outComeName) {
		this.outComeName = outComeName;
	}

	@Column(name="out_come")
	private String outCome; 
	
	public String getOutCome() {
		return outCome;
	}
	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}
	 

	@Column(name="Handle_user_id")
	private String handleUserId; 
	public String getHandleUserId() {
		return this.handleUserId;
	} 
	public void setHandleUserId(String handleUserId) {
		this.handleUserId = handleUserId;
	}
	@Column(name="handle_user_no")
	private String handleUserNo; 
	public String getHandleUserNo() {
		return this.handleUserNo;
	} 
	public void setHandleUserNo(String handleUserNo) {
		this.handleUserNo = handleUserNo;
	}
	@Column(name="Handle_user_name")
	private String handleUserName; 
	public String getHandleUserName() {
		return this.handleUserName;
	} 
	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}
	@Column(name="task_id")
	private String taskId; 
	public String getTaskId() {
		return this.taskId;
	} 
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Column(name="hand_time")
	private String handTime; 
	
	@Column(name="PROCESS_ID")
	private String  processId;
	public String getHandTime() {
		return handTime;
	}
	public void setHandTime(String handTime) {
		this.handTime = handTime;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	} 
    @Transient  
	private String assigneeVariable;
	public String getAssigneeVariable() {
		return assigneeVariable;
	}
	public void setAssigneeVariable(String assigneeVariable) {
		this.assigneeVariable = assigneeVariable;
	}
	 @Transient  
	private String assignee;
	 @Transient  
	private String jointlySigner;
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getJointlySigner() {
		return jointlySigner;
	}
	public void setJointlySigner(String jointlySigner) {
		this.jointlySigner = jointlySigner;
	}
	 @Transient  
    private String exclusiveGateway;
	public String getExclusiveGateway() {
		return exclusiveGateway;
	}
	public void setExclusiveGateway(String exclusiveGateway) {
		this.exclusiveGateway = exclusiveGateway;
	}
    
    
	 
	

	

  





}


