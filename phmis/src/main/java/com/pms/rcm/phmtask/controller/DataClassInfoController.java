package com.pms.rcm.phmtask.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.base.common.JQgridBean;
import com.pms.base.common.ResponseBean;
import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.manager.DataClassInfoManager;
import com.pms.rcm.phmtask.vo.DataClassInfo;
/**
 * data_class_info表DataClassInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName DataClassInfoController.java
 */
@RestController
@RequestMapping(value="/dataClassInfo")
@Api("DataClassInfo操作controller")
public class DataClassInfoController  extends BaseController<DataClassInfo, DataClassInfoManager> {
	private static final long serialVersionUID = 870737037808L;  
	public static final Logger logger = LoggerFactory.getLogger(DataClassInfoController.class); 
	@ApiOperation(value="获取dataClassInfo列表", notes="分页查询获取dataClassInfo列表信息",httpMethod = "GET") 
	@PostMapping("/list")
	@ResponseBody
	public Object list(Page page,DataClassInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		try
		{  
			 List<DataClassInfo> dataClassInfoList =this.baseManager.findPage(entity, page, true);
		     return new JQgridBean(String.valueOf(page.getTotalPage()),String.valueOf(page.getTotalResult()),dataClassInfoList);
  		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 return new ResponseBean(500, "error", e.getLocalizedMessage());		
		}
	} 
	@ApiOperation(value="dataClassInfo信息", notes="根据url的id来获取dataClassInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "dataClassInfo的ID", required = true, dataType = "String", paramType = "path")
   	@GetMapping("/getEntityById/{id}")
	@ResponseBody
	public ResponseBean  getEntityById(@PathVariable("id") String id)throws Exception{		 
		 try{
			 return  new ResponseBean(200, "success", this.baseManager.get(id));	
		}catch(Exception e){
			 ///e.printStackTrace();
			 return new ResponseBean(500, "error", e.getLocalizedMessage());	
		}					 
	}  
	@ApiOperation(value="DataClassInfo信息修改或新增", notes="根据页面传入的DataClassInfo信息进行新增或修改",httpMethod = "POST") 
	@PostMapping("/save")
	@ResponseBody 
	public Object save(@ApiParam @RequestBody DataClassInfo dataClassInfo) throws Exception{	
		entity=dataClassInfo; 	 
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				logger.info("DataClassInfo信息修改:"+entity.toString());		
			}
			else{  
				this.baseManager.insert(entity);		
				logger.info("DataClassInfo信息新增:"+entity.toString());		 
			}
			return  new ResponseBean(200, "success","保存或修改成功");
		}catch(Exception e){ 
			logger.info("DataClassInfo信息操作错误:"+getExceptionAll(e));	
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}  
	}
	 
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */  
	@ApiOperation(value="dataClassInfo信息删除", notes="根据url的id来删除dataClassInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "dataClassInfo的ID", required = true, dataType = "String", paramType = "path")
	@GetMapping("/delete/{id}")
	@ResponseBody 
	public Object delete(@PathVariable("id") String id) throws Exception{    
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("DataClassInfo执行删除:"+id);
		    return  new ResponseBean(200, "success","保存或修改成功");	
		}catch(Exception e){ 
			//e.printStackTrace(); 
			logger.info("DataClassInfo[id="+id+"]执行删除出错:"+getExceptionAll(e));
			return new ResponseBean(500, "error", getExceptionAll(e));		
		}  
	}
	@GetMapping("/getAll")
	@ResponseBody  
	public ResponseBean getAll()throws Exception{ 	
		try{
		    List<DataClassInfo> list=this.baseManager.findAll();	
			return  new ResponseBean(200, "success",list);
		}catch(Exception e){
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}				 
	} 
	@ResponseBody 
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
}
