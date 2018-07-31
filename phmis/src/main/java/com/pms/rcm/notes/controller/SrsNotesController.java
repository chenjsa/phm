package com.pms.rcm.notes.controller;
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
import com.pms.base.util.StrUtil;
import com.pms.rcm.notes.manager.SrsNotesManager;
import com.pms.rcm.notes.vo.SrsNotes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * srs_notes表SrsNotes维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName SrsNotesController.java
 */
@Controller
@RequestMapping(value="/srsNotes")
@Api("SrsNotes操作controller")
public class SrsNotesController  extends BaseController<SrsNotes, SrsNotesManager> {
	private static final long serialVersionUID = 760899273249L;  
	 
	@ApiOperation(value="获取srsNotes列表", notes="分页查询获取srsNotes列表信息",httpMethod = "GET") 
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "page对象，包括起始页，每页条数，排序等", required = true, dataType = "String", paramType = "form"),
					@ApiImplicitParam(name = "entity", value = "srsNotes对象，根据消息对象查询消息", required = true, dataType = "String", paramType = "form"),
					@ApiImplicitParam(name = "from", value = "请求来源", required = false, dataType = "String", paramType = "form")
	})
	@ResponseBody
	@RequestMapping(value="/listReceiver")
	public String listReceiver(Page page,SrsNotes entity,String from){
		String errCode="0";
		String errMsg="";
		entity.setNotesReceiverId(this.getLoginUser().getId());
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		
		JSONObject jsonobject = new JSONObject();
		try
		{  
			List<SrsNotes> srsNotesList =this.baseManager.findPage(entity, page, true);
			if(from!=null && from.equals("app")){
				this.baseManager.updateNotesIsRead(srsNotesList,"1");
			}
			
		 	JSONArray jsonarray = JSONArray.fromObject(srsNotesList);
			jsonobject.put("total", String.valueOf(page.getTotalPage()));
			jsonobject.put("records", String.valueOf(page.getTotalResult()));
	 		jsonobject.put("rows", jsonarray);
	 		jsonobject.put("err_code", errCode);
	 		jsonobject.put("err_msg", errMsg);
	 		String basicbridgelistString = jsonobject.toString();  
	 		logger.info("接受消息返回的json："+basicbridgelistString);
			return basicbridgelistString;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonobject.put("err_msg", e.getMessage());
			errCode="1";
			jsonobject.put("err_code", errCode);
			return jsonobject.toString();
		}
	}
	
	@ApiOperation(value="获取srsNotes列表", notes="分页查询获取srsNotes列表信息",httpMethod = "GET") 
	@ResponseBody
	@RequestMapping(value="/listSender")
	public String listSender(Page page,SrsNotes entity){
		entity.setNotesSenderId(this.getLoginUser().getId());
		page.setCurrentPage(Integer.valueOf(page.getPage()));
		page.setShowCount(Integer.valueOf(page.getRows())); 
		List<SrsNotes> srsNotesList =this.baseManager.findPage(entity, page, true);
		try
		{  
		 	JSONArray jsonarray = JSONArray.fromObject(srsNotesList);
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
	@ApiOperation(value="srsNotes信息", notes="根据url的id来获取srsNotes信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "srsNotes的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public SrsNotes getEntityById(@PathVariable("id") String id)throws Exception{		 
		 return this.entity=this.baseManager.get(id); 						 
	}
	@ResponseBody
	@RequestMapping(value="/save")
	@ApiOperation(value="SrsNotes信息修改或新增", notes="根据页面传入的SrsNotes信息进行新增或修改",httpMethod = "POST") 
	public Object save(@ApiParam @RequestBody SrsNotes srsNotes) throws Exception{	
		entity=srsNotes; 			 
		if(isUpdate(entity) ){				
			entity = this.baseManager.update(entity); 			
		}
		else{  
			this.baseManager.insert(entity);			 
		}
		Map<String,String> map = new HashMap<String,String>(); 
		String errInfo = "success";  
		map.put("result", errInfo);
		return map;
	}
	@ApiParam
	@ResponseBody
	@RequestMapping(value="/updateIsRead")
	@ApiOperation(value="修改srsNotes是否阅读", notes="修改srsNotes是否阅读",httpMethod = "POST") 
	public Object updateIsRead(@RequestParam(value="id")String id,
							   @RequestParam(value="notesIsRead") String notesIsRead) throws Exception{	
		entity=this.baseManager.get(id);
		entity.setNotesIsRead(notesIsRead);
		entity.setNotesReadTime(StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
		this.baseManager.update(entity); 
		Map<String,String> map = new HashMap<String,String>(); 
		String errInfo = "success";  
		map.put("result", errInfo);
		return map;
	}
	
	 
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */ 
	@ResponseBody
	@ApiOperation(value="srsNotes信息删除", notes="根据url的id来删除srsNotes信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "srsNotes的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Object delete(@PathVariable("id") String id) throws Exception{  
		Map<String,String> map = new HashMap<String,String>(); 
		String errInfo = "success";
		baseManager.delete(entity, id);//执行删除 
		map.put("result", errInfo);
		return map;
	}
	     
	 
}
