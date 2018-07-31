package com.pms.rcm.maintain.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="help_type_info")
public class HelpTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 836732136861L; 
    public HelpTypeInfo() { 
    } 
   
    @Id
	@Column(name = "Help_type_id",length=32)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="Help_type_name")
	private String helpTypeName; 
	public String getHelpTypeName() {
		return this.helpTypeName;
	} 
	public void setHelpTypeName(String helpTypeName) {
		this.helpTypeName = helpTypeName;
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


