package com.pms.base;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.pms.base.common.DaoException; 
 
 
 
@Repository(value = "baseDao") 
public   class BaseDao {
	private Logger logger = Logger.getLogger(getClass());
	@PersistenceContext
    private EntityManager entityManager;  
	/**
	 * 
	 * <li>方法名：load
	 * <li>@param id 身份标示对象。
	 * <li>@param entityClass 实体类型。
	 * <li>@return 实体对象。
	 * <li>返回类型：Object
	 * <li>说明：根据ID和实体类型获取实体对象；如果缓存中有这个实体对象就不到数据库中去查找。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */ 
	public Object load(Serializable id, Class entityClass) {
		/*String hql = "from "+entityClass.getClass().getName()+" where id='"+id+"'"; 
		logger.info("load="+hql);
		javax.persistence.Query query=this.entityManager.createQuery(hql);//此时使用的是hql语句   
        return query.getSingleResult();  */
		return this.getSession().load(entityClass, id);
	}
	
	 
	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id  实体身份标示对象
	 * <li>@param cls 实体类型
	 * <li>@return 实体对象
	 * <li>返回类型：Object
	 * <li>说明：根据ID和实体类型从数据库中获取实体对象。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public Object get(Serializable id, Class cls) {
		/*String hql = "from "+cls.getClass().getName()+" where id='"+id+"'"; 
		logger.info("get="+hql);
		javax.persistence.Query query=this.entityManager.createQuery(hql);//此时使用的是hql语句   
        return query.getSingleResult();  
        
*/
		 return this.getSession().get(cls, id);

	}
	@SuppressWarnings("unchecked")
	public Object get(Integer id, Class cls) {
		/*String hql = "from "+cls.getClass().getName()+" where id='"+id+"'"; 
		logger.info("get="+hql);
		javax.persistence.Query query=this.entityManager.createQuery(hql);//此时使用的是hql语句   
        return query.getSingleResult();  
        
*/
		 return this.getSession().get(cls, id);

	}
 

	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity  实体对象
	 * <li>@return 实体对象
	 * <li>返回类型：Object
	 * <li>说明：向数据库中插入一个新的实体对象。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public Object insert(Object entity) {
		this.getSession().save(entity);
		return entity;
	}

	/**
	 * 
	 * <li>方法名：update
	 * <li>@param entity  实体对象
	 * <li>@return 实体对象
	 * <li>返回类型：Object
	 * <li>说明：更新数据库中的实体对象。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public Object update(Object entity) {
		// System.out.println("ceshi:"+entity.getClass().getName());
//		this.entityManager.
//		 this.entityManager.persist(entity);
		this.entityManager.merge(entity);
//		 this.getSession().update(entity);
		return entity;
	}

	public void delete(Object cls){
		 this.getSession().delete(cls);
	}
	 
 

	

	/**
	 * 
	 * <li>方法名：find
	 * <li>@param cls  要查找的类
	 * <li>@param ids  要查找的对象ID动态数组
	 * <li>@return
	 * <li>返回类型：List
	 * <li>说明：
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-17
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List find(Class cls, String... ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("from " + cls.getName() + " obj where obj.id in('',");
		logger.info("classNameqxxxxxx:"+cls.getName());
		for (String id : ids) {
			if ("".equals(id)) {
				continue;
			} else {
				sb.append("'" + id + "',");
			}
		}
		if (sb.lastIndexOf(",") == sb.length() - 1) {// 删除最后一个逗号
			sb.deleteCharAt(sb.lastIndexOf(","));
		}

		sb.append(")");

		return this.find(sb.toString());
	}

	@SuppressWarnings("unchecked")
	public List findAll(Class cls) {
		StringBuffer sb = new StringBuffer();
		sb.append("from " + cls.getName());
		logger.info("classNameqxxxxxx:"+cls.getName());  
		return this.find(sb.toString());
	}

	/**
	 * 
	 * <li>方法名：find
	 * <li>@param queryString  要执行的hql语句。
	 * <li>@param params  hql语句中需要的参数集合。
	 * <li>@return 返回查询结果集合。
	 * <li>返回类型：List
	 * <li>说明：执行带参数的hql语句，并返回查询结果集合；查询结果不做后台分页。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString, Object... params) {
		javax.persistence.Query query = this.entityManager.createQuery(queryString);
         if (params != null) {
				for (int i = 0; i < params.length; ++i) {
					query.setParameter(i, params[i]);
				}
		}
        return query.getResultList(); 
	} 
	
 
	
	/**
     * 
     * <li>方法名：findSingle
     * <li>@param queryString 要执行的hql语句。
     * <li>@param params
     * <li>@return
     * <li>返回类型：Object
     * <li>说明：
     * <li>创建人：zb
     * <li>创建日期：2018-3-1
     * <li>修改人： 
     * <li>修改日期：
     */
    @SuppressWarnings("unchecked")
	public Object findSingle(String queryString, Object ... params){
    	List list = findPage(queryString, params,1,1);
    	if(list != null && list.size()>0)
    		return list.get(0);
    	
    	return null;
    }
    
	 
	
	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param queryString  要执行的hql语句。
	 * <li>@param pageNo  要查询的分页记录的页码。
	 * <li>@param pageSize 每页显示记录的条数。
	 * <li>@return 返回查询结果集合。
	 * <li>返回类型：List
	 * <li>说明：执行hql语句，查询指定分页的数据。后台分页。
	 * <li>创建人：zb
	 * <li>创建日期：2018-3-1
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List findPage(final String queryString, final int pageNo,
			final int pageSize) {
		return findPage(queryString, null, pageNo, pageSize);
	}
	
	 

	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param queryString  要执行的hql语句。
	 * <li>@param params hql语句中的所有参数的集合。
	 * <li>@param pageNo  要查询的分页记录的页码。
	 * <li>@param pageSize 每页显示记录的条数。
	 * <li>@return 返回查询结果集合。
	 * <li>返回类型：List
	 * <li>说明：执行带参数的hql语句，查询指定分页的数据。后台分页。
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List findPage(final String queryString, final Object[] params,
			final int pageNo, final int pageSize) {
		      try{
		    	 Query query = getSession().createQuery(queryString);
	          
	            if (params != null) {
					for (int i = 0; i < params.length; ++i) {
						query.setParameter(i, params[i]);
					}
				}
	         //   System.out.println("pageNo=="+pageNo);
	         //   System.out.println("pageSize=="+pageSize);
	            query.setFirstResult(pageSize * (pageNo - 1));
	            query.setMaxResults(pageSize);	            
	            return query.list();
	        }catch(RuntimeException re){
	            throw re;
	        }	        
	
	}

