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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GenerationType;
@Entity
@Table(name="task_knowledge_info")
public class TaskKnowledgeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 349990069726L; 
    public TaskKnowledgeInfo() { 
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
	@Column(name="Task_id")
	private String taskId; 
	public String getTaskId() {
		return this.taskId;
	} 
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	} 
	@Column(name="knowledge_id")
	private String knowledgeId; 
	public String getKnowledgeId() {
		return this.knowledgeId;
	} 
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
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


