package com.pms.rcm.radar.vo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pms.base.util.DateUtil;
@Entity
@Table(name="radar_subsystem_info")
public class RadarSubsystemInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 5022763464L; 
    public RadarSubsystemInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="PHMSubs_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="subsystem_type_id")
	private String subsystemTypeId; 
	public String getSubsystemTypeId() {
		return this.subsystemTypeId;
	} 
	public void setSubsystemTypeId(String subsystemTypeId) {
		this.subsystemTypeId = subsystemTypeId;
	} 
	@Column(name="Radar_id")
	private String radarId; 
	public String getRadarId() {
		return this.radarId;
	} 
	public void setRadarId(String radarId) {
		this.radarId = radarId;
	} 
	@Column(name="begin_time")
	private Date beginTime; 
	public Date getBeginTime() {
		return this.beginTime;
	} 
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	} 
	@Transient
	private String beginTimeStr; 
	
	public String getBeginTimeStr() {
		return DateUtil.format(beginTime);
	}
	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}
	@Transient
	private String beginTimeStr1; 
	
	public String getBeginTimeStr1() {
		return beginTimeStr1;
	}
	public void setBeginTimeStr1(String beginTimeStr1) {
		this.beginTimeStr1 = beginTimeStr1;
	}

	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	} 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Radar_id",insertable = false, updatable = false) 
	private RadarDeviceInfo radarDeviceInfo;
	

	public RadarDeviceInfo getRadarDeviceInfo() {
		return radarDeviceInfo;
	}
	public void setRadarDeviceInfo(RadarDeviceInfo radarDeviceInfo) {
		this.radarDeviceInfo = radarDeviceInfo;
	}

	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "subsystem_type_id",insertable = false, updatable = false) 
	private SubsystemInfo subsystemInfo;
	public SubsystemInfo getSubsystemInfo() {
		return subsystemInfo;
	}
	public void setSubsystemInfo(SubsystemInfo subsystemInfo) {
		this.subsystemInfo = subsystemInfo;
	}
    
  





}


