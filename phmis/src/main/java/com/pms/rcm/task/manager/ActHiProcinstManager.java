package com.pms.rcm.task.manager;
import java.io.Serializable; 
import java.util.List;

import com.pms.rcm.task.vo.ActHiActinst;
import com.pms.rcm.task.vo.ActHiProcinst; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil; 
/**
 * Service object for domain model class ActHiProcinst.
 * @see com.pms.rcm.task.vo.ActHiProcinst
 * @author bao.zhou
 */
@Service("actHiProcinstManager")
public class ActHiProcinstManager extends BaseManager<ActHiProcinst>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiProcinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActHiProcinst get(Serializable id) throws BusinessException {
		ActHiProcinst entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiProcinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActHiProcinst> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActHiProcinst where id in(''");
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
	 * <li>返回类型：List<ActHiProcinst>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActHiProcinst> findPage(ActHiProcinst entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActHiProcinst w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getProcInstId())){
			sb.append(" and w.procInstId='" + entitySearch.getProcInstId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getBusinessKey())){
			sb.append(" and w.businessKey='" + entitySearch.getBusinessKey() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getProcDefId())){
			sb.append(" and w.procDefId='" + entitySearch.getProcDefId() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getStartUserId())){
			sb.append(" and w.startUserId='" + entitySearch.getStartUserId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStartActId())){
			sb.append(" and w.startActId='" + entitySearch.getStartActId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getEndActId())){
			sb.append(" and w.endActId='" + entitySearch.getEndActId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getSuperProcessInstanceId())){
			sb.append(" and w.superProcessInstanceId='" + entitySearch.getSuperProcessInstanceId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDeleteReason())){
			sb.append(" and w.deleteReason='" + entitySearch.getDeleteReason() + "'");
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
	 * <li>返回类型：List<ActHiProcinst>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActHiProcinst insert(ActHiProcinst entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiProcinst>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActHiProcinst  update(ActHiProcinst entity) throws BusinessException {
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
	public void remove(ActHiProcinst entity) {
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
	public void checkUnique(ActHiProcinst entity) throws BusinessException {
		
	}
	
	public ActHiProcinst getActHiProcinstForStart(String processId){
		String hql="from ActHiProcinst where procInstId='"+processId+"'";
		return (ActHiProcinst)this.baseDao.findSingle(hql);
	}
	 
	 /**
     * 某一次流程的执行经历的多少任务
     */ 
    public ActHiActinst   queryFirstActHiActinst(String processInstanceId) { 
    	String hql="from ActHiActinst where procInstId='"+processInstanceId+"' and actType='startEvent'" ;
        
    	return (ActHiActinst)this.baseDao.findSingle(hql);
    }

}

