package com.pms.rcm.sys.manager;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.base.util.StringHandle;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.User;

 

/**
 * <li>类型名称：
 * <li>说明：
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-10-31
 * <li>修改人： 
 * <li>修改日期：
 */
@Service("userManager")
public class UserManager extends BaseManager<User> {
	protected static final Log logger = LogFactory.getLog(UserManager.class);
	 
	@Resource(name="deptManager")
	private DeptManager deptManager;
	/**
	 * 
	 * <li>方法名：findByDeptId
	 * <li>@param deptId
	 * <li>@return
	 * <li>返回类型：List<User>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-7-23
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<User> findByDeptId(String deptId) throws BusinessException{
		String hql = "from User where deptId='" + deptId + "' and state='1'";
		return this.baseDao.find(hql);
	}
	
	/**
	 * 
	 * <li>方法名：findPageByDeptId
	 * <li>@param deptId
	 * <li>@param page
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<User>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-7-23
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<User> findPageByDeptId(String deptId, Page page,User entitySearch) throws BusinessException {
		StringBuffer sb=new StringBuffer();
		Dept dept=deptManager.get(deptId);
		String hql="from Dept where casCode like '"+dept.getCasCode()+"%'";
		List<Dept> list=this.deptManager.find(hql);
		StringBuffer sb2=new StringBuffer();
		sb2.append(" in (");
		for(Dept d:list){
			sb2.append("'"+d.getId()+"',");
		}
		sb2.deleteCharAt(sb2.lastIndexOf(","));
		sb2.append(")");
		sb.append("from User where deptId  ");
		sb.append(sb2.toString());
		if(StrUtil.isNotEmpty(entitySearch.getNo())){
			sb.append( " and no like '%"+entitySearch.getNo()+"%'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getName())){
			sb.append( " and name like '%"+entitySearch.getName()+"%'");
		}
		sb.append(" order by no desc");
	    hql=sb.toString();
		logger.info(hql);
		page.setTotalResult(this.baseDao.getCount(hql));
		
		/*String orderStr = page.getOrderString();
		if(! StrUtil.isNotEmpty(orderStr)){
			orderStr = "ORDER BY no asc";
		}
		hql = hql + orderStr;*/
		
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}
	
	
	public List<User> findPageByRadarId(String deptId, Page page,User entitySearch) throws BusinessException {
		StringBuffer sb=new StringBuffer(); 
		sb.append("from User where stationId = '"+deptId+"' ");  
		sb.append(" order by no desc");
	    String hql=sb.toString();
		logger.info(hql);
		page.setTotalResult(this.baseDao.getCount(hql));
		
		/*String orderStr = page.getOrderString();
		if(! StrUtil.isNotEmpty(orderStr)){
			orderStr = "ORDER BY no asc";
		}
		hql = hql + orderStr;*/
		
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}

	/**
	 * 
	 * <li>方法名：findUserByMenuId
	 * <li>@param menuId
	 * <li>@return
	 * <li>返回类型：Map<String,user>
	 * <li>说明：查询能使用该菜单的所有用户，Map的Key为用户id，排除了该用户拥有无效角色的情况
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-12
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Map<String, User> findUserByMenuId(String menuId){
		String hql = "select u from User u join u.roleSet r join r.menuSet m where r.isUse='1' and m.id='" + menuId + "'";
		List<User> roleList= this.baseDao.find(hql);
		Map<String, User> roleMap = new TreeMap<String, User>();
		for (User user : roleList) {
			roleMap.put(user.getId(), user);
		}
		return roleMap;		
	}
	
	/**
	 * 
	 * <li>方法名：findUserByModuleFunId
	 * <li>@param moduleFunId
	 * <li>@return
	 * <li>返回类型：Map<String,user>
	 * <li>说明：查询能使用该功能的所有用户，Map的Key为用户id，排除了该用户拥有无效角色的情况
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-12
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Map<String, User> findUserByModuleFunId(String moduleFunId){
		String hql = "select u from User u join u.roleSet r join r.moduleFunSet m where r.isUse='1' and m.id='" + moduleFunId + "'";
		List<User> roleList= this.baseDao.find(hql);
		Map<String, User> roleMap = new TreeMap<String, User>();
		for (User user : roleList) {
			roleMap.put(user.getId(), user);
		}
		return roleMap;		
	}
	/**
	 * 
	 * <li>方法名：findLoginUser
	 * <li>@param entity
	 * <li>@return
	 * <li>返回类型：user
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-9
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public User findLoginUser(User entity){
		String hql = "select u from User u where u.no='" + entity.getNo() + "'";
		User user = null;
		Object obj =baseDao.findSingle(hql);
		if(obj != null){
			user = (User)obj;
			baseDao.initialize(user.getRoleSet());
			baseDao.initialize(user.getManageDeptSet());
		}
		return user;
	}
	 
	@Override
	public User get(Serializable id) throws BusinessException {
		User user = super.get(id);
		baseDao.initialize(user.getRoleSet());
		baseDao.initialize(user.getManageDeptSet());
		return user;
	}
	public User getUser(Serializable id) throws BusinessException {
		User user = super.get(id); 
		return user;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public User insert(User entity) throws BusinessException {
		entity.setPassword(StrUtil.MD5Encode("123456"));/*//默认登录密码
		String hql="from SysUserFromBoc where userNo = '"+entity.getNo()+"'";
		List list= this.baseDao.find(hql);
		SysUserFromBoc boc=(SysUserFromBoc)list.get(0);
		entity.setFromId(boc.getId());*/
		/*String hql="from User order by no desc";
		User user=(User)this.baseDao.findSingle(hql);
		Integer maxNo=1;
		if(user!=null&&user.getNo()!=null){
			 maxNo=Integer.valueOf(user.getNo())+1;
		} 
		String maxCode=StrUtil.addZeroForNum(String.valueOf(maxNo), 5);*/
		//entity.setNo(maxCode);
		if(StrUtil.isNotEmpty(entity.getDeptId())){
			entity.setTypeId("2");
		}else{
			entity.setTypeId("1");
		}
		entity.setUserStateId("1");
		this.checkUnique(entity);
		super.insert(entity);
		 
		
		return entity;
	}
	@Transactional(rollbackFor = { Exception.class })
	public  User update(User entity) throws BusinessException { 
		this.checkUnique(entity);
		if(StrUtil.isNotEmpty(entity.getDeptId())){
			entity.setTypeId("2");
		}else{
			entity.setTypeId("1");
		}
		entity.setUserStateId("1");
		baseDao.update(entity);
		
		return entity;
	}
	
	public boolean giveUpValidate(User user){
		String hql="from SrsCheckPlanExe where isInvalid=1 and planState  in ('1','2','3') and (planChecker='"+user.getId()+"' or planInitiatorId='"+user.getId()+"')";
		List list=this.baseDao.find(hql);
		if(list.isEmpty())
			return true;
		else
			return false;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public  void delete(User entity,String id) throws SQLException {
		//this.checkDelete(id);
		super.delete(entity, id);
	}
	/**
	 * 
	 * <li>方法名：updateDept
	 * <li>@param entity
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：user
	 * <li>说明：变更用户的机构，并将该用户的不属于新机构及父机构的角色删除
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-11
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = { Exception.class })
	public User updateDept(User entity, String newDeptId, Dept manageDept) throws BusinessException {
		Dept newDept = (Dept)this.baseDao.findSingle("from Dept where id='" + newDeptId + "'");
		StringBuffer deptCasCode = new StringBuffer();
		for (int i = manageDept.getCasCode().length(); i <= newDept.getCasCode().length();) {			
			deptCasCode.append("'"+ newDept.getCasCode().substring(0,i) +"',");
			i = i+3;			
		}
		deptCasCode.deleteCharAt(deptCasCode.lastIndexOf(","));
		
		String hql = "select r from Role r,Dept d where r.deptId=d.id and d.casCode in("
			+ deptCasCode.toString() + ")";

		for (Role role : entity.getRoleSet()) {
			baseDao.getSession().lock(role, LockMode.READ);
			
		}

		List<Role> roleList = baseDao.find(hql);//查询出新机构允许的角色
		Set<Role> enableRoleList = new TreeSet<Role>();//允许的角色
		for (Role role : entity.getRoleSet()) {
			if( roleList.contains(role)){//属于新机构的角色
				enableRoleList.add(role);
			}
		}
		
		//entity.setRoleSet(enableRoleList);//设置允许的角色
		//entity.setDeptId(newDeptId);//设置新机构
		entity.setRoleSet(new TreeSet<Role>());//设置允许的角色
		entity.setDeptId(newDeptId);//设置新机构
		Set<Dept> enableDeptList = new TreeSet<Dept>();
		enableDeptList.add(newDept);
		entity.setManageDeptSet(enableDeptList);
		
		return (User)baseDao.update(entity);
	}
	/**
	 * 
	 * <li>方法名：updatePassword
	 * <li>@param id
	 * <li>@param oldPassword
	 * <li>@param newPassword
	 * <li>@return
	 * <li>返回类型：user
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-10
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public User updatePassword(String id, String oldPassword, String newPassword) throws BusinessException {
		Object obj = this.baseDao.findSingle("from User where id='"+ id +"'");
		User user = (User)obj;
		if(! user.getPassword().equals(StrUtil.MD5Encode(oldPassword))){
			throw new BusinessException("对不起,您输入原来的密码有误,请重新输入!");
		}

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		String hql = "update User set password='" + newPassword;
		hql += "', pwdEditDate='" + format.format(new Date());
		hql += "' where id='" + id + "'";
		
		this.baseDao.execute(hql);
		
		return user;
	}
	@Transactional(rollbackFor = { Exception.class })
	public void updateFirstMenu(String id, String firstMenuId) throws BusinessException {
				
		String hql = "update User set firstMenuId='" + firstMenuId ;
		hql += "' where id='" + id + "'";
		
		this.baseDao.execute(hql);
		
	}
	/**
	 * 
	 * <li>方法名：resetPassword
	 * <li>@param id
	 * <li>@param password
	 * <li>@return
	 * <li>返回类型：void
	 * <li>说明：
	 * <li>创建人：张凌
	 * <li>创建日期：2009-11-04
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void updatePassword(String id, String passWord) throws BusinessException {
		String hql = "update User set password='" + passWord + "',pwdEditDate='"+StrUtil.getDateString("yyyy-MM-dd")+"' where id='" + id + "'";
		this.baseDao.execute(hql);
	}
	
	/**
	 * 
	 * <li>方法名：resetLock
	 * <li>@param id
	 * <li>@param password
	 * <li>@return
	 * <li>返回类型：void
	 * <li>说明：
	 * <li>创建人：张凌
	 * <li>创建日期：2009-11-04
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void resetLock(String id) throws BusinessException {
		String hql = "update User set isLock='0' where id='" + id + "'";
		this.baseDao.execute(hql);
	}
	
	/**
	 * 
	 * <li>方法名：updateBatchPurview
	 * <li>@param entity 保存有用户的角色、检修中心、配件库信息
	 * <li>@param userList 需要批量保存的用户
	 * <li>@param operType 只能为增加（add）或删除（del）
	 * <li>@throws BusinessException
	 * <li>返回类型：user
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void updateBatchPurview(User entity, Collection<User> userList, String operType) throws BusinessException {
		if((! "add".equals(operType)) && (! "del".equals(operType))){
			throw new BusinessException("operType 只能为add或del");
		}
		for (Role role : entity.getRoleSet()) {
			baseDao.getSession().lock(role, LockMode.READ);
		}
		
		for (User user : userList) {
			user = this.get(user.getId());//获得用户

			if("add".equals(operType)){
				user.getRoleSet().addAll(entity.getRoleSet());//增加新角色
			}
			else{
				user.getRoleSet().removeAll(entity.getRoleSet());//删除新角色
			}
			baseDao.update(user);//保存
		}
	}

	@Override
	protected void checkUnique(User entity) throws BusinessException {
		List<String> list = new ArrayList<String>();
		list.add("no");
		if(baseDao.isNotUnique(entity, list)){
			throw new BusinessException("登录名【"+ entity.getNo() +"】已存在！");
		}
		list.clear();
		/*list.add("mobile");
		if(baseDao.isNotUnique(entity, list)){
			throw new BusinessException("用户手机号【"+ entity.getTelephone()+"】已存在！");
		}*/
		
		list.clear();
		list.add("deptId");
		list.add("name");
		if(baseDao.isNotUnique(entity, list)){
			///throw new BusinessException("同一机构下用户姓名【"+ entity.getName() +"】已存在！");
		}
	}
	
	@Override
	public void checkDelete(Serializable id) throws BusinessException {
		
		User user =this.get(id);
//		String hql = "select d from SysLog d where userNo = '"+user.getNo()+"'";
		String hql = "select d from srsCheckModel d where d.userId = '"+id+"'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + user.getName() + "】用户不能删除，已经发生创建模型业务！");
		}
		hql = "select d from SrsCheckPlan d where d.planInitiatorId = '"+id+"' or d.planCheckerId='"+id+"'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + user.getName() + "】用户不能删除，已经发生创建计划业务！");
		}
		hql = "select d from SrsDevice d where d.editeUserId = '"+id+"'  ";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + user.getName() + "】用户不能删除，已经发生导入设备业务！");
		}
			 
			 
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public int deleteSysUserBak(){
		String deleteSql = "delete from sys_user_bak";
		SQLQuery deleteQuery = this.baseDao.getSessionForPro().createSQLQuery(deleteSql);
		int deleteCount = deleteQuery.executeUpdate();
		logger.info("导入user到sys_user_bak数据前，删除sys_user_bak表所有数据！删除条数："+deleteCount);
		return deleteCount;
	}
	
 
	
	/**<li>调用存储过程;
	 * @param procName 存储过程名称
	 * @param args 输入参数集合
	 * @param outputArgsCount outputArgsCount 输出参数个数
	 * @return
	 * @throws Exception
	 */
	public String execProcedure(String procName,List<String> args,int outputArgsCount)throws Exception{
		return this.baseDao.execProcedure(procName, args,outputArgsCount);
	}
	
	/**<li>sys_user_bak表的数据总条数
	 * @return
	 */
	public Integer findAllCount(){
		String sql="select count(1) from User";
		return this.baseDao.getCount(sql);
	}
	
	/**
	 * 
	 * <li>方法名：getUserWithOrgAndRole
	 * <li>
	 * <li>返回类型：List
	 * <li>说明：查询指定角色指定机构下所有用户信息
	 * <li>创建人：廖迎春
	 * <li>创建日期：2011-11-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<User> getUserWithOrgAndRole(String casCode,String roleCode){
		Role role = new Role();
		role.setRoleCode(roleCode);
		role = (Role)this.baseDao.findSingleByEntity(role);
		StringBuffer sb = new StringBuffer("");
		sb.append(" select id,no,name from sys_user");
		sb.append(" where dept_id in (");
		sb.append(" select id from sys_dept");
		sb.append(" where cas_Code like '"+casCode+"%'");
		sb.append(" )");
		if(!"".equals(roleCode.trim())){
			sb.append(" and id in (");
			sb.append(" select  user_id from Sys_User_Role");
			sb.append(" where role_id = '"+role.getId()+"'");
			sb.append(" )");
		}
		SQLQuery query = this.baseDao.getSessionForPro().createSQLQuery(sb.toString());
		return (List<User>)query.list();
	}
	
	public List<User> getUserWithRole(String roleCode){
		Role role = new Role();
		role.setRoleCode(roleCode);
		role = (Role)this.baseDao.findSingleByEntity(role);
		StringBuffer sb = new StringBuffer("");
		sb.append(" select id,no,name from sys_user");
		sb.append(" where 1=1 "); 
		if(!"".equals(roleCode.trim())){
			sb.append(" and id in (");
			sb.append(" select  user_id from Sys_User_Role");
			sb.append(" where role_id = '"+role.getId()+"'");
			sb.append(" )");
		}
		SQLQuery query = this.baseDao.getSessionForPro().createSQLQuery(sb.toString());
		return (List<User>)query.list();
	}
	
	/**
	 * 添加指纹,dwr调用
	 * @param user
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	public String updateUserToAddFinger(String id,String fingerPrint){
		String reStr = "";
		fingerPrint=StringHandle.unescape(fingerPrint);
		fingerPrint=StringHandle.unescape(fingerPrint);
		String sql = "update sys_user set finger_print = '"+fingerPrint+"' where id = '"+id+"'";
		try{
		this.jdbcHelper.executeUpdate(sql);
		}catch(Exception e){
			reStr = e.getMessage();
			e.printStackTrace();
		}
		return reStr;
	}
	public boolean isFirstLogin(User user){
		String hql = "select d from SysLog d where userNo = '"+user.getNo()+"'";
		if(baseDao.isExist(hql)){
			return false;
		}
		return true;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public List<User> findUserNo(String no) {
		String sqlUserNo="from User where no = '"+no+"'";
		return (List<User>)this.baseDao.find(sqlUserNo);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public User findByMobile(String mobile){
		String hql="from User u where u.mobile='"+mobile+"'";
		return (User) this.baseDao.findSingle(hql);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public User getUserByToken(String token){
		String hql="from User where token='"+token+"'";		
		return (User)this.baseDao.findSingle(hql);
	}
	
	/**根据用户号和密码查询用户；不存在返回空，用户状态和传入状态不符合返回空，存在且状态相同返回json字符串
	 * @param userNo
	 * @param password
	 * @param state
	 * @return
	 */
	public String checkUserAuth(String userNo,String password,String... state){
		String result="";
		//查询数据库中用户为userNo的用户
		String sql="from User where no='"+userNo+"'";
		Object obj=this.baseDao.findSingle(sql);
		User user=null;
		if(obj instanceof User){
			user=(User)obj;
		}
		if(user!=null){
			if(user.getPassword().trim().equals(StrUtil.MD5Encode(password))){//密码验证成功
				result="{\"no\":\""+user.getNo()+"\",\"name\":\""+user.getName()+"\"}";
			}
		}
		if(state!=null){
			if(!user.getUserStateInfo().getUserStateName().equals(state)){
				return "";
			}
		}
		return result;
	}
	public List<Menu> listTree(User user,Set<Role> roleSet){
		List<Role> list=new ArrayList<Role>();
		List<Menu> list1=new ArrayList<Menu>(); 
		List<Menu> list2=new ArrayList<Menu>(); 
		Menu menu1=new Menu();
		menu1.setId("0");
		menu1.setMenuName("系统角色"); 
		menu1.setTarget("treeFrame"); 
		menu1.setHasMenu(false); 
		menu1.setNocheck(true);
		if(user.isAdmin()){
			String hql="from Role ";
		    list=this.baseDao.find(hql); 
			for(Role role:list){
				Menu menu=new Menu();
				menu.setId(role.getId());
				menu.setMenuName(role.getRoleName());
				menu.setParentId("0"); 
				menu.setTarget("treeFrame"); 
				if(roleSet!=null){
					if(roleSet.contains(role))
						menu.setHasMenu(true);
					else
						menu.setHasMenu(false); 
				}else
					menu.setHasMenu(false);
				list1.add(menu);
			}
		}else{
			String hql="from Role  ";
			list=this.baseDao.find(hql);  
			for(Role role:list){
				Menu menu=new Menu();
				menu.setId(role.getId());
				menu.setMenuName(role.getRoleName());
				menu.setParentId("0"); 
				menu.setTarget("treeFrame"); 
				if(roleSet!=null){
					if(roleSet.contains(role))
						menu.setHasMenu(true);
					else
						menu.setHasMenu(false); 
				}else
					menu.setHasMenu(false);
				list1.add(menu);
			}
		}
		menu1.setSubMenu(list1);
		list2.add(menu1);
		return list2;
	}
	 
	  
}
