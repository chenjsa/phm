package com.pms.rcm.leave.controller;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.activiti.manager.ActivitiHelper;
import com.pms.base.controller.BaseController;
import com.pms.base.util.Const;
import com.pms.base.util.DateUtil;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.Page;
import com.pms.rcm.leave.manager.TBizLeaveManager;
import com.pms.rcm.leave.vo.TBizLeave;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.DictionariesManager;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.task.manager.ActRuTaskManager;
import com.pms.rcm.task.vo.ActRuTask;
import com.pms.rcm.task.vo.ActUserTaskModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * t_biz_leave表TBizLeave维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName TBizLeaveController.java
 */
@Controller
@RequestMapping(value="/tBizLeave")
@Api("TBizLeave操作controller")
public class TBizLeaveController  extends BaseController<TBizLeave, TBizLeaveManager> {
	private static final long serialVersionUID = 59828836191L;  
	@Resource(name="deptManager")
	private DeptManager deptManager;
	@Resource(name="dictionariesManager")
	private DictionariesManager dictionariesManager;
	@Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService; 
    @Autowired
	private TaskService taskService;
    @Autowired
    private ActRuTaskManager actRuTaskManager; 
    @Autowired
    private ActivitiHelper activitiHelper;
	@ApiOperation(value="获取tBizLeave列表", notes="分页查询获取tBizLeave列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,TBizLeave entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<TBizLeave> tBizLeaveList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(tBizLeaveList);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(page.getTotalPage()));
			jsonobject.put("records", String.valueOf(page.getTotalResult()));
	 		jsonobject.put("rows", jsonarray);
	 		String basicbridgelistString = jsonobject.toString();  
			return basicbridgelistString;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@ApiOperation(value="tBizLeave信息", notes="根据url的id来获取tBizLeave信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "tBizLeave的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public TBizLeave getEntityById(@PathVariable("id") String taskId)throws Exception{		 
		/// taskService.claim(task.getId(), user.getNo());//签收任务
		 this.entity=this.baseManager.get(taskId);	 
		 return entity;
	}
	
	@ResponseBody
	@ApiOperation(value="tBizLeave信息", notes="根据url的id来获取tBizLeave信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "tBizLeave的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getApprovalInfo/{taskId}",method=RequestMethod.GET)
	public TBizLeave getApprovalInfo(@PathVariable("taskId") String taskId)throws Exception{		
		 ActRuTask task=this.actRuTaskManager.get(taskId); 
		 this.entity=this.baseManager.find("from TBizLeave where processId='"+task.getProcInstId()+"'").get(0); 	
		 entity.setOutGoingTransNames(this.actRuTaskManager.getOutGoingTransNames(taskId));
		 entity.setTaskId(taskId);
		 return entity;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="TBizLeave信息修改或新增", notes="根据页面传入的TBizLeave信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody TBizLeave tBizLeave) throws Exception{	
		entity=tBizLeave; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				 Map<String, Object> vars = new HashMap<String, Object>();
				 vars.put("outcome", entity.getOutcome());		         
		         this.baseManager.completeTask(entity.getTaskId(), vars, this.getLoginUser(), entity.getOutcome(), entity.getOutcome());
			}
			else{  
				//启动流程
				//获取当前用户ID
		        Session session = Jurisdiction.getSession();
		        User user = new User();
		        user = (User)session.getAttribute(Const.SESSION_USER);
		        String strUID = user.getId();  
		        Map<String, Object> variables = new HashMap<String, Object>();
		       // identityService.setAuthenticatedUserId(strUID);
		        Dept dept=deptManager.get(user.getDeptId());
		        variables.put("candidateGroups",dept.getCasCode());
		        variables.put("creater",user.getNo());
		       
		        entity.setApplyDate(DateUtil.format(new Date()));
		        String flowKey="sealFlow"; 
		        this.baseManager.startProcessInstanceByKey(user,tBizLeave, flowKey, variables); 
		        //保存请假数据
		        logger.info("准备保存请假数据，用户ID：" + strUID);    
			    logger.info("流程实例已启动，流程实例ID：" + entity.getProcessId());   
							 
			}
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
		} 
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	 
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */ 
	@ResponseBody
	@ApiOperation(value="tBizLeave信息删除", notes="根据url的id来删除tBizLeave信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "tBizLeave的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.delete(entity, id);//执行删除
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
		} 
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	     
	
	@ResponseBody
	@ApiOperation(value="tBizLeave信息删除", notes="根据url的id来删除tBizLeave信息",httpMethod = "POST")
	@ApiImplicitParam(name = "id", value = "tBizLeave的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/taskHandle/{id}",method=RequestMethod.POST)
	public Object taskHandle(@PathVariable("id") String id,String outcome,String pers) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
	 		 Map<String, Object> vars = new HashMap<String, Object>();
	        // vars.put("outcome", outcome);//outcome为提交按钮的名称	 
	         List<String> assigneeList=new ArrayList<String>(); //分配任务的人员
	         String[] ass=pers.split(",");
	         for(String as: ass){
	        	 System.out.println(as);
	        	 assigneeList.add(as);
	         } 
	         Dept dept=deptManager.get(this.getLoginUser().getDeptId());
	         vars.put("casCode",dept.getCasCode());
	         vars.put("assigneeList", assigneeList);
	         vars.put("outcome", outcome);
	         
	         this.baseManager.completeTask(id, vars, this.getLoginUser(), outcome, outcome);
            // taskService.complete(id,vars);
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
		} 
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	
	
	
	@ResponseBody
	@ApiOperation(value="tBizLeave信息删除", notes="根据url的id来删除tBizLeave信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "taskId", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/getNextTaskInfo/{taskId}",method=RequestMethod.GET)
	public List<ActUserTaskModel> getNextTaskInfo(@PathVariable("taskId") String taskId) throws Exception{  
	 	try{
	 		List<ActUserTaskModel> list=this.activitiHelper.getNextTaskInfo(taskId); 
	 		JSONArray jsonarray = JSONArray.fromObject(list); 
		    System.out.println(jsonarray.toString()); 
			return list;
		}catch(Exception e){
			 
			e.printStackTrace(); 
		} 
		 
		return null;
	}
	     
	     
	
	 
}
