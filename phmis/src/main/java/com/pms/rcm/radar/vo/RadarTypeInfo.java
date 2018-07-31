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

import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.UserTypeInfo;

@Entity
@Table(name="radar_type_info")
public class RadarTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 412961180927L; 
    public RadarTypeInfo() { 
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
	@Column(name="radar_type_name")
	private String radarTypeName; 
	public String getRadarTypeName() {
		return this.radarTypeName;
	} 
	public void setRadarTypeName(String radarTypeName) {
		this.radarTypeName = radarTypeName;
	}
	@Column(name="radar_type_description")
	private String radarTypeDescription; 
	public String getRadarTypeDescription() {
		return this.radarTypeDescription;
	} 
	public void setRadarTypeDescription(String radarTypeDescription) {
		this.radarTypeDescription = radarTypeDescription;
	}
	@Column(name="radar_service_type_id")
	private String radarServiceTypeId; 
	public String getRadarServiceTypeId() {
		return this.radarServiceTypeId;
	} 
	public void setRadarServiceTypeId(String radarServiceTypeId) {
		this.radarServiceTypeId = radarServiceTypeId;
	}
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "radar_service_type_id",insertable = false, updatable = false) 
	private RadarServiceType radarServiceType;
	public RadarServiceType getRadarServiceType() {
		return radarServiceType;
	}
	public void setRadarServiceType(RadarServiceType radarServiceType) {
		this.radarServiceType = radarServiceType;
	} 

	

	

  





}


