package com.pms.rcm.soft.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pms.base.common.JQgridBean;
import com.pms.base.common.ResponseBean;
import com.pms.base.controller.BaseController;
import com.pms.base.util.FileUtil;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;
import com.pms.rcm.soft.manager.SoftwareVersionInfoManager;
import com.pms.rcm.soft.vo.SoftwareVersionInfo;
import com.pms.rcm.sys.vo.User;
/**
 * software_version_info表SoftwareVersionInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName SoftwareVersionInfoController.java
 */
@RestController
@RequestMapping(value="/softwareVersionInfo")
@Api("SoftwareVersionInfo操作controller")
public class SoftwareVersionInfoController  extends BaseController<SoftwareVersionInfo, SoftwareVersionInfoManager> {
	private static final long serialVersionUID = 154200322345L;  
	public static final Logger logger = LoggerFactory.getLogger(SoftwareVersionInfoController.class); 
	@ApiOperation(value="获取softwareVersionInfo列表", notes="分页查询获取softwareVersionInfo列表信息",httpMethod = "GET") 
	@PostMapping("/list")
	@ResponseBody
	public Object list(Page page,SoftwareVersionInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		try
		{  
			 List<SoftwareVersionInfo> softwareVersionInfoList =this.baseManager.findPage(entity, page, true);
		     return new JQgridBean(String.valueOf(page.getTotalPage()),String.valueOf(page.getTotalResult()),softwareVersionInfoList);
  		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 return new ResponseBean(500, "error", e.getLocalizedMessage());		
		}
	} 
	@ApiOperation(value="softwareVersionInfo信息", notes="根据url的id来获取softwareVersionInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "softwareVersionInfo的ID", required = true, dataType = "String", paramType = "path")
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
	@ApiOperation(value="SoftwareVersionInfo信息修改或新增", notes="根据页面传入的SoftwareVersionInfo信息进行新增或修改",httpMethod = "POST") 
	@PostMapping("/save")
	@ResponseBody 
	public Object save(@ApiParam @RequestBody SoftwareVersionInfo softwareVersionInfo) throws Exception{	
		entity=softwareVersionInfo; 	 
	 	try{
	 		entity.setReleaseTime(new java.sql.Date(System.currentTimeMillis()));
	 		User user=this.getLoginUser();
	 		entity.setUserId(user.getName());
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				logger.info("SoftwareVersionInfo信息修改:"+entity.toString());		
			}
			else{  
				this.baseManager.insert(entity);		
				logger.info("SoftwareVersionInfo信息新增:"+entity.toString());		 
			}
			return  new ResponseBean(200, "success","保存或修改成功");
		}catch(Exception e){ 
			logger.info("SoftwareVersionInfo信息操作错误:"+getExceptionAll(e));	
			return new ResponseBean(500, "error", getExceptionAll(e));	
		}  
	}
	 
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */  
	@ApiOperation(value="softwareVersionInfo信息删除", notes="根据url的id来删除softwareVersionInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "softwareVersionInfo的ID", required = true, dataType = "String", paramType = "path")
	@GetMapping("/delete/{id}")
	@ResponseBody 
	public Object delete(@PathVariable("id") String id) throws Exception{    
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("SoftwareVersionInfo执行删除:"+id);
		    return  new ResponseBean(200, "success","保存或修改成功");	
		}catch(Exception e){ 
			//e.printStackTrace(); 
			logger.info("SoftwareVersionInfo[id="+id+"]执行删除出错:"+getExceptionAll(e));
			return new ResponseBean(500, "error", getExceptionAll(e));		
		}  
	}
	@Autowired
    private SystemParametersInfoManager systemParametersInfoManager;
	@ResponseBody
    @RequestMapping(value = "/uploadFile" ,method = RequestMethod.POST)
    public SoftwareVersionInfo uploadFile(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");

        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
                multipartFile=(MultipartFile) map.get(obj);

               }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path = "";
        SoftwareVersionInfo entity=new SoftwareVersionInfo();
        try {
       	 	List<SystemParametersInfo> ss=(List<SystemParametersInfo>)this.systemParametersInfoManager.find("from SystemParametersInfo where systemParametersCode='uploadFile'");
       	 	String uploadFile=ss.get(0).getParametersValues();
            path=uploadFile;
			File fileFloder = new File(path);
			if(!fileFloder.exists()){
				fileFloder.mkdirs();
			}
			String fileName = filename.substring(filename.lastIndexOf("\\") + 1);
		   // String fileType = fileName.substring(fileName.lastIndexOf("."));
			File destFile  = new File(path,filename);
			inputStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(inputStream, destFile); 		
			entity.setSoftwareUrl(path+File.separator+fileName);
			entity.setFileSize(FileUtil.getFilesize(path+File.separator+fileName).intValue());

        } catch (Exception e) {
            e.printStackTrace();
        }       
        return entity;
    }
	@GetMapping("/getAll")
	@ResponseBody  
	public ResponseBean getAll()throws Exception{ 	
		try{
		    List<SoftwareVersionInfo> list=this.baseManager.findAll();	
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
