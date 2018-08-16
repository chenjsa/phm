package com.pms.rcm.phmtask.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.phmtask.vo.TaskResult; 
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
 * Service object for domain model class TaskResult.
 * @see com.pms.rcm.phmtask.vo.TaskResult
 * @author bao.zhou
 */
@Service("taskResultManager")
public class TaskResultManager extends BaseManager<TaskResult>{ 
 	 @Autowired
     private TaskResultMapper taskResultMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskResult>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public TaskResult get(Serializable id) throws BusinessException {
		TaskResult entity = super.get(id); 
		return entity;
	}
	public TaskResult getByMybatis(String id) throws BusinessException {
		TaskResult entity = taskResultMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskResult>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<TaskResult> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from TaskResult where id in(''");
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
	 * <li>返回类型：List<TaskResult>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<TaskResult> findPage(TaskResult entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from TaskResult w where 1=1 ");   
		 
		if(entitySearch.getTaskId()!=null){
			sb.append(" and w.taskId='" + entitySearch.getTaskId() + "'");
		}
		if(entitySearch.getRecordTime()!=null){
			sb.append(" and w.recordTime='" + entitySearch.getRecordTime() + "'");
		}
		if(entitySearch.getRecordTimeB()!=null){
			sb.append(" and w.recordTime>='" + entitySearch.getRecordTimeB() + "'");
		}
		if(entitySearch.getRecordTimeE()!=null){
			sb.append(" and w.recordTime<='" + entitySearch.getRecordTimeE() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getResultData())){
			sb.append(" and w.resultData='" + entitySearch.getResultData() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRemark())){
			sb.append(" and w.remark='" + entitySearch.getRemark() + "'");
		}
		///radar编号
		if(entitySearch.getPhmtaskInfo()!=null){
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getRadarId())){
				sb.append(" and w.phmtaskInfo.radarId='" + entitySearch.getPhmtaskInfo().getRadarId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getPhmfunctionTypeId())){
				sb.append(" and w.phmtaskInfo.phmfunctionTypeId='" + entitySearch.getPhmtaskInfo().getPhmfunctionTypeId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getModuleId())){
				sb.append(" and w.phmtaskInfo.moduleId='" + entitySearch.getPhmtaskInfo().getModuleId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getPhmsubsId())){
				sb.append(" and w.phmtaskInfo.phmsubsId='" + entitySearch.getPhmtaskInfo().getPhmsubsId() + "'");
			}
		}
		

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		System.out.println(hql);
		return super.findPage(hql, page, needSearchCount);
	}
    
    public List<TaskResult> findPageEchart(TaskResult entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from TaskResult w where 1=1 ");   
		 
		if(entitySearch.getTaskId()!=null){
			sb.append(" and w.taskId='" + entitySearch.getTaskId() + "'");
		}
		if(entitySearch.getRecordTime()!=null){
			sb.append(" and w.recordTime='" + entitySearch.getRecordTime() + "'");
		}
		if(entitySearch.getRecordTimeB()!=null){
			sb.append(" and w.recordTime>='" + entitySearch.getRecordTimeB() + "'");
		}
		if(entitySearch.getRecordTimeE()!=null){
			sb.append(" and w.recordTime<='" + entitySearch.getRecordTimeE() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getResultData())){
			sb.append(" and w.resultData='" + entitySearch.getResultData() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRemark())){
			sb.append(" and w.remark='" + entitySearch.getRemark() + "'");
		}
		///radar编号
		if(entitySearch.getPhmtaskInfo()!=null){
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getRadarId())){
				sb.append(" and w.phmtaskInfo.radarId='" + entitySearch.getPhmtaskInfo().getRadarId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getPhmfunctionTypeId())){
				sb.append(" and w.phmtaskInfo.phmfunctionTypeId='" + entitySearch.getPhmtaskInfo().getPhmfunctionTypeId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getModuleId())){
				sb.append(" and w.phmtaskInfo.moduleId='" + entitySearch.getPhmtaskInfo().getModuleId() + "'");
			}
			
			if(StrUtil.isNotEmpty(entitySearch.getPhmtaskInfo().getPhmsubsId())){
				sb.append(" and w.phmtaskInfo.phmsubsId='" + entitySearch.getPhmtaskInfo().getPhmsubsId() + "'");
			}
		}
		

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		System.out.println(hql); 
		return this.baseDao.find(hql);
	}
	
	public List<TaskResult> listPage(TaskResult entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<TaskResult> list=  taskResultMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskResult>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public TaskResult insert(TaskResult entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(TaskResult entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		taskResultMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(TaskResult entity) throws BusinessException {
		taskResultMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TaskResult>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public TaskResult  update(TaskResult entity) throws BusinessException {
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
	public void remove(TaskResult entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			taskResultMapper.delete(id);
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
	public void checkUnique(TaskResult entity) throws BusinessException {
		
	}
	 

}

