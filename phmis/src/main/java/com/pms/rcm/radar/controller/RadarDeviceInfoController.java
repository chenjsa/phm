package com.pms.rcm.radar.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.radar.manager.RadarDeviceInfoManager;
import com.pms.rcm.radar.manager.RadarStateInfoManager;
import com.pms.rcm.radar.manager.RadarStateInfoValueManager;
import com.pms.rcm.radar.manager.RadarTypeInfoManager;
import com.pms.rcm.radar.manager.RadarUserInfoManager;
import com.pms.rcm.radar.manager.TaskAttributeManager;
import com.pms.rcm.radar.vo.RadarDeviceInfo;
import com.pms.rcm.radar.vo.RadarStateInfo;
import com.pms.rcm.radar.vo.RadarStateInfoValue;
import com.pms.rcm.sys.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * radar_device_info表RadarDeviceInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName RadarDeviceInfoController.java
 */
@Controller
@RequestMapping(value="/radarDeviceInfo")
@Api("RadarDeviceInfo操作controller")
public class RadarDeviceInfoController  extends BaseController<RadarDeviceInfo, RadarDeviceInfoManager> {
	private static final long serialVersionUID = 940825150092L;  
	public static final Logger logger = LoggerFactory.getLogger(RadarDeviceInfoController.class); 
	@Resource(name="radarUserInfoManager")
	private RadarUserInfoManager radarUserInfoManager;
	@Resource(name="radarTypeInfoManager")
	private RadarTypeInfoManager radarTypeInfoManager;
	@Resource(name="taskAttributeManager")
	private TaskAttributeManager taskAttributeManager;
	@Resource(name="radarStateInfoManager")
	private RadarStateInfoManager radarStateInfoManager;
	@Resource(name="radarStateInfoValueManager")
	private RadarStateInfoValueManager radarStateInfoValueManager;
	@ApiOperation(value="获取radarDeviceInfo列表", notes="分页查询获取radarDeviceInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,RadarDeviceInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		User user=this.getLoginUser();
		if(user.isRadar()){
			entity.setStationId(user.getStationId());
		}
		List<RadarDeviceInfo> radarDeviceInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(radarDeviceInfoList);
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
	@ApiOperation(value="radarDeviceInfo信息", notes="根据url的id来获取radarDeviceInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarDeviceInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public RadarDeviceInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="RadarDeviceInfo信息修改或新增", notes="根据页面传入的RadarDeviceInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody RadarDeviceInfo radarDeviceInfo) throws Exception{	
		entity=radarDeviceInfo; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				logger.info("RadarDeviceInfo信息修改:"+entity.toString());	
			}
			else{  
				this.baseManager.insert(entity);	
				logger.info("RadarDeviceInfo信息修改:"+entity.toString());	
			}
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
			logger.info("RadarDeviceInfo信息操作错误:"+getExceptionAll(e));	
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
	@ApiOperation(value="radarDeviceInfo信息删除", notes="根据url的id来删除radarDeviceInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarDeviceInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("RadarDeviceInfo[id="+id+"]执行删除");
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
			logger.info("RadarDeviceInfo信息操作错误:"+getExceptionAll(e));	
		} 
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	     
	@ResponseBody
    @RequestMapping(value="/getAll",method=RequestMethod.GET)
	public Map getAll()throws Exception{	
		Map map = new HashMap();  
		User user=this.getLoginUser();
		List radarUserInfos=new ArrayList();
		if(user.isAdmin()){
			 radarUserInfos= this.radarUserInfoManager.findAll();
		}else{
			String[] ids=new String[1];
			ids[0]=user.getStationId();
			radarUserInfos=this.radarUserInfoManager.findByIds(ids);
		}
		map.put("radarUserInfos", radarUserInfos);
		List radarTypeInfos=radarTypeInfoManager.findAll();
		map.put("radarTypeInfos", radarTypeInfos);
		List taskAttributes=taskAttributeManager.findAll();
		map.put("taskAttributes", taskAttributes);
		
		List<RadarStateInfo> radarStateInfos=radarStateInfoManager.findAll();
		for(RadarStateInfo radarStateInfo:radarStateInfos){
			List<RadarStateInfoValue> radarStateInfoValues=this.radarStateInfoValueManager.find("from RadarStateInfoValue where radarStateInfoId='"+radarStateInfo.getId()+"'");
			List<RadarStateInfoValue> li=new ArrayList<RadarStateInfoValue>();
			for(RadarStateInfoValue r:radarStateInfoValues){
				RadarStateInfoValue rr=new RadarStateInfoValue();
				rr.setId(r.getId());
				rr.setSvalue(r.getSvalue());
				li.add(rr);
			}
			radarStateInfo.getRadarStateInfoValues().addAll(li);
		}
		map.put("radarStateInfos", radarStateInfos);
		
		
		return map;
		 			 
	} 
}
