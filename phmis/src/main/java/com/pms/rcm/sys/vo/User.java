package com.pms.rcm.sys.vo;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pms.base.util.Const;
import com.pms.base.util.StrUtil;
import com.pms.base.util.Tools;
  
@Entity

@Table(name="user_info")
public class User  implements java.io.Serializable {



    private static final long serialVersionUID = 325537043987L; 

    public User() { 

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
	@Column(name="dept_Id")
	private String deptId; 
	public String getDeptId() {
		return this.deptId;
	} 
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name="Station_id")
	private String stationId; 
	public String getStationId() {
		return this.stationId;
	} 
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	@Column(name="User_id")
	private String no; 
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}



	@Column(name="name")
	private String name; 
	public String getName() {
		return this.name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="password")
	private String password; 
	public String getPassword() {
		return this.password;
	} 
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="Type_id")
	private String typeId; 
	public String getTypeId() {
		return this.typeId;
	} 
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Column(name="User_state_id")
	private String userStateId; 
	
	public String getUserStateId() {
		return userStateId;
	}
	public void setUserStateId(String userStateId) {
		this.userStateId = userStateId;
	}



	@Column(name="Registration_date")
	private String registrationDate; 
	public String getRegistrationDate() {
		return this.registrationDate;
	} 
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Column(name="ID_number")
	private String idNumber; 
	public String getIdNumber() {
		return this.idNumber;
	} 
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="ID_pic")
	private String idPic; 
	public String getIdPic() {
		return this.idPic;
	} 
	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}
	@Column(name="telephone")
	private String telephone; 
	public String getTelephone() {
		return this.telephone;
	} 
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name="email")
	private String email; 
	public String getEmail() {
		return this.email;
	} 
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="remark")
	private String remark; 
	public String getRemark() {
		return this.remark;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="is_Lock")
	private String isLock; 
	public String getIsLock() {
		return this.isLock;
	} 
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	@Column(name="pwd_Edit_Date")
	private String pwdEditDate; 
	public String getPwdEditDate() {
		return this.pwdEditDate;
	} 
	public void setPwdEditDate(String pwdEditDate) {
		this.pwdEditDate = pwdEditDate;
	}
	@Column(name="last_Login_Date")
	private String lastLoginDate; 
	public String getLastLoginDate() {
		return this.lastLoginDate;
	} 
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	} 
	
	@Column(name="sex")
	private String sex; 
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name="physiclal_status_id")
	private String physiclalStatusId; 

	@Column(name="post_info_id")
	private String postInfoId; 

	@Column(name="Professional_field_id")
	private String professionalFieldId; 
	
	@Column(name="political_status")
	private String politicalStatus; 
	
	

	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getPhysiclalStatusId() {
		return physiclalStatusId;
	}
	public void setPhysiclalStatusId(String physiclalStatusId) {
		this.physiclalStatusId = physiclalStatusId;
	}
	public String getPostInfoId() {
		return postInfoId;
	}
	public void setPostInfoId(String postInfoId) {
		this.postInfoId = postInfoId;
	}
	public String getProfessionalFieldId() {
		return professionalFieldId;
	}
	public void setProfessionalFieldId(String professionalFieldId) {
		this.professionalFieldId = professionalFieldId;
	}

	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "physiclal_status_id",insertable = false, updatable = false) 
	private PhysiclalStatus physiclalStatus; 
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "post_info_id",insertable = false, updatable = false) 
	private PostInfo postInfo; 
 // 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Professional_field_id",insertable = false, updatable = false) 
	private ProfessionalField professionalField; 

    

	public PhysiclalStatus getPhysiclalStatus() {
		return physiclalStatus;
	}
	public void setPhysiclalStatus(PhysiclalStatus physiclalStatus) {
		this.physiclalStatus = physiclalStatus;
	}
	public PostInfo getPostInfo() {
		return postInfo;
	}
	public void setPostInfo(PostInfo postInfo) {
		this.postInfo = postInfo;
	}
	public ProfessionalField getProfessionalField() {
		return professionalField;
	}
	public void setProfessionalField(ProfessionalField professionalField) {
		this.professionalField = professionalField;
	}



	// 多对一：optional=false表示外键type_id不能为空 
    @ManyToOne(optional = true)
    @JoinColumn(name = "Type_id",insertable = false, updatable = false) 
	private UserTypeInfo userTypeInfo; 

	public UserTypeInfo getUserTypeInfo() {
		return userTypeInfo;
	}
	public void setUserTypeInfo(UserTypeInfo userTypeInfo) {
		this.userTypeInfo = userTypeInfo;
	}
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "User_state_id",insertable = false, updatable = false) 
	private UserStateInfo userStateInfo;  
	public UserStateInfo getUserStateInfo() {
		return userStateInfo;
	}
	public void setUserStateInfo(UserStateInfo userStateInfo) {
		this.userStateInfo = userStateInfo;
	}


	 @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)//一对多,	    // fetch = FetchType.EAGER 这个是否开启延迟加载
	 @JoinColumn(name = "user_id")//添加主键
	 private List<UserContactInfo> userContactInfos = new ArrayList<UserContactInfo>();
	 @Transient
	 private List<UserContactInfo> userContactInfoArr = new ArrayList<UserContactInfo>();
	 
	public List<UserContactInfo> getUserContactInfoArr() {
		return userContactInfoArr;
	}
	public void setUserContactInfoArr(List<UserContactInfo> userContactInfoArr) {
		this.userContactInfoArr = userContactInfoArr;
	}
	 



    public List<UserContactInfo> getUserContactInfos() {
		return userContactInfos;
	}
	public void setUserContactInfos(List<UserContactInfo> userContactInfos) {
		this.userContactInfos = userContactInfos;
	}

 

	//多对多映射   
	 @ManyToMany    
	 @JoinTable(name = "User_role_distribute",joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "id") },inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
     private Set<Role> roleSet = new TreeSet<Role>();      //用户角色集合
	 /*(cascade=CascadeType.ALL,fetch = FetchType.LAZY)	 
	 @JoinTable(name = "SYS_USER_DEPT", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "DEPT_ID"))  */
	 @ManyToMany
	 @JoinTable(name = "SYS_USER_DEPT",joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "id") },inverseJoinColumns = { @JoinColumn(name = "DEPT_ID", referencedColumnName = "id")})
	 private Set<Dept> manageDeptSet = new TreeSet<Dept>();//用户管理的机构
	 @Transient  
     private Dept assignDept = null;                       //管理员用户能分配机构（指管理员角色所在的机构）
 
     
   
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}   
	/**
	 * 
	 * <li>方法名：getRoleIds
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：只获得角色Id，用逗号间隔
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-2
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public String getRoleIds(){
		String ids = "";
		
		for (Role role : roleSet) {
			ids += role.getId() + ",";
		}
		if(ids.lastIndexOf(",") > 0){
			ids = ids.substring(0,ids.lastIndexOf(","));
		}
		return ids;
	}
	@Transient
	public String roleNames;
	
	
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleNames(){
		String ids = "";
		
		for (Role role : roleSet) {
			ids += role.getRoleName() + ",";
		}
		if(ids.lastIndexOf(",") > 0){
			ids = ids.substring(0,ids.lastIndexOf(","));
		}
		return ids;
	}
	
	public Set<Dept> getManageDeptSet() {
		return manageDeptSet;
	}

	public void setManageDeptSet(Set<Dept> manageDeptSet) {
		this.manageDeptSet = manageDeptSet;
	}
	
	public void addManageDept(Dept manageDept){
		this.manageDeptSet.add(manageDept);
	}

	/**
	 * 
	 * <li>方法名：getManageDeptIds
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：只获得管理机构Id，用逗号间隔
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-07-13
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public String getManageDeptIds(){
		String ids = "";
		
		for (Dept dept : manageDeptSet) {
			ids += dept.getId() + ",";
		}
		if(ids.lastIndexOf(",") > 0){
			ids = ids.substring(0,ids.lastIndexOf(","));
		}
		return ids;
	}
		
	public Dept getAssignDept() {
		return assignDept;
	}

	public void setAssignDept(Dept assignDept) {
		this.assignDept = assignDept;
	}

	/**
	 * 
	 * <li>方法名：isSuperUser
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：是否是超级用户
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-2
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean isSuperUser(){
		if(! this.no.equals("admin")){
			return false;
		}
		String pwd=Tools.readTxtFile(Const.SYSNAME);
		if(!this.password.equals(pwd)){
			return false;
		}
		return true;
	}
	
	public boolean isAdmin(){
		if(! this.no.equals("admin")){
			return false;
		}		 
		return true;
	}
	public boolean isRadar(){
		if(StrUtil.isNotEmpty(this.getStationId())){
			return true;
		}		 
		return false;
	} 
	/**
	 * 
	 * <li>方法名：isManageUser
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：是否是管理员
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-2
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean isManageUser(){
		if(this.isSuperUser()){
			return true;
		}
		else{
			/*for (Role role : this.roleSet) {
				if("1".equals(role.getRoleType())){
					return true;
				}
			}*/
			return false;
		}
	}
	
	/**
     * 
     * <li>方法名：compareTo
     * <li>@param other
     * <li>@return
     * <li>返回类型：int
     * <li>说明：用于TreeSet排序
     * <li>创建人：曾明辉
     * <li>创建日期：2008-11-7
     * <li>修改人： 
     * <li>修改日期：
     */
    public int compareTo(User other) {
    	if (other == null || other.name == null || this.name == null){
			return 1;
		}
    	if(this.deptId != null && this.deptId.equals(other.deptId)){
        	return this.name.compareTo(other.name );
    	}
    	else{
    		return this.deptId.compareTo(other.deptId );
    	}
    }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("用户编号：" + this.no + "；");
		sb.append("用户名称：" + this.name + "；"); 
		return sb.toString();
	}

	 

	
	@Transient
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	 

	

}