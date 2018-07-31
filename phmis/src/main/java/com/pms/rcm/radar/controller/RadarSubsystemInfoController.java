package com.pms.rcm.radar.controller;
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

import com.pms.base.common.BusinessException;
import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.DateUtil;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.radar.vo.RadarSubsystemInfo;

import com.pms.rcm.radar.manager.RadarSubsystemInfoManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * radar_subsystem_info表RadarSubsystemInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName RadarSubsystemInfoController.java
 */
@Controller
@RequestMapping(value="/radarSubsystemInfo")
@Api("RadarSubsystemInfo操作controller")
public class RadarSubsystemInfoController  extends BaseController<RadarSubsystemInfo, RadarSubsystemInfoManager> {
	private static final long serialVersionUID = 249229401226L;  
	 
	@ApiOperation(value="获取radarSubsystemInfo列表", notes="分页查询获取radarSubsystemInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,RadarSubsystemInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<RadarSubsystemInfo> radarSubsystemInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(radarSubsystemInfoList);
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
	@ApiOperation(value="radarSubsystemInfo信息", notes="根据url的id来获取radarSubsystemInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarSubsystemInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public RadarSubsystemInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="RadarSubsystemInfo信息修改或新增", notes="根据页面传入的RadarSubsystemInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody RadarSubsystemInfo radarSubsystemInfo) throws Exception{	
		entity=radarSubsystemInfo; 	
		entity.setBeginTime(DateUtil.parse(entity.getBeginTimeStr1()));
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 			
			}
			else{  
				this.baseManager.insert(entity);			 
			}
		}catch(BusinessException e){
			errInfo = "err";
			e.printStackTrace();
			msg=e.getMessage().toString();
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
	@ApiOperation(value="radarSubsystemInfo信息删除", notes="根据url的id来删除radarSubsystemInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarSubsystemInfo的ID", required = true, dataType = "String", paramType = "path")
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
	public List<RadarSubsystemInfo> getAll()throws Exception{		 
		return this.baseManager.findAll();						 
	} 
	@ResponseBody
    @RequestMapping(value="/getListByRadarId/{radarId}",method=RequestMethod.GET)
	public List<RadarSubsystemInfo> getListByRadarId(@PathVariable("radarId") String radarId)throws Exception{		 
		return this.baseManager.getListByRadarId(radarId);					 
	} 
}
