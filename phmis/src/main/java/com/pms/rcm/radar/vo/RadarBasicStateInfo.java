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
import javax.persistence.GenerationType;
@Entity
@Table(name="radar_basic_state_info")
public class RadarBasicStateInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 687381071133L; 
    public RadarBasicStateInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="id")

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
	@Column(name="Radar_state_info_id")
	private Integer radarStateInfoId; 
	public Integer getRadarStateInfoId() {
		return this.radarStateInfoId;
	} 
	public void setRadarStateInfoId(Integer radarStateInfoId) {
		this.radarStateInfoId = radarStateInfoId;
	} 
	@Column(name="Radar_state_info_value_id")
	private Integer radarStateInfoValueId; 
	public Integer getRadarStateInfoValueId() {
		return this.radarStateInfoValueId;
	} 
	public void setRadarStateInfoValueId(Integer radarStateInfoValueId) {
		this.radarStateInfoValueId = radarStateInfoValueId;
	} 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Radar_state_info_value_id",insertable = false, updatable = false) 
	private RadarStateInfoValue radarStateInfoValue;
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Radar_state_info_id",insertable = false, updatable = false) 
	private RadarStateInfo radarStateInfo;
	public RadarStateInfoValue getRadarStateInfoValue() {
		return radarStateInfoValue;
	}
	public void setRadarStateInfoValue(RadarStateInfoValue radarStateInfoValue) {
		this.radarStateInfoValue = radarStateInfoValue;
	}
	public RadarStateInfo getRadarStateInfo() {
		return radarStateInfo;
	}
	public void setRadarStateInfo(RadarStateInfo radarStateInfo) {
		this.radarStateInfo = radarStateInfo;
	}
    
	

	

  





}


