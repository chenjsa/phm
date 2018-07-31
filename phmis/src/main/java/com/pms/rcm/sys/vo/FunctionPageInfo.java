package com.pms.rcm.sys.vo;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;

import java.util.TreeSet;

import javax.persistence.Basic;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;

import javax.persistence.ManyToMany;

import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;



@Entity

@Table(name="function_page_info")

public class FunctionPageInfo  implements java.io.Serializable {



    private static final long serialVersionUID = 980872507869L; 

    public FunctionPageInfo() { 

    } 

   

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Function_page_id")
	private String id; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}



	@Column(name="interface_name")
	private String interfaceName; 
	public String getInterfaceName() {
		return this.interfaceName;
	} 
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	@Column(name="description")
	private String description; 
	public String getDescription() {
		return this.description;
	} 
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="URL")
	private String url; 
	public String getUrl() {
		return this.url;
	} 
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="remark")
	private String remark; 
	public String getRemark() {
		return this.remark;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="cas_Code")
	private String casCode; 
	public String getCasCode() {
		return this.casCode;
	} 
	public void setCasCode(String casCode) {
		this.casCode = casCode;
	}
	@Column(name="serial")
	private Long serial; 
	public Long getSerial() {
		return this.serial;
	} 
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	@Column(name="PARENT_ID")
	private String parentId; 
	public String getParentId() {
		return this.parentId;
	} 
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="menu_icon")
	private String menuIcon; 
	public String getMenuIcon() {
		return this.menuIcon;
	} 
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	@Column(name="is_Use")
	private String isUse; 
	public String getIsUse() {
		return this.isUse;
	} 
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	@Transient  
    private List<FunctionPageInfo> children = new ArrayList<FunctionPageInfo>();
	@Transient  
	private FunctionPageInfo parentMenu;
	@Transient  
	private List<FunctionPageInfo> subMenu;
	@Transient  
	private boolean hasMenu = true;
	@Transient
	private String Target;
	@Transient  
	private boolean nocheck = false;

	public List<FunctionPageInfo> getChildren() {
		return children;
	}
	public void setChildren(List<FunctionPageInfo> children) {
		this.children = children;
	}
	public FunctionPageInfo getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(FunctionPageInfo parentMenu) {
		this.parentMenu = parentMenu;
	}
	public List<FunctionPageInfo> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<FunctionPageInfo> subMenu) {
		this.subMenu = subMenu;
	}
	public boolean isHasMenu() {
		return hasMenu;
	}
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	 
	/**
     * 
     * <li>方法名：getParentCasCode
     * <li>@return
     * <li>返回类型：String
     * <li>说明：获得父菜单级联编号
     * <li>创建人：曾明辉
     * <li>创建日期：2008-11-21
     * <li>修改人： 
     * <li>修改日期：
     */
    public String getParentCasCode(){
    	if(casCode == null || casCode.length()<=3){
    		return "";
    	}
    	return casCode.substring(0,casCode.length()-3);
    }
	/**
     * 
     * <li>方法名：compareTo
     * <li>@param other
     * <li>@return
     * <li>返回类型：int
     * <li>说明：用于TreeSet排序
     * <li>创建人：曾明辉
     * <li>创建日期：2008-11-5
     * <li>修改人： 
     * <li>修改日期：
     */
    public int compareTo(FunctionPageInfo other) {
    	if (other == null || other.casCode == null || this.casCode == null){
			return 1;
		}
    	if(this.casCode.length() != other.casCode.length()){
    		Integer myLength = this.casCode.length();
    		return myLength.compareTo(other.casCode.length());
    	}
    	if(this.getParentCasCode().equals(other.getParentCasCode())){
    		return this.serial.compareTo(other.serial);
    	}
    	
    	return this.casCode.compareTo(other.casCode);//按表示级联关系的编号排序
    }
	



	



  











}





