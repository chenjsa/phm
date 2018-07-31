package com.pms.rcm.maintain.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pms.base.util.DateUtil;
import com.pms.rcm.sys.vo.AdminInfo;
import com.pms.rcm.sys.vo.User;

@Entity
@Table(name = "system_parameters_info")
public class SystemParametersInfo implements java.io.Serializable {

	private static final long serialVersionUID = 884806646938L;

	public SystemParametersInfo() {
	}

	@Id
	@Column(name = "System_parameters_id",length=32)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "System_parameters_code")
	private String systemParametersCode;

	public String getSystemParametersCode() {
		return this.systemParametersCode;
	}

	public void setSystemParametersCode(String systemParametersCode) {
		this.systemParametersCode = systemParametersCode;
	}

	@Column(name = "Parameters_type_id")
	private String parametersTypeId;

	public String getParametersTypeId() {
		return this.parametersTypeId;
	}

	public void setParametersTypeId(String parametersTypeId) {
		this.parametersTypeId = parametersTypeId;
	}

	@Column(name = "Parameters_values")
	private String parametersValues;

	public String getParametersValues() {
		return this.parametersValues;
	}

	public void setParametersValues(String parametersValues) {
		this.parametersValues = parametersValues;
	}

	@Column(name = "Maintenance_time")
	private Date maintenanceTime;

	public Date getMaintenanceTime() {
		return this.maintenanceTime;
	}

	public void setMaintenanceTime(Date maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}

	@Transient
	private String maintenanceTimeStr;

	public String getMaintenanceTimeStr() {
		return DateUtil.format(maintenanceTime, "yyyy-MM-dd");
	}

	public void setMaintenanceTimeStr(String maintenanceTimeStr) {
		this.maintenanceTimeStr = maintenanceTimeStr;
	}

	@Column(name = "Station_id")
	private String stationId;

	public String getStationId() {
		return this.stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	// 多对一：optional=false表示外键Parameters_type_id不能为空
	@ManyToOne(optional = true)
	@JoinColumn(name = "Parameters_type_id", insertable = false, updatable = false)
	private ParTypeInfo parTypeInfo;

	// 多对一：optional=false表示外键Parameters_type_id不能为空
	@ManyToOne(optional = true)
	@JoinColumn(name = "Station_id", insertable = false, updatable = false)
	private User user;
	 

	 

	public ParTypeInfo getParTypeInfo() {
		return parTypeInfo;
	}

	public void setParTypeInfo(ParTypeInfo parTypeInfo) {
		this.parTypeInfo = parTypeInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
