package com.pms.base;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

import com.pms.base.common.BusinessException;
import com.pms.base.util.GenericsUtils;
import com.pms.base.util.Page; 
 

/**
 * 
 * <li>类型名称：
 * <li>说明：普通服务类的基类，该基类提供了增删改查业务方法的基本实现；本类主要调用daoUtils对象进行数据库操作
 * <li>要继承基类必须提供T，S两个范型参数，T指明manger操作的实体类，S指明了manager查询时使用的SearchBean类型
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-10-14
 * <li>修改人： 
 * <li>修改日期：
 */
@Repository("baseManager")
public abstract class BaseManager<T> {
	static protected String DEFAULT_IS_USE = "isUse";	// 默认启用弃用属性名
	static protected String DEFAULT_IS_USE_START_UP = "1";	// 启用选项值
	static protected String DEFAULT_IS_USE_GIVE_UP = "0";	// 弃用选项值
	@Resource(name = "baseDao")
	protected BaseDao baseDao;	    // 数据库操作工具
	protected Class<T> entityClass; // DAO所管理的Entity类型.  
	@Resource(name = "jdbcHelper")
	protected JdbcHelper jdbcHelper; 
 
	/**
	 * 
	 * <li>说明：根据子类定义时传入的实体类参数设置entityClass
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public BaseManager() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass()); 
	}
	
	/**
	 * 
	 * <li>方法名：load
	 * <li>@param id 实体身份标示
	 * <li>@return 返回范型指定类型的实体
	 * <li>返回类型：T
	 * <li>说明：根据制定的实体身份标示获取实体。如果缓存中不存在，从数据库获取实体；如果存在则直接从缓存中获取。
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public T load(Serializable id) throws BusinessException{
		return (T) this.baseDao.load(id,entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws BusinessException{
		return (List<T>) this.baseDao.findAll(entityClass);
	}
	
	
	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id 实体身份标示
	 * <li>@return 返回范型指定类型的实体
	 * <li>返回类型：T
	 * <li>说明：根据制定的实体身份标示从数据库获取实体。
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id)throws BusinessException{
		return (T)baseDao.get(id,entityClass);
	}
	@SuppressWarnings("unchecked")
	public T get(Integer id)throws BusinessException{
		return (T)baseDao.get(id,entityClass);
	}
	
 
	 
	
	/**
	 * 
	 * <li>方法名：find
	 * <li>@param hql
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql)throws BusinessException{
		return baseDao.find(hql);
	}
	
	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param hql 查询HQL语句
	 * <li>@param page EC翻页
	 * <li>@param needSearchCount 需不需要查询数量
	 * <li>@return 返回一个查询结果集合
	 * <li>返回类型：List
	 * <li>说明：根据HQL语句进行翻页查询；
	 * <li>如果默认的实现方式能满足程序需要，在子类中可以不写任何代码，如果默认的实现不能满足程序的需要
	 * <li>请在子类中覆盖此方法
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<T> findPage(String hql, Page page, boolean needSearchCount)throws BusinessException{
		//System.out.println("findPage:"+hql);
		if(needSearchCount == true){
			//page.setTotalRows(this.baseDao.getCount(hql));
			page.setTotalResult(this.baseDao.getCount(hql));
		}else{
			page.setTotalResult(this.baseDao.getCount(hql));
			page.setCurrentPage(1);
			page.setShowCount(page.getTotalResult());
		}
		if(hql.indexOf("order")==-1){
			if(null!=page.getSidx() && !page.getSidx().equals("")){
				hql=hql+" order by "+page.getSidx()+" "+page.getSord();
			}
		}
		
		//return this.baseDao.findPage(hql, page.getCurrentPage(), page.getDisplayRowsNum());
		return this.baseDao.findPage(hql, page.getCurrentPage(), page.getShowCount());
	}
	
	 

	 
	
	
	
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity 范型指定类型的实体。
	 * <li>@return 返回范型制定类型的实体对象。
	 * <li>@throws BusinessException
	 * <li>返回类型：T
	 * <li>说明：新增实体。
	 * <li>创建人：曾锤鑫
	 * <li>创建日期：2008-11-6
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public  T insert(T entity) throws BusinessException { 
		baseDao.insert(entity); 
		return entity;
	}
	
	/**
	 * 
	 * <li>方法名：update
	 * <li>@param entity 范型指定类型的实体。
	 * <li>@return 返回范型制定类型的实体对象。
	 * <li>@throws BusinessException
	 * <li>返回类型：T
	 * <li>说明：更新实体。
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-6
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public  T update(T entity) throws BusinessException { 
		baseDao.update(entity);
		
		return entity;
	}
	
	public  void delete(Object entity,String id) throws BusinessException {
		entity=this.baseDao.get(id, entity.getClass());
		this.baseDao.delete(entity);
	}
	abstract protected void checkDelete(Serializable id) throws BusinessException;

	abstract protected void checkUnique(T entity) throws BusinessException;
	 
	/**
	 * 
	 * <li>方法名：getNextCasCode
	 * <li>@param entity
	 * <li>@param criterions 附加筛选条件，当一个表中允许相同的级联编码时需要设置
	 * <li>@return
	 * <li>返回类型：String
 	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	protected String getNextCasCode(Object entity, Criterion... criterions){
		String codeProName = "casCode";
		BeanWrapper bwEntity = new BeanWrapperImpl(entity);
		String entityCode =  bwEntity.getPropertyValue(codeProName).toString();
		
		String parentCasCode = entityCode;
		if(parentCasCode.indexOf("___") > 0){
			parentCasCode = parentCasCode.substring(0, entityCode.length()-3);
		}
		
		String maxCasCode = (String)this.getMaxProperty(entity, "casCode", criterions);
		if(maxCasCode == null){
			entityCode = parentCasCode + "001";//默认从001开始
		}
		else{
			String oldMax = maxCasCode.substring(maxCasCode.length()-3);
			int intNext = Integer.parseInt(oldMax) + 1;//下一个编码数字
			String strNext =  String.valueOf(intNext);
			int length = String.valueOf(intNext).toCharArray().length;//字符位数
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 3-length; i++) {//补零
				sb.append("0");
			}
			sb.append(strNext);
			
			entityCode = parentCasCode + sb.toString();
		}
		return entityCode;
	}
	public Object getMaxProperty(Object entity, String propertyName, Criterion... criterions){
		return baseDao.getMaxProperty(entity, propertyName, criterions);
		
	}
	 
	
}
