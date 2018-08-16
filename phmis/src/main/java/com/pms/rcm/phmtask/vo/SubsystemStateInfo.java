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
@Table(name="subsystem_state_info")
public class SubsystemStateInfo  implements java.io.Serializable {

    private static final long serialVersionUID = 810058508454L; 
    public SubsystemStateInfo() { 
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
	@Column(name="data_class_info_id")
	private String dataClassInfoId; 
	public String getDataClassInfoId() {
		return this.dataClassInfoId;
	} 
	public void setDataClassInfoId(String dataClassInfoId) {
		this.dataClassInfoId = dataClassInfoId;
	} 
	@Column(name="status_en_name")
	private String statusEnName; 
	public String getStatusEnName() {
		return this.statusEnName;
	} 
	public void setStatusEnName(String statusEnName) {
		this.statusEnName = statusEnName;
	} 
	@Column(name="status_ch_name")
	private String statusChName; 
	public String getStatusChName() {
		return this.statusChName;
	} 
	public void setStatusChName(String statusChName) {
		this.statusChName = statusChName;
	} 
	@Column(name="status_value")
	private String statusValue; 
	public String getStatusValue() {
		return this.statusValue;
	} 
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	} 
	@Column(name="data_length")
	private Integer dataLength; 
	public Integer getDataLength() {
		return this.dataLength;
	} 
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	} 
	@Column(name="status_bit_number")
	private Integer statusBitNumber; 
	public Integer getStatusBitNumber() {
		return this.statusBitNumber;
	} 
	public void setStatusBitNumber(Integer statusBitNumber) {
		this.statusBitNumber = statusBitNumber;
	} 

	

	

  



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + this.id + "；");
		sb.append("数据分类ID:" + this.dataClassInfoId + "；"); 
		sb.append("状态英文名称:" + this.statusEnName + "；"); 
		sb.append("状态中文名称:" + this.statusChName + "；"); 
		sb.append("状态值（逻辑值）:" + this.statusValue + "；"); 
		sb.append("长度:" + this.dataLength + "；"); 
		sb.append("状态位序号:" + this.statusBitNumber + "；"); 

		return sb.toString();
	}
	

}


