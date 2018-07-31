package com.pms.rcm.sys.vo;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user_state_info")
public class UserStateInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 537731470460L; 
    public UserStateInfo() { 
    } 
   
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="User_state_id")
	private String id; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="User_state_name")
	private String userStateName; 
	public String getUserStateName() {
		return this.userStateName;
	} 
	public void setUserStateName(String userStateName) {
		this.userStateName = userStateName;
	}
	@Column(name="User_state_description")
	private String userStateDescription; 
	public String getUserStateDescription() {
		return this.userStateDescription;
	} 
	public void setUserStateDescription(String userStateDescription) {
		this.userStateDescription = userStateDescription;
	}

	 
	

	

  





}


