package com.pms.rcm.phmtask.vo;
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

import com.pms.rcm.radar.vo.RadarTypeInfo;

import javax.persistence.GenerationType;
@Entity
@Table(name="task_relevant_data")
public class TaskRelevantData  implements java.io.Serializable {

    private static final long serialVersionUID = 228740954812L; 
    public TaskRelevantData() { 
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
	@Column(name="data_id")
	private String dataId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "data_id",insertable = false, updatable = false) 
	private RealtimeDatastructure realtimeDatastructure;
	public String getDataId() {
		return this.dataId;
	} 
	public void setDataId(String dataId) {
		this.dataId = dataId;
	} 
	@Column(name="PHM_task_id")
	private String phmTaskId; 
	public String getPhmTaskId() {
		return this.phmTaskId;
	} 
	public void setPhmTaskId(String phmTaskId) {
		this.phmTaskId = phmTaskId;
	} 
	@Column(name="Task_relevant_name")
	private String taskRelevantName; 
	public String getTaskRelevantName() {
		return this.taskRelevantName;
	} 
	public void setTaskRelevantName(String taskRelevantName) {
		this.taskRelevantName = taskRelevantName;
	} 
	@Column(name="other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	}
	public RealtimeDatastructure getRealtimeDatastructure() {
		return realtimeDatastructure;
	}
	public void setRealtimeDatastructure(RealtimeDatastructure realtimeDatastructure) {
		this.realtimeDatastructure = realtimeDatastructure;
	} 
	
	

	

  





}


