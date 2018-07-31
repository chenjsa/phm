package com.pms.rcm.people.vo;
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
@Table(name="people")
public class People  implements java.io.Serializable {

    private static final long serialVersionUID = 340541029553L; 
    public People() { 
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
	@Column(name="name")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="sex")
	private String sex; 
	public String getSex() {
		return this.sex;
	} 
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="addr")
	private String addr; 
	public String getAddr() {
		return this.addr;
	} 
	public void setAddr(String addr) {
		this.addr = addr;
	}

	 
	

	

  





}


