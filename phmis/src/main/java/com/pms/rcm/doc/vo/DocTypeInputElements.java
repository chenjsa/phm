package com.pms.rcm.doc.vo;
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
@Table(name="doc_type_input_elements")
public class DocTypeInputElements  implements java.io.Serializable {

    private static final long serialVersionUID = 626729083431L; 
    public DocTypeInputElements() { 
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
	@Column(name="label_name")
	private String labelName; 
	public String getLabelName() {
		return this.labelName;
	} 
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	@Column(name="label_key")
	private String labelKey; 
	public String getLabelKey() {
		return this.labelKey;
	} 
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	@Column(name="doc_type_id")
	private String docTypeId; 
	public String getDocTypeId() {
		return this.docTypeId;
	} 
	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	 
	
	@Column(name="serial_number")
	private Integer serialNumber; 
	public Integer getSerialNumber() {
		return serialNumber;
	} 
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	

  





}


