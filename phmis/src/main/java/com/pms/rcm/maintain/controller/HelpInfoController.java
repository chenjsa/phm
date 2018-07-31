package com.pms.rcm.maintain.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray; 
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.manager.HelpInfoManager;
import com.pms.rcm.maintain.manager.LogInfoManager;
import com.pms.rcm.maintain.vo.HelpInfo;
import com.pms.rcm.maintain.vo.LogInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * help_info表HelpInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName HelpInfoController.java
 */
@Controller
@RequestMapping(value="/helpInfo")
@Api("HelpInfo操作controller")
public class HelpInfoController  extends BaseController<HelpInfo, HelpInfoManager> {
	private static final long serialVersionUID = 376679610738L;  
	
	@Resource(name="logInfoManager")
	private LogInfoManager logInfoManager;
	
	 
	@ApiOperation(value="获取helpInfo列表", notes="分页查询获取helpInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,HelpInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<HelpInfo> helpInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(helpInfoList);
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
	@ApiOperation(value="helpInfo信息", notes="根据url的id来获取helpInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "helpInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public HelpInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="HelpInfo信息修改或新增", notes="根据页面传入的HelpInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody HelpInfo helpInfo) throws Exception{	
		entity=helpInfo; 	
		entity.setPublisherTime(new Date());
		entity.setPublisherId(this.getLoginUser().getId());
		//entity.setPublisherId("402881696411707301641177a1480004");
		//填充日志
		LogInfo logInfo = new LogInfo();
		logInfo.setRadarId(this.getLoginUser().getId());
		//logInfo.setRadarId("2c92b6194ed93eae014ed941e4ab0003");
		logInfo.setTypeId("2c92482a64e8b7430164e91b8c440003");
		logInfo.setDate(new Date());
		
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){	
				logInfo.setLogContent("系统帮助表修改了参数值为：'"+entity.getDataName()+"'记录信息");
				entity = this.baseManager.update(entity); 			
			}
			else{  
				logInfo.setLogContent("系统帮助表新增了参数值为：'"+entity.getDataName()+"'记录信息");
				this.baseManager.insert(entity);			 
			}
			this.logInfoManager.insert(logInfo);
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
	@ApiOperation(value="helpInfo信息删除", notes="根据url的id来删除helpInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "helpInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
		//填充日志
		LogInfo logInfo = new LogInfo();
		//logInfo.setRadarId(this.getLoginUser().getId());
		logInfo.setRadarId("2c92b6194ed93eae014ed941e4ab0003");
		logInfo.setTypeId("2c92482a64e8b7430164e91b8c440003");
		logInfo.setDate(new Date());
		logInfo.setLogContent("系统帮助表删除了id为："+id+"的信息");
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    this.logInfoManager.insert(logInfo);
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