	@SuppressWarnings("unchecked")
	public int getCount(final String queryString) {
		Assert.hasText(queryString);
		final String countQueryString = " select count (*) "
			+ removeSelect(removeOrders(queryString));		
	//System.out.println("countQueryString====="+countQueryString);
	Query q = this.getSession().createQuery(countQueryString); 
	Object c = (Object) q.iterate().next();		 
	if (c instanceof Integer)
		return ((Integer) c).intValue();
	else if (c instanceof Long)
		return ((Long) c).intValue();
	else 
		return (int) Double.parseDouble(c.toString());
	}
	public Session getSession(){
		HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        Session session = hEntityManager.getSession();
        return session;
	} 
	public Class getEntityClass(Object entity) {
		if (sessionFactory.getClassMetadata(entity.getClass()) == null) {
			return entity.getClass().getSuperclass();
		} else {
			return entity.getClass();
		}
	}
	public int getCount(Object exampleEntity) { 
		Criteria criteria = getSession().createCriteria(getEntityClass(exampleEntity)).add(Example.create(exampleEntity).enableLike(MatchMode.ANYWHERE));
		Assert.notNull(criteria);
		
		// 执行查询
		int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();			
		return totalCount;
	}

	/**
	 * 
	 * <li>方法名：buildHql
	 * <li>@param entity  持久化模板对象
	 * <li>@param alias  对象别名，与附加HQL语句中应一致
	 * <li>@param addHql  附加HQL查询条件语句，必须有where关键字
	 * <li>@return hql语句
	 * <li>返回类型：String
	 * <li>说明：通过查询模板对象的持久化字符及数字字段做为条件与附加HQL语句一起组成HQL
	 * <li>注意：字符默认按模糊条件查询，数字默认进行精确查询
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-20
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public String buildHql(final Object example, final String alias,
			final String addHql) throws DaoException {

		int indexWhere = addHql.toLowerCase().indexOf("where ");

		if (indexWhere < 0) {
			throw new DaoException("附加HQL【" + addHql + "】必须包含'where '关键字！");
		}

		StringBuffer sbHql = new StringBuffer();
		sbHql.append("select " + alias + " from ");// 返回别名
		sbHql.append(example.getClass().getSimpleName());
		sbHql.append(" " + alias);// 定义HQL中的别名

		String beforeWhere = addHql.substring(0, indexWhere).trim();
		String afterWhere = addHql.substring(indexWhere + 6).trim();
		if (!"".equals(beforeWhere)) {// 需要其它表
			sbHql.append(",");
			sbHql.append(beforeWhere);
		}
		sbHql.append(" where 1=1");// 添加条件
		sbHql.append(getSearchCondHql(example, alias));// 模板条件
		if (!"".equals(afterWhere)) {
			sbHql.append(" and " + afterWhere);// 附加条件
		}

		return sbHql.toString();
	}

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 
	 * <li>方法名：getSearchCondHql
	 * <li>@param example  持久化模板对象
	 * <li>@param alias  对象别名
	 * <li>返回类型：String
	 * <li>说明：获取持久化对象查询条件HQL，排除了非持久化字段及非空或空字符情况，
	 * <li> 持久化字段仅针对字符及数字，字符默认按模糊条件查询，数字默认进行精确查询
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-20
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public String getSearchCondHql(final Object example, final String alias) {
		StringBuffer sbHql = new StringBuffer();
		BeanWrapper bw = new BeanWrapperImpl(example);
		ClassMetadata metaData = sessionFactory.getClassMetadata(
				example.getClass());
		String[] proNames = metaData.getPropertyNames();
		for (String name : proNames) {
			Object value = bw.getPropertyValue(name);
			Object v = metaData
					.getPropertyValue(example, name);
			//System.out.println(value + "        " + v);
			if (value != null && !"".endsWith(value.toString()) && ! (value instanceof Set)) {
				sbHql.append(" and ");
				sbHql.append(alias + ".");
				sbHql.append(name);
				if (value instanceof String || value instanceof Character) {// 是字符串的采用模糊查询
					sbHql.append(" like '%" + value + "%'");
				} else if (value instanceof Integer || value instanceof Byte
						|| value instanceof Short || value instanceof Long
						|| value instanceof Float || value instanceof Double
						|| value instanceof Number) {
					sbHql.append(" =" + value + "");
				}
			}
		}
		return sbHql.toString();
	} 
  
 
	
	/**
	 * 
	 * <li>方法名：removeSelect
	 * <li>@param hql   hql语句内容。
	 * <li>@return 返回剔出掉from 之前的所有内容的hql语句。
	 * <li>返回类型：String
	 * <li>说明：去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 
	 * <li>方法名：removeOrders
	 * <li>@param hql hql语句内容。
	 * <li>@return 返回剔出掉排序内容的hql语句。
	 * <li>返回类型：String
	 * <li>说明：去除hql的orderby 子句，用于pagedQuery.
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	private String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	 
	
	public boolean isExist(final String queryString){
		Assert.hasText(queryString);
		// Count查询
		String countQueryString = removeSelect(removeOrders(queryString));
		return findSingle(countQueryString) != null ? true : false;
	}
	public boolean isNotUniqueCriterion(Object entity,
			List<Criterion> criterions) {

		Criteria criteria = getSession().createCriteria(getEntityClass(entity))
				.setProjection(Projections.rowCount());
		Long result = null;
		for (Criterion crit : criterions) {// 加入定制条件
			criteria.add(crit);
		}

		String idName = getIdName(entity);
		Object id = getId(entity);

		if (id != null && !id.toString().equals("")) {// 新增还是修改
			criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		}

		result =(Long)criteria.uniqueResult();

		return result > 0;
	}
	private String getIdName(Object obj) {
		return sessionFactory.getClassMetadata(obj.getClass())
				.getIdentifierPropertyName();
	}
	public Serializable getId(Object obj) {
		Serializable id = null;

		String idName = this.getIdName(obj);
		BeanWrapper bw = new BeanWrapperImpl(obj);
		id = (Serializable) bw.getPropertyValue(idName);

		return id;
	}	
	
	public Session getSessionForPro(){
		return this.getSession();
	}
	public boolean isNotUnique(Object entity, List<String> propertyName) {
		List<Criterion> listCri = new ArrayList<Criterion>();

		BeanWrapper bw = new BeanWrapperImpl(entity);

		for (String name : propertyName) {
			Object value = bw.getPropertyValue(name);
			if (value instanceof String) {// 字符类型忽略大小写
				listCri.add(Restrictions.like(name, value));
			} else {
				listCri.add(Restrictions.eq(name, value));
			}
		}

		return isNotUniqueCriterion(entity, listCri);
	}

	public void initialize(Object customer){
		 //Hibernate.initialize(customer);  
	}
	public int execute(String hql, Object... values) {
		return this.createQuery(hql, values).executeUpdate();
	}
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if (values == null || values.length == 0)
			return query;

		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	public String execProcedure(String procName,List<String> args,int outputArgsCount)throws Exception{
		String procedure = "{call "+ procName +" }";
		CallableStatement cstmt = null;
		Connection con = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		int j=0;
		String resultSign="0";
		try {
			cstmt = con.prepareCall(procedure);
			
			if (null != args && !args.isEmpty()) {
				for (int i=0; i < args.size(); i++) {
					cstmt.setString(i+1, args.get(i));
					j=i+1;
				}
			}
			for (int i = 0; i < outputArgsCount; i++) {
				cstmt.registerOutParameter(j+1+i, Types.VARCHAR);
			}
			
			cstmt.executeUpdate();
//			for (int i = 0; i < outputArgsCount; i++) {
//				System.out.println("存储过程执行结果："+ cstmt.getString(j+1+i));
//			}
			resultSign = cstmt.getString(j+outputArgsCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				if(cstmt != null){
					cstmt.close();
				}
				if(con != null){
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return resultSign;
	}
	@SuppressWarnings("unchecked")
	public Object findSingleByEntity(Object exampleEntity) {
		Criteria criteria = getSession().createCriteria(
				getEntityClass(exampleEntity)).add(
				Example.create(exampleEntity).enableLike(MatchMode.ANYWHERE));
		Assert.notNull(criteria);

		List list = criteria.setMaxResults(1).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	public String execProcedure2(String procName,List<String> args,int outputArgsCount)throws Exception{
		String procedure = "{call "+ procName +" }";
		CallableStatement cstmt = null;
		Connection con = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		int j=0;
		String resultSign="0";
		try {
			cstmt = con.prepareCall(procedure);
			
			if (null != args && !args.isEmpty()) {
				for (int i=0; i < args.size(); i++) {
					cstmt.setString(i+1, args.get(i));
					j=i+1;
				}
			}
			for (int i = 0; i < outputArgsCount; i++) {
				cstmt.registerOutParameter(j+1+i, Types.VARCHAR);
			}
			
			cstmt.executeUpdate();
//			for (int i = 0; i < outputArgsCount; i++) {
//				System.out.println("存储过程执行结果："+ cstmt.getString(j+1+i));
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				if(cstmt != null){
					cstmt.close();
				}
				if(con != null){
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return resultSign;
	}
	@SuppressWarnings("unchecked")
	public Object getMaxProperty(Object entity, String propertyName,
			Criterion... criterions) {
		String codeProName = "casCode";
		BeanWrapper bwEntity = new BeanWrapperImpl(entity);
		String entityCode = "";
		try{
			if (bwEntity.getPropertyValue(codeProName) != null) {
				entityCode = bwEntity.getPropertyValue(codeProName).toString();
			}
		}catch(NotReadablePropertyException e){
			logger.warn("-----bwEntity.getPropertyValue(codeProName)："+codeProName+"不存在。"+e.getMessage());
		}
		if (entityCode.length() < 3) {// 处理树顶层id为"0"的情况
			entityCode = "";
		}
		if (!entityCode.equals("") && !entityCode.endsWith("___")) {// 查询下级条件
			entityCode += "___";
			bwEntity.setPropertyValue(codeProName, entityCode);
		}
		Criteria criteria = getSession().createCriteria(getEntityClass(entity));
		if (criterions != null) {
			for (Criterion crit : criterions) {// 加入附加条件
				criteria.add(crit);
			}
		}
		if(!entityCode.equals("")){
			criteria.add(Restrictions.like(codeProName, entityCode));// 编码条件
		}
		criteria.addOrder(Order.desc(propertyName));// 按所需的属性排序
		criteria.setFirstResult(0);
		List list = criteria.list();
		if (list.size() > 0) {
			BeanWrapper bwMaxObj = new BeanWrapperImpl(list.get(0));
			return bwMaxObj.getPropertyValue(propertyName);// 符合条件的最大值
		}

		return null;
	}
	
	/**
	 * 
	 * <li>方法名：bluckInsert
	 * <li>@param v  要批量插入的实体向量。
	 * <li>返回类型：void
	 * <li>说明：本方法提供对业务实体的批量插入，传入的参数为实体的矢量容器。
	 * <li>创建人：曾明辉
	 * <li>创建日期：2008-11-26
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public void bluckInsert(List<Object> v) {
		Session session = this.getSession();
		int i = 1;
		for (Object object : v) {
			session.save(object);

			if (i % 20 == 0) { // 20, same as the JDBC batch size
				session.flush();
				session.clear();
			}
		}

		session.flush();
		session.clear();
	}


}
