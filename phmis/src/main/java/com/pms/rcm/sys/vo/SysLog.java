package com.pms.rcm.sys.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SYS_LOG")
public class SysLog extends DataRangeSearch{

	// Fields
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id;
	@Column(name="cas_Code")
	private String casCode;
	@Column(name="sys_Code")
	private String sysCode;
	@Column(name="dept_No")
	private String deptNo;
	@Column(name="dept_Name")
	private String deptName;
	@Column(name="user_No")
	private String userNo;
	@Column(name="user_Name")
	private String userName;
	@Column(name="user_Ip")
	private String userIp;
	@Column(name="action_Name")
	private String actionName;
	@Column(name="action_Method")
	private String actionMethod;
	@Column(name="log_Time")
	private String logTime;
	@Column(name="log_Content")
	private String logContent;
	 //这是此注解后该属性不会数据持久化也是本例要说明的注解  
    @Transient  
	private String beginLogTimeSea;  //日志开始时间
    @Transient  
	private String endLogTimeSea;  //日志结束时间
	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(String userNo, String userName, String actionName,
			String actionMethod) {
		this.userNo = userNo;
		this.userName = userName;
		this.actionName = actionName;
		this.actionMethod = actionMethod;
	}

	/** full constructor */
	public SysLog(String casCode, String sysCode, String deptNo,
			String deptName, String userNo, String userName, String userIp,
			String actionName, String actionMethod, String logTime,
			String logContent) {
		this.casCode = casCode;
		this.sysCode = sysCode;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.userNo = userNo;
		this.userName = userName;
		this.userIp = userIp;
		this.actionName = actionName;
		this.actionMethod = actionMethod;
		this.logTime = logTime;
		this.logContent = logContent;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCasCode() {
		return this.casCode;
	}

	public void setCasCode(String casCode) {
		this.casCode = casCode;
	}

	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionMethod() {
		return this.actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getBeginLogTimeSea() {
		return beginLogTimeSea;
	}

	public void setBeginLogTimeSea(String beginLogTimeSea) {
		this.beginLogTimeSea = beginLogTimeSea;
	}

	public String getEndLogTimeSea() {
		return endLogTimeSea;
	}

	public void setEndLogTimeSea(String endLogTimeSea) {
		this.endLogTimeSea = endLogTimeSea;
	}

	
}