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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="radar_service_type")
public class RadarServiceType  implements java.io.Serializable {

    private static final long serialVersionUID = 403063702397L; 
    public RadarServiceType() { 
    } 
   
    @Id
 	@GeneratedValue(generator="system-uuid")
 	@GenericGenerator(name = "system-uuid",strategy="uuid") 
 	@Column(name="id")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="code")
	private String code; 
	public String getCode() {
		return this.code;
	} 
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="name")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	}

	 
	

	

  





}


