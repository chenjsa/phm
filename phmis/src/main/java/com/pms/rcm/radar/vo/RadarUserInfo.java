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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

import com.pms.rcm.sys.vo.UserTypeInfo;

@Entity
@Table(name="radar_user_info")
public class RadarUserInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 338664956099L; 
    public RadarUserInfo() { 
    } 
   
    @Id 
    @Column(name = "id")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	 
	@Column(name="Station_name")
	private String stationName; 
	public String getStationName() {
		return this.stationName;
	} 
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	@Column(name="Province_id")
	private Integer provinceId; 
	public Integer getProvinceId() {
		return this.provinceId;
	} 
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="Contact_info")
	private String contactInfo; 
	 
	@Column(name="designation_code")
	private String designationCode; 
	 
	@Column(name="unit")
	private String unit; 
	 
	@Column(name="Mailing_address")
	private String mailingAddress; 
	 
	 
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	}
	@Column(name="longitude")
	private String longitude;
	@Column(name="latitude")
	private String latitude;
	
	

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Province_id",insertable = false, updatable = false) 
	private ProvinceInfo provinceInfo;
	public ProvinceInfo getProvinceInfo() {
		return provinceInfo;
	}
	public void setProvinceInfo(ProvinceInfo provinceInfo) {
		this.provinceInfo = provinceInfo;
	} 
	
    @Transient
	private String isAdd;
	public String getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	

  



	


}


