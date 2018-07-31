package com.pms.rcm.modellib.vo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pms.base.util.DateUtil;

@Entity
@Table(name="algorithms_info")
public class AlgorithmsInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 296054440696L; 
    public AlgorithmsInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AID")
	private String id; 
	
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="Aname")
	private String aname; 
	public String getAname() {
		return this.aname;
	} 
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Column(name="Creation_date")
	private Date creationDate; 
	public Date getCreationDate() {
		return this.creationDate;
	} 
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Column(name="dynamiclibrary_URL")
	private String dynamiclibraryUrl; 
	public String getDynamiclibraryUrl() {
		return this.dynamiclibraryUrl;
	} 
	public void setDynamiclibraryUrl(String dynamiclibraryUrl) {
		this.dynamiclibraryUrl = dynamiclibraryUrl;
	}
	@Column(name="code_URL")
	private String codeUrl; 
	public String getCodeUrl() {
		return this.codeUrl;
	} 
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	@Column(name="Algorithm_Descriptions")
	private String algorithmDescriptions; 
	public String getAlgorithmDescriptions() {
		return this.algorithmDescriptions;
	} 
	public void setAlgorithmDescriptions(String algorithmDescriptions) {
		this.algorithmDescriptions = algorithmDescriptions;
	}
	@Column(name="Input_parameter_description")
	private String inputParameterDescription; 
	public String getInputParameterDescription() {
		return this.inputParameterDescription;
	} 
	public void setInputParameterDescription(String inputParameterDescription) {
		this.inputParameterDescription = inputParameterDescription;
	}
	@Column(name="output_parameter_description")
	private String outputParameterDescription; 
	public String getOutputParameterDescription() {
		return this.outputParameterDescription;
	} 
	public void setOutputParameterDescription(String outputParameterDescription) {
		this.outputParameterDescription = outputParameterDescription;
	}

	 
	
	@Transient
	private String creationDateStr;
	public String getCreationDateStr() {
		return DateUtil.format(this.creationDate);
	}
	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	} 
	
	
  





}


