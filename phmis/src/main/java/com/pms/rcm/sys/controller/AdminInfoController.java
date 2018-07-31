package com.pms.rcm.sys.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.rcm.sys.manager.AdminInfoManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.AdminInfo;
import com.pms.rcm.sys.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * admin_info表AdminInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName AdminInfoController.java
 */
@Controller
@RequestMapping(value="/adminInfo")
@Api("AdminInfo操作controller")
public class AdminInfoController  extends BaseController<AdminInfo, AdminInfoManager> {
	private static final long serialVersionUID = 360811350586L;  
	@Resource(name="userManager")
	private UserManager userManager;
	@ApiOperation(value="获取adminInfo列表", notes="分页查询获取adminInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,AdminInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<AdminInfo> adminInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(adminInfoList);
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
	@ApiOperation(value="adminInfo信息", notes="根据url的id来获取adminInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "adminInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public AdminInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="AdminInfo信息修改或新增", notes="根据页面传入的AdminInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody AdminInfo adminInfo) throws Exception{	
		entity=adminInfo; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				entity = this.baseManager.update(entity); 	
				User user=new User();
				user.setNo(entity.getAdminId());
				user=this.userManager.findLoginUser(user);
				this.userManager.delete(user,user.getId());
				User user1=new User();
				user1.setName("平台管理员");
				user1.setNo(entity.getAdminId());
				this.userManager.insert(user1);
			}
			else{  
				this.baseManager.insert(entity);	
				User user=new User();
				user.setName("平台管理员");
				user.setNo(entity.getAdminId());
				this.userManager.insert(user);
				 
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
	@ApiOperation(value="adminInfo信息删除", notes="根据url的id来删除adminInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "adminInfo的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.delete(entity, id);//执行删除
		    User user=new User();
			user.setNo(entity.getAdminId());
			user=this.userManager.findLoginUser(user);
			this.userManager.delete(user,user.getId());
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
	public List<AdminInfo> getAll()throws Exception{		 
		return this.baseManager.findAll();						 
	} 
}
