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
@Table(name="admin_info")
public class AdminInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 820145739662L; 
    public AdminInfo() { 
    } 
   
    @Id
   	@GeneratedValue(generator="system-uuid")
   	@GenericGenerator(name = "system-uuid",strategy="uuid")
  	@Column(name="id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Admin_id")
	private String adminId; 
	public String getAdminId() {
		return this.adminId;
	} 
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	} 
	@Column(name="Admin_password")
	private String adminPassword; 
	public String getAdminPassword() {
		return this.adminPassword;
	} 
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	} 
	@Column(name="telephone")
	private String telephone; 
	public String getTelephone() {
		return this.telephone;
	} 
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	} 

	

	

  





}


