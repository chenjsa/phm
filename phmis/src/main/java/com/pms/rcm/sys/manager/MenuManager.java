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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.ModuleFun;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.SysButton;
import com.pms.rcm.sys.vo.User;

 
/**
 * <li>类型名称：
 * <li>说明：
 * <li>创建人： 王剑
 * <li>创建日期：2008-10-29
 * <li>修改人： 
 * <li>修改日期：
 */
@Service("menuManager")
public class MenuManager extends BaseManager<Menu> {

	/**
	 * 
	 * <li>方法名：findAllMenu
	 * <li>@return
	 * <li>返回类型：List<Menu>
	 * <li>说明：找到所有有效菜单
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-8
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findAllMenu(){
		String hql = "select m from Menu m where m.isUse='1'";
		return (List<Menu>)baseDao.find(hql);
	}
	/**
	 * 
	 * <li>方法名：findMenuByUser
	 * <li>@param user
	 * <li>@return
	 * <li>返回类型：List<Menu>
	 * <li>说明：找到用户的有效菜单
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-8
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findMenuByUser(User user){
		String hql = "select distinct m from User u join u.roleSet r join r.menuSet m where r.isUse='1' and m.isUse='1' and u.id='" + user.getId() + "'";
		return baseDao.find(hql);
	}
	
	@Override
	public Menu get(Serializable id) throws BusinessException {
		Menu entity = super.get(id);
		//baseDao.initialize(entity.getModuleFunSet());
		return entity;
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
	public Menu getByCasCode(String casCode){
		String hql = "from Menu d where d.casCode='" + casCode + "'";
		return (Menu)baseDao.findSingle(hql);
	}
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param parentMenu
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean hasChildren(Menu parentMenu){
		String casCode = "___";
		if(parentMenu.getCasCode()!=null){
			casCode = parentMenu.getCasCode() + casCode;
		}
		String hql = "from Menu d where d.casCode like '" + casCode + "'";
		return baseDao.isExist(hql);
	}
	
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param Menu
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findChildrenPage(String parentCasCode, Page page, boolean needSearchCount){
		String casCode = "___";
		if(parentCasCode!=null&& !  "".equals(parentCasCode)){
			casCode = parentCasCode + casCode;
		}

		String hql = "from Menu d where d.casCode like '" + casCode + "' ";
		/*String orderStr = page.getOrderString();
		if(! StrUtil.isNotEmpty(orderStr)){
			orderStr = "ORDER BY d.serial";
		}
		hql = hql + orderStr;*/
		
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
	 * <li>返回类型：List<Menu>
	 * <li>说明：不分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-27
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findChildren(String parentCasCode){
		String casCode = "___";
		if(parentCasCode!=null && ! "".equals(parentCasCode)&& ! "0".equals(parentCasCode)){//从树过来的顶层节点为0
			casCode = parentCasCode + casCode;
		}
		String hql = "from Menu d where d.casCode like '" + casCode + "' order by d.serial";
		return baseDao.find(hql);
	}
	/*
	 * 查找最高级菜单的第二级菜单
	 */
	public List<Menu> findOneChildMenu(String casCode){
//		String casCode = "___";
//		if(parentCasCode!=null && ! "".equals(parentCasCode)&& ! "0".equals(parentCasCode)){//从树过来的顶层节点为0
//			casCode = parentCasCode + casCode;
//		}
		List<Menu> menuList = new ArrayList<Menu>();
		
		String hql = "from Menu d where d.casCode like '" + casCode+"___" + "%' order by d.serial";
		menuList = baseDao.find(hql);
		if(menuList ==null || menuList.size() == 0){
			hql = "from Menu d where d.casCode = '" + casCode+"' order by d.serial";
			menuList = baseDao.find(hql);
		}
		return menuList;
	}
	
