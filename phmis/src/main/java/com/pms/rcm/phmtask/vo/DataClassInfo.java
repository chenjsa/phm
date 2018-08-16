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
@Table(name="data_class_info")
public class DataClassInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 707716523792L; 
    public DataClassInfo() { 
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
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 
	@Column(name="data_length")
	private Integer dataLength; 
	public Integer getDataLength() {
		return this.dataLength;
	} 
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	} 

	

	

  



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("分类名称:" + this.name + "；"); 
		sb.append("分类描述:" + this.description + "；"); 
		sb.append("长度:" + this.dataLength + "；"); 

		return sb.toString();
	}
	

}


