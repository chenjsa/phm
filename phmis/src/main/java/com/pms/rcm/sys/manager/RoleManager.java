package com.pms.rcm.sys.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
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
@Service("roleManager")
public class RoleManager extends BaseManager<Role> {
	
	/**
	 * 
	 * <li>方法名：findByDeptId
	 * <li>@param deptId
	 * <li>@return
	 * <li>返回类型：List<Role>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-7-23
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<Role> findByDeptId(String deptId) throws BusinessException{
		String hql = "from Role r where deptId='" + deptId + "'";
		return this.baseDao.find(hql);
	}
	
	/**
	 * 
	 * <li>方法名：findPageByDeptId
	 * <li>@param deptId
	 * <li>@param page
	 * <li>@return
	 * <li>返回类型：List<Role>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-7-23
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<Role> findPageByDeptId(String deptId, Page page) throws BusinessException{

		String hql = "from Role r where deptId='" + deptId + "'";

		page.setTotalResult(this.baseDao.getCount(hql));
		  
		
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}
	
	/**
	 * 
	 * <li>方法名：findRoleByMenuId
	 * <li>@param menuId
	 * <li>@return
	 * <li>返回类型：Map<String, Role>
	 * <li>说明：查询能使用该菜单的所有角色，Map的Key为角色id
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-12
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Role> findRoleByMenuId(String menuId){
		String hql = "select r from Role r join r.menuSet m where m.id='" + menuId + "'";
		List<Role> roleList= this.baseDao.find(hql);
		Map<String, Role> roleMap = new TreeMap<String, Role>();
		for (Role role : roleList) {
			roleMap.put(role.getId(), role);
		}
		return roleMap;		
	}
	/**
	 * 
	 * <li>方法名：findRoleByModuleFunId
	 * <li>@param moduleFunId
	 * <li>@return
	 * <li>返回类型：Map<String,Role>
	 * <li>说明：查询能使用该功能的所有角色，Map的Key为角色id
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-12
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Role> findRoleByModuleFunId(String moduleFunId){
		String hql = "select r from Role r join r.moduleFunSet m where m.id='" + moduleFunId + "'";
		List<Role> roleList= this.baseDao.find(hql);
		Map<String, Role> roleMap = new TreeMap<String, Role>();
		for (Role role : roleList) {
			roleMap.put(role.getId(), role);
		}
		return roleMap;		
	}
	
	@Override
	public Role get(Serializable id) throws BusinessException {
		Role entity = super.get(id);
		baseDao.initialize(entity.getMenuSet());
		baseDao.initialize(entity.getSysOperationSet());
		return entity;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Role update(Role entity) throws BusinessException {
//		HttpSession session = WebContextFactory.get().getSession(); 
//		User loginUser = (User)session.getAttribute(LoginPurviewCheckFilter.USER_SESSION_NAME);
//		for (Role role : loginUser.getRoleSet()) {
//			if(role.getId().equals(entity.getId())){
//				throw new BusinessException("对不起,您属于角色【" + entity.getRoleName() + "】,没有权限进行管理该角色！");
//			}
//		} ();
		super.update(entity); 
		return entity;
	}
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public  void delete(Object entity,String id) throws BusinessException {
		entity=this.baseDao.get(id, entity.getClass());
		this.baseDao.delete(entity);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public Role insert(Role entity) throws BusinessException { 
		/*String hql="from Role where roleCode not in ('DWGLY','XJRY','PTGY') order by roleCode desc";
		Role role=(Role)this.baseDao.findSingle(hql);
		Integer maxNo=1;
		if(role!=null&&role.getRoleCode()!=null){
			 maxNo=Integer.valueOf(role.getRoleCode())+1;
		} 
		String maxCode=StrUtil.addZeroForNum(String.valueOf(maxNo), 5);
		entity.setRoleCode(maxCode);*/
	 	super.insert(entity); 
		return entity;
	}



	@Override
	protected void checkUnique(Role entity) throws BusinessException {
		List<String> list = new ArrayList<String>();
		list.add("roleCode");
		if(baseDao.isNotUnique(entity, list)){
			throw new BusinessException("角色编码【"+ entity.getRoleCode() +"】已存在，请注意大小写！");
		}
		
		list.remove(0);
		list.add("deptId");

		list.add("roleName");
		if(baseDao.isNotUnique(entity, list)){
			throw new BusinessException("同一机构下的角色【"+ entity.getRoleName() +"】已存在，请注意大小写！");
		}
				
	}

	@Override
	protected void checkDelete(Serializable id) throws BusinessException {
		String hql = "select u from User u join u.roleSet r where r.id = '"+id+"'";
		if(baseDao.isExist(hql)){			
			throw new BusinessException("【" + this.get(id).getRoleName() + "】存在用户，不能删除！");
		}
		
//		HttpSession session = WebContextFactory.get().getSession(); 
//		User loginUser = (User)session.getAttribute(LoginPurviewCheckFilter.USER_SESSION_NAME);
//		for (Role role : loginUser.getRoleSet()) {
//			if(role.getId().equals(id)){
//				throw new BusinessException("对不起,您属于角色【" +this.get(id).getRoleName() + "】,没有权限删除该角色！");
//			}
//		}
	}
	
	public boolean deleteCheck(Serializable id){
		String hql = "select u from User u join u.roleSet r where r.id = '"+id+"'";
		if(baseDao.isExist(hql)){			
			return false;
		}else
			return true;
	}

	/**
	 * 校验角色（id）下是否存在用户给
	 * @param id 角色id
	 * @return true 存在用户  false 不存在
	 */
	public boolean isExist(String id) {
		String sql="select u from User u join u.roleSet r where r.id = '"+id+"' ";
		if(baseDao.isExist(sql)){			
			return true;
		}else{
			return false;
		}
	}
	
	public List<Role> listAllRolesByUId(String uid){
		String hql="from User u where u.id='"+uid+"'";
		User user=(User)this.baseDao.findSingle(hql);
		Set<Role> roleSet=user.getRoleSet();
		List<Role> roleList=new ArrayList<Role>();
		roleList.addAll(roleSet);
		return roleList;
	}
}
