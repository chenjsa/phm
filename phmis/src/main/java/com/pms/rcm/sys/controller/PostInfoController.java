package com.pms.rcm.sys.controller;
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
import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.vo.PostInfo;

import com.pms.rcm.sys.manager.PostInfoManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * post_info表PostInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName PostInfoController.java
 */
@Controller
@RequestMapping(value="/postInfo")
@Api("PostInfo操作controller")
public class PostInfoController  extends BaseController<PostInfo, PostInfoManager> {
	private static final long serialVersionUID = 217266409485L;  
	 
	@ApiOperation(value="获取postInfo列表", notes="分页查询获取postInfo列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/list")
	public String list(Page page,PostInfo entity){ 		 		 
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<PostInfo> postInfoList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(postInfoList);
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
	@ApiOperation(value="postInfo信息", notes="根据url的id来获取postInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "postInfo的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public PostInfo getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="PostInfo信息修改或新增", notes="根据页面传入的PostInfo信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody PostInfo postInfo) throws Exception{	
		entity=postInfo; 	
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
	@ApiOperation(value="postInfo信息删除", notes="根据url的id来删除postInfo信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "postInfo的ID", required = true, dataType = "String", paramType = "path")
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
	     
	 
}
