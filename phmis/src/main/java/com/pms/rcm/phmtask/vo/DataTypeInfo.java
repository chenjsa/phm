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
@Table(name="data_type_info")
public class DataTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 923000761388L; 
    public DataTypeInfo() { 
    } 
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="Data_type_id")

	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="Data_type_name")
	private String dataTypeName; 
	public String getDataTypeName() {
		return this.dataTypeName;
	} 
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	} 
	@Column(name="data_length")
	private String dataLength; 
	public String getDataLength() {
		return this.dataLength;
	} 
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	} 

	

	

  





}


