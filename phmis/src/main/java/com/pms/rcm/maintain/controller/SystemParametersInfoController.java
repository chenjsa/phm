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
import com.pms.rcm.maintain.vo.LogInfo;
import com.pms.rcm.maintain.vo.SystemParametersInfo;
import com.pms.rcm.maintain.manager.LogInfoManager;
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * system_parameters_info表SystemParametersInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName SystemParametersInfoController.java
 */
@Controller
@RequestMapping(value="/systemParametersInfo")
@Api("SystemParametersInfo操作controller")
public class SystemParametersInfoController  extends BaseController<SystemParametersInfo, SystemParametersInfoManager> {
	private static final long serialVersionUID = 826666645880L;  
	 
	@Resource(name="logInfoManager")
	private LogInfoManager logInfoManager;
	
	
	@ApiOperation(value="获取systemParametersInfo列表", notes="分页查询获取systemParametersInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,SystemParametersInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<SystemParametersInfo> systemParametersInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(systemParametersInfoList);
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
	@ApiOperation(value="systemParametersInfo信息", notes="根据url的id来获取systemParametersInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "systemParametersInfo的ID", required = true, dataType = "String ", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public SystemParametersInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="SystemParametersInfo信息修改或新增", notes="根据页面传入的SystemParametersInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody SystemParametersInfo systemParametersInfo) throws Exception{	
		entity=systemParametersInfo;
		//获取系统时间
		entity.setMaintenanceTime(new Date());
		//获取操作人员ID
		entity.setStationId(this.getLoginUser().getId());
		System.out.println(this.getLoginUser().getId());
	//	entity.setStationId("2c92b6194ed93eae014ed941e4ab0003");
		LogInfo logInfo = new LogInfo();
		logInfo.setRadarId(this.getLoginUser().getId());
		//logInfo.setRadarId("2c92b6194ed93eae014ed941e4ab0003");
		logInfo.setTypeId("402881ed64e65fb50164e6723c110007");
		logInfo.setDate(new Date());
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){	
				logInfo.setLogContent("系统参数表修改了参数值为：'"+entity.getSystemParametersCode()+"'记录信息");
				entity = this.baseManager.update(entity); 			
			}
			else{  
				logInfo.setLogContent("系统参数表新增了 参数值为："+entity.getParametersValues()+"的信息");
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
	@ApiOperation(value="systemParametersInfo信息删除", notes="根据url的id来删除systemParametersInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "systemParametersInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
		LogInfo logInfo = new LogInfo();
		logInfo.setRadarId(this.getLoginUser().getId());
		//logInfo.setRadarId("2c92b6194ed93eae014ed941e4ab0003");
		logInfo.setDate(new Date());
		logInfo.setTypeId("402881ed64e65fb50164e6723c110007");
		logInfo.setLogContent("系统参数表删除了id为："+id+"的信息");
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
