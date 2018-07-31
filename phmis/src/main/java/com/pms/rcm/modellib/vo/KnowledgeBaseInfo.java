package com.pms.rcm.modellib.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pms.rcm.radar.vo.ModuleTypeInfo;
import com.pms.rcm.radar.vo.RadarTypeInfo;
import com.pms.rcm.radar.vo.SubsystemInfo;

@Entity
@Table(name = "knowledge_base_info")
public class KnowledgeBaseInfo implements java.io.Serializable {

	private static final long serialVersionUID = 398683536262L;

	public KnowledgeBaseInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "knowledge_id")

	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "knowledge_name")
	private String knowledgeName;

	public String getKnowledgeName() {
		return this.knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	@Column(name = "PHMfunction_type_id")
	private Integer phmfunctionTypeId;
	@ManyToOne(optional = true)
	@JoinColumn(name = "PHMfunction_type_id", insertable = false, updatable = false)
	private PhmfunctionTypeInfo phmfunctionTypeInfo;

	public Integer getPhmfunctionTypeId() {
		return this.phmfunctionTypeId;
	}

	public void setPhmfunctionTypeId(Integer phmfunctionTypeId) {
		this.phmfunctionTypeId = phmfunctionTypeId;
	}

	@Column(name = "Radar_type_id")
	private String radarTypeId;
	@ManyToOne(optional = true)
	@JoinColumn(name = "Radar_type_id", insertable = false, updatable = false)
	private RadarTypeInfo radarTypeInfo;

	public String getRadarTypeId() {
		return this.radarTypeId;
	}

	public void setRadarTypeId(String radarTypeId) {
		this.radarTypeId = radarTypeId;
	}

	@Column(name = "subsystem_type_id")
	private String subsystemTypeId;
	@ManyToOne(optional = true)
	@JoinColumn(name = "subsystem_type_id", insertable = false, updatable = false)
	private SubsystemInfo subsystemInfo;

	public String getSubsystemTypeId() {
		return this.subsystemTypeId;
	}

	public void setSubsystemTypeId(String subsystemTypeId) {
		this.subsystemTypeId = subsystemTypeId;
	}

	@Column(name = "Module_ID")
	private Integer moduleId;
	@ManyToOne(optional = true)
	@JoinColumn(name = "Module_ID", insertable = false, updatable = false)
	private ModuleTypeInfo moduleTypeInfo;

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "key_name")
	private String key;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "key_value")
	private String value;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PhmfunctionTypeInfo getPhmfunctionTypeInfo() {
		return phmfunctionTypeInfo;
	}

	public void setPhmfunctionTypeInfo(PhmfunctionTypeInfo phmfunctionTypeInfo) {
		this.phmfunctionTypeInfo = phmfunctionTypeInfo;
	}

	public RadarTypeInfo getRadarTypeInfo() {
		return radarTypeInfo;
	}

	public void setRadarTypeInfo(RadarTypeInfo radarTypeInfo) {
		this.radarTypeInfo = radarTypeInfo;
	}

	public SubsystemInfo getSubsystemInfo() {
		return subsystemInfo;
	}

	public void setSubsystemInfo(SubsystemInfo subsystemInfo) {
		this.subsystemInfo = subsystemInfo;
	}

	public ModuleTypeInfo getModuleTypeInfo() {
		return moduleTypeInfo;
	}

	public void setModuleTypeInfo(ModuleTypeInfo moduleTypeInfo) {
		this.moduleTypeInfo = moduleTypeInfo;
	}

}
