package com.pms.rcm.radar.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.radar.manager.ModuleTypeInfoManager;
import com.pms.rcm.radar.vo.ModuleTypeInfo;
/**
 * module_type_info表ModuleTypeInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName ModuleTypeInfoController.java
 */
@Controller
@RequestMapping(value="/moduleTypeInfo")
@Api("ModuleTypeInfo操作controller")
public class ModuleTypeInfoController  extends BaseController<ModuleTypeInfo, ModuleTypeInfoManager> {
	private static final long serialVersionUID = 154772738087L;  
	 
	@ApiOperation(value="获取moduleTypeInfo列表", notes="分页查询获取moduleTypeInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,ModuleTypeInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<ModuleTypeInfo> moduleTypeInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(moduleTypeInfoList);
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
	@ApiOperation(value="moduleTypeInfo信息", notes="根据url的id来获取moduleTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "moduleTypeInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public ModuleTypeInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@ApiOperation(value="moduleTypeInfo信息", notes="根据url的id来获取moduleTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "moduleTypeInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/checkIdValidMethod",method=RequestMethod.POST)
	public Map<String, Boolean> checkIdValidMethod(@RequestParam String id)throws Exception{		
		 boolean result = true;
		 this.entity=this.baseManager.get(id); 	
		 Map<String, Boolean> map = new HashMap<String, Boolean>();	      
		 if(this.entity==null){
			  map.put("valid", result);
		 }else{
			 map.put("valid", false);
		 }
		 return map;
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="ModuleTypeInfo信息修改或新增", notes="根据页面传入的ModuleTypeInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody ModuleTypeInfo moduleTypeInfo) throws Exception{	
		entity=moduleTypeInfo; 	
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
	@ApiOperation(value="moduleTypeInfo信息删除", notes="根据url的id来删除moduleTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "moduleTypeInfo的ID", required = true, dataType = "String", paramType = "path")
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
    @RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<ModuleTypeInfo> getAll()throws Exception{		 
		return this.baseManager.findAll();						 
	}     
	 
}
