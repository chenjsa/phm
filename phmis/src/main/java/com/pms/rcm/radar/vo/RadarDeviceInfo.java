package com.pms.rcm.radar.vo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pms.base.util.DateUtil;
@Entity
@Table(name="radar_device_info")
public class RadarDeviceInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 617708488655L; 
    public RadarDeviceInfo() { 
    } 
   
    @Id 
  	@Column(name="Radar_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Radar_name")
	private String radarName; 
	public String getRadarName() {
		return this.radarName;
	} 
	public void setRadarName(String radarName) {
		this.radarName = radarName;
	} 
	@Column(name="Type_id")
	private String typeId; 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Type_id",insertable = false, updatable = false) 
	private RadarTypeInfo radarTypeInfo;
	public String getTypeId() {
		return this.typeId;
	} 
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	} 
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 
	@Column(name="manufacturer")
	private String manufacturer; 
	public String getManufacturer() {
		return this.manufacturer;
	} 
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	} 
	@Column(name="Station_id")
	private String stationId; 
	
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Station_id",insertable = false, updatable = false) 
	private RadarUserInfo radarUserInfo;
    
	public String getStationId() {
		return this.stationId;
	} 
	public void setStationId(String stationId) {
		this.stationId = stationId;
	} 
	@Column(name="Manufacturer_date")
	private Date manufacturerDate; 
	public Date getManufacturerDate() {
		return this.manufacturerDate;
	} 
	public void setManufacturerDate(Date manufacturerDate) {
		this.manufacturerDate = manufacturerDate;
	} 
	@Transient
	private String manufacturerDateStr; 
	
	public String getManufacturerDateStr() {
		return DateUtil.format(this.manufacturerDate,"yyyy-MM-dd");
	}
	public void setManufacturerDateStr(String manufacturerDateStr) {
		this.manufacturerDateStr = manufacturerDateStr;
	}

	@Column(name="Address")
	private String address; 
	public String getAddress() {
		return this.address;
	} 
	public void setAddress(String address) {
		this.address = address;
	} 
	@Column(name="Work_order")
	private String workOrder; 
	public String getWorkOrder() {
		return this.workOrder;
	} 
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	} 
	@Column(name="Erection_time")
	private Date erectionTime; 
	public Date getErectionTime() {
		return this.erectionTime;
	} 
	public void setErectionTime(Date erectionTime) {
		this.erectionTime = erectionTime;
	} 
	@Transient
	private String erectionTimeStr; 
	
	public String getErectionTimeStr() {
		return DateUtil.format(this.erectionTime,"yyyy-MM-dd");
	}
	public void setErectionTimeStr(String erectionTimeStr) {
		this.erectionTimeStr = erectionTimeStr;
	}

	@Column(name="In_service_state")
	private String inServiceState; 
	public String getInServiceState() {
		return this.inServiceState;
	} 
	public void setInServiceState(String inServiceState) {
		this.inServiceState = inServiceState;
	} 
	@Column(name="task_attribute_id")
	private Integer taskAttributeId; 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "task_attribute_id",insertable = false, updatable = false) 
	private TaskAttribute taskAttribute;
	public Integer getTaskAttributeId() {
		return this.taskAttributeId;
	} 
	public void setTaskAttributeId(Integer taskAttributeId) {
		this.taskAttributeId = taskAttributeId;
	}
	public RadarTypeInfo getRadarTypeInfo() {
		return radarTypeInfo;
	}
	public void setRadarTypeInfo(RadarTypeInfo radarTypeInfo) {
		this.radarTypeInfo = radarTypeInfo;
	}
	public RadarUserInfo getRadarUserInfo() {
		return radarUserInfo;
	}
	public void setRadarUserInfo(RadarUserInfo radarUserInfo) {
		this.radarUserInfo = radarUserInfo;
	}
	public TaskAttribute getTaskAttribute() {
		return taskAttribute;
	}
	public void setTaskAttribute(TaskAttribute taskAttribute) {
		this.taskAttribute = taskAttribute;
	} 

	 @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)//一对多,	    // fetch = FetchType.EAGER 这个是否开启延迟加载
	 @JoinColumn(name = "Radar_id")//添加主键
	private Set<RadarBasicStateInfo> radarBasicStateInfoes = new HashSet<RadarBasicStateInfo>();
	 
	@Transient  
	private List<RadarBasicStateInfo> radarBasicStateInfos =  new ArrayList<RadarBasicStateInfo>();
	public Set<RadarBasicStateInfo> getRadarBasicStateInfoes() {
		return radarBasicStateInfoes;
	}
	public void setRadarBasicStateInfoes(Set<RadarBasicStateInfo> radarBasicStateInfoes) {
		this.radarBasicStateInfoes = radarBasicStateInfoes;
	}
	public List<RadarBasicStateInfo> getRadarBasicStateInfos() {
		return radarBasicStateInfos;
	}
	public void setRadarBasicStateInfos(List<RadarBasicStateInfo> radarBasicStateInfos) {
		this.radarBasicStateInfos = radarBasicStateInfos;
	}
	 
	
	

  





}


