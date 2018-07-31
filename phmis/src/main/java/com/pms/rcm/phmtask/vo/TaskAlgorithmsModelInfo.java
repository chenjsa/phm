package com.pms.rcm.phmtask.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pms.rcm.modellib.vo.AlgorithmsModelsInfo;
@Entity
@Table(name="task_algorithms_model_info")
public class TaskAlgorithmsModelInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 342094601593L; 
    public TaskAlgorithmsModelInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="ID")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Algorithms_models_id")
	private String algorithmsModelsId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "Algorithms_models_id", insertable = false, updatable = false)
	private AlgorithmsModelsInfo algorithmsModelsInfo;
	
	public AlgorithmsModelsInfo getAlgorithmsModelsInfo() {
		return algorithmsModelsInfo;
	}
	public void setAlgorithmsModelsInfo(AlgorithmsModelsInfo algorithmsModelsInfo) {
		this.algorithmsModelsInfo = algorithmsModelsInfo;
	}

	@Column(name="Task_id")
	private String taskId; 
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAlgorithmsModelsId() {
		return this.algorithmsModelsId;
	} 
	public void setAlgorithmsModelsId(String algorithmsModelsId) {
		this.algorithmsModelsId = algorithmsModelsId;
	} 
	@Column(name="Begin_time")
	private java.sql.Timestamp beginTime; 
	public java.sql.Timestamp getBeginTime() {
		return this.beginTime;
	} 
	public void setBeginTime(java.sql.Timestamp beginTime) {
		this.beginTime = beginTime;
	} 
	@Column(name="Model_url")
	private String modelUrl; 
	public String getModelUrl() {
		return this.modelUrl;
	} 
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	} 
	@Column(name="state")
	private String state; 
	public String getState() {
		return this.state;
	} 
	public void setState(String state) {
		this.state = state;
	} 

	

	

  





}


