package com.pms.rcm.task.vo;
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

@Entity
@Table(name="act_ru_identitylink")
public class ActRuIdentitylink  implements java.io.Serializable {

    private static final long serialVersionUID = 37369389063L; 
    public ActRuIdentitylink() { 
    } 
   
    @Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid") 
	@Column(name="ID_")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="REV_")
	private Integer rev; 
	public Integer getRev() {
		return this.rev;
	} 
	public void setRev(Integer rev) {
		this.rev = rev;
	}
	@Column(name="GROUP_ID_")
	private String groupId; 
	public String getGroupId() {
		return this.groupId;
	} 
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name="TYPE_")
	private String type; 
	public String getType() {
		return this.type;
	} 
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="USER_ID_")
	private String userId; 
	public String getUserId() {
		return this.userId;
	} 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="TASK_ID_")
	private String taskId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	} 

	
	 
	 





}


