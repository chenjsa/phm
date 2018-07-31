package com.pms.rcm.radar.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.pms.rcm.radar.vo.ProvinceInfo;
import com.pms.rcm.radar.vo.RadarUserInfo;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.radar.manager.ProvinceInfoManager;
import com.pms.rcm.radar.manager.RadarUserInfoManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * radar_user_info表RadarUserInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName RadarUserInfoController.java
 */
@Controller
@RequestMapping(value="/radarUserInfo")
@Api("RadarUserInfo操作controller")
public class RadarUserInfoController  extends BaseController<RadarUserInfo, RadarUserInfoManager> {
	private static final long serialVersionUID = 488519598972L;
	@Resource(name="provinceInfoManager")
	private ProvinceInfoManager provinceInfoManager;
	@Resource(name="deptManager")
	private DeptManager deptManager;
	 
	@ApiOperation(value="获取radarUserInfo列表", notes="分页查询获取radarUserInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,RadarUserInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<RadarUserInfo> radarUserInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(radarUserInfoList);
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
	@ApiOperation(value="radarUserInfo信息", notes="根据url的id来获取radarUserInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarUserInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public RadarUserInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="RadarUserInfo信息修改或新增", notes="根据页面传入的RadarUserInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody RadarUserInfo radarUserInfo) throws Exception{	
		entity=radarUserInfo; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 
				logger.info("站区信息修改:"+entity.toString());		
			}
			else{  
				this.baseManager.insert(entity);	
				logger.info("站区信息新增:"+entity.toString());	
			}
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
			logger.info("RadarUserInfo信息操作错误:"+getExceptionAll(e));	
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
	@ApiOperation(value="radarUserInfo信息删除", notes="根据url的id来删除radarUserInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "radarUserInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    logger.info("RadarUserInfo[id="+id+"]执行删除");
		}catch(Exception e){
			errInfo = "err";
			e.printStackTrace();
			msg="操作出错："+e.getLocalizedMessage();
			logger.info("RadarUserInfo信息操作错误:"+getExceptionAll(e));	
		} 
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;
	}
	     
	
	@ResponseBody 
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public Object tree(String menuId) throws Exception{   
		String hql="from ProvinceInfo "; 
		List<ProvinceInfo> list = this.provinceInfoManager.find(hql);
		List<Dept> dlist=new ArrayList<Dept>();
		for(ProvinceInfo depar : list){
			Dept dept1=new Dept();
			dept1.setDeptName(depar.getProvinceName());
			dept1.setParentId("999999"); 
			List list1=deptManager.listAllForAreas(depar.getId());
			dept1.setSubDepartment(list1);
			dept1.setTreeurl("/html/radarUserInfo/radarUserInfo_list.html?provinceId="+depar.getId()+"&menuId="+menuId);  
			dept1.setTarget("treeFrame"); 
			dlist.add(dept1);
		} 
		
		JSONArray arr = JSONArray.fromObject(dlist);
		String json = arr.toString();
		json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
		Map<String,String> map = new HashMap<String,String>();  
		map.put("result", json);
		return map;
	}
	     
	 
}
