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
@Table(name="module_number_info")
public class ModuleNumberInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 289743304582L; 
    public ModuleNumberInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="Module_number_code")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="model_name")
	private String modelName; 
	public String getModelName() {
		return this.modelName;
	} 
	public void setModelName(String modelName) {
		this.modelName = modelName;
	} 
	@Column(name="Module_type_code")
	private Integer moduleTypeCode; 
	public Integer getModuleTypeCode() {
		return this.moduleTypeCode;
	} 
	public void setModuleTypeCode(Integer moduleTypeCode) {
		this.moduleTypeCode = moduleTypeCode;
	} 
	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	} 

	
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Module_type_code",insertable = false, updatable = false) 
	private ModuleTypeInfo moduleTypeInfo;
	public ModuleTypeInfo getModuleTypeInfo() {
		return moduleTypeInfo;
	}
	public void setModuleTypeInfo(ModuleTypeInfo moduleTypeInfo) {
		this.moduleTypeInfo = moduleTypeInfo;
	}
	
    
  





}


