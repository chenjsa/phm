package com.pms.rcm.radar.vo;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

import com.pms.rcm.sys.vo.UserContactInfo;

import javax.persistence.GenerationType;
@Entity
@Table(name="radar_state_info")
public class RadarStateInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 514614166278L; 
    public RadarStateInfo() { 
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
	@Column(name="sname")
	private String sname; 
	public String getSname() {
		return this.sname;
	} 
	public void setSname(String sname) {
		this.sname = sname;
	} 
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 
	@Transient
    private Set<RadarStateInfoValue> radarStateInfoValues = new HashSet<RadarStateInfoValue>();
	public Set<RadarStateInfoValue> getRadarStateInfoValues() {
		return radarStateInfoValues;
	}
	public void setRadarStateInfoValues(Set<RadarStateInfoValue> radarStateInfoValues) {
		this.radarStateInfoValues = radarStateInfoValues;
	}
	 
	 
	

	

  





}


