package com.pms.base.controller;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.pms.base.BaseDao;
import com.pms.base.BaseManager;
import com.pms.base.util.Const;
import com.pms.base.util.GenericsUtils;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.PageData;
import com.pms.base.util.SpringContextUtil;
import com.pms.base.util.UuidUtil;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.SysButton;
import com.pms.rcm.sys.vo.User;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray; 
 
 

/**
 * 修改时间：2015-12-11
 */
public abstract class BaseController <T,M extends BaseManager<T>>{
	public static final Logger logger = LoggerFactory.getLogger(BaseController.class); 
	private static final long serialVersionUID = 6357869213649815390L;
	 
	
	protected T entity;              //action 所操作的主导业务实体对象
	protected T entitySearch;        // 用于保存传递查询的JavaBean	 
	protected M baseManager;             // action 所依赖的服务对象
	@Value("${logFolder}")
	private String logFolder;  
	@SuppressWarnings("unchecked")
	protected Object fillCollectionField(Object entity,String fieldName, String paramName) throws Exception {
		String[] strParams = getRequest().getParameterValues(paramName);
		if(strParams == null || strParams.length == 0){//没有填充的数据
			return entity;
		}
		
		List<String> listId = new ArrayList<String>();	
		for (String param : strParams) {//处理数据
			if("".endsWith(param)){
				continue;
			}
			else{
				String[] ids = param.split(",");//解析逗号分隔的参数
				for (String id : ids) {
					if(! id.equals("")){
						listId.add(id); 					   
					}
				}
			}
		}	
		
		if(listId.size() == 0){//没有数据
			return entity;
		}
		
		String[] allIds = new String[listId.size()];
		allIds = listId.toArray(allIds);
				
		Field field = entity.getClass().getDeclaredField(fieldName);//获得属性
		Type fieldType = field.getGenericType();
		
		if (fieldType instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) fieldType;
			Type[] types = pType.getActualTypeArguments();//获得参数类型
			if(types.length > 0){
				String className = types[0].toString().substring(
						types[0].toString().indexOf(" "),
						types[0].toString().length()).trim();//前面是Class，后面是类名称，中间用空格间隔
				Class cls = Class.forName(className);
			
				List<Object> fillObjList = new ArrayList<Object>();
				BaseDao daoUtils = (BaseDao)SpringContextUtil.getBean("baseDao");
				fillObjList = daoUtils.find(cls, allIds);
				
				BeanWrapper bw = new BeanWrapperImpl(entity);
				Class collCls = bw.getPropertyType(fieldName);
				if(collCls.getName() .equals(Set.class.getName())){//是Set属性
					Set<Object> fillObjSet = new TreeSet<Object>();
					 fillObjSet.addAll(fillObjList); 
					bw.setPropertyValue(fieldName, fillObjSet);
					 //System.out.println("yyyy:"+fillObjSet.size());
				}
				else{
					bw.setPropertyValue(fieldName, fillObjList);
					
				}
			}
		} 
		return entity;
	}
	
		protected Dept getAssignDept(){		
		
		User user = this.getLoginUser();
		Dept assignDept = user.getAssignDept();
		return assignDept;
	}	
 

	/**
	    * 
	    * <li>说明：构造方法，创建entity实体，并获取entity的具体类型；
	    * <li>设置默认的查询，编辑页面名称；并设置方法返回页面影射表的内容。
	    * <li>创建人：曾明辉
	    * <li>创建日期：2008-10-14
	    * <li>修改人： 
	    * <li>修改日期：
	    */
    @SuppressWarnings("unchecked")
	public BaseController(){
    	try {    		
			this.entity = (T) GenericsUtils.getSuperClassGenricType(getClass()).newInstance();
			this.entitySearch = (T)GenericsUtils.getSuperClassGenricType(getClass()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String managerName = StringUtils.uncapitalize(GenericsUtils.getSuperClassGenricType(getClass(),1).getSimpleName()); 
		//System.out.println("managerName=="+managerName);
		this.baseManager = (M)SpringContextUtil.getBean(managerName);
		
		
    }
    
    public boolean isUpdate(Object entity){
		boolean isUpdate = true;
		if(entity == null){
			return false;
		}
		BeanWrapper bw = new BeanWrapperImpl(entity);
		Object id = bw.getPropertyValue("id");
		if(id == null || "".equals(id.toString().trim())){//没有id
			isUpdate = false;
		}
		else{
			isUpdate = true;			
		}
		
		return isUpdate;
	}
    
    protected User getLoginUser(){ 
		Object obj = getSession().getAttribute(Const.SESSION_USER);
		if(obj != null){
			return (User)obj;
		}
		else{ 
			return null;
		}
	}
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
 
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	public Session getSession(){
		Session session = Jurisdiction.getSession();
		return session;
	}
	public HttpServletResponse getResponse(){
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/getMenuFun")
	@ApiOperation(value="功能点信息", notes="根据url的menuid来获取功能点信息",httpMethod = "GET")
	public Object getMenuFun(@RequestParam String menuId) throws Exception{  
		User user=this.getLoginUser();
		List<SysButton> selModuleFunMethod=new ArrayList<SysButton>();
		if(user.isAdmin()){
			String hql = "from SysButton m where   m.menuId='"+menuId+"' ";
			selModuleFunMethod = (List<SysButton>)this.baseManager.find(hql);//查找用户配置的功能，只查功能点
		}else{
			String hql = "select distinct m from User u join u.roleSet r join r.sysOperationSet m where   m.menuId='"+menuId+"'  and u.id='" + user.getId() + "'";
			selModuleFunMethod = (List<SysButton>)this.baseManager.find(hql);//查找用户配置的功能，只查功能点
		}
		 
		return JSONArray.fromObject(selModuleFunMethod);
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
	binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	protected static String getExceptionAll(Throwable t) {
		Throwable next = t.getCause();
		if (next == null) {
				return t.getLocalizedMessage();
			} else {
				return getExceptionAll(next);
			} 
		}

	 
}
