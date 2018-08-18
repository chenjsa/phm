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
import com.pms.rcm.sys.vo.User;

@Entity
@Table(name="log_info")
public class LogInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 520499320073L; 
    public LogInfo() { 
    } 
   
    @Id
	@Column(name = "ID",length=32)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Radar_id")
	private String radarId; 
	public String getRadarId() {
		return this.radarId;
	} 
	public void setRadarId(String radarId) {
		this.radarId = radarId;
	}
	@Column(name="Type_id")
	private String typeId; 
	public String getTypeId() {
		return this.typeId;
	} 
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Column(name="date")
	private Date date; 
	public Date getDate() {
		return this.date;
	} 
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Transient
	private String dateStr;
	public String getDateStr() {
		return DateUtil.format(date,DateUtil.PATTERN_CLASSICAL);
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	@Column(name="Log_content")
	private String logContent; 
	public String getLogContent() {
		return this.logContent;
	} 
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	 
	
	/*@ManyToOne(optional = false)
	@JoinColumn(name = "Radar_id", insertable = false, updatable = false)
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "Type_id", insertable = false, updatable = false)
	private LogTypeInfo logTypeInfo;
	public LogTypeInfo getLogTypeInfo() {
		return logTypeInfo;
	}
	public void setLogTypeInfo(LogTypeInfo logTypeInfo) {
		this.logTypeInfo = logTypeInfo;
	}
	
	@Transient
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

  





}


