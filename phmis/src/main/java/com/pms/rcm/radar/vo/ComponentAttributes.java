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
import javax.persistence.GenerationType;
@Entity
@Table(name="component_attributes")
public class ComponentAttributes  implements java.io.Serializable {

    private static final long serialVersionUID = 16007599762L; 
    public ComponentAttributes() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="component_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="component_name")
	private String componentName; 
	public String getComponentName() {
		return this.componentName;
	} 
	public void setComponentName(String componentName) {
		this.componentName = componentName;
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


