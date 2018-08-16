package com.pms.rcm.phmtask.vo;
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

import com.pms.rcm.radar.vo.RadarTypeInfo;

import javax.persistence.GenerationType;
@Entity
@Table(name="realtime_datastructure")
public class RealtimeDatastructure  implements java.io.Serializable {

    private static final long serialVersionUID = 436332142902L; 
    public RealtimeDatastructure() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="data_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="datafile_type_id")
	private String datafileTypeId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "datafile_type_id",insertable = false, updatable = false) 
	private FileTypeInfo fileTypeInfo;
	public String getDatafileTypeId() {
		return this.datafileTypeId;
	} 
	public void setDatafileTypeId(String datafileTypeId) {
		this.datafileTypeId = datafileTypeId;
	} 
	@Column(name="data_order")
	private String order; 
	public String getOrder() {
		return this.order;
	} 
	public void setOrder(String order) {
		this.order = order;
	} 
	@Column(name="data_name")
	private String dataName; 
	public String getDataName() {
		return this.dataName;
	} 
	public void setDataName(String dataName) {
		this.dataName = dataName;
	} 
	@Column(name="data_type")
	private String dataType; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "data_type",insertable = false, updatable = false) 
	private DataTypeInfo dataTypeInfo;
	public String getDataType() {
		return this.dataType;
	} 
	public void setDataType(String dataType) {
		this.dataType = dataType;
	} 
	@Column(name="Data_length")
	private String dataLength; 
	public String getDataLength() {
		return this.dataLength;
	} 
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	} 
	@Column(name="radar_type_id")
	private String radarTypeId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "radar_type_id",insertable = false, updatable = false) 
	private RadarTypeInfo radarTypeInfo;
	public String getRadarTypeId() {
		return this.radarTypeId;
	} 
	public void setRadarTypeId(String radarTypeId) {
		this.radarTypeId = radarTypeId;
	}
	
	public FileTypeInfo getFileTypeInfo() {
		return fileTypeInfo;
	}
	public void setFileTypeInfo(FileTypeInfo fileTypeInfo) {
		this.fileTypeInfo = fileTypeInfo;
	}
	public DataTypeInfo getDataTypeInfo() {
		return dataTypeInfo;
	}
	public void setDataTypeInfo(DataTypeInfo dataTypeInfo) {
		this.dataTypeInfo = dataTypeInfo;
	}
	public RadarTypeInfo getRadarTypeInfo() {
		return radarTypeInfo;
	}
	public void setRadarTypeInfo(RadarTypeInfo radarTypeInfo) {
		this.radarTypeInfo = radarTypeInfo;
	} 
	
	
	@Column(name="en_name")
	private String enName; 
	
	@Column(name="ch_name")
	private String chName; 
	
	@Column(name="Unit")
	private String unit;
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	} 

	
	
	@Column(name="parameter_custom_class_id")
	private String parameterCustomClassId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "parameter_custom_class_id",insertable = false, updatable = false) 
	private ParameterCustomClass parameterCustomClass;
	
	
	@Column(name="data_class_info_id")
	private String dataClassInfoId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "data_class_info_id",insertable = false, updatable = false) 
	private DataClassInfo dataClassInfo;
	public String getParameterCustomClassId() {
		return parameterCustomClassId;
	}
	public void setParameterCustomClassId(String parameterCustomClassId) {
		this.parameterCustomClassId = parameterCustomClassId;
	}
	public ParameterCustomClass getParameterCustomClass() {
		return parameterCustomClass;
	}
	public void setParameterCustomClass(ParameterCustomClass parameterCustomClass) {
		this.parameterCustomClass = parameterCustomClass;
	}
	public String getDataClassInfoId() {
		return dataClassInfoId;
	}
	public void setDataClassInfoId(String dataClassInfoId) {
		this.dataClassInfoId = dataClassInfoId;
	}
	public DataClassInfo getDataClassInfo() {
		return dataClassInfo;
	}
	public void setDataClassInfo(DataClassInfo dataClassInfo) {
		this.dataClassInfo = dataClassInfo;
	}

	
  





}


