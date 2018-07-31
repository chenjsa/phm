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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GenerationType;
@Entity
@Table(name="user_type_info")
public class UserTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 929391064813L; 
    public UserTypeInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="User_type_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="User_name")
	private String userName; 
	public String getUserName() {
		return this.userName;
	} 
	public void setUserName(String userName) {
		this.userName = userName;
	} 
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 

	

	 
   


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("名称:" + this.userName + "；"); 
		sb.append("描述:" + this.description + "；"); 

		return sb.toString();
	}
	

}


