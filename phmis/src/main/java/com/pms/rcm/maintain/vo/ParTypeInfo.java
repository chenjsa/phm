package com.pms.rcm.maintain.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pms.base.util.DateUtil;


/**
 * ling.zhang
 * 2018-07-23
 */
@Entity
@Table(name = "Parameters_type_info")
public class ParTypeInfo implements java.io.Serializable {

	private static final long serialVersionUID = 20180723000001L; 
	public ParTypeInfo(){
		
	}
	
	@Id
	@Column(name = "Parameters_type_id",length=32)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id;
	
	
	@Column(name = "Parameters_type_name")
	private String typeName;
	@Column(name = "Maintenance_time")
	private Date mainTime;
	
	@Transient
	private String mainTimeStr;
	
	public String getMainTimeStr() {
		return DateUtil.format(mainTime,"yyyy-MM-dd");
	}
	public void setMainTimeStr(String mainTimeStr) {
		this.mainTimeStr = mainTimeStr;
	}

	@Column(name = "other")
	private String other;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getMainTime() {
		return mainTime;
	}
	public void setMainTime(Date mainTime) {
		this.mainTime = mainTime;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

}
