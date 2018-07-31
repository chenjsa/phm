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
@Table(name="subsystem_info")
public class SubsystemInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 251498548296L; 
    public SubsystemInfo() { 
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
	@Column(name="subsystem_name")
	private String subsystemName; 
	public String getSubsystemName() {
		return this.subsystemName;
	} 
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
	@Column(name="Subsystem_description")
	private String subsystemDescription; 
	public String getSubsystemDescription() {
		return this.subsystemDescription;
	} 
	public void setSubsystemDescription(String subsystemDescription) {
		this.subsystemDescription = subsystemDescription;
	}
	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	}

	 
	

	

  





}


