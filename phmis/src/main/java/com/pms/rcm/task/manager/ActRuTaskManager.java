package com.pms.rcm.task.manager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.DateUtil;
import com.pms.base.util.Page;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.task.vo.ActRuExecution;
import com.pms.rcm.task.vo.ActRuTask;
import com.pms.rcm.task.vo.ActTaskExtend;
import com.pms.rcm.task.vo.ActTransModel; 
/**
 * Service object for domain model class ActRuTask.
 * @see com.pms.rcm.task.vo.ActRuTask
 * @author bao.zhou
 */
@Service("actRuTaskManager")
public class ActRuTaskManager extends BaseManager<ActRuTask>{ 
	@Resource(name="deptManager")
	private DeptManager deptManager;
	@Autowired
	private TaskService taskService;
	@Autowired
    private RepositoryService repositoryService;
	@Autowired
    private RuntimeService runtimeService;
    @Autowired    
    private ActRuExecutionManager actRuExecutionManager;
    @Autowired
	private ActTaskExtendManager actTaskExtendManager;
    @Autowired
  	private UserManager userManager;  
    
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuTask>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ActRuTask get(Serializable id) throws BusinessException {
		ActRuTask entity = super.get(id); 
		return entity;
	}
    
    public void claim(String taskId,User user) throws BusinessException {
    	taskService.claim(taskId, user.getNo());//签收任务
    }
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuTask>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ActRuTask> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ActRuTask where id in(''");
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
	 * <li>返回类型：List<ActRuTask>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ActRuTask> findPage(User user, Page page,
			boolean needSearchCount) throws BusinessException { 
	    StringBuffer sb = new StringBuffer();  
		sb.append("select t from ActRuTask t  ");
        sb.append(" where (t.assignee='"+user.getNo()+"' or (t.assignee is null and   exists(select 1 from ActRuIdentitylink pt where pt.taskId=t.id and   pt.type = 'candidate'");  
        sb.append(" and ((pt.groupId =''");
		for (String groupId : this.getActivitiGroupList(user)) {
			sb.append(" or pt.groupId like'" + groupId + "%'");
		}		
		sb.append(" or pt.userId='"+user.getNo()+"' )))))"); 
		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		 
		return super.findPage(hql, page, needSearchCount);
	}
    public int getSelfTaskCount(User user){
    	
    	 StringBuffer sb = new StringBuffer();  
 		 sb.append("from ActRuTask t  ");
         sb.append(" where t.assignee='"+user.getNo()+"'");
 		 String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
 		 return this.baseDao.getCount(hql);
    } 
    
    public int getGroupTaskCount(User user){
    	
    	  StringBuffer sb = new StringBuffer();  
  		 sb.append("from ActRuTask t  ");
          sb.append(" where t.assignee is null and   (exists(select 1 from ActRuIdentitylink pt where pt.taskId=t.id and   pt.type = 'candidate'");  
          sb.append(" and ((pt.groupId =''");
  		for (String groupId : this.getActivitiGroupList(user)) {
  			sb.append(" or pt.groupId like'" + groupId + "%'"); 
  		}		
  		sb.append("  ))))"); 
  		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());	
  		 return this.baseDao.getCount(hql);
   } 
    private List<String> getActivitiGroupList(User user){
		List<String> groupList = new ArrayList<String>(); 
		
		for (Role role : user.getRoleSet()) {
			for (Dept dept : user.getManageDeptSet()) {
				groupList.add(role.getRoleCode() + "-" + dept.getCasCode());
			} 
		}
		
		
		return groupList;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuTask>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ActRuTask insert(ActRuTask entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ActRuTask>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ActRuTask  update(ActRuTask entity) throws BusinessException {
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
	public void remove(ActRuTask entity) {
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
	public void checkUnique(ActRuTask entity) throws BusinessException {
		
	}
	 
	public List<ActTransModel> getOutGoingTransNames(String taskId)  throws BusinessException {
        List<ActTransModel> transNames = new ArrayList<ActTransModel>();
        // 1.获取流程定义
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        // 2.获取流程实例
      //  ProcessInstance pi =runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult(); 
      //  Execution execution = null;
        // ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();  
     //   ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(task.getProcessInstanceId());
      //    ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId());
       //  List<Execution> list = (List<Execution>) runtimeService.createExecutionQuery().list();
        ActRuExecution execution=this.actRuExecutionManager.findActiveExecutionByProcInstId(task.getProcessInstanceId());
        
     	
        //当前流程节点Id信息   
       String activitiId = execution.getActId();  
        // 4.通过活动的ID在流程定义中找到对应的活动对象
        System.out.println("taskId="+taskId+" activitiId="+activitiId+" execution="+execution.getId());
        ActivityImpl activity = pd.findActivity(activitiId);
     
        // 5.通过活动对象找当前活动的所有出口
         List<PvmTransition> transitions =  activity.getOutgoingTransitions();
        // 6.提取所有出口的名称，封装成集合
         
         for (PvmTransition trans : transitions) {
             String transName = (String) trans.getProperty("name");
             String transId =trans.getId(); 
             if(!StringUtils.isEmpty(transName)){
               //  transNames.add(transId);
            	 ActTransModel m=new ActTransModel();
                 m.setId(transId);
                 m.setName(transName);
                 transNames.add(m);
             }
        }
         if(transNames.size()==0){
        	   ActTransModel m=new ActTransModel();
        	   m.setId("");
               m.setName("提交");
               transNames.add(m); 
         }
        return transNames;
    }
	
	@Transactional(rollbackFor = { Exception.class })
	public void completeTask(Map vars,User user,ActTaskExtend actTaskExtend)  throws BusinessException {
		Task task=taskService.createTaskQuery() // 创建任务查询
                .taskId(actTaskExtend.getTaskId()) // 根据任务id查询
                .singleResult();  
		actTaskExtend.setHandleUserId(user.getId());
		actTaskExtend.setHandleUserName(user.getName());
		actTaskExtend.setHandleUserNo(user.getNo());
		actTaskExtend.setTaskId(task.getId()); 
		actTaskExtend.setTaskNode(task.getName());
		actTaskExtend.setProcessId(task.getProcessInstanceId());
		actTaskExtend.setHandTime(DateUtil.format(new Date()));
		this.actTaskExtendManager.insert(actTaskExtend);
	    taskService.complete(actTaskExtend.getTaskId(),vars);
	    
	    
	}
	
	public List<User> getUsersByRole(String roleCode) throws BusinessException {
		 List<User> list=userManager.find("from User");
		 List<User> users=new ArrayList<User>();
		 for(User user:list){
			 Set<Role> roleSet=user.getRoleSet();
			 Dept dept=this.deptManager.get(user.getDeptId());
			 for(Role role:roleSet){
				 if(role.getRoleCode().equals(roleCode)){
					 user.setDeptName(dept.getDeptName());
					 users.add(user);
					 break;
				 }
			 }
		 }
		 return users;
		
	}

}

