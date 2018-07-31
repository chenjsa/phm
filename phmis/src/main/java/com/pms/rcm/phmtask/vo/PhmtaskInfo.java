package com.pms.rcm.phmtask.vo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pms.base.util.DateUtil;
import com.pms.rcm.modellib.vo.KnowledgeBaseInfo;
import com.pms.rcm.modellib.vo.PhmfunctionTypeInfo;
import com.pms.rcm.radar.vo.KeyModulesInfo;
import com.pms.rcm.radar.vo.RadarDeviceInfo;
import com.pms.rcm.radar.vo.RadarSubsystemInfo;
@Entity
@Table(name="phmtask_info")
public class PhmtaskInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 702494242437L; 
    public PhmtaskInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="PHM_task_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="PHMfunction_type_id")
	private String phmfunctionTypeId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "PHMfunction_type_id", insertable = false, updatable = false)
	private PhmfunctionTypeInfo phmfunctionTypeInfo;
	public String getPhmfunctionTypeId() {
		return this.phmfunctionTypeId;
	} 
	public void setPhmfunctionTypeId(String phmfunctionTypeId) {
		this.phmfunctionTypeId = phmfunctionTypeId;
	} 
	@Column(name="Radar_id")
	private String radarId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "Radar_id", insertable = false, updatable = false)
	private RadarDeviceInfo radarDeviceInfo;
	public String getRadarId() {
		return this.radarId;
	} 
	public void setRadarId(String radarId) {
		this.radarId = radarId;
	} 
	@Column(name="PHMSubs_id")
	private String phmsubsId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "PHMSubs_id", insertable = false, updatable = false)
	private RadarSubsystemInfo radarSubsystemInfo;
	public String getPhmsubsId() {
		return this.phmsubsId;
	} 
	public void setPhmsubsId(String phmsubsId) {
		this.phmsubsId = phmsubsId;
	} 
	@Column(name="Module_ID")
	private String moduleId; 
	@ManyToOne(optional = true)
	@JoinColumn(name = "Module_ID", insertable = false, updatable = false)
	private KeyModulesInfo keyModulesInfo;
	public String getModuleId() {
		return this.moduleId;
	} 
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	} 
	@Column(name="Task_deadline")
	private Date taskDeadline; 
	@Transient
	private String taskDeadlineStr;
	
	public String getTaskDeadlineStr() {
		return DateUtil.format(taskDeadline,"yyyy-MM-dd");
	}
	public void setTaskDeadlineStr(String taskDeadlineStr) {
		this.taskDeadlineStr = taskDeadlineStr;
	}
	public Date getTaskDeadline() {
		return this.taskDeadline;
	} 
	public void setTaskDeadline(Date taskDeadline) {
		this.taskDeadline = taskDeadline;
	} 
	@Column(name="Task_description")
	private String taskDescription; 
	public String getTaskDescription() {
		return this.taskDescription;
	} 
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	} 
	@Column(name="Other")
	private String other; 
	public String getOther() {
		return this.other;
	} 
	public void setOther(String other) {
		this.other = other;
	} 
	//多对多映射   
	// @ManyToMany    
	// @JoinTable(name = "Task_knowledge_info",joinColumns = { @JoinColumn(name = "Task_id", referencedColumnName = "PHM_task_id") },inverseJoinColumns = { @JoinColumn(name = "knowledge_id", referencedColumnName = "knowledge_id")})
	 @Transient
	 private Set<KnowledgeBaseInfo> knowledgeBaseInfoSet = new HashSet<KnowledgeBaseInfo>();      //专家知识库
	 
	 @Transient
	 private List<TaskKnowledgeInfo> taskKnowledgeInfos = new ArrayList<TaskKnowledgeInfo>();
	 
	// @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)//一对多,	    // fetch = FetchType.EAGER 这个是否开启延迟加载
	// @JoinColumn(name = "Task_id")//添加主键
	 @Transient
	 private Set<TaskAlgorithmsModelInfo> taskAlgorithmsModelInfoSet = new HashSet<TaskAlgorithmsModelInfo>();
	 @Transient
	 private List<TaskAlgorithmsModelInfo> taskAlgorithmsModelInfos = new ArrayList<TaskAlgorithmsModelInfo>();
	// @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)//一对多,	    // fetch = FetchType.EAGER 这个是否开启延迟加载
	// @JoinColumn(name = "PHM_task_id")//添加主键
	 @Transient
	 private Set<TaskRelevantData> taskRelevantDataSet = new HashSet<TaskRelevantData>();
	 @Transient
	 private List<TaskRelevantData> taskRelevantDatas = new ArrayList<TaskRelevantData>();
	 
	 
	 
	public List<TaskKnowledgeInfo> getTaskKnowledgeInfos() {
		return taskKnowledgeInfos;
	}
	public void setTaskKnowledgeInfos(List<TaskKnowledgeInfo> taskKnowledgeInfos) {
		this.taskKnowledgeInfos = taskKnowledgeInfos;
	}
	public Set<KnowledgeBaseInfo> getKnowledgeBaseInfoSet() {
		return knowledgeBaseInfoSet;
	}
	public void setKnowledgeBaseInfoSet(Set<KnowledgeBaseInfo> knowledgeBaseInfoSet) {
		this.knowledgeBaseInfoSet = knowledgeBaseInfoSet;
	}
	public Set<TaskAlgorithmsModelInfo> getTaskAlgorithmsModelInfoSet() {
		return taskAlgorithmsModelInfoSet;
	}
	public void setTaskAlgorithmsModelInfoSet(Set<TaskAlgorithmsModelInfo> taskAlgorithmsModelInfoSet) {
		this.taskAlgorithmsModelInfoSet = taskAlgorithmsModelInfoSet;
	}
	public List<TaskAlgorithmsModelInfo> getTaskAlgorithmsModelInfos() {
		return taskAlgorithmsModelInfos;
	}
	public void setTaskAlgorithmsModelInfos(List<TaskAlgorithmsModelInfo> taskAlgorithmsModelInfos) {
		this.taskAlgorithmsModelInfos = taskAlgorithmsModelInfos;
	}
	public Set<TaskRelevantData> getTaskRelevantDataSet() {
		return taskRelevantDataSet;
	}
	public void setTaskRelevantDataSet(Set<TaskRelevantData> taskRelevantDataSet) {
		this.taskRelevantDataSet = taskRelevantDataSet;
	}
	public List<TaskRelevantData> getTaskRelevantDatas() {
		return taskRelevantDatas;
	}
	public void setTaskRelevantDatas(List<TaskRelevantData> taskRelevantDatas) {
		this.taskRelevantDatas = taskRelevantDatas;
	}
	public PhmfunctionTypeInfo getPhmfunctionTypeInfo() {
		return phmfunctionTypeInfo;
	}
	public void setPhmfunctionTypeInfo(PhmfunctionTypeInfo phmfunctionTypeInfo) {
		this.phmfunctionTypeInfo = phmfunctionTypeInfo;
	}
	public RadarDeviceInfo getRadarDeviceInfo() {
		return radarDeviceInfo;
	}
	public void setRadarDeviceInfo(RadarDeviceInfo radarDeviceInfo) {
		this.radarDeviceInfo = radarDeviceInfo;
	}
	public RadarSubsystemInfo getRadarSubsystemInfo() {
		return radarSubsystemInfo;
	}
	public void setRadarSubsystemInfo(RadarSubsystemInfo radarSubsystemInfo) {
		this.radarSubsystemInfo = radarSubsystemInfo;
	}
	public KeyModulesInfo getKeyModulesInfo() {
		return keyModulesInfo;
	}
	public void setKeyModulesInfo(KeyModulesInfo keyModulesInfo) {
		this.keyModulesInfo = keyModulesInfo;
	}
	

	

  





}


