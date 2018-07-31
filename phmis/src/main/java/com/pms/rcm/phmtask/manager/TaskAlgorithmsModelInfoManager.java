package com.pms.rcm.phmtask.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.phmtask.vo.TaskAlgorithmsModelInfo; 
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil; 

/**
 * Service object for domain model class TaskAlgorithmsModelInfo.
 * @see com.pms.rcm.phmtask.vo.TaskAlgorithmsModelInfo
 * @author bao.zhou
 */
@Service("taskAlgorithmsModelInfoManager")
public class TaskAlgorithmsModelInfoManager extends BaseManager<TaskAlgorithmsModelInfo>{ 
 	 @Autowired
     private TaskAlgorithmsModelInfoMapper taskAlgorithmsModelInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskAlgorithmsModelInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public TaskAlgorithmsModelInfo get(Serializable id) throws BusinessException {
		TaskAlgorithmsModelInfo entity = super.get(id); 
		return entity;
	}
	public TaskAlgorithmsModelInfo getByMybatis(String id) throws BusinessException {
		TaskAlgorithmsModelInfo entity = taskAlgorithmsModelInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskAlgorithmsModelInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<TaskAlgorithmsModelInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from TaskAlgorithmsModelInfo where id in(''");
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
	 * <li>返回类型：List<TaskAlgorithmsModelInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<TaskAlgorithmsModelInfo> findPage(TaskAlgorithmsModelInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from TaskAlgorithmsModelInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getAlgorithmsModelsId())){
			sb.append(" and w.algorithmsModelsId='" + entitySearch.getAlgorithmsModelsId() + "'");
		}
		if(entitySearch.getBeginTime()!=null){
			sb.append(" and w.beginTime='" + entitySearch.getBeginTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getModelUrl())){
			sb.append(" and w.modelUrl='" + entitySearch.getModelUrl() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getState())){
			sb.append(" and w.state='" + entitySearch.getState() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<TaskAlgorithmsModelInfo> listPage(TaskAlgorithmsModelInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<TaskAlgorithmsModelInfo> list=  taskAlgorithmsModelInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskAlgorithmsModelInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public TaskAlgorithmsModelInfo insert(TaskAlgorithmsModelInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(TaskAlgorithmsModelInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		taskAlgorithmsModelInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(TaskAlgorithmsModelInfo entity) throws BusinessException {
		taskAlgorithmsModelInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskAlgorithmsModelInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public TaskAlgorithmsModelInfo  update(TaskAlgorithmsModelInfo entity) throws BusinessException {
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
	public void remove(TaskAlgorithmsModelInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			taskAlgorithmsModelInfoMapper.delete(id);
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
	public void checkUnique(TaskAlgorithmsModelInfo entity) throws BusinessException {
		
	}
	 

}

