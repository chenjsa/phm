package com.pms.rcm.leave.manager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.DateUtil;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.rcm.leave.vo.TBizLeave;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.task.manager.ActHiProcinstManager;
import com.pms.rcm.task.manager.ActTaskExtendManager;
import com.pms.rcm.task.vo.ActHiActinst;
import com.pms.rcm.task.vo.ActTaskExtend; 
/**
 * Service object for domain model class TBizLeave.
 * @see com.pms.rcm.leave.vo.TBizLeave
 * @author bao.zhou
 */
@Service("tBizLeaveManager")
public class TBizLeaveManager extends BaseManager<TBizLeave>{ 
	 @Autowired
	 private TaskService taskService;
	 @Autowired
	 private ActTaskExtendManager actTaskExtendManager;
	 @Autowired
	 private RuntimeService runtimeService;
	 @Autowired
	 private IdentityService identityService; 
	 @Autowired
	 private ActHiProcinstManager actHiProcinstManager;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TBizLeave>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public TBizLeave get(Serializable id) throws BusinessException {
		TBizLeave entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TBizLeave>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<TBizLeave> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from TBizLeave where id in(''");
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
	 * <li>返回类型：List<TBizLeave>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<TBizLeave> findPage(TBizLeave entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from TBizLeave w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getApplyUserId())){
			sb.append(" and w.applyUserId='" + entitySearch.getApplyUserId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getLeaveType())){
			sb.append(" and w.leaveType='" + entitySearch.getLeaveType() + "'");
		}
	 
		if(StrUtil.isNotEmpty(entitySearch.getReason())){
			sb.append(" and w.reason='" + entitySearch.getReason() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getManagerId())){
			sb.append(" and w.managerId='" + entitySearch.getManagerId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRemarks1())){
			sb.append(" and w.remarks1='" + entitySearch.getRemarks1() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getLeaderId())){
			sb.append(" and w.leaderId='" + entitySearch.getLeaderId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRemarks2())){
			sb.append(" and w.remarks2='" + entitySearch.getRemarks2() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getProcessId())){
			sb.append(" and w.processId='" + entitySearch.getProcessId() + "'");
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
	 * <li>返回类型：List<TBizLeave>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public TBizLeave insert(TBizLeave entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
  	
  	@Transactional(rollbackFor = { Exception.class })
	public TBizLeave startProcessInstanceByKey(User user,TBizLeave entity,String flowKey,Map<String, Object> variables) throws BusinessException {		
  		 this.insert(entity); 
  		 identityService.setAuthenticatedUserId(user.getNo());
  		 String bsKey="/html/tBizLeave/tBizLeave_edit.html?id="+entity.getId();
  		 variables.put("bussinessKey", bsKey);
         ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowKey,variables);
	     String proId=processInstance.getId(); 
         entity.setProcessId(proId);
         this.update(entity);
        ActTaskExtend actTaskExtend=new ActTaskExtend();
        actTaskExtend.setHandleUserId(user.getId());
 		actTaskExtend.setHandleUserName(user.getName());
 		actTaskExtend.setHandleUserNo(user.getNo());
 		//actTaskExtend.setTaskId(processInstance.get);   
 		  
 		 ActHiActinst actHiActinst= actHiProcinstManager.queryFirstActHiActinst(proId);
 		actTaskExtend.setTaskNode(actHiActinst.getActName());
 		actTaskExtend.setProcessId(processInstance.getId());
 		actTaskExtend.setHandTime(DateUtil.format(new Date()));
 		this.actTaskExtendManager.insert(actTaskExtend);
		 return entity;
	}


    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<TBizLeave>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public TBizLeave  update(TBizLeave entity) throws BusinessException {
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
	public void remove(TBizLeave entity) {
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
	public void checkUnique(TBizLeave entity) throws BusinessException {
		
	}
	@Transactional(rollbackFor = { Exception.class })
	public void completeTask(String id,Map vars,User user,String auditContent,String transName){
		Task task=taskService.createTaskQuery() // 创建任务查询
                .taskId(id) // 根据任务id查询
                .singleResult(); 
		ActTaskExtend taskExtend=new ActTaskExtend();
		taskExtend.setAuditContent(auditContent);
		taskExtend.setHandleUserId(user.getId());
		taskExtend.setHandleUserName(user.getName());
		taskExtend.setHandleUserNo(user.getNo());
		taskExtend.setTaskId(task.getId()); 
		taskExtend.setTaskNode(task.getName());
		taskExtend.setProcessId(task.getProcessInstanceId());
		taskExtend.setHandTime(DateUtil.format(new Date()));
		this.actTaskExtendManager.insert(taskExtend);
	    taskService.complete(id,vars);
	}
	 

}

