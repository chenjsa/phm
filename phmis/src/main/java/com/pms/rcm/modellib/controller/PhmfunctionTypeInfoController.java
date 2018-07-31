package com.pms.rcm.modellib.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray; 
import net.sf.json.JSONObject;
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
import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.modellib.vo.PhmfunctionTypeInfo;

import com.pms.rcm.modellib.manager.PhmfunctionTypeInfoManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * phmfunction_type_info表PhmfunctionTypeInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName PhmfunctionTypeInfoController.java
 */
@Controller
@RequestMapping(value="/phmfunctionTypeInfo")
@Api("PhmfunctionTypeInfo操作controller")
public class PhmfunctionTypeInfoController  extends BaseController<PhmfunctionTypeInfo, PhmfunctionTypeInfoManager> {
	private static final long serialVersionUID = 372541385841L;  
	 
	@ApiOperation(value="获取phmfunctionTypeInfo列表", notes="分页查询获取phmfunctionTypeInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,PhmfunctionTypeInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<PhmfunctionTypeInfo> phmfunctionTypeInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(phmfunctionTypeInfoList);
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
	@ApiOperation(value="phmfunctionTypeInfo信息", notes="根据url的id来获取phmfunctionTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "phmfunctionTypeInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public PhmfunctionTypeInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="PhmfunctionTypeInfo信息修改或新增", notes="根据页面传入的PhmfunctionTypeInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody PhmfunctionTypeInfo phmfunctionTypeInfo) throws Exception{	
		entity=phmfunctionTypeInfo; 	
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
	@ApiOperation(value="phmfunctionTypeInfo信息删除", notes="根据url的id来删除phmfunctionTypeInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "phmfunctionTypeInfo的ID", required = true, dataType = "String", paramType = "path")
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
	public List<PhmfunctionTypeInfo> getAll()throws Exception{		 
		return this.baseManager.findAll();						 
	}
	 
}
