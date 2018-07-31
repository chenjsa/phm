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

import com.pms.base.util.DateUtil;

import javax.persistence.GenerationType;

@Entity
@Table(name = "key_modules_info")
public class KeyModulesInfo implements java.io.Serializable {

	private static final long serialVersionUID = 748098973856L;

	public KeyModulesInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Module_ID")

	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Extension_id")
	private Integer extensionId;

	public Integer getExtensionId() {
		return this.extensionId;
	}

	public void setExtensionId(Integer extensionId) {
		this.extensionId = extensionId;
	}

	@ManyToOne(optional = true)
	@JoinColumn(name = "Extension_id", insertable = false, updatable = false)
	private ExtensionInfo extensionInfo;
	@ManyToOne(optional = true)
	@JoinColumn(name = "component_id", insertable = false, updatable = false)
	private ComponentAttributes componentAttributes;
	
	@Column(name = "Module_number_code")
	private Integer moduleNumberCode;
	// 多对一：optional=false表示外键type_id不能为空
	@ManyToOne(optional = true)
	@JoinColumn(name = "Module_number_code", insertable = false, updatable = false)
	private ModuleNumberInfo moduleNumberInfo;

	public Integer getModuleNumberCode() {
		return this.moduleNumberCode;
	}

	public void setModuleNumberCode(Integer moduleNumberCode) {
		this.moduleNumberCode = moduleNumberCode;
	}

	public ModuleNumberInfo getModuleNumberInfo() {
		return moduleNumberInfo;
	}

	public void setModuleNumberInfo(ModuleNumberInfo moduleNumberInfo) {
		this.moduleNumberInfo = moduleNumberInfo;
	}

	@Column(name = "key_modules_name")
	private String keyModulesName;

	public String getKeyModulesName() {
		return this.keyModulesName;
	}

	public void setKeyModulesName(String keyModulesName) {
		this.keyModulesName = keyModulesName;
	}

	@Column(name = "Module_function")
	private String moduleFunction;

	public String getModuleFunction() {
		return this.moduleFunction;
	}

	public void setModuleFunction(String moduleFunction) {
		this.moduleFunction = moduleFunction;
	}

	@Column(name = "begin_time")
	private java.sql.Timestamp beginTime;
	@Transient
	private String beginTimeStr;
	
	public String getBeginTimeStr() {
		return DateUtil.format(this.beginTime,"yyyy-MM-dd");
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public java.sql.Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(java.sql.Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "other")
	private String other;

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "component_id")
	private Integer componentId;

	public Integer getComponentId() {
		return this.componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	@Column(name = "PHMSubs_id")
	private Integer phmsubsId;
	@ManyToOne(optional = true)
	@JoinColumn(name = "PHMSubs_id", insertable = false, updatable = false)
	private RadarSubsystemInfo radarSubsystemInfo;
	public Integer getPhmsubsId() {
		return this.phmsubsId;
	}

	public void setPhmsubsId(Integer phmsubsId) {
		this.phmsubsId = phmsubsId;
	}

	public ExtensionInfo getExtensionInfo() {
		return extensionInfo;
	}

	public void setExtensionInfo(ExtensionInfo extensionInfo) {
		this.extensionInfo = extensionInfo;
	}

	public ComponentAttributes getComponentAttributes() {
		return componentAttributes;
	}

	public void setComponentAttributes(ComponentAttributes componentAttributes) {
		this.componentAttributes = componentAttributes;
	}

	public RadarSubsystemInfo getRadarSubsystemInfo() {
		return radarSubsystemInfo;
	}

	public void setRadarSubsystemInfo(RadarSubsystemInfo radarSubsystemInfo) {
		this.radarSubsystemInfo = radarSubsystemInfo;
	}

	
}
