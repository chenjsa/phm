package com.pms.rcm.task.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.task.vo.ActHiActinst; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil; 
/**
 * Service object for domain model class ActHiActinst.
 * @see com.pms.rcm.task.vo.ActHiActinst
 * @author bao.zhou
 */
@Service("actHiActinstManager")
public class ActHiActinstManager extends BaseManager<ActHiActinst>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiActinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActHiActinst get(Serializable id) throws BusinessException {
		ActHiActinst entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiActinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActHiActinst> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActHiActinst where id in(''");
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
	 * <li>返回类型：List<ActHiActinst>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActHiActinst> findPage(ActHiActinst entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActHiActinst w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getProcDefId())){
			sb.append(" and w.procDefId='" + entitySearch.getProcDefId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getProcInstId())){
			sb.append(" and w.procInstId='" + entitySearch.getProcInstId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getExecutionId())){
			sb.append(" and w.executionId='" + entitySearch.getExecutionId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getActId())){
			sb.append(" and w.actId='" + entitySearch.getActId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getTaskId())){
			sb.append(" and w.taskId='" + entitySearch.getTaskId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getCallProcInstId())){
			sb.append(" and w.callProcInstId='" + entitySearch.getCallProcInstId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getActName())){
			sb.append(" and w.actName='" + entitySearch.getActName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getActType())){
			sb.append(" and w.actType='" + entitySearch.getActType() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getAssignee())){
			sb.append(" and w.assignee='" + entitySearch.getAssignee() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getTenantId())){
			sb.append(" and w.tenantId='" + entitySearch.getTenantId() + "'");
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
	 * <li>返回类型：List<ActHiActinst>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActHiActinst insert(ActHiActinst entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiActinst>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActHiActinst  update(ActHiActinst entity) throws BusinessException {
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
	public void remove(ActHiActinst entity) {
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
	public void checkUnique(ActHiActinst entity) throws BusinessException {
		
	}
	 

}

