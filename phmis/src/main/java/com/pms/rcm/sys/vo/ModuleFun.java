package com.pms.rcm.sys.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;





/**
 * ModuleFun generated by MyEclipse - Hibernate Tools
 */
@Entity
@Table(name="sys_module_fun")
public class ModuleFun  implements java.io.Serializable,Comparable<ModuleFun> {


    // Fields    
	 @Id
	 @GeneratedValue(generator="system-uuid")
	 @GenericGenerator(name = "system-uuid",strategy="uuid")
     private String id;
	 @Column(name="cas_Code")
     private String casCode;
	 @Column(name="type")
     private String type;
	 @Column(name="name")
     private String name;
	 @Column(name="KEY_WORD")
     private String keyWord;
	 @Column(name="serial")
     private Long serial;
	 @Column(name="IS_ALLOW_CTR")
     private String isAllowCtr;


    // Constructors

    /** default constructor */
    public ModuleFun() {
    }

	/** minimal constructor */
    public ModuleFun(String casCode, String type, String name) {
        this.casCode = casCode;
        this.type = type;
        this.name = name;
    }
    
    /** full constructor */
    public ModuleFun(String casCode, String type, String name, String keyWord, Long serial, String isAllowCtr) {
        this.casCode = casCode;
        this.type = type;
        this.name = name;
        this.keyWord = keyWord;
        this.serial = serial;
        this.isAllowCtr = isAllowCtr;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCasCode() {
        return this.casCode;
    }
    
    public void setCasCode(String casCode) {
        this.casCode = casCode;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getKeyWord() {
        return this.keyWord;
    }
    
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getSerial() {
        return this.serial;
    }
    
    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public String getIsAllowCtr() {
        return this.isAllowCtr;
    }
    
    public void setIsAllowCtr(String isAllowCtr) {
        this.isAllowCtr = isAllowCtr;
    }
    
    /**
     * 
     * <li>方法名：getParentCasCode
     * <li>@return
     * <li>返回类型：String
     * <li>说明：获得父模块功能级联编号
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
    public int compareTo(ModuleFun other) {
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