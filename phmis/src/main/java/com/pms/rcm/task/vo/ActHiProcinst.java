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
@Table(name="act_hi_procinst")
public class ActHiProcinst  implements java.io.Serializable {

    private static final long serialVersionUID = 830208664659L; 
    public ActHiProcinst() { 
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
	@Column(name="BUSINESS_KEY_")
	private String businessKey; 
	public String getBusinessKey() {
		return this.businessKey;
	} 
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	@Column(name="PROC_DEF_ID_")
	private String procDefId; 
	public String getProcDefId() {
		return this.procDefId;
	} 
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
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
	@Column(name="START_USER_ID_")
	private String startUserId; 
	public String getStartUserId() {
		return this.startUserId;
	} 
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	@Column(name="START_ACT_ID_")
	private String startActId; 
	public String getStartActId() {
		return this.startActId;
	} 
	public void setStartActId(String startActId) {
		this.startActId = startActId;
	}
	@Column(name="END_ACT_ID_")
	private String endActId; 
	public String getEndActId() {
		return this.endActId;
	} 
	public void setEndActId(String endActId) {
		this.endActId = endActId;
	}
	@Column(name="SUPER_PROCESS_INSTANCE_ID_")
	private String superProcessInstanceId; 
	public String getSuperProcessInstanceId() {
		return this.superProcessInstanceId;
	} 
	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}
	@Column(name="DELETE_REASON_")
	private String deleteReason; 
	public String getDeleteReason() {
		return this.deleteReason;
	} 
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	@Column(name="TENANT_ID_")
	private String tenantId; 
	public String getTenantId() {
		return this.tenantId;
	} 
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Column(name="NAME_")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}

	 
	

	

  





}


