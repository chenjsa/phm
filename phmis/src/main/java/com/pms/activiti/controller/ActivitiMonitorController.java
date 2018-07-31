package com.pms.activiti.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.activiti.manager.ActivitiMonitorManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/activitiMonitor")
public class ActivitiMonitorController extends BaseController {
	@Autowired    
    private ActivitiMonitorManager activitiMonitorManager; 
	
	@ResponseBody
	@ApiOperation(value="actRuTask信息删除", notes="根据url的id来删除actRuTask信息",httpMethod = "GET")
	@ApiImplicitParam(name = "processInstanceId", value = "actRuTask的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/queryHistoricTaskInstance/{processInstanceId}",method=RequestMethod.GET)
	public String queryHistoricTaskInstance(@PathVariable("processInstanceId") String processInstanceId) throws Exception{   
	  	try{
	  		ByteArrayOutputStream output = new ByteArrayOutputStream();
	 		List list=activitiMonitorManager.queryActHiTaskinst(processInstanceId);   
			JSONArray jsonarray = JSONArray.fromObject(list);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(list.size()));
			jsonobject.put("records", String.valueOf(list.size()));
	 		jsonobject.put("rows", jsonarray);
	 		String basicbridgelistString = jsonobject.toString();  
	 		//System.out.println("basicbridgelistString=="+basicbridgelistString);
			return basicbridgelistString; 
		}catch(Exception e){ 
			e.printStackTrace(); 
			return null;
		} 
		 
	}
	
	@ResponseBody
	@ApiOperation(value="actRuTask信息删除", notes="根据url的id来删除actRuTask信息",httpMethod = "GET")
	@ApiImplicitParam(name = "processInstanceId", value = "actRuTask的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/queryHistoricActivitiInstance/{processInstanceId}",method=RequestMethod.GET)
	public String queryHistoricActivitiInstance(@PathVariable("processInstanceId") String processInstanceId) throws Exception{   
	  	try{
	  		ByteArrayOutputStream output = new ByteArrayOutputStream();
	 		List list=activitiMonitorManager.queryActHiActinst(processInstanceId);   
			JSONArray jsonarray = JSONArray.fromObject(list);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(list.size()));
			jsonobject.put("records", String.valueOf(list.size()));
	 		jsonobject.put("rows", jsonarray);
	 		String basicbridgelistString = jsonobject.toString();  
	 		//System.out.println("basicbridgelistString=="+basicbridgelistString);
			return basicbridgelistString; 
		}catch(Exception e){ 
			e.printStackTrace(); 
			return null;
		} 
		 
	}

	@ResponseBody
	@ApiOperation(value="actRuTask信息删除", notes="根据url的id来删除actRuTask信息",httpMethod = "GET")
	@ApiImplicitParam(name = "processInstanceId", value = "actRuTask的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/queryProHighLighted/{processInstanceId}",method=RequestMethod.GET)
	public void queryProHighLighted(@PathVariable("processInstanceId") String processInstanceId,HttpServletResponse response) throws Exception{   
	  	try{
	  		ByteArrayOutputStream output = new ByteArrayOutputStream();
	 		activitiMonitorManager.queryProHighLighted(processInstanceId,output);
	 		ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
		 
	}

	@ResponseBody
	@ApiOperation(value="获取业务表单页面", notes="获取业务表单页面",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "processInstanceId", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/getBusinesskey/{processInstanceId}",method=RequestMethod.GET)
	public Object getBusinesskey(@PathVariable("processInstanceId") String processInstanceId) throws Exception{   
		String errInfo = "success";
		String msg="";
		Map<String,String> map = new HashMap<String,String>();  
	 	try{
		    String bsKey=this.activitiMonitorManager.getBusinesskey(processInstanceId);
		    msg=bsKey;
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
		} 
		
		
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	     
}
