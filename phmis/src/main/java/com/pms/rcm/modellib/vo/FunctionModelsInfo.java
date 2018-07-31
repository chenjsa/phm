package com.pms.rcm.modellib.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="function_models_info")
public class FunctionModelsInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 840792567226L; 
    public FunctionModelsInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="function_model_ID")
    private String id; 
	
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="function_model_name")
	private String functionModelName; 
	public String getFunctionModelName() {
		return this.functionModelName;
	} 
	public void setFunctionModelName(String functionModelName) {
		this.functionModelName = functionModelName;
	}
	@Column(name="PHMfunction_type_id")
	private String phmfunctionTypeId; 
	
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "PHMfunction_type_id",insertable = false, updatable = false) 
	private PhmfunctionTypeInfo phmfunctionTypeInfo; 
    
    
	public PhmfunctionTypeInfo getPhmfunctionTypeInfo() {
		return phmfunctionTypeInfo;
	}
	public void setPhmfunctionTypeInfo(PhmfunctionTypeInfo phmfunctionTypeInfo) {
		this.phmfunctionTypeInfo = phmfunctionTypeInfo;
	}
	public String getPhmfunctionTypeId() {
		return this.phmfunctionTypeId;
	} 
	public void setPhmfunctionTypeId(String phmfunctionTypeId) {
		this.phmfunctionTypeId = phmfunctionTypeId;
	}
	@Column(name="Model_URL")
	private String modelUrl; 
	public String getModelUrl() {
		return this.modelUrl;
	} 
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	@Column(name="Model_description")
	private String modelDescription; 
	public String getModelDescription() {
		return this.modelDescription;
	} 
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	 
	

	

  





}