	/*
	 * 查找最高级菜单的第二级菜单
	 */
	public List<Menu> findOneChildMenuForUser(String casCode,String userId){
 
		List<Menu> menuList = new ArrayList<Menu>();
		String sql=
			"select id from Function_page_info m where exists (\n" +
			"  select 1 from sys_role_menu rm where rm.menu_id=m.id and exists (\n" + 
			"   select 1 from sys_user_role ur where ur.role_id=rm.role_id and ur.user_id='"+userId+"'\n" + 
			" )\n" + 
			")";
        List list=this.jdbcHelper.SqlQuery(sql);
        StringBuffer sb=new StringBuffer();
     
        for(int i=0;i<list.size();i++)
        {
        	Object[] data=(Object[])list.get(i);
        	
        	if(i==list.size()-1)
        	{
        		sb.append("'"+data[0]+"')");
        	}
        	else if(i==0)
        	{
        		sb.append(" and d.id in ('"+data[0]+"',");
        	}else
        	{
        		sb.append("'"+data[0]+"',");
        	}
        }
		
		String hql = "from Menu d where d.casCode like '" + casCode+"___" + "%'  "+sb.toString()+" order by d.serial";
		//System.out.println("hql=="+hql);
		menuList = baseDao.find(hql);
		if(menuList ==null || menuList.size() == 0){
			hql = "from Menu d where d.casCode = '" + casCode+"'    "+sb.toString()+" order by d.serial";
			menuList = baseDao.find(hql);
		}
		return menuList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> findDisMenus(){
		String hql = "select m from Menu m where m.menuType='1' and m.casCode like '___' and exists(select n from Menu n where n.menuType='1' and n.casCode like m.casCode||'___')";
		return baseDao.find(hql);
	}
		
	
	/**
	 * 
	 * <li>方法名：findAllMenuAndFun
	 * <li>@param loadModuleFun 是否加载链接菜单的功能
	 * <li>@return
	 * <li>返回类型：List<Menu>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-13
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findAllMenuAndFun(boolean loadModuleFun){		
		List<Menu> menuList = this.baseDao.find("select m from Menu m");//所有菜单
		if(loadModuleFun == true){
			
			//List<ModuleFun> allowFunList = this.baseDao.find("select f from ModuleFun f ");//所有功能

			for (Menu menu : menuList) {
				if(menu.getMenuType().equals("3")){//链接菜单需要支持的功能
					//baseDao.initialize(menu.getModuleFunSet());//加载角色功能	
				}
			}
			
		}
		Collections.sort(menuList);
		return menuList;
	}
	/**
	 * 
	 * <li>方法名：findMenuAndFunByUser
	 * <li>@param user
	 * <li>@param loadModuleFun 是否加载链接菜单的功能
	 * <li>@return
	 * <li>返回类型：<Menu>
	 * <li>说明：获得用户管理员角色的菜单
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-13
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findMenuAndFunByUser(User user, boolean loadModuleFun){
		String hql = "select m from User u join u.roleSet r join r.menuSet m where r.roleType='2' and u.id='" + user.getId() + "'";
		
		List<Menu> menuList = this.baseDao.find(hql);//该用户允许转授的菜单
		if(loadModuleFun == true){
			hql = "select f from User u join u.roleSet r join r.moduleFunSet f where r.roleType='2' and u.id='" + user.getId() + "'";
			List<ModuleFun> allowFunList = this.baseDao.find(hql);//该用户允许转授的功能
			List<ModuleFun> actionFunList = this.baseDao.find("select m from ModuleFun m where m.type='2'");
			
			Map<String, ModuleFun> actionFunMap = new HashMap<String, ModuleFun>();//所有Action功能
			for (ModuleFun fun : actionFunList) {
				actionFunMap.put(fun.getCasCode(), fun);
			}
			
			/*for (Menu menu : menuList) {
				if(menu.getMenuType().equals("3")){//链接菜单需要支持的功能
					baseDao.initialize(menu.getModuleFunSet());//加载角色功能
					Set<ModuleFun> moduleFunSet = new TreeSet<ModuleFun>();//允许的功能
					for (ModuleFun fun : menu.getModuleFunSet()) {
						if(allowFunList.contains(fun)){//该用户有该功能
							moduleFunSet.add(fun);
							ModuleFun actionFun = actionFunMap.get(fun.getParentCasCode());
							if(! moduleFunSet.contains(actionFun)){//没加时
								moduleFunSet.add(actionFun);
							}
						}
					}
					
					menu.setModuleFunSet(moduleFunSet);//设置允许的功能
				}
			}*/
			
		}
		Collections.sort(menuList);
		return menuList;
	}
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Menu insert(Menu entity) throws BusinessException {
		
		String nextCasCode = super.getNextCasCode(entity, null);
		entity.setCasCode(nextCasCode);//设置casCode

		return super.insert(entity);//同时验证
	}
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public  Menu update(Menu entity) throws BusinessException { 
		baseDao.update(entity);
		
		return entity;
	}
	/**
	 * 
	 * <li>方法名：updateMove
	 * <li>@param entity
	 * <li>@param newCasCode 新的级联编码
	 * <li>@return
	 * <li>返回类型：Menu
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-28
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Menu updateMove(Menu entity, String moveCasCode) throws BusinessException {
			
		Menu newMenu = new Menu();
		newMenu.setCasCode(moveCasCode);
		String nextCasCode = this.getNextCasCode(newMenu, null);

		int start = entity.getCasCode().length()+1;
//		String hql = "update Menu m set casCode= '" + nextCasCode + "'||substr(casCode," + start + ") "//Oracle用substr函数
//					+"where casCode like '"+ entity.getCasCode() +"%'";
		
		String hql = "update Menu m set casCode= '" + nextCasCode + "'||substring(casCode," + start + ",len(casCode)-"+ start +"+1) "//SQLServer用substring函数
					+"where casCode like '"+ entity.getCasCode() +"%'";
		baseDao.execute(hql);
		entity.setCasCode(nextCasCode);
		return entity;
	}
	
	
	@Override
	protected void checkUnique(Menu entity) throws BusinessException {
		List<Criterion> listCri = new ArrayList<Criterion>();

		listCri.add(Restrictions.like("casCode", entity.getParentCasCode()+"___"));
		listCri.add(Restrictions.ilike("menuName", entity.getMenuName()));
		
		if(baseDao.isNotUniqueCriterion(entity, listCri)){
			throw new BusinessException("同一级菜单【"+ entity.getMenuName() +"】已存在，请注意大小写！");
		}
		
//		listCri.remove(1);
//		if(entity.getMenuType().equals("1")){//顶级菜单，不能与目录菜单及链接菜单同时存在
//			listCri.add(Restrictions.in("menuType", new String[]{"2","3"}));
//			if(baseDao.isNotUniqueCriterion(entity, listCri)){
//				throw new BusinessException("同一级菜单已存在目录菜单或链接菜单，不能再有顶级菜单！");
//			}
//		}
//		else{//目录菜单及链接菜单，不能与顶级菜单同时存在
//			listCri.add(Restrictions.eq("menuType", "1"));
//			if(baseDao.isNotUniqueCriterion(entity, listCri)){
//				throw new BusinessException("同一级菜单已存在顶级菜单，不能再有目录菜单或链接菜单！");
//			}
//			if(entity.getMenuType().equals("3")){//是链接菜单
//				if(entity.getId()!=null && !entity.getId().equals("")){//修改时判断有无下级菜单，有则不能修改为链接菜单
//					String hql = "from Menu where casCode like '" + entity.getCasCode() + "%' and casCode !='" + entity.getCasCode() + "'";
//					if(baseDao.isExist(hql)){//有下级菜单
//						throw new BusinessException("菜单【" + entity.getMenuName() + "】有下级菜单，不能设置为链接菜单！");
//					}
//				}
//			}
//		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void checkDelete(Serializable id) throws BusinessException {
		Menu menu = this.get(id);
		String hql = "select m from Menu m where casCode like '"+menu.getCasCode()+"___%'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + menu.getMenuName() + "】存在下级菜单，不能删除！");
		}
		
		hql = "select r from Role r join r.menuSet menu where menu.id = '"+id+"'";
		List<Role> roleList = baseDao.find(hql);
		if(roleList.size() > 0){			
			throw new BusinessException("【" + this.get(id).getMenuName() + "】已被角色【"+roleList.get(0).getRoleName() +"】使用，不能删除！");
		}
//		hql = "select u from User u join u.menuSet menu where menu.id = '"+id+"'";
//		List<User> userList = baseDao.find(hql);
//		if(roleList.size() > 0){			
//			throw new BusinessException("【" + this.get(id).getMenuName() + "】已被用户【"+userList.get(0).getName() +"】设为自选菜单使用，不能删除！");
//		}
		hql = "select u from User u where u.firstMenuId = '"+id+"'";
		List<User> firstUserList = baseDao.find(hql);
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + menu.getMenuName() + "】已被用户【" + firstUserList.get(0).getName() + "】设为首页，不能删除！");
		}
	}
	
//	/**
//	 * 
//	 * <li>方法名：findAll
//	 * <li>@param parentCasCode 父级联编码
//	 * <li>@param loadModuleFun 是否加载链接菜单的功能
//	 * <li>@return
//	 * <li>返回类型：<Menu>
//	 * <li>说明：递归查询出菜单
//	 * <li>创建人：曾明辉
//	 * <li>创建日期：2008-12-1
//	 * <li>修改人： 
//	 * <li>修改日期：
//	 */
//	private <Menu> findAll(String parentCasCode, boolean loadModuleFun){
//		<Menu> menuList = this.findChildren(parentCasCode);
//		for (Menu menu : menuList) {
//			if(menu.getMenuType().equals("3")){//链接菜单有功能
//				if(loadModuleFun == true){//需要加载菜单的功能
//					daoUtils.initialize(menu.getModuleFunSet());
//				}
//			}
//			else{//顶级菜单及目录菜单有子菜单
//				<Menu> children = this.findAll(menu.getCasCode(),loadModuleFun);
//				menu.setChildren(children);
//			}
//		}
//		return menuList;
//	} 
	public List<Menu> getTopMenuByUserNo(User user)
	{
	 
		if(user.isSuperUser())
		{
			String hql = "select m from Menu m where m.isUse='1' and length(m.casCode)=3 order by m.serial";
			return (List<Menu>)baseDao.find(hql);
		}		
		else
		{ 
			String hql = "select distinct m from User u join u.roleSet r join r.menuSet m where r.isUse='1' and m.isUse='1' and length(m.casCode)=3 and u.id='" + user.getId() + "' order by m.serial";
			return baseDao.find(hql);
			
		}
	}  
	
	/**
	 * 获取所有菜单并填充每个菜单的子菜单列表(菜单管理)(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listUserAllMenu(String MENU_ID,User user) throws Exception {
		List<Menu> menuList=new ArrayList<Menu>();
		if(user.isAdmin()){
			menuList = findAdminSubMenuForUser(MENU_ID); 
			for(Menu menu : menuList){  
				List<Menu> subList=listUserAllMenu(menu.getId(),user);
				menu.setSubMenu(subList);  
				if(menu.getActionPath().indexOf("#")!=-1){
					 
				}else{
					if(menu.getActionPath().indexOf("?")!=-1){ 
						menu.setActionPath(menu.getActionPath()+"&menuId="+menu.getId());
						//System.out.println("url="+menu.getActionPath());
					}else
					{
						menu.setActionPath(menu.getActionPath()+"?menuId="+menu.getId());
					}
				}
			}
		}else{
			menuList = findSubMenuForUser(MENU_ID,user.getId()); 
			for(Menu menu : menuList){  
				List<Menu> subList=listUserAllMenu(menu.getId(),user);
				menu.setSubMenu(subList);   
				if(menu.getActionPath().indexOf("#")!=-1){
					 
				}else{
					if(menu.getActionPath().indexOf("?")!=-1){ 
						menu.setActionPath(menu.getActionPath()+"&menuId="+menu.getId());
						//System.out.println("url="+menu.getActionPath());
					}else
					{
						menu.setActionPath(menu.getActionPath()+"?menuId="+menu.getId());
					}
				}
			}
		}		
		return menuList;
	}
	@SuppressWarnings("unchecked")
	public  List<Menu> listAllMenu(String menuId){		 
        String  hql = "from Menu d where   d.parentId = '" +menuId+"'   order by d.serial"; 
    	List<Menu> menuList = new ArrayList<Menu>();
		menuList = (List<Menu>)baseDao.find(hql); 
		for(Menu menu : menuList){  
			menu.setActionPath("menu/toEdit.do?MENU_ID="+menu.getId()); 
			menu.setSubMenu(listAllMenu(menu.getId()));    
			menu.setTarget("treeFrame");
		}
		return menuList;
	}
	@SuppressWarnings("unchecked")
	public  List<Menu> listAllMenuForRole(String menuId, Role role){		 
		 String  hql = "from Menu d where   d.parentId = '" +menuId+"'   order by d.serial"; 
    	List<Menu> menuList = new ArrayList<Menu>();
		menuList = (List<Menu>)baseDao.find(hql); 
		Set<Menu> menuSet=role.getMenuSet();
		Set<SysButton> sysOperationSet=role.getSysOperationSet(); 
		for(Menu menu : menuList){  			
			List<Menu> subMenuList=listAllMenuForRole(menu.getId(),role);
			String shql="from SysButton where menuId='"+menu.getId()+"'";
			List<SysButton> sList=(List<SysButton>)this.baseDao.find(shql);
			for(SysButton s:sList){
				Menu m=new Menu();
				m.setMenuName(s.getBtnName()+"(功能点)");
				m.setParentId(menu.getId());
				m.setActionPath("");
				m.setTarget("treeFrame");
				if(sysOperationSet.contains(s))
					m.setHasMenu(true);
				else
					m.setHasMenu(false);
				m.setId(s.getId());
				subMenuList.add(m);
			}
			menu.setActionPath("menu/toEdit.do?MENU_ID="+menu.getId()); 
			menu.setSubMenu(subMenuList);    
			menu.setTarget("treeFrame");
			if(menuSet.contains(menu)){
				menu.setHasMenu(true);
			}else{
				menu.setHasMenu(false);
			}
		}
		return menuList;
	}
	@SuppressWarnings("unchecked")
	public  List<Menu> findAdminSubMenuForUser(String menuId){		 
        String  hql = "from Menu d where d.isUse='1' and  d.parentId = '" +menuId+"'   order by d.serial"; 
    	List<Menu> menuList = new ArrayList<Menu>();
		menuList = (List<Menu>)baseDao.find(hql); 
		return menuList;
	}
	@SuppressWarnings("unchecked")
	public Menu getMenuById(String menuId) throws Exception {
		String  hql = "from Menu d where   id = '" +menuId+"'   order by d.serial"; 
    	Menu menu =(Menu)baseDao.findSingle(hql);
		return menu;
	}
	
	/**
	 * 保存菜单图标 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Menu editicon(Menu pd) throws Exception {
		Menu menu=this.getMenuById(pd.getId());
		menu.setMenuIcon(pd.getMenuIcon());		
		menu=(Menu)this.update(menu); 
		return menu;
	}
	/**
	 * 删除菜单
	 * @param MENU_ID
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void deleteMenuById(String id) throws Exception {
		Menu menu=this.get(id);
		this.baseDao.delete(menu);
	}
	
	/**
	 * 通过菜单ID获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getMenuById(PageData pd) throws Exception {
		String  hql = "from Menu d where   id = '" +pd.get("MENU_ID")+"'   order by d.serial"; 
    	Menu menu =(Menu)baseDao.findSingle(hql);
		pd.put("entity", menu);
		return pd;
	}
	
	@SuppressWarnings("unchecked")
	public  List<Menu> listSubMenuByParentId(String menuId){		 
        String  hql = "from Menu d where   d.parentId = '" +menuId+"'   order by d.serial"; 
    	List<Menu> menuList = new ArrayList<Menu>();
		menuList = (List<Menu>)baseDao.find(hql); 
		return menuList;
	}
	
	public  List<Menu> findSubMenuForUser(String menuId,String userId){
		String sql=
			"select Function_page_id from Function_page_info m where exists (\n" +
			"  select 1 from role_fucntion_distribute rm where rm.Function_page_id=m.Function_page_id and exists (\n" + 
			"   select 1 from user_role_distribute ur where ur.role_id=rm.role_id and ur.user_id='"+userId+"'\n" + 
			" )\n" + 
			")";
        List list=this.jdbcHelper.SqlQuery(sql);
        StringBuffer sb=new StringBuffer();     
        for(int i=0;i<list.size();i++)
        {
        	Object[] data=(Object[])list.get(i);
        	if(i==0){
        		if(list.size()==1){
        			sb.append(" and d.id in ('"+data[0]+"')");
        		}else{
        			sb.append(" and d.id in ('"+data[0]+"',");
        		}
        	}else if(i==list.size()-1){
        		sb.append("'"+data[0]+"')");
        	}else{
        		sb.append("'"+data[0]+"',");
        	}
        }
        String   hql = "from Menu d where d.isUse='1' and  d.parentId = '" +menuId+"'  "+sb.toString()+" order by d.serial"; 
    	List<Menu> menuList = new ArrayList<Menu>();
		menuList = (List<Menu>)baseDao.find(hql); 
		return menuList;
	}

	/*
	 * 查找最高级菜单的第二级菜单
	 */
	public List<Menu> findChildMenuForUser(String menuId,String userId){
		Menu menu=new Menu();
		if(menuId.equals("")||menuId==null){ 
		}else{
			  menu=this.get(menuId);
		}
		List<Menu> menuList = new ArrayList<Menu>();
		String sql=
			"select id from Function_page_info m where exists (\n" +
			"  select 1 from sys_role_menu rm where rm.menu_id=m.id and exists (\n" + 
			"   select 1 from sys_user_role ur where ur.role_id=rm.role_id and ur.user_id='"+userId+"'\n" + 
			" )\n" + 
			")";
        List list=this.jdbcHelper.SqlQuery(sql);
        StringBuffer sb=new StringBuffer();
     
        for(int i=0;i<list.size();i++)
        {
        	Object[] data=(Object[])list.get(i);
        	if(i==0){
        		if(list.size()==1){
        			sb.append(" and d.id in ('"+data[0]+"')");
        		}else{
        			sb.append(" and d.id in ('"+data[0]+"',");
        		}
        	}else if(i==list.size()-1){
        		sb.append("'"+data[0]+"')");
        	}else{
        		sb.append("'"+data[0]+"',");
        	}
        }
        String hql="";
        if(menuId!=null&&!menuId.equals(""))
        	 hql = "from Menu d where d.isUse='1' and  d.casCode like '" + menu.getCasCode()+"___" + "'  "+sb.toString()+" order by d.serial";
        else
        	hql = "from Menu d where  d.isUse='1' and  length(d.casCode)=3  "+sb.toString()+" order by d.serial";
	  ///System.out.println("hql=="+hql);
		menuList = baseDao.find(hql);
		if(menuList ==null || menuList.size() == 0){
			hql = "from Menu d where d.casCode = '" + menu.getCasCode()+"'    "+sb.toString()+" order by d.serial";
			menuList = baseDao.find(hql);
		}
		return menuList;
	}
	

	public Integer getMaxSearial(){
		String hql="select max(serial+1) from Function_page_info ";
		return this.jdbcHelper.getInt(hql);
		
	}
}
