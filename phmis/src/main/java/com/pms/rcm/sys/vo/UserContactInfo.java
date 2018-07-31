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

@Entity
@Table(name="user_contact_info")
public class UserContactInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 669864604830L; 
    public UserContactInfo() { 
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
	@Column(name="contact_id")
	private String contactId; 
	public String getContactId() {
		return this.contactId;
	} 
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	@Column(name="user_id")
	private String userId; 
	public String getUserId() {
		return this.userId;
	} 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="no")
	private String no; 
	public String getNo() {
		return this.no;
	} 
	public void setNo(String no) {
		this.no = no;
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


