package com.pms.rcm.task.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.task.vo.ActTaskExtend; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil; 
/**
 * Service object for domain model class ActTaskExtend.
 * @see com.pms.rcm.task..vo.ActTaskExtend
 * @author bao.zhou
 */
@Service("actTaskExtendManager")
public class ActTaskExtendManager extends BaseManager<ActTaskExtend>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActTaskExtend>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActTaskExtend get(Serializable id) throws BusinessException {
		ActTaskExtend entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActTaskExtend>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActTaskExtend> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActTaskExtend where id in(''");
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
	 * <li>返回类型：List<ActTaskExtend>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActTaskExtend> findPage(ActTaskExtend entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActTaskExtend w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getTaskNode())){
			sb.append(" and w.taskNode='" + entitySearch.getTaskNode() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getAuditContent())){
			sb.append(" and w.auditContent='" + entitySearch.getAuditContent() + "'");
		}
		 
		if(StrUtil.isNotEmpty(entitySearch.getHandleUserId())){
			sb.append(" and w.handleUserId='" + entitySearch.getHandleUserId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getHandleUserNo())){
			sb.append(" and w.handleUserNo='" + entitySearch.getHandleUserNo() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getHandleUserName())){
			sb.append(" and w.handleUserName='" + entitySearch.getHandleUserName() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
    
    public List<ActTaskExtend> findAll(String processId) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ActTaskExtend w where 1=1 ");   
		sb.append(" and processId='"+processId+"' ");    
		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return this.find(hql);
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActTaskExtend>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActTaskExtend insert(ActTaskExtend entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActTaskExtend>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActTaskExtend  update(ActTaskExtend entity) throws BusinessException {
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
	public void remove(ActTaskExtend entity) {
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
	public void checkUnique(ActTaskExtend entity) throws BusinessException {
		
	}
	 

}

