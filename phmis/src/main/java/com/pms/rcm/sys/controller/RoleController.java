package com.pms.rcm.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.base.JdbcHelper;
import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.manager.MenuManager;
import com.pms.rcm.sys.manager.RoleManager;
import com.pms.rcm.sys.vo.Role;

import net.sf.json.JSONArray;

 
@Controller
@RequestMapping(value="/role")
public class RoleController  extends BaseController<Role,RoleManager> { 
	@Resource(name="menuManager")
	private MenuManager menuManager;	
	@Resource(name = "jdbcHelper")
	protected JdbcHelper jdbcHelper;
	@RequestMapping(value="/list")
	public ModelAndView list(Page page)throws Exception{
		page.setEntityOrField(true);
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{ 
			List<Role> roleList =this.baseManager.findPage("from Role order by id", page, true);
			mv.addObject("roleList", roleList); 
			mv.setViewName("jsp/system/role/role_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			JSONArray arr = JSONArray.fromObject(menuManager.listAllMenuForRole("0",this.entity));
			String json = arr.toString();
			json = json.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			logger.error(json);
			model.addAttribute("zTreeNodes", json); 
			entity=new Role();
			mv.setViewName("jsp/system/role/role_edit");
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(Model model,String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			this.entity=this.baseManager.get(id);
			JSONArray arr = JSONArray.fromObject(menuManager.listAllMenuForRole("0",this.entity));
			String json = arr.toString();
			json = json.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			logger.error(json);
			model.addAttribute("zTreeNodes", json); 			
			mv.addObject("entity",this.entity);
			mv.setViewName("jsp/system/role/role_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/goView")
	public ModelAndView goView(Model model,String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			this.entity=this.baseManager.get(id);
			JSONArray arr = JSONArray.fromObject(menuManager.listAllMenuForRole("0",this.entity));
			String json = arr.toString();
			json = json.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			logger.error(json);
			model.addAttribute("zTreeNodes", json); 			
			mv.addObject("entity",this.entity);
			mv.setViewName("jsp/system/role/role_view");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/save")
	public ModelAndView save(Role role) throws Exception{	
		entity=role; 		  
	    super.fillCollectionField(entity, "menuSet", "menuIds");  
	    super.fillCollectionField(entity, "sysOperationSet", "menuIds");  
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
	 * @param DEPARTMENT_ID
	 * @param
	 * @throws Exception 
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) throws Exception{  
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData(); 
		String errInfo = "success";
		boolean canDelete=this.baseManager.deleteCheck(id); 
		if(!canDelete){//判断是否有子级，是：不允许删除
			errInfo = "false";
		}else{
			baseManager.delete(entity, id);//执行删除
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}
