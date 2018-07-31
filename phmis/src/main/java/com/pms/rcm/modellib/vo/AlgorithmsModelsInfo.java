package com.pms.rcm.modellib.vo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pms.base.util.DateUtil;

@Entity
@Table(name="algorithms_models_info")
public class AlgorithmsModelsInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 560971255708L; 
    public AlgorithmsModelsInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Algorithms_models_id")
    private String id; 
	
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="AID")
	private Integer aid; 
	public Integer getAid() {
		return this.aid;
	} 
	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "AID",insertable = false, updatable = false) 
	private AlgorithmsInfo algorithmsInfo; 
    
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "MID",insertable = false, updatable = false) 
	private FunctionModelsInfo functionModelsInfo;

   
    
	public AlgorithmsInfo getAlgorithmsInfo() {
		return algorithmsInfo;
	}
	public void setAlgorithmsInfo(AlgorithmsInfo algorithmsInfo) {
		this.algorithmsInfo = algorithmsInfo;
	}
	public FunctionModelsInfo getFunctionModelsInfo() {
		return functionModelsInfo;
	}
	public void setFunctionModelsInfo(FunctionModelsInfo functionModelsInfo) {
		this.functionModelsInfo = functionModelsInfo;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	@Column(name="MID")
	private Integer mid; 
	public Integer getMid() {
		return this.mid;
	} 
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	@Column(name="version")
	private String version; 
	public String getVersion() {
		return this.version;
	} 
	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name="Generate_time")
	private Date generateTime; 
	public Date getGenerateTime() {
		return this.generateTime;
	} 
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
	@Column(name="URL")
	private String url; 
	public String getUrl() {
		return this.url;
	} 
	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	private String generateTimeStr;
	public String getGenerateTimeStr() {
		return DateUtil.format(this.generateTime);
	}
	public void setGenerateTimeStr(String generateTimeStr) {
		this.generateTimeStr = generateTimeStr;
	} 
	
	

  





}


