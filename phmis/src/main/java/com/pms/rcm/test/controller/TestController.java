package com.pms.rcm.test.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pms.base.controller.BaseController;
import com.pms.base.util.Page;
import com.pms.configure.CityDubboConsumerService;
import com.pms.dubbo.UserService;
import com.pms.rcm.test.manager.TestManager;
import com.pms.rcm.test.vo.Test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * test表Test维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName TestController.java
 */
@Controller
@RequestMapping(value="/test")
@Api("Test操作controller")
public class TestController  extends BaseController<Test, TestManager> {
	private static final long serialVersionUID = 530571444203L;   
	 
	@ApiOperation(value="获取test列表", notes="分页查询获取test列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,Test entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		///List<Test> testList =this.baseManager.findPage(entity, page, true);
		List<Test> testList =this.baseManager.listPage(entity, page);
	///	System.out.println("testList=="+testList.size());
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(testList);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("total", String.valueOf(page.getTotalPage()));
			jsonobject.put("records", String.valueOf(page.getTotalResult()));
	 		jsonobject.put("rows", jsonarray);
	 		String basicbridgelistString = jsonobject.toString();  
	 		//System.out.println("basicbridgelistString=="+basicbridgelistString);
			return basicbridgelistString;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Reference(version = "1.0.0")
    private UserService userService;
	@ResponseBody
	@ApiOperation(value="test信息", notes="根据url的id来获取test信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "test的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public Test getEntityById(@PathVariable("id") String id)throws Exception{		
		/* entity=testManager.get(id);
		 System.out.println("dubbo:"+entity.getNo());*/
		 System.out.println(userService.getUserName());
		 return this.entity=this.baseManager.getByMybatis(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="Test信息修改或新增", notes="根据页面传入的Test信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody Test test) throws Exception{	
		entity=test; 	
		String errInfo = "success";
		String msg="";
	 	try{
			if(isUpdate(entity) ){				
				 this.baseManager.updateMybatis(entity); 			
			}
			else{  
				this.baseManager.insertMybatis(entity);			 
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
	@ApiOperation(value="test信息删除", notes="根据url的id来删除test信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "test的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{   
		String errInfo = "success";
		String msg="";
	 	try{
		    baseManager.deleteMybatis(entity, id);//执行删除
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
