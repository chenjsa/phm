package com.pms.rcm.sys.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pms.rcm.radar.vo.RadarTypeInfo;
import com.pms.rcm.radar.vo.SubsystemInfo;

@Entity
@Table(name="professional_field")
public class ProfessionalField  implements java.io.Serializable {

    private static final long serialVersionUID = 618296485783L; 
    public ProfessionalField() { 
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
	@Column(name="subsystem_info_id")
	private String subsystemInfoId; 
	public String getSubsystemInfoId() {
		return this.subsystemInfoId;
	} 
	public void setSubsystemInfoId(String subsystemInfoId) {
		this.subsystemInfoId = subsystemInfoId;
	}
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "subsystem_info_id",insertable = false, updatable = false) 
	private SubsystemInfo subsystemInfo; 
    
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "radar_type_info_id",insertable = false, updatable = false) 
	private RadarTypeInfo radarTypeInfo; 
    
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Support_pattern_id",insertable = false, updatable = false) 
	private SupportPattern supportPattern; 
    
    
	public SubsystemInfo getSubsystemInfo() {
		return subsystemInfo;
	}
	public void setSubsystemInfo(SubsystemInfo subsystemInfo) {
		this.subsystemInfo = subsystemInfo;
	}
	public RadarTypeInfo getRadarTypeInfo() {
		return radarTypeInfo;
	}
	public void setRadarTypeInfo(RadarTypeInfo radarTypeInfo) {
		this.radarTypeInfo = radarTypeInfo;
	}
	public SupportPattern getSupportPattern() {
		return supportPattern;
	}
	public void setSupportPattern(SupportPattern supportPattern) {
		this.supportPattern = supportPattern;
	}

	@Column(name="radar_type_info_id")
	private String radarTypeInfoId; 
	public String getRadarTypeInfoId() {
		return this.radarTypeInfoId;
	} 
	public void setRadarTypeInfoId(String radarTypeInfoId) {
		this.radarTypeInfoId = radarTypeInfoId;
	}
	@Column(name="Support_pattern_id")
	private String supportPatternId; 
	public String getSupportPatternId() {
		return this.supportPatternId;
	} 
	public void setSupportPatternId(String supportPatternId) {
		this.supportPatternId = supportPatternId;
	}

	 
	

	

  





}


