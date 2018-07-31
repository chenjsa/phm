package com.pms.rcm.task.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.task.vo.ActHiVarinst; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil; 
/**
 * Service object for domain model class ActHiVarinst.
 * @see com.pms.rcm.task.vo.ActHiVarinst
 * @author bao.zhou
 */
@Service("actHiVarinstManager")
public class ActHiVarinstManager extends BaseManager<ActHiVarinst>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiVarinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActHiVarinst get(Serializable id) throws BusinessException {
		ActHiVarinst entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiVarinst>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActHiVarinst> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActHiVarinst where id in(''");
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
	 * <li>返回类型：List<ActHiVarinst>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActHiVarinst> findPage(ActHiVarinst entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActHiVarinst w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getProcInstId())){
			sb.append(" and w.procInstId='" + entitySearch.getProcInstId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getExecutionId())){
			sb.append(" and w.executionId='" + entitySearch.getExecutionId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getTaskId())){
			sb.append(" and w.taskId='" + entitySearch.getTaskId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getName())){
			sb.append(" and w.name='" + entitySearch.getName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getVarType())){
			sb.append(" and w.varType='" + entitySearch.getVarType() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getBytearrayId())){
			sb.append(" and w.bytearrayId='" + entitySearch.getBytearrayId() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getText())){
			sb.append(" and w.text='" + entitySearch.getText() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getText2())){
			sb.append(" and w.text2='" + entitySearch.getText2() + "'");
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
	 * <li>返回类型：List<ActHiVarinst>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActHiVarinst insert(ActHiVarinst entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActHiVarinst>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActHiVarinst  update(ActHiVarinst entity) throws BusinessException {
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
	public void remove(ActHiVarinst entity) {
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
	public void checkUnique(ActHiVarinst entity) throws BusinessException {
		
	}
	
	public ActHiVarinst getActHiVarinstByBussinessKey(String procInstId) throws BusinessException {
		String hql="from ActHiVarinst where name='bussinessKey' and procInstId='"+procInstId+"'";
		return (ActHiVarinst)this.baseDao.findSingle(hql);
	}
	 

}

