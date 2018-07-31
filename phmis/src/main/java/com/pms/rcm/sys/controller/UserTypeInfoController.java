package com.pms.rcm.sys.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.sys.manager.UserTypeInfoManager;
import com.pms.rcm.sys.vo.UserTypeInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * user_type_info表UserTypeInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName UserTypeInfoController.java
 */
@Controller
@RequestMapping(value="/userTypeInfo")
@Api("UserTypeInfo操作controller")
public class UserTypeInfoController  extends BaseController<UserTypeInfo, UserTypeInfoManager> {
	private static final long serialVersionUID = 939476455249L;  
	public static final Logger logger = LoggerFactory.getLogger(UserTypeInfoController.class); 
	@ApiOperation(value="获取userTypeInfo列表", notes="分页查询获取userTypeInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,UserTypeInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<UserTypeInfo> userTypeInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(userTypeInfoList);
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
	@ApiOperation(value="userTypeInfo信息", notes="根据url的id来获取userTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "userTypeInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public UserTypeInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="UserTypeInfo信息修改或新增", notes="根据页面传入的UserTypeInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody UserTypeInfo userTypeInfo) throws Exception{	
		entity=userTypeInfo; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				logger.info("UserTypeInfo信息修改:"+entity.toString());		
			}
			else{  
				this.baseManager.insert(entity);		
				logger.info("UserTypeInfo信息新增:"+entity.toString());		 
			}
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
			logger.info("UserTypeInfo信息操作错误:"+getExceptionAll(e));	
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
	@ApiOperation(value="userTypeInfo信息删除", notes="根据url的id来删除userTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "userTypeInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("UserTypeInfo[id="+id+"]执行删除");	
		}catch(Exception e){
			errInfo = "err";
		///	e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();  
		 
			logger.info("UserTypeInfo[id="+id+"]执行删除出错:"+getExceptionAll(e.getCause()));	 
		} 
		 
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	     
	
	@ResponseBody
    @RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<UserTypeInfo> getAll()throws Exception{		 
		return this.baseManager.findAll();						 
	} 
}
