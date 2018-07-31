package com.pms.rcm.modellib.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phmfunction_type_info")
public class PhmfunctionTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 318482970640L; 
    public PhmfunctionTypeInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHMfunction_type_id")
	private String id; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="PHMfunction_type_name")
	private String phmfunctionTypeName; 
	public String getPhmfunctionTypeName() {
		return this.phmfunctionTypeName;
	} 
	public void setPhmfunctionTypeName(String phmfunctionTypeName) {
		this.phmfunctionTypeName = phmfunctionTypeName;
	}
	@Column(name="PHMfunction_explanation")
	private String phmfunctionExplanation; 
	public String getPhmfunctionExplanation() {
		return this.phmfunctionExplanation;
	} 
	public void setPhmfunctionExplanation(String phmfunctionExplanation) {
		this.phmfunctionExplanation = phmfunctionExplanation;
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


