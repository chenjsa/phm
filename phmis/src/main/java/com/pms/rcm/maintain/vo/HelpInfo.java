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
@Table(name = "help_info")
public class HelpInfo implements java.io.Serializable {

	private static final long serialVersionUID = 481875358173L;

	public HelpInfo() {
	}

	@Id
	@Column(name = "help_id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Data_name")
	private String dataName;

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	@Column(name = "Publisher_id")
	private String publisherId;

	public String getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Column(name = "Content_summary")
	private String contentSummary;

	public String getContentSummary() {
		return this.contentSummary;
	}

	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}

	@Column(name = "Help_type_id")
	private String helpTypeId;

	public String getHelpTypeId() {
		return this.helpTypeId;
	}

	public void setHelpTypeId(String helpTypeId) {
		this.helpTypeId = helpTypeId;
	}

	@Column(name = "Data_URL")
	private String dataUrl;

	public String getDataUrl() {
		return this.dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	@Column(name = "Publisher_time")
	private Date publisherTime;

	public Date getPublisherTime() {
		return publisherTime;
	}

	public void setPublisherTime(Date publisherTime) {
		this.publisherTime = publisherTime;
	}
	@Transient
	private String publisherTimeStr;

	public String getPublisherTimeStr() {
		return DateUtil.format(publisherTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
	}

	public void setPublisherTimeStr(String publisherTimeStr) {
		this.publisherTimeStr = publisherTimeStr;
	}
	
	
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "Publisher_id", insertable = false, updatable = false)
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "Help_type_id", insertable = false, updatable = false)
	private HelpTypeInfo helpTypeInfo;
	public HelpTypeInfo getHelpTypeInfo() {
		return helpTypeInfo;
	}
	public void setHelpTypeInfo(HelpTypeInfo helpTypeInfo) {
		this.helpTypeInfo = helpTypeInfo;
	}
	
}
