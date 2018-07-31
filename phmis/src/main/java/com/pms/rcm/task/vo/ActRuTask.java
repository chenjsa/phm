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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="act_ru_task")
public class ActRuTask  implements java.io.Serializable {

    private static final long serialVersionUID = 115075221437L; 
    public ActRuTask() { 
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
	@Column(name="REV_")
	private Integer rev; 
	public Integer getRev() {
		return this.rev;
	} 
	public void setRev(Integer rev) {
		this.rev = rev;
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
	@Column(name="TASK_DEF_KEY_")
	private String taskDefKey; 
	public String getTaskDefKey() {
		return this.taskDefKey;
	} 
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
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
	@Column(name="DELEGATION_")
	private String delegation; 
	public String getDelegation() {
		return this.delegation;
	} 
	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}
	@Column(name="PRIORITY_")
	private Integer priority; 
	public Integer getPriority() {
		return this.priority;
	} 
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(name="CREATE_TIME_")
	private String createTime; 
	public String getCreateTime() {
		return this.createTime;
	} 
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="DUE_DATE_")
	private String dueDate; 
	public String getDueDate() {
		return this.dueDate;
	} 
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	@Column(name="CATEGORY_")
	private String category; 
	public String getCategory() {
		return this.category;
	} 
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="SUSPENSION_STATE_")
	private Integer suspensionState; 
	public Integer getSuspensionState() {
		return this.suspensionState;
	} 
	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}
	@Column(name="TENANT_ID_")
	private String tenantId; 
	public String getTenantId() {
		return this.tenantId;
	} 
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Column(name="FORM_KEY_")
	private String formKey; 
	public String getFormKey() {
		return this.formKey;
	} 
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	@Column(name="PROC_INST_ID_")
	private String  procInstId; 
	
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	/**
	 *  actReProcdefByProcDefId ActReProcdef 通过ProcDefId外键字段关联的ActReProcdef对象
	*/	
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PROC_DEF_ID_")
	private ActReProcdef actReProcdef;
	public ActReProcdef getActReProcdef() {
		return actReProcdef;
	}
	public void setActReProcdef(ActReProcdef actReProcdef) {
		this.actReProcdef = actReProcdef;
	}
	 
	
	 




}


