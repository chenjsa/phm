package com.pms.rcm.sys.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.manager.MenuManager;
import com.pms.rcm.sys.manager.SysOperationManager;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.SysButton;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * sys_operation表SysOperation维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2013-09-19
 * @fileName SysOperationController.java
 */
@Controller
@RequestMapping(value="/sysOperation")
@Api("SysOperation操作controller")
public class SysOperationController  extends BaseController<SysButton, SysOperationManager> {
	@Resource(name="menuManager")
	private MenuManager menuManager;	
	private static final long serialVersionUID = 25951111068L;   
	@ApiOperation(value="获取sysOperation列表", notes="分页查询获取sysOperation列表信息",httpMethod = "GET") 
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(Page page,SysButton entity)throws Exception{
		ModelAndView mv = this.getModelAndView(); 
		try{ 
			Menu menu=menuManager.get(entity.getMenuId());
			List<SysButton> sysOperationList =this.baseManager.findPage(entity, page, true);
			mv.addObject("sysOperationList", sysOperationList); 
			mv.addObject("entity", entity); 
			mv.addObject("menu", menu); 
			mv.setViewName("jsp/system/sysOperation/sysOperation_list");
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**去新增页面
	 * @param
	 * @throws Exception
	 */ 
	@RequestMapping(value="/goAdd/{menuId}",method=RequestMethod.GET)
	@ApiImplicitParam(name = "menuId", value = "menuId", required = true, dataType = "String", paramType = "path")
	public ModelAndView goAdd(@PathVariable("menuId") String menuId)throws Exception{
		ModelAndView mv = this.getModelAndView(); 
		entity=new SysButton(); 
		entity.setMenuId(menuId);
		Menu menu=menuManager.get(entity.getMenuId());
	    mv.addObject("menu",menu);
	    mv.addObject("entity",this.entity);
		mv.setViewName("jsp/system/sysOperation/sysOperation_edit"); 
		return mv;
	}	 
	@ApiOperation(value="sysOperation信息编辑", notes="根据url的id来编辑sysOperation信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "sysOperation的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/goEdit/{id}",method=RequestMethod.GET)
	public ModelAndView goEdit(Model model,@PathVariable("id") String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			this.entity=this.baseManager.get(id); 		
			mv.addObject("entity",this.entity);
			Menu menu=menuManager.get(entity.getMenuId());
		    mv.addObject("menu",menu);
			mv.setViewName("jsp/system/sysOperation/sysOperation_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
 
	@ApiOperation(value="sysOperation信息", notes="根据url的id来获取sysOperation信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "sysOperation的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/goView/{id}",method=RequestMethod.GET)
	public ModelAndView goView(Model model,@PathVariable("id") String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			this.entity=this.baseManager.get(id); 			
			mv.addObject("entity",this.entity);
			Menu menu=menuManager.get(entity.getMenuId());
		    mv.addObject("menu",menu);
			mv.setViewName("jsp/system/sysOperation/sysOperation_view");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/save")
	@ApiOperation(value="sysOperation信息修改或新增", notes="根据页面传入的sysOperation信息进行新增或修改",httpMethod = "POST") 
	public ModelAndView save(SysButton sysOperation) throws Exception{	
		entity=sysOperation; 		 		 
		if(isUpdate(entity) ){				
			entity = this.baseManager.update(entity); 				
		}
		else{  
			this.baseManager.insert(entity);			 
		}
		ModelAndView mv = this.getModelAndView();  
		mv.addObject("msg","success");
		mv.setViewName("jsp/save_result");
		return mv;
	}
	  /**
	 * 删除
	 * @param id
	 * @param
	 * @throws Exception 
	 */ 
	@ResponseBody
	@ApiOperation(value="sysOperation信息删除", notes="根据url的id来删除sysOperation信息",httpMethod = "POST")
	@ApiImplicitParam(name = "id", value = "sysOperation的ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value="/delete")
	public Object delete(@RequestParam String id) throws Exception{  
		Map<String,String> map = new HashMap<String,String>(); 
		String errInfo = "success";
		baseManager.delete(entity, id);//执行删除 
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	     
	 
}
