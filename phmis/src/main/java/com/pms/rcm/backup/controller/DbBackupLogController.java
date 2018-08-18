package com.pms.rcm.backup.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.sql.Date;
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
import com.pms.base.util.DbFH;
import com.pms.base.util.FileUtil;
import com.pms.base.util.Page;
import com.pms.rcm.backup.manager.DbBackupLogManager;
import com.pms.rcm.backup.vo.DbBackupLog;
/**
 * db_backup_log表DbBackupLog维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName DbBackupLogController.java
 */
@RestController
@RequestMapping(value="/dbBackupLog")
@Api("DbBackupLog操作controller")
public class DbBackupLogController  extends BaseController<DbBackupLog, DbBackupLogManager> {
	private static final long serialVersionUID = 510253963570L;  
	public static final Logger logger = LoggerFactory.getLogger(DbBackupLogController.class); 
	@ApiOperation(value="获取dbBackupLog列表", notes="分页查询获取dbBackupLog列表信息",httpMethod = "GET") 
	@PostMapping("/list")
	@ResponseBody
	public Object list(Page page,DbBackupLog entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		try
		{  
			 List<DbBackupLog> dbBackupLogList =this.baseManager.findPage(entity, page, true);
		     return new JQgridBean(String.valueOf(page.getTotalPage()),String.valueOf(page.getTotalResult()),dbBackupLogList);
  		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 return new ResponseBean(500, "error", e.getLocalizedMessage());		
		}
	} 
	@ApiOperation(value="dbBackupLog信息", notes="根据url的id来获取dbBackupLog信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "dbBackupLog的ID", required = true, dataType = "String", paramType = "path")
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
	@ApiOperation(value="DbBackupLog信息修改或新增", notes="根据页面传入的DbBackupLog信息进行新增或修改",httpMethod = "POST") 
	@PostMapping("/save")
	@ResponseBody 
	public Object save(@ApiParam @RequestBody DbBackupLog dbBackupLog) throws Exception{	
		entity=dbBackupLog; 	 
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				logger.info("DbBackupLog信息修改:"+entity.toString());		
			}
			else{  
				this.baseManager.insert(entity);		
				logger.info("DbBackupLog信息新增:"+entity.toString());		 
			}
			return  new ResponseBean(200, "success","保存或修改成功");
		}catch(Exception e){ 
			logger.info("DbBackupLog信息操作错误:"+getExceptionAll(e));	
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}  
	}
	
	@ApiOperation(value="DbBackupLog信息修改或新增", notes="根据页面传入的DbBackupLog信息进行新增或修改",httpMethod = "POST") 
	@PostMapping("/backup")
	@ResponseBody 
	public Object backup() throws Exception{	
		///entity=dbBackupLog; 		 
	 	try{
	 		String str = DbFH.getDbFH().backup("").toString();//调用数据库备份
			System.out.println(FileUtil.getFilesize(str));
			entity=new DbBackupLog();
			entity.setBackupModel("临时备份");
			entity.setBackupName(str.substring(str.lastIndexOf("/")+1));
			entity.setBackupTime(new java.sql.Timestamp(System.currentTimeMillis()));
			entity.setBackupType("全量备份");
			entity.setFileSize(Double.valueOf(FileUtil.getFilesize(str)/1024).shortValue()+"M");
			entity.setStorePath(str); 
			entity.setBackupStatus("完成");
			this.baseManager.insert(entity);		
			logger.info("DbBackupLog信息新增:"+entity.toString());	 
			return  new ResponseBean(200, "success","保存或修改成功");
		}catch(Exception e){ 
			logger.info("DbBackupLog信息操作错误:"+getExceptionAll(e));	
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}  
	}
	@ApiOperation(value="DbBackupLog信息修改或新增", notes="根据页面传入的DbBackupLog信息进行新增或修改",httpMethod = "POST") 
	@PostMapping("/recover/{id}")
	@ResponseBody 
	public Object recover(@PathVariable("id") String id)throws Exception{	
		entity=this.baseManager.get(id); 		 
	 	try{ 
	 				logger.info("开始-》恢复");	 
		 		this.baseManager.runsqlBySpringUtils(entity.getStorePath());//调用数据库备份
		 			 
				///System.out.println(FileUtil.getFilesize(str));			 	
				logger.info("完成-》恢复");	  
	 	 
	 		
			return  new ResponseBean(200, "success","保存或修改成功");
		}catch(Exception e){ 
			e.printStackTrace();
			logger.info("D恢复数据库错误:"+getExceptionAll(e));	
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}  
	}
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */  
	@ApiOperation(value="dbBackupLog信息删除", notes="根据url的id来删除dbBackupLog信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "dbBackupLog的ID", required = true, dataType = "String", paramType = "path")
	@GetMapping("/delete/{id}")
	@ResponseBody 
	public Object delete(@PathVariable("id") String id) throws Exception{    
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("DbBackupLog执行删除:"+id);
		    return  new ResponseBean(200, "success","保存或修改成功");	
		}catch(Exception e){ 
			//e.printStackTrace(); 
			logger.info("DbBackupLog[id="+id+"]执行删除出错:"+getExceptionAll(e));
			return new ResponseBean(500, "error", getExceptionAll(e));		
		}  
	}
	@GetMapping("/getAll")
	@ResponseBody  
	public ResponseBean getAll()throws Exception{ 	
		try{
		    List<DbBackupLog> list=this.baseManager.findAll();	
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
