/**
 * <li>文件名：DeptManager.java
 * <li>说明：
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-10-31
 * <li>修改人： 
 * <li>修改日期：
 */
package com.pms.rcm.sys.manager;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.rcm.radar.manager.RadarUserInfoManager;
import com.pms.rcm.radar.vo.RadarUserInfo;
import com.pms.rcm.sys.vo.Dept;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
 

/**
 * <li>类型名称：
 * <li>说明：
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-10-31
 * <li>修改人： 
 * <li>修改日期：
 */
@Service("deptManager")
public class DeptManager extends BaseManager<Dept> {
	protected static final Log logger = LogFactory.getLog(DeptManager.class);
	@Resource(name="radarUserInfoManager")
	private RadarUserInfoManager radarUserInfoManager;
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
	public Dept getByCasCode(String casCode){
		String hql = "from Dept d where d.casCode='" + casCode + "'";
		return (Dept)baseDao.findSingle(hql);
	}

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> list(Page page)throws Exception{
		 String keywords=page.getPd().getString("keywords");
		 StringBuffer hql=new StringBuffer();
		 hql.append("from Dept where 1=1 ");
		 if(StrUtil.isNotEmpty(keywords)){
			 hql.append(" and (deptName like '%"+keywords+"%' or deptNo like '%"+keywords+"%')"); 
		 }
		 if(StrUtil.isNotEmpty(page.getPd().getString("DEPARTMENT_ID"))){
			 hql.append(" and parentId='"+page.getPd().getString("DEPARTMENT_ID")+"'");
		 } 
		 hql.append("order by casCode");
	    
		 return this.findPage(hql.toString(), page, true);
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> listAll(String parentId)throws Exception{
		 String hql="from Dept where parentId='"+parentId+"' order by casCode"; 
		 List<Dept> departmentList = this.find(hql);
			for(Dept depar : departmentList){
				depar.setTreeurl("department/list.do?DEPARTMENT_ID="+depar.getId());
				depar.setSubDepartment(this.listAll(depar.getId()));
				depar.setTarget("treeFrame");
			}
			return departmentList;
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> listAllForUser(String parentId)throws Exception{
		 String hql="from Dept where parentId='"+parentId+"' order by casCode"; 
		 List<Dept> departmentList = this.find(hql);
			for(Dept depar : departmentList){
				depar.setTreeurl("user/list.do?DEPARTMENT_ID="+depar.getId()); 
				depar.setSubDepartment(this.listAllForUser(depar.getId()));
				depar.setTarget("treeFrame"); 
			}
			
			
			return departmentList;
	}
	
	public List<Dept> listAllForArea(String areaID)throws Exception{
		 String hql="from RadarUserInfo where provinceId='"+areaID+"' "; 
		 List<RadarUserInfo> radarList = this.radarUserInfoManager.find(hql);
			List<Dept> dlist=new ArrayList<Dept>();
			for(RadarUserInfo radar : radarList){
				Dept dept1=new Dept();
				dept1.setDeptName(radar.getStationName());
				dept1.setParentId(areaID);  
				dept1.setTreeurl("user/listRadar.do?radarId="+radar.getId());  
				dept1.setTarget("treeFrame"); 
				dlist.add(dept1);
			} 
			return dlist;
	}
	
	
	public List<Dept> listAllForAreas(String areaID)throws Exception{
		 String hql="from RadarUserInfo where provinceId='"+areaID+"' "; 
		 List<RadarUserInfo> radarList = this.radarUserInfoManager.find(hql);
			List<Dept> dlist=new ArrayList<Dept>();
			for(RadarUserInfo radar : radarList){
				Dept dept1=new Dept();
				dept1.setDeptName(radar.getStationName());
				dept1.setParentId(areaID);  
				///dept1.setTreeurl("/html/radarUserInfo/radarUserInfo_list.html?provinceId="+radar);  
				dept1.setTarget("treeFrame"); 
				dlist.add(dept1);
			} 
			return dlist;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> listTreeForAdd(String parentId,Set<Dept> entityManagerDepts)throws Exception{
		 String hql="from Dept where parentId='"+parentId+"' order by casCode"; 
		 List<Dept> departmentList = this.find(hql);
			for(Dept depar : departmentList){
				depar.setTreeurl(""); 
				depar.setSubDepartment(this.listTreeForAdd(depar.getId(),entityManagerDepts));
				depar.setTarget("treeFrame"); 
				if(null!=entityManagerDepts){
					if(entityManagerDepts.contains(depar))
						depar.setHasDepartment(true);
				}
			}
			return departmentList;
	}
	
	/**机构树选择，叶子节点才有选择框
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public JSONArray listTreeForSelect(String parentCasCode)throws Exception{
//		List<Dept> custOrgList = (List<Dept>) dao.findForList("CustOrgMapper.listAllCustOrgByParentId", parentId);
		
		String hql="from Dept where casCode like '"+parentCasCode+"%' order by casCode"; 
		List<Dept> departmentList = this.find(hql);
		
		List<Dept> tempDepartmentList=new ArrayList<Dept>(departmentList);
		//JSONArray json = new JSONArray();
		//{ id:1, pId:0, name:"随意勾选 1", open:true},
		JSONArray jsonArray = new JSONArray();
		if(null!=departmentList && !departmentList.isEmpty()){
			
			for(Dept dept:departmentList){
				JSONObject jo = new JSONObject();
				jo.put("id", dept.getId());
				jo.put("pId", dept.getParentId());
				jo.put("name", dept.getDeptName());
				
				boolean nocheck=false;
				//循环tempCustOrgList，找是否有机构的code是custOrg机构code开头的，以此判断是否有子节点，叶子节点有选择框，否则没有选择框
				for (Iterator<Dept> iterator = tempDepartmentList.iterator(); iterator.hasNext();) {
					Dept tempDept = (Dept) iterator.next();
					if(!tempDept.getId().equals(dept.getId())){
						if(tempDept.getCasCode().startsWith(dept.getCasCode())){
							nocheck=true;
							tempDepartmentList.remove(dept);
							break;
						}
					}
				}
				jo.put("nocheck", nocheck);
				jo.put("open", false);
				
//				this.generatorJson(custOrg.getCUST_ORG_ID(),jsonArray);
				jsonArray.add(jo);
			}
		}
		return jsonArray;
	}
	
	/**
	 * 
	 * @param sysCode
	 * @return
	 */
	public Dept getBySysCode(String sysCode){
		String hql = "from Dept d where d.deptNo='" + sysCode + "'";
		return (Dept)baseDao.findSingle(hql);
	}
	
	public Dept getByDeptNoAndType(String deptNo, String deptType){
		String hql = "from Dept d where d.deptNo='" + deptNo + "' and d.deptType='" + deptType + "'";
		return (Dept)baseDao.findSingle(hql);
	}
	
	/**
	 * 
	 * <li>方法名：findBySysCodes
	 * <li>@param sysCodes
	 * <li>@return
	 * <li>返回类型：List<Dept>
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public List<Dept> findBySysCodes(String sysCodes){
		String[] codeArr = sysCodes.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append("from Dept d where (");
		for (int i = 0; i < codeArr.length; i++) {
			if(StrUtil.isNotEmpty(codeArr[i])){
				if(i != 0){
					sb.append(" or ");
				}
				sb.append(" d.sysCode='" + codeArr[i] + "' ");
			}
		}
		sb.append(")");
		
		return baseDao.find(sb.toString());
	}
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param dept
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean hasChildren(Dept parentDept){
		String casCode = "___";
		if(parentDept.getCasCode()!=null){
			casCode = parentDept.getCasCode() + casCode;
		}
		String hql = "from Dept d where d.casCode like '" + casCode + "'";
		return baseDao.isExist(hql);
	}
	
	/**
	 * 
	 * <li>方法名：hasChild
	 * <li>@param dept
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> findChildrenPage(String parentCasCode, Page page, boolean needSearchCount){
		String casCode = "___";
		if(parentCasCode!=null && ! "".equals(parentCasCode)){
			casCode = parentCasCode + casCode;
		}

		String hql = "from Dept d where d.casCode like '" + casCode + "' ";
		
		/*String orderStr = page.getOrderString();
		if(! StrUtil.isNotEmpty(orderStr)){
			orderStr = "ORDER BY d.deptType desc";
		}
		hql = hql + orderStr;
		*/
		if(needSearchCount == true){
			page.setTotalResult(this.baseDao.getCount(hql));
		}
		System.out.println("hql=="+hql);
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}
	
	/**
	 * 
	 * <li>方法名：findChildren
	 * <li>@param parentCasCode
	 * <li>@return
	 * <li>返回类型：List<Dept>
	 * <li>说明：不分页查找
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-26
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> findChildren(String parentCasCode){
		String casCode = "___";
		if(parentCasCode!=null && ! "".equals(parentCasCode) && ! "0".equals(parentCasCode)){//树顶层节点为0
			casCode = parentCasCode + casCode;
		}
		String hql = "from Dept d where d.casCode like '" + casCode + "' ORDER BY d.sysCode ";
		return baseDao.find(hql);
	}
	

	/**
	 * 
	 * <li>方法名：findAllDeptAndOther
	 * <li>@param casCode
	 * <li>@param findMode 动态数组，有"Role"表示加载角色、"User"表示加载用户
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<Dept>
	 * <li>说明：查找casCode下的所有机构,根据findMode决定是否加载机构下的角色、用户
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> findAllDeptAndOther(String casCode,String... findMode)throws BusinessException{
		List<Dept> deptList = baseDao.find("select d from Dept d where casCode like '"+casCode+"%' order by casCode");
		
		Map<String, Dept> deptMap = new HashMap<String, Dept>();
		for (Dept dept : deptList) {
			deptMap.put(dept.getId(), dept);
		}
		/*for (String mode : findMode) {
			if(mode.equals("Role")){//需要查找机构的角色
				List<Role> roleList = baseDao.find("select r from Role r,Dept d where r.deptId=d.id and d.casCode like '"+casCode+"%'");
				for (Role role : roleList) {
					deptMap.get(role.getDeptId()).addRole(role);
				}
			}
			else if(mode.equals("User")){//需要查找机构的用户
				List<User> userList = baseDao.find("select u from User u,Dept d where u.deptId=d.id and d.casCode like '"+casCode+"%'");
				for (User user : userList) {
					deptMap.get(user.getDeptId()).addUser(user);
				}
			}
		}
		*/
		return deptList;
	}
	/**
	 * 
	 * <li>方法名：findDeptAndOther
	 * <li>@param lowerCasCod
	 * <li>@param superCasCode
	 * <li>@param findMode 动态数组，有"Role"表示加载角色、"User"表示加载用户
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：Dept
	 * <li>说明：查找从下级到上级直接的所有机构,根据findMode决定是否加载机构下的角色、用户
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-12-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> findDeptAndOther(String lowerCasCod, String superCasCode, String... findMode) throws BusinessException{
		if(! lowerCasCod.startsWith(superCasCode)){
			throw new BusinessException("上级级联编码【" + superCasCode + "】与下级级联编码【" + lowerCasCod + "】不存在级联关系！");
		}
		
		StringBuffer deptCasCode = new StringBuffer();
		for (int i = superCasCode.length(); i <= lowerCasCod.length();) {			
			deptCasCode.append("'"+ lowerCasCod.substring(0,i) +"',");
			i = i+3;			
		}
		deptCasCode.deleteCharAt(deptCasCode.lastIndexOf(","));

		List<Dept> deptList = baseDao.find("select d from Dept d where casCode in("+deptCasCode.toString()+") order by sysCode");
		
		Map<String, Dept> deptMap = new HashMap<String, Dept>();
		for (Dept dept : deptList) {
			deptMap.put(dept.getId(), dept);
		}
		/*for (String mode : findMode) {
			if(mode.equals("Role")){//需要查找机构的角色
				List<Role> roleList = baseDao.find("select r from Role r,Dept d where r.deptId=d.id and d.casCode in("+deptCasCode.toString()+")");
				for (Role role : roleList) {
					deptMap.get(role.getDeptId()).addRole(role);
				}
			}
			else if(mode.equals("User")){//需要查找机构的用户
				List<User> userList = baseDao.find("select u from User u,Dept d where u.deptId=d.id and d.casCode in("+deptCasCode.toString()+")");
				for (User user : userList) {
					deptMap.get(user.getDeptId()).addUser(user);
				}
			}
		}
		*/
		return deptList;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Dept insert(Dept entity) throws BusinessException {
		
		if("1".equals(entity.getDeptType())){//不能加入多个总行
			String hql = "from Dept where deptType like '0'";
			if(baseDao.isExist(hql)){
				throw new BusinessException("总行已存在一个，不能添加！");
			}
		}
		String nextCasCode ;//= super.getNextCasCode(entity, null);
		String hql="from Dept d where d.parentId='"+entity.getParentId()+"' order by d.casCode DESC";
		List<Dept> deptList=this.baseDao.createQuery(hql).list();
		if(deptList!=null && !deptList.isEmpty()){
			Dept dept =deptList.get(0);
			String pre=dept.getCasCode().substring(0, dept.getCasCode().length()-3);
			String last=dept.getCasCode().substring(dept.getCasCode().length()-3,dept.getCasCode().length());
			int addedList=Integer.parseInt(last)+1;
			
			nextCasCode=pre+StrUtil.addZeroForNum(String.valueOf(addedList), 3);
		}else{
			Dept dept=this.get(entity.getParentId());
			nextCasCode=dept.getCasCode()+"001";
		}
		entity.setCasCode(nextCasCode);//设置机构的casCode

		return super.insert(entity);//同时验证
	}
	@Transactional(rollbackFor = { Exception.class })
	public  Dept update(Dept entity) throws BusinessException { 
		 return super.update(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	public  void delete(Object entity,String id) throws BusinessException {
		super.delete(entity, id);
	}
	/**
	 * 移动机构,需要将该机构的所有下属机构移动到新机构下
	 * @param entity
	 * @param newParentDept
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Dept updateMove(Dept entity, Dept newParentDept) throws BusinessException {
		if(entity.getParentCasCode().equals(newParentDept.getCasCode())){
			throw new BusinessException("机构【"+ entity.getDeptNo() +"】本身已在机构【"+ newParentDept.getDeptNo() +"】下,不需要移动！");
		}
		if(entity.getCasCode().equals(newParentDept.getCasCode())){
			throw new BusinessException("机构【"+ entity.getDeptNo() +"】不能移动到自己【"+ newParentDept.getDeptNo() +"】下,移动失败！");
		}
		if(newParentDept.getCasCode().startsWith(entity.getCasCode())){
			throw new BusinessException("机构【"+ entity.getDeptNo() +"】不能移动到自己的下属机构【"+ newParentDept.getDeptNo() +"】下,移动失败！");
		}
		
		String nextCasCode = super.getNextCasCode(newParentDept, null);

		String hql = "update Dept set casCode='" + nextCasCode + "' where id='" + entity.getId() + "'";
		this.baseDao.execute(hql);//修改自己
		hql = "update Dept set casCode='" + nextCasCode 
			+ "' + substring(casCode," + (entity.getCasCode().length() + 1) + ",len(casCode)-" + entity.getCasCode().length() + ")"
			+ " where id<>'" + entity.getId() + "' and casCode like '" + entity.getCasCode() + "%'";
		this.baseDao.execute(hql);//修改下级
		
		
		/**
		 * 业务数据更新 2012-02-10 廖迎春
		 */
		List<String> listArgs = new ArrayList<String>();
		listArgs.add(entity.getDeptNo());
		listArgs.add(nextCasCode);
		listArgs.add(entity.getCasCode());
		
		try {
//			System.out.println("机构调整："+entity.getDeptNo()+"-new:"+nextCasCode+"-old:"+entity.getCasCode()+"--------------");
			this.execProcedure("sp_changeOrg(?,?,?)", listArgs, 0);
//			System.out.println("机构调整成功："+entity.getDeptNo()+"-new:"+nextCasCode+"-old:"+entity.getCasCode()+"--------------");
		} catch (Exception e) {
//			System.out.println("机构调整失败："+entity.getDeptNo()+"-new:"+nextCasCode+"-old:"+entity.getCasCode()+"--------------");
			e.printStackTrace();
		}
		/**
		 * 业务数据更新 2012-02-10 廖迎春
		 */
		
		
		entity.setCasCode(nextCasCode);//设置机构新的casCode
		
//			#casCode# + substring(casCode,len(#oldCasCode#)+1,len(casCode)-len(#oldCasCode#))
//			WHERE casCode &lt;&gt; #casCode# AND casCode LIKE #oldCasCode#+'%'
		return entity;
	}

	/**
	 * 
	 * <li>方法名：checkDataRange
	 * <li>@param sysCodes
	 * <li>@return
	 * <li>返回类型：Dept,其中casCode属性获得通过验证的级联编码,remark属性获得未通过验证的出错提示,
	 * <li>说明：仅dwr,用于检查数据权限,验证系统编码是否是用户管理的范围
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public Dept checkDataRange(String sysCodes){
	/*	HttpSession session = WebContextFactory.get().getSession(); 
		User user = (User)session.getAttribute(LoginPurviewCheckFilter.USER_SESSION_NAME);
		Set<Dept> manageDeptSet = user.getManageDeptSet();
		
		return  this.checkDataRangeBySet(sysCodes, manageDeptSet);
		*/
		return null;
	}
	
	/**
	 * 
	 * <li>方法名：checkDataRange
	 * <li>@param sysCodes
	 * <li>@param manageDeptSet
	 * <li>@return
	 * <li>返回类型：Dept,其中casCode属性获得通过验证的级联编码,remark属性获得未通过验证的出错提示,
	 * <li>说明：验证系统编码是否是manageDeptSet中的范围
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public Dept checkDataRangeBySet(String sysCodes, Set<Dept> manageDeptSet){
		Dept reDept = new Dept();
		String casCode = "";
		List<Dept> operList = this.findBySysCodes(sysCodes);
		
		if(operList == null || operList.isEmpty()){//错误的级联编码
			reDept.setText("没有【" + sysCodes + "】的机构系统编码！");
		}
						
		List<String> findCodeList = new ArrayList<String>();
		boolean  purview;
		for (Dept operDept : operList) {
			purview = false;
			for (Dept dept : manageDeptSet) {
				if(operDept.getCasCode().startsWith(dept.getCasCode())){//有权限
					purview = true;
					break;
				}
			}
			if(purview == false){//无权限
				reDept.setText("你没有管理【" + operDept.getDeptNo() + "】机构的权限！");
			}
			findCodeList.add(operDept.getDeptNo());
			casCode += operDept.getCasCode()+ ",";
		}
		
		List<String> inputCodeList = new ArrayList<String>();
		inputCodeList.addAll(Arrays.asList(sysCodes.split(",")));
		inputCodeList.removeAll(findCodeList);
		
		String otherCode = "";		
		for (String code : inputCodeList) {
			if(StrUtil.isNotEmpty(code)){
				otherCode += code + ",";
			}
		}		
		if( StrUtil.isNotEmpty(otherCode)){
			reDept.setText( "没有【" + otherCode + "】的机构系统编码！");
		}

		if(! StrUtil.isNotEmpty(reDept.getText())){
			if(casCode.lastIndexOf(",") > 0){
				casCode = casCode.substring(0, casCode.lastIndexOf(","));
			}
			reDept.setCasCode(casCode);
		}
		
		return reDept;
	}
	
	@Override
	protected void checkUnique(Dept entity) throws BusinessException {
		List<Criterion> listCri = new ArrayList<Criterion>();
				
		listCri.add(Restrictions.ilike("sysCode", entity.getDeptNo()));
		
		if(baseDao.isNotUniqueCriterion(entity, listCri)){
			throw new BusinessException("机构系统编码【"+ entity.getDeptNo() +"】已存在，请注意不区分大小写！");
		}
		listCri.remove(0);//将机构代号验证规则删除
		listCri.add(Restrictions.like("deptType", entity.getDeptType()));
		listCri.add(Restrictions.ilike("deptNo", entity.getDeptNo()));
		if(baseDao.isNotUniqueCriterion(entity, listCri)){
			throw new BusinessException("同类机构的机构编号【"+ entity.getDeptNo() +"】已存在，请注意大小写！");
		}
		
		
		listCri.remove(1);//将机构代号验证规则删除
		listCri.remove(0);//将机构代号验证规则删除
		listCri.add(Restrictions.like("casCode", entity.getParentCasCode()+"___"));
		listCri.add(Restrictions.ilike("deptName", entity.getDeptName()));
		
		if(baseDao.isNotUniqueCriterion(entity, listCri)){
			throw new BusinessException("同一级机构名称【"+ entity.getDeptName() +"】已存在，请注意大小写！");
		}
				
	}

	/**删除检查***/
	@Override
	public void checkDelete(Serializable id) throws BusinessException {
		Dept dept = this.get(id);
		String hql = "select d from Dept d where casCode like '"+dept.getCasCode()+"___%'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】存在下级机构，不能删除！");
		}
		
//		hql = "select w from WebService w where deptId = '"+dept.getId()+"'";
//		if(baseDao.isExist(hql)){
//			throw new BusinessException("【" + dept.getDeptName() + "】已被设置了影像服务器，不能删除！");
//		}
		
