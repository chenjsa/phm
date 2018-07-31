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
@Table(name="Button_type_info")
public class SysButton  implements java.io.Serializable,Comparable<SysButton> {

    private static final long serialVersionUID = 261534388481L; 
    public SysButton() { 
    } 
   
    @Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid") 
	@Column(name="button_type_id")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="btn_code")
	private String btnCode; 
	public String getBtnCode() {
		return this.btnCode;
	} 
	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
	}
	@Column(name="button_name")
	private String btnName; 
	public String getBtnName() {
		return this.btnName;
	} 
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	@Column(name="Function_page_id")
	private String menuId; 
	public String getMenuId() {
		return this.menuId;
	} 
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name="button_description")
	private String depict; 
	
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	
	@Column(name="remark")
	private String remark; 
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int compareTo(SysButton other) {
	    	if (other == null || other.id == null || this.id == null){
				return 1;
			}
	    	 return this.id.compareTo(other.id );
	    	 
	    }  


	

  





}


