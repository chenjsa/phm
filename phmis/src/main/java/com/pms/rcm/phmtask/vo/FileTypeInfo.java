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
@Table(name="file_type_info")
public class FileTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 527159694403L; 
    public FileTypeInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="file_type_id")

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
	private RadarDatafileTypeInfo radarDatafileTypeInfo;
	public String getDatafileTypeId() {
		return this.datafileTypeId;
	} 
	public void setDatafileTypeId(String datafileTypeId) {
		this.datafileTypeId = datafileTypeId;
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
	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	}
	public RadarDatafileTypeInfo getRadarDatafileTypeInfo() {
		return radarDatafileTypeInfo;
	}
	public void setRadarDatafileTypeInfo(RadarDatafileTypeInfo radarDatafileTypeInfo) {
		this.radarDatafileTypeInfo = radarDatafileTypeInfo;
	}
	public RadarTypeInfo getRadarTypeInfo() {
		return radarTypeInfo;
	}
	public void setRadarTypeInfo(RadarTypeInfo radarTypeInfo) {
		this.radarTypeInfo = radarTypeInfo;
	} 

	

	
	
  





}


