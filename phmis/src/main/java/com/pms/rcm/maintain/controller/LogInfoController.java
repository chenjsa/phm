package com.pms.rcm.maintain.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.manager.LogInfoManager;
import com.pms.rcm.maintain.vo.LogInfo;
import com.pms.rcm.sys.manager.AdminInfoManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.AdminInfo;
import com.pms.rcm.sys.vo.User;
/**
 * log_info表LogInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName LogInfoController.java
 */
@Controller
@RequestMapping(value="/logInfo")
@Api("LogInfo操作controller")
public class LogInfoController  extends BaseController<LogInfo, LogInfoManager> {
	private static final long serialVersionUID = 206789317729L;  
	@Autowired
	private UserManager userManager;
	@Autowired
	private AdminInfoManager adminInfoManager;
	@ApiOperation(value="获取logInfo列表", notes="分页查询获取logInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,LogInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<LogInfo> logInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
			for(LogInfo info:logInfoList){
				User user=this.userManager.getUser(info.getRadarId());
				if(user!=null)
					info.setUserName(user.getName());
				else{
					AdminInfo adminInfo=adminInfoManager.get(info.getRadarId());
					if(adminInfo!=null){
						info.setUserName(adminInfo.getAdminId());
					}
				}
			}
		 	JSONArray jsonarray = JSONArray.fromObject(logInfoList);
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
	@ApiOperation(value="logInfo信息", notes="根据url的id来获取logInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "logInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public LogInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="LogInfo信息修改或新增", notes="根据页面传入的LogInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody LogInfo logInfo) throws Exception{	
		entity=logInfo; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 			
			}
			else{  
				this.baseManager.insert(entity);			 
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
	@ApiOperation(value="logInfo信息删除", notes="根据url的id来删除logInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "logInfo的ID", required = true, dataType = "String", paramType = "path")
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
	     
	 
}
