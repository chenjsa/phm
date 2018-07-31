package com.pms.rcm.task.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray; 
import net.sf.json.JSONObject;

import org.activiti.bpmn.model.FlowElement;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.activiti.manager.ActivitiHelper;
import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.task.vo.ActRuTask;
import com.pms.rcm.task.vo.ActTaskExtend;
import com.pms.rcm.task.vo.ActUserTaskModel;
import com.pms.rcm.leave.vo.TBizLeave;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.task.manager.ActRuTaskManager;
import com.pms.rcm.task.manager.ActTaskExtendManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * act_ru_task表ActRuTask维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName ActRuTaskController.java
 */
@Controller
@RequestMapping(value="/actRuTask")
@Api("ActRuTask操作controller")
public class ActRuTaskController  extends BaseController<ActRuTask, ActRuTaskManager> {
	private static final long serialVersionUID = 650346100332L;  
	@Autowired   
	private ActTaskExtendManager actTaskExtendManager;
    @Autowired
	private ActivitiHelper activitiHelper;
    @Resource(name="deptManager")
	private DeptManager deptManager; 
    
	@ApiOperation(value="获取actRuTask列表", notes="分页查询获取actRuTask列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,ActRuTask entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<ActRuTask> actRuTaskList =this.baseManager.findPage(this.getLoginUser(), page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(actRuTaskList);
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
 
	  
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@ResponseBody
	@ApiOperation(value="获取业务表单页面", notes="获取业务表单页面",httpMethod = "GET")
	@ApiImplicitParam(name = "processInstanceId", value = "processInstanceId", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/gotoTaskHandle/{processInstanceId}",method=RequestMethod.GET) 
	public ModelAndView gotoTaskHandle(@PathVariable("processInstanceId") String processInstanceId,String taskId) throws Exception{ 
		ModelAndView mv = this.getModelAndView(); 
	    List<ActTaskExtend>  list=this.actTaskExtendManager.findAll(processInstanceId);	    
		List<ActUserTaskModel> modelList=this.activitiHelper.getNextTaskInfo(taskId); 		
		//List<FlowElement> nodeList=this.activitiHelper.getAllNodes(processInstanceId);
	//	JSONArray jsonarray = JSONArray.fromObject(nodeList);
		//System.out.println("jsonarray:"+jsonarray.toString()); 
	    mv.addObject("taskId",taskId);
	    mv.addObject("varList",list);
	   // mv.addObject("nodeList",nodeList);
	    mv.addObject("modelList",modelList);
		mv.setViewName("jsp/task/task_handle");
		return mv;
	}
	 
		@ResponseBody
		@ApiOperation(value="流转信息页面", notes="流转信息页面",httpMethod = "GET")
		@ApiImplicitParam(name = "processInstanceId", value = "processInstanceId", required = true, dataType = "String", paramType = "path")
		@RequestMapping(value="/gotoRoam/{processInstanceId}",method=RequestMethod.GET) 
		public ModelAndView gotoRoam(@PathVariable("processInstanceId") String processInstanceId,String taskId) throws Exception{ 
			ModelAndView mv = this.getModelAndView(); 
		    List<ActTaskExtend>  list=this.actTaskExtendManager.findAll(processInstanceId);
			List<ActUserTaskModel> modelList=this.activitiHelper.getNextTaskInfo(taskId); 
			
		    mv.addObject("taskId",taskId);
		    mv.addObject("varList",list);
		    mv.addObject("modelList",modelList);
			mv.setViewName("jsp/task/task_roam");
			return mv;
		}
	
	@ResponseBody
	@ApiOperation(value="签收任务进入任务处理页面", notes="根据url的id来获取任务信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "taskId", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/claimTask/{taskId}",method=RequestMethod.GET)
	public ActRuTask claimTask(@PathVariable("taskId") String taskId)throws Exception{		
		///获取任务信息
		 ActRuTask task=this.baseManager.get(taskId);
		 User user=this.getLoginUser();
		 ///签收任务
		 this.baseManager.claim(taskId, user); 
		 return task;
	}
	
	
	@ResponseBody
	@ApiOperation(value="获取巡检员信息", notes="获取巡检员信息",httpMethod = "POST") 
	@RequestMapping(value="/getUsers/{roleType}",method=RequestMethod.POST)
	public String getUsers(@PathVariable("roleType")String roleType) throws Exception{  
		// User user=this.getLoginUser();
		 List<User> list=this.baseManager.getUsersByRole(roleType); 
		 List<User> users=new ArrayList<User>(); 
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(list);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(users.size()));
			jsonobject.put("records", String.valueOf(users.size()));
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
	@ApiOperation(value="TBizLeave信息修改或新增", notes="根据页面传入的TBizLeave信息进行新增或修改",httpMethod = "POST") 
	@RequestMapping(value="/taskHandle",method=RequestMethod.POST)
	public Object taskHandle(@ApiParam @RequestBody ActTaskExtend actTaskExtend) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
	 		 System.out.println("会签人员："+actTaskExtend.getAssigneeVariable()+"=="+actTaskExtend.getJointlySigner()+"==="+actTaskExtend.getOutCome());
	 		 Dept dept=deptManager.get(this.getLoginUser().getDeptId());
	 		 Map<String, Object> vars = new HashMap<String, Object>();
	 		 String assigneeVariable=actTaskExtend.getAssigneeVariable();
	 		 String exclusiveGateway=actTaskExtend.getExclusiveGateway();///网关流向id
	         vars.put("outcome", actTaskExtend.getOutCome());//outcome为提交按钮的key
	         if(StringUtils.isNotEmpty(assigneeVariable)){
	        	 if("jointlySign".equals(assigneeVariable)){
		        	 List<String> assigneeList=new ArrayList<String>(); //分配会签任务的人员
		        	 String[] jointlySignerArr=actTaskExtend.getJointlySigner().split(",");
		        	 for(String as: jointlySignerArr){
			        	 System.out.println("会签人员："+as);
			        	 assigneeList.add(as);
			         } 
		        	 vars.put("jointlySignList", assigneeList);
		        	 vars.put("assignee", "");
		         }else  if("assignee".equals(assigneeVariable)){
		        	  vars.put(assigneeVariable, actTaskExtend.getAssignee());
		        	  System.out.println("任务人："+actTaskExtend.getAssignee());
		         }else  if("candidateGroups".equals(assigneeVariable)){
		        	  ///跟据角色+机构cascode来决策
		        	  vars.put(assigneeVariable, dept.getCasCode());
		         }
	         } 
	         if(StringUtils.isNotEmpty(exclusiveGateway)){ 
		        	  ///跟据角色+机构cascode来决策
		           vars.put("exclusiveGateway", exclusiveGateway); 
	         } 
           this.baseManager.completeTask(vars, this.getLoginUser(),actTaskExtend); 
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
	
	     
	 
}
