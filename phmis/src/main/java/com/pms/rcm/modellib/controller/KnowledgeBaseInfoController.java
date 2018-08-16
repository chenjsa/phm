package com.pms.rcm.modellib.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.manager.KnowledgeBaseInfoManager;
import com.pms.rcm.modellib.vo.KnowledgeBaseInfo;
import com.pms.rcm.radar.manager.KeyModulesInfoManager;
import com.pms.rcm.radar.manager.RadarDeviceInfoManager;
import com.pms.rcm.radar.manager.RadarSubsystemInfoManager;
import com.pms.rcm.radar.vo.KeyModulesInfo;
import com.pms.rcm.radar.vo.RadarDeviceInfo;
import com.pms.rcm.radar.vo.RadarSubsystemInfo;
/**
 * knowledge_base_info表KnowledgeBaseInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName KnowledgeBaseInfoController.java
 */
@Controller
@RequestMapping(value="/knowledgeBaseInfo")
@Api("KnowledgeBaseInfo操作controller")
public class KnowledgeBaseInfoController  extends BaseController<KnowledgeBaseInfo, KnowledgeBaseInfoManager> {
	private static final long serialVersionUID = 417686308755L;  
	
	@ApiOperation(value="获取knowledgeBaseInfo列表", notes="分页查询获取knowledgeBaseInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,KnowledgeBaseInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<KnowledgeBaseInfo> knowledgeBaseInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(knowledgeBaseInfoList);
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
	 @Autowired
     private RadarDeviceInfoManager radarDeviceInfoManager;
	 @Autowired
	 private RadarSubsystemInfoManager  radarSubsystemInfoManager;
	 @Autowired
	 private KeyModulesInfoManager keyModulesInfoManager;
	@ApiOperation(value="获取knowledgeBaseInfo列表", notes="分页查询获取knowledgeBaseInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/listSelect")
	public String listSelect(Page page,KnowledgeBaseInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		     
		  
		KeyModulesInfo keyModulesInfo=  keyModulesInfoManager.get(entity.getRadarModuleId());
		entity.setModuleId(Integer.valueOf(keyModulesInfo.getModuleNumberCode()));
		RadarSubsystemInfo sys=this.radarSubsystemInfoManager.get(entity.getPhmsubsId());
		entity.setSubsystemTypeId(sys.getSubsystemInfo().getId());
		RadarDeviceInfo radar=this.radarDeviceInfoManager.get(entity.getRadarId());
		entity.setRadarTypeId(radar.getRadarTypeInfo().getId());
		List<KnowledgeBaseInfo> knowledgeBaseInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(knowledgeBaseInfoList);
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
	@ApiOperation(value="knowledgeBaseInfo信息", notes="根据url的id来获取knowledgeBaseInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "knowledgeBaseInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public KnowledgeBaseInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="KnowledgeBaseInfo信息修改或新增", notes="根据页面传入的KnowledgeBaseInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody KnowledgeBaseInfo knowledgeBaseInfo) throws Exception{	
		entity=knowledgeBaseInfo; 	
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
	@ApiOperation(value="knowledgeBaseInfo信息删除", notes="根据url的id来删除knowledgeBaseInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "knowledgeBaseInfo的ID", required = true, dataType = "String", paramType = "path")
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
	public Map getAll()throws Exception{		 
		return this.baseManager.getAll();						 
	} 
}
