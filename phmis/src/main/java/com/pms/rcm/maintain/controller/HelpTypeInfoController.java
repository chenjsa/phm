package com.pms.rcm.maintain.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.pms.rcm.maintain.manager.HelpTypeInfoManager;
import com.pms.rcm.maintain.vo.HelpTypeInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * help_type_info表HelpTypeInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName HelpTypeInfoController.java
 */
@Controller
@RequestMapping(value="/helpTypeInfo")
@Api("HelpTypeInfo操作controller")
public class HelpTypeInfoController  extends BaseController<HelpTypeInfo, HelpTypeInfoManager> {
	private static final long serialVersionUID = 919385453070L;  
	 
	@ApiOperation(value="获取helpTypeInfo列表", notes="分页查询获取helpTypeInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,HelpTypeInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<HelpTypeInfo> helpTypeInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(helpTypeInfoList);
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
	@ApiOperation(value="helpTypeInfo信息", notes="根据url的id来获取helpTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "helpTypeInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public HelpTypeInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="HelpTypeInfo信息修改或新增", notes="根据页面传入的HelpTypeInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody HelpTypeInfo helpTypeInfo) throws Exception{	
		entity=helpTypeInfo; 	
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
	@ApiOperation(value="helpTypeInfo信息删除", notes="根据url的id来删除helpTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "helpTypeInfo的ID", required = true, dataType = "String", paramType = "path")
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
