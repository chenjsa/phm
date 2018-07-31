/**
 * <li>文件名：BugManager.java
 * <li>说明：
 * <li>创建人： 王剑
 * <li>创建日期：2008-10-29
 * <li>修改人： 
 * <li>修改日期：
 */
package com.pms.rcm.sys.manager;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.ModuleFun;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.User;

 
/**
 * <li>类型名称：
 * <li>说明：
 * <li>创建人： 王剑
 * <li>创建日期：2008-10-29
 * <li>修改人： 
 * <li>修改日期：
 */
@Service("moduleFunManager")
public class ModuleFunManager extends BaseManager<ModuleFun> {
	
	/**
	 * 
	 * <li>方法名：findDisModuleFun
	 * <li>@param user
	 * <li>@return
	 * <li>返回类型：Map<String,String> key为功能点关键字及Action关键字组合
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-8
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> findDisModuleFun(User user){
		String hql = "select m from ModuleFun m where m.isAllowCtr='1' and m.type='3'";
		List<ModuleFun> allModuleFunMethod = baseDao.find(hql);//查找到需要控制的功能，只查功能点
		hql = "select distinct m from User u join u.roleSet r join r.moduleFunSet m where r.isUse='1' and m.isAllowCtr='1' and m.type='3' and u.id='" + user.getId() + "'";
		
		List<ModuleFun> selModuleFunMethod = baseDao.find(hql);//查找用户配置的功能，只查功能点
		allModuleFunMethod.removeAll(selModuleFunMethod);//排除用户允许的功能则为禁止的功能
		
		hql = "select m from ModuleFun m where m.type='2'";
		List<ModuleFun> moduleFunAction = baseDao.find(hql);//查找到需要控制的功能，只查Action
		
		Map<String, ModuleFun> actionMap = new HashMap<String, ModuleFun>();//Action的关键字
		for (ModuleFun fun : moduleFunAction) {
			actionMap.put(fun.getCasCode(), fun);
		}
		
		Map<String,String> disKeyWordMap = new HashMap<String, String>();//禁用功能，key为功能点关键字及Action关键字组合
		for (ModuleFun fun : allModuleFunMethod) {
			ModuleFun  action = actionMap.get(fun.getParentCasCode());
			String url = action.getKeyWord() + "!" + fun.getKeyWord();
			String name = action.getName()+"【"+fun.getName()+"】";
			disKeyWordMap.put(url, name);
		}
		
		return disKeyWordMap;
	}
	/**
	 * 
	 * <li>方法名：getByCasCode
	 * <li>@param casCode
	 * <li>@return
	 * <li>返回类型：Dept
	 * <li>说明：通过级联编号获得机构对象
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-26
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public ModuleFun getByCasCode(String casCode){
		String hql = "from ModuleFun d where d.casCode='" + casCode + "'";
		return (ModuleFun)baseDao.findSingle(hql);
	}
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param parentModuleFun
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean hasChildren(ModuleFun parentModuleFun){
		String casCode = "___";
		if(parentModuleFun.getCasCode()!=null){
			casCode = parentModuleFun.getCasCode() + casCode;
		}
		String hql = "from ModuleFun d where d.casCode like '" + casCode + "'";
		return baseDao.isExist(hql);
	}
	
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param ModuleFun
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ModuleFun> findChildrenPage(String parentCasCode, Page page, boolean needSearchCount){
		String casCode = "___";
		if(parentCasCode!=null && ! "".equals(parentCasCode)){
			casCode = parentCasCode + casCode;
		}

		String hql = "from ModuleFun d where d.casCode like '" + casCode + "' order by d.serial";
		if(needSearchCount == true){
			page.setTotalResult(this.baseDao.getCount(hql));
		}
		
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}
	/**
	 * 
	 * <li>方法名：findChildren
	 * <li>@param parentCasCode
	 * <li>@return
	 * <li>返回类型：List<ModuleFun>
	 * <li>说明：不分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ModuleFun> findChildren(String parentCasCode){
		String casCode = "___";
		if(parentCasCode!=null && ! "".equals(parentCasCode) && ! "0".equals(parentCasCode)){//树顶层为0
			casCode = parentCasCode + casCode;
		}
		String hql = "from ModuleFun d where d.casCode like '" + casCode + "' order by d.serial";
		return baseDao.find(hql);
	}
	@Override
	public ModuleFun insert(ModuleFun entity) throws BusinessException {
		
		String nextCasCode = super.getNextCasCode(entity, null);
		entity.setCasCode(nextCasCode);//设置casCode
		
		return super.insert(entity);//同时验证
	}
	
	@Override
	protected void checkUnique(ModuleFun entity) throws BusinessException {
		List<Criterion> listCri = new ArrayList<Criterion>();

		listCri.add(Restrictions.like("casCode", entity.getParentCasCode()+"___"));
		listCri.add(Restrictions.ilike("name", entity.getName()));
		
		if(baseDao.isNotUniqueCriterion(entity, listCri)){
			throw new BusinessException("同一级模块功能【"+ entity.getName() +"】已存在，请注意大小写！");
		}
		if("3".equals(entity.getType())){//功能点
			listCri.remove(1);//删除名称
			listCri.add(Restrictions.eq("type", entity.getType()));
			listCri.add(Restrictions.ilike("keyWord", entity.getKeyWord()));

			if(baseDao.isNotUniqueCriterion(entity, listCri)){
				throw new BusinessException("功能点关键字【" + entity.getKeyWord() + "】在该功能下已存在，请注意大小写！");
			}
		}
		if("2".equals(entity.getType())){//功能
			List<String> list = new ArrayList<String>();
			list.add("type");
			list.add("keyWord");
			if(baseDao.isNotUnique(entity, list)){
				throw new BusinessException("功能关键字【" + entity.getKeyWord() + "】已存在，请注意大小写！");
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void checkDelete(Serializable id) throws BusinessException {
		ModuleFun moduleFun = this.get(id);
		String hql = "select m from ModuleFun m where casCode like '"+moduleFun.getCasCode()+"___%'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + moduleFun.getName() + "】存在下级功能，不能删除！");
		}		
		hql = "select r from Role r join r.moduleFunSet fun where fun.id = '"+id+"'";
		List<Role> roleList = baseDao.find(hql);
		if(roleList.size() > 0){			
			throw new BusinessException("【" + this.get(id).getName() + "】已被角色【"+roleList.get(0).getRoleName() +"】使用，不能删除！");
		}
		hql = "select m from Menu m join m.moduleFunSet fun where fun.id = '"+id+"'";
		List<Menu> menuList = baseDao.find(hql);
		if(menuList.size() > 0){			
			throw new BusinessException("【" + this.get(id).getName() + "】已被菜单【"+menuList.get(0).getMenuName() +"】使用，不能删除！");
		}
	}

}
