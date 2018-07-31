package com.pms.rcm.maintain.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.manager.LogInfoManager;
import com.pms.rcm.maintain.manager.ParTypeInfoManager;
import com.pms.rcm.maintain.vo.LogInfo;
import com.pms.rcm.maintain.vo.ParTypeInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/parTypeInfo")
@Api("ParTypeInfo操作controller")
public class ParTypeInfoController extends BaseController<ParTypeInfo, ParTypeInfoManager> {

	@Resource(name="logInfoManager")
	private LogInfoManager logInfoManager;
	
	/**
	 * Parameters_type_info表ParTypeInfo查询
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-23
	 * @fileName ParTypeInfoController.java
	 */
	@ApiOperation(value = "获取parrTypeInfo列表", notes = "分页查询获取parrTypeInfo列表信息", httpMethod = "GET")
	@ResponseBody
	@RequestMapping(value = "/list")
	public String list(Page page, ParTypeInfo entity) {
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows()));
		List<ParTypeInfo> parTypeInfoList = this.baseManager.findPage(entity, page, true);
		try {
			JSONArray jsonarray = JSONArray.fromObject(parTypeInfoList);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(page.getTotalPage()));
			jsonobject.put("records", String.valueOf(page.getTotalResult()));
			jsonobject.put("rows", jsonarray);
			String basicbridgelistString = jsonobject.toString();
			System.out.println("basicbridgelistString--" + basicbridgelistString);
			return basicbridgelistString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Parameters_type_info表ParTypeInfo新增
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-23
	 * @fileName ParTypeInfoController.java
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@ApiOperation(value = "parTypeInfo信息修改或新增", notes = "根据页面传入的parTypeInfo信息进行新增或修改", httpMethod = "POST")
    public Object save(@ApiParam @RequestBody ParTypeInfo parTypeInfo) throws Exception {
		parTypeInfo.setMainTime(new Date());
		this.entity = parTypeInfo;
		String errInfo = "success";
		String msg = "";
		//填充日志信息
		LogInfo logInfo = new LogInfo();
		logInfo.setRadarId(this.getLoginUser().getId());
		//logInfo.setRadarId("402881696411707301641177a1480004");
		logInfo.setDate(new Date());
		logInfo.setTypeId("402881ed64e65fb50164e6723c110007");
		try {
			if (isUpdate(entity)) {
				logInfo.setLogContent("系统参数类型表修改了参数类型为：'"+entity.getTypeName()+"'记录信息");
				this.baseManager.update(entity);
			} else {
				logInfo.setLogContent("系统参数类型表新增了 参数类型为："+entity.getTypeName()+"的信息");
				this.baseManager.insert(entity);
			}
			this.logInfoManager.insert(logInfo);

		} catch (Exception e) {
			errInfo = "err";
			e.printStackTrace();
			msg = "操作出错：" + e.getLocalizedMessage();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	
	
	
	/**
	 * Parameters_type_info表ParTypeInfo单体查询
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-23
	 * @fileName ParTypeInfoController.java
	 */
	@ApiOperation(value = "获取parrTypeInfo单条数据", notes = "通过ID获取单条数据", httpMethod = "GET")
	@ResponseBody
	@RequestMapping(value = "/getEntityById/{id}",method=RequestMethod.GET)
	public ParTypeInfo getEntityById(@PathVariable("id") String id) throws Exception{
		this.entity = this.baseManager.get(id);
		return this.entity;
	}
	
	
	/**
	 * Parameters_type_info表ParTypeInfo删除
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-23
	 * @fileName ParTypeInfoController.java
	 */
	@ResponseBody
	@ApiOperation(value="parTypeInfo信息删除", notes="根据url的id来删除parTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "parTypeInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
		//填充日志信息
		LogInfo logInfo = new LogInfo();
		//logInfo.setRadarId(this.getLoginUser().getId());
		logInfo.setRadarId("402881696411707301641177a1480004");
		logInfo.setDate(new Date());
		logInfo.setTypeId("402881ed64e65fb50164e6723c110007");
		logInfo.setLogContent("系统参数类型表删除了ID："+id+"的记录信息！");
	 	try{
	 		baseManager.delete(this.entity, id);//执行删除
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
