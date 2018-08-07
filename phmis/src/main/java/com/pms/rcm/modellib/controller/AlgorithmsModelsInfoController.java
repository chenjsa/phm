package com.pms.rcm.modellib.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pms.base.controller.BaseController;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page; 
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;
import com.pms.rcm.modellib.manager.AlgorithmsModelsInfoManager;
import com.pms.rcm.modellib.vo.AlgorithmsModelsInfo;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.User;
/**
 * algorithms_models_info表AlgorithmsModelsInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName AlgorithmsModelsInfoController.java
 */
@Controller
@RequestMapping(value="/algorithmsModelsInfo")
@Api("AlgorithmsModelsInfo操作controller")
public class AlgorithmsModelsInfoController  extends BaseController<AlgorithmsModelsInfo, AlgorithmsModelsInfoManager> {
	private static final long serialVersionUID = 413695704002L;  
	@Autowired
    private SystemParametersInfoManager systemParametersInfoManager;
	@ApiOperation(value="获取algorithmsModelsInfo列表", notes="分页查询获取algorithmsModelsInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,AlgorithmsModelsInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<AlgorithmsModelsInfo> algorithmsModelsInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(algorithmsModelsInfoList);
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
	@ApiOperation(value="algorithmsModelsInfo信息", notes="根据url的id来获取algorithmsModelsInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "algorithmsModelsInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public AlgorithmsModelsInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="AlgorithmsModelsInfo信息修改或新增", notes="根据页面传入的AlgorithmsModelsInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody AlgorithmsModelsInfo algorithmsModelsInfo) throws Exception{	
		entity=algorithmsModelsInfo; 	
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
	@ApiOperation(value="algorithmsModelsInfo信息删除", notes="根据url的id来删除algorithmsModelsInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "algorithmsModelsInfo的ID", required = true, dataType = "String", paramType = "path")
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
    @RequestMapping(value="/getSelect",method=RequestMethod.GET)
	public Map getSelect()throws Exception{		 
		 return this.baseManager.getSelect();		 
	}
	      
	@ResponseBody
    @RequestMapping(value="/getAllByPhmfunctionTypeId/{phmfunctionTypeId}",method=RequestMethod.POST)
	public List<AlgorithmsModelsInfo> getAllByPhmfunctionTypeId(@PathVariable("phmfunctionTypeId") String phmfunctionTypeId)throws Exception{		 
		return this.baseManager.getAllByPhmfunctionTypeId(phmfunctionTypeId);	 					 
	} 
	
	@ResponseBody
    @RequestMapping(value = "/uploadFile" ,method = RequestMethod.POST)
    public AlgorithmsModelsInfo uploadFile(HttpServletRequest request,HttpServletResponse response)
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
        AlgorithmsModelsInfo entity=new AlgorithmsModelsInfo();
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
			entity.setUrl(path+File.separator+fileName);
			entity.setGenerateTime(new Date());

        } catch (Exception e) {
            e.printStackTrace();
        }       
        return entity;
    }
	 
	 
}
