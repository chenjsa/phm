package com.pms.rcm.radar.vo;
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
@Table(name="extension_info")
public class ExtensionInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 735948673934L; 
    public ExtensionInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="Extension_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Extension_name")
	private String extensionName; 
	public String getExtensionName() {
		return this.extensionName;
	} 
	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	} 
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 
	@Column(name="PHMSubs_id")
	private String phmsubsId; 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "PHMSubs_id",insertable = false, updatable = false) 
	private SubsystemInfo subsystemInfo;
	public String getPhmsubsId() {
		return this.phmsubsId;
	} 
	public void setPhmsubsId(String phmsubsId) {
		this.phmsubsId = phmsubsId;
	}
	public SubsystemInfo getSubsystemInfo() {
		return subsystemInfo;
	}
	public void setSubsystemInfo(SubsystemInfo subsystemInfo) {
		this.subsystemInfo = subsystemInfo;
	} 

	

	

  





}


