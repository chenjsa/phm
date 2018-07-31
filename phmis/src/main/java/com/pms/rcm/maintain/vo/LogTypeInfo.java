package com.pms.rcm.maintain.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="log_type_info")
public class LogTypeInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 128048796872L; 
    public LogTypeInfo() { 
    } 
   
    @Id
	@Column(name = "Log_type_id",length=32)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	private String id; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="Log_type_name")
	private String logTypeName; 
	public String getLogTypeName() {
		return this.logTypeName;
	} 
	public void setLogTypeName(String logTypeName) {
		this.logTypeName = logTypeName;
	}
	@Column(name="remark")
	private String remark; 
	public String getRemark() {
		return this.remark;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	}

	 
	

	

  





}


