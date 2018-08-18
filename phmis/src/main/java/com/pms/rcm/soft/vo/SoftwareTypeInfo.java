package com.pms.rcm.soft.vo;
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
@Table(name="software_type_info")
public class SoftwareTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 693609687257L; 
    public SoftwareTypeInfo() { 
    } 
   
    @Id
    @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
  	@Column(name="Software_type_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Software_name")
	private String softwareName; 
	public String getSoftwareName() {
		return this.softwareName;
	} 
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	} 
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	} 
	@Column(name="remark")
	private String remark; 
	public String getRemark() {
		return this.remark;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	} 

	

	

  



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("软件名称:" + this.softwareName + "；"); 
		sb.append("用途说明:" + this.description + "；"); 
		sb.append("备注:" + this.remark + "；"); 

		return sb.toString();
	}
	

}


