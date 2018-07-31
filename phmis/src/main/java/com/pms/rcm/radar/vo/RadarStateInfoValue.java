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
@Table(name="radar_state_info_value")
public class RadarStateInfoValue  implements java.io.Serializable {

    private static final long serialVersionUID = 634424931609L; 
    public RadarStateInfoValue() { 
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
	@Column(name="svalue")
	private String svalue; 
	public String getSvalue() {
		return this.svalue;
	} 
	public void setSvalue(String svalue) {
		this.svalue = svalue;
	} 
	@Column(name="radar_state_info_id")
	private Integer radarStateInfoId; 
	public Integer getRadarStateInfoId() {
		return this.radarStateInfoId;
	} 
	public void setRadarStateInfoId(Integer radarStateInfoId) {
		this.radarStateInfoId = radarStateInfoId;
	} 
	@ManyToOne(optional = true)
    @JoinColumn(name = "radar_state_info_id",insertable = false, updatable = false) 
	private RadarStateInfo radarStateInfo;
	public RadarStateInfo getRadarStateInfo() {
		return radarStateInfo;
	}
	public void setRadarStateInfo(RadarStateInfo radarStateInfo) {
		this.radarStateInfo = radarStateInfo;
	}
	

	

  





}


