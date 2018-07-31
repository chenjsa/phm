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
@Table(name="doc_type")
public class DocType  implements java.io.Serializable {

    private static final long serialVersionUID = 759462593810L; 
    public DocType() { 
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
	@Column(name="code")
	private String code; 
	public String getCode() {
		return this.code;
	} 
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="name")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="serial_number")
	private Integer serialNumber; 
	public Integer getSerialNumber() {
		return serialNumber;
	} 
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Transient  
	public List<DocTypeInputElements> docTypeInputElementses;
	public List<DocTypeInputElements> getDocTypeInputElementses() {
		return docTypeInputElementses;
	}
	public void setDocTypeInputElementses(List<DocTypeInputElements> docTypeInputElementses) {
		this.docTypeInputElementses = docTypeInputElementses;
	}
	
	

	

  





}