		hql = "select r from Role r where deptId = '"+dept.getId()+"'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】存在角色，不能删除！");
		}
		
		hql = "select u from User u where deptId = '"+dept.getId()+"'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】存在用户，不能删除！");
		}		
		
		hql = "select u from User u join u.manageDeptSet d where d.id = '"+dept.getId()+"'";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】已被设置为用户管理机构，不能删除！");
		}
		
//		if(DeptAction.DEPTTYP_BR.equals(dept.getDeptType())){//网点
//			hql = "select d from ChkData d where brCasCode = '"+dept.getCasCode()+"'";
//			if(baseDao.isExist(hql)){
//				throw new BusinessException("【" + dept.getDeptName() + "】网点有质检数据，不能删除！");
//			}
//			hql = "select d from MonDataSum d where brCasCode = '"+dept.getCasCode()+"'";
//			if(baseDao.isExist(hql)){
//				throw new BusinessException("【" + dept.getDeptName() + "】网点有监测数据，不能删除！");
//			}
//		}
//		
//		hql = "select p from ParamCoinLimit p where p.deptId = '"+dept.getId()+"'";
//		if(baseDao.isExist(hql)){
//			throw new BusinessException("【" + dept.getDeptName() + "】存在库限，不能删除！");
//		}
		hql = "select d from Device d where d.deptId = '"+dept.getId()+"' ";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】存在设备，不能删除！");
		}
		hql = "select c from Chapter c where c.deptId = '"+dept.getId()+"' ";
		if(baseDao.isExist(hql)){
			throw new BusinessException("【" + dept.getDeptName() + "】存在印章，不能删除！");
		}
	}

	 
	
	
	/**<li>将list<String[]>的内容构造成List<Dept>
	 * @param list
	 * @throws Exception
	 */
	public List<Dept> constructDept(List list) {

		List<Dept> deptList = new ArrayList<Dept>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			Dept dept = new Dept();
			dept.setCasCode(strings[1]);
			dept.setDeptType(strings[2]);
			dept.setDeptNo(strings[3]); 
			dept.setDeptName(strings[5]); 
			dept.setText(strings[9]);
			deptList.add(dept);
		}
		return deptList;
	}
	
	/**<li>调用存储过程，存储过程无返回值
	 * @param procName
	 * @param args
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	public String execProcedure(String procName,List<String> args,int outputArgsCount)throws Exception{
		return this.baseDao.execProcedure2(procName, args,outputArgsCount);
	}
	@Transactional(rollbackFor = { Exception.class })
	public String findAllCount(){
		String sql="select count(*) from sys_dept";
		SQLQuery query = this.baseDao.getSessionForPro().createSQLQuery(sql);
		return String.valueOf((Integer)query.list().get(0));
	}
	
	public List<Dept> findPageList(Dept dept, Page page,boolean needSearchCount)throws BusinessException{
		StringBuffer sb = new StringBuffer();	
		sb.append("from Dept d where 1=1");
		if(StrUtil.isNotEmpty(dept.getDeptNo())){
			sb.append(" and deptNo like '%"+dept.getDeptNo()+"%'"); 
		}
		if(StrUtil.isNotEmpty(dept.getDeptName())){
			sb.append(" and deptName like '%"+dept.getDeptName()+"%'"); 
		}
       String hql=sb.toString();
       
       return super.findPage(hql, page, needSearchCount);
	}
	
	public String deleteCheck(String id) throws BusinessException{
		Dept dept = this.get(id);
		String hql = "select d from Dept d where casCode like '"+dept.getCasCode()+"___%'";
		if(baseDao.isExist(hql)){
			return "【" + dept.getDeptName() + "】存在下级机构，不能删除！";
		} 		
		  
		hql = "select u from User u where deptId = '"+dept.getId()+"'";
		if(baseDao.isExist(hql)){
			return "【" + dept.getDeptName() + "】存在用户，不能删除！";
		}		
		
		hql = "select u from User u join u.manageDeptSet d where d.id = '"+dept.getId()+"'";
		if(baseDao.isExist(hql)){
			return "【" + dept.getDeptName() + "】已被设置为用户管理机构，不能删除！";
		}  
		return "canDelete";
	}
}
