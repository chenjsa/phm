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
@Table(name="act_re_procdef")
public class ActReProcdef  implements java.io.Serializable {

    private static final long serialVersionUID = 655435818293L; 
    public ActReProcdef() { 
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
	@Column(name="CATEGORY_")
	private String category; 
	public String getCategory() {
		return this.category;
	} 
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="NAME_")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="KEY_")
	private String key; 
	public String getKey() {
		return this.key;
	} 
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="VERSION_")
	private Integer version; 
	public Integer getVersion() {
		return this.version;
	} 
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="DEPLOYMENT_ID_")
	private String deploymentId; 
	public String getDeploymentId() {
		return this.deploymentId;
	} 
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	@Column(name="RESOURCE_NAME_")
	private String resourceName; 
	public String getResourceName() {
		return this.resourceName;
	} 
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Column(name="DGRM_RESOURCE_NAME_")
	private String dgrmResourceName; 
	public String getDgrmResourceName() {
		return this.dgrmResourceName;
	} 
	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}
	@Column(name="DESCRIPTION_")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="HAS_START_FORM_KEY_")
	private String hasStartFormKey; 
	public String getHasStartFormKey() {
		return this.hasStartFormKey;
	} 
	public void setHasStartFormKey(String hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}
	@Column(name="HAS_GRAPHICAL_NOTATION_")
	private String hasGraphicalNotation; 
	public String getHasGraphicalNotation() {
		return this.hasGraphicalNotation;
	} 
	public void setHasGraphicalNotation(String hasGraphicalNotation) {
		this.hasGraphicalNotation = hasGraphicalNotation;
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

	 
	

	 




}


