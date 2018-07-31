package com.pms.rcm.task.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.task.vo.ActRuExecution; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil; 
/**
 * Service object for domain model class ActRuExecution.
 * @see com.pms.rcm.task.vo.ActRuExecution
 * @author bao.zhou
 */
@Service("actRuExecutionManager")
public class ActRuExecutionManager extends BaseManager<ActRuExecution>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuExecution>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActRuExecution get(Serializable id) throws BusinessException {
		ActRuExecution entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuExecution>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActRuExecution> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActRuExecution where id in(''");
		for (String id : ids) {
			sb.append(",'" + id + "'");
		}
		sb.append(")");
		
		return this.baseDao.find(sb.toString());
	}
	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param entitySearch，page，needSearchCount
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuExecution>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActRuExecution> findPage(ActRuExecution entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActRuExecution w where 1=1 ");   
		 
	 
		if(StrUtil.isNotEmpty(entitySearch.getActId())){
			sb.append(" and w.actId='" + entitySearch.getActId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getIsActive())){
			sb.append(" and w.isActive='" + entitySearch.getIsActive() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getIsConcurrent())){
			sb.append(" and w.isConcurrent='" + entitySearch.getIsConcurrent() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getIsScope())){
			sb.append(" and w.isScope='" + entitySearch.getIsScope() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getIsEventScope())){
			sb.append(" and w.isEventScope='" + entitySearch.getIsEventScope() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getTenantId())){
			sb.append(" and w.tenantId='" + entitySearch.getTenantId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getName())){
			sb.append(" and w.name='" + entitySearch.getName() + "'");
		}
	 

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuExecution>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActRuExecution insert(ActRuExecution entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuExecution>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActRuExecution  update(ActRuExecution entity) throws BusinessException {
		entity = super.update(entity);
		return entity;
	}
	 
    
    /**
	 * 
	 * <li>方法名：remove
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明：删除指定的实体对象,删除级联情况视hibernate的配置情况。
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void remove(ActRuExecution entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }

	/**
	 * 
	 * <li>方法名：checkDelete
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明：删除指定的实体对象校验
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	public void checkDelete(Serializable id) throws BusinessException {
		
	}
	/**
	 * 
	 * <li>方法名：checkUnique
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明： 判断数据库是是否已有数据该代码数据
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	public void checkUnique(ActRuExecution entity) throws BusinessException {
		
	}
	
	@SuppressWarnings("unchecked")
	public ActRuExecution findActiveExecutionByProcInstId(String procInstId) throws BusinessException{
		 
		StringBuffer sb = new StringBuffer();
		sb.append("from ActRuExecution where procInstId='"+procInstId+"' and isActive='1'");
	 
		return (ActRuExecution)this.baseDao.findSingle(sb.toString());
	}
	 

}

