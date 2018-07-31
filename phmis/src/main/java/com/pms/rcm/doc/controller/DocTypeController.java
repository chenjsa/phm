package com.pms.rcm.doc.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.doc.manager.DocTypeManager;
import com.pms.rcm.doc.vo.DocType;
import com.pms.rcm.doc.vo.DocTypeInputElements;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * doc_type表DocType维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName DocTypeController.java
 */
@Controller
@RequestMapping(value="/docType")
@Api("DocType操作controller")
public class DocTypeController  extends BaseController<DocType, DocTypeManager> {
	private static final long serialVersionUID = 203847249063L;  
	 
	@ApiOperation(value="获取docType列表", notes="分页查询获取docType列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,DocType entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<DocType> docTypeList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(docTypeList);
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
	@ApiOperation(value="sealInfo信息", notes="根据url的id来获取sealInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "sealInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/findAll",method=RequestMethod.GET)
	public List<DocType> findAll(){ 	 		  
		
		try
		{  
			List<DocType> docTypeList =this.baseManager.findAll();
			return docTypeList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@ApiOperation(value="docType信息", notes="根据url的id来获取docType信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "docType的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public DocType getEntityById(@PathVariable("id") String id)throws Exception{		 
		 this.entity=this.baseManager.get(id);
	     return this.entity;
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="DocType信息修改或新增", notes="根据页面传入的DocType信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody DocType docType) throws Exception{	
		entity=docType; 	
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
	@ApiOperation(value="docType信息删除", notes="根据url的id来删除docType信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "docType的ID", required = true, dataType = "String", paramType = "path")
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
