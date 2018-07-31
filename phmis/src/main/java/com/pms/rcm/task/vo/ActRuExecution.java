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
@Table(name="act_ru_execution")
public class ActRuExecution  implements java.io.Serializable {

    private static final long serialVersionUID = 560535904037L; 
    public ActRuExecution() { 
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
	@Column(name="BUSINESS_KEY_")
	private String businessKey; 
	public String getBusinessKey() {
		return this.businessKey;
	} 
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	@Column(name="ACT_ID_")
	private String actId; 
	public String getActId() {
		return this.actId;
	} 
	public void setActId(String actId) {
		this.actId = actId;
	}
	@Column(name="IS_ACTIVE_")
	private String isActive; 
	public String getIsActive() {
		return this.isActive;
	} 
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="IS_CONCURRENT_")
	private String isConcurrent; 
	public String getIsConcurrent() {
		return this.isConcurrent;
	} 
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	@Column(name="IS_SCOPE_")
	private String isScope; 
	public String getIsScope() {
		return this.isScope;
	} 
	public void setIsScope(String isScope) {
		this.isScope = isScope;
	}
	@Column(name="IS_EVENT_SCOPE_")
	private String isEventScope; 
	public String getIsEventScope() {
		return this.isEventScope;
	} 
	public void setIsEventScope(String isEventScope) {
		this.isEventScope = isEventScope;
	}
	@Column(name="SUSPENSION_STATE_")
	private Integer suspensionState; 
	public Integer getSuspensionState() {
		return this.suspensionState;
	} 
	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}
	@Column(name="CACHED_ENT_STATE_")
	private Integer cachedEntState; 
	public Integer getCachedEntState() {
		return this.cachedEntState;
	} 
	public void setCachedEntState(Integer cachedEntState) {
		this.cachedEntState = cachedEntState;
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
	@Column(name="LOCK_TIME_")
	private java.sql.Timestamp lockTime; 
	public java.sql.Timestamp getLockTime() {
		return this.lockTime;
	} 
	public void setLockTime(java.sql.Timestamp lockTime) {
		this.lockTime = lockTime;
	}
 
	@Column(name="PROC_INST_ID_")
	private String procInstId;
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
    
   




}


