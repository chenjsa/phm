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

import com.pms.base.controller.BaseController;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.manager.MenuManager;
import com.pms.rcm.sys.vo.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
 
/** 
 * 类名称：MenuController 菜单处理
 * 创建人：FH 
 * 创建时间：2015年10月27日
 * @version
 */
@Controller
@RequestMapping(value="/menu")
public class MenuController extends BaseController<Menu,MenuManager> {

	String menuUrl = "menu.do"; //菜单地址(权限用)
	@Resource(name="menuManager")
	private MenuManager menuManager;
	
	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 */
	@RequestMapping
	public ModelAndView list()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString()))?"0":pd.get("MENU_ID").toString();
			List<Menu> menuList = menuManager.listSubMenuByParentId(MENU_ID);
			mv.addObject("pd", menuManager.getMenuById(pd));	//传入父菜单所有信息
			mv.addObject("MENU_ID", MENU_ID);
			mv.addObject("MSG", null == pd.get("MSG")?"list":pd.get("MSG").toString()); //MSG=change 则为编辑或删除后跳转过来的
			mv.addObject("menuList", menuList);
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			mv.setViewName("jsp/system/menu/menu_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			PageData pd = new PageData();
			pd = this.getPageData(); 
			String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString()))?"0":pd.get("MENU_ID").toString();//接收传过来的上级菜单ID,如果上级为顶级就取值“0”
			pd.put("MENU_ID",MENU_ID);
			Menu parentMenu=menuManager.getMenuById(MENU_ID);
			mv.addObject("pds", parentMenu);	//传入父菜单所有信息
			Menu menu = new Menu();
			///logger.info("parentMenu----"+parentMenu);
			menu.setMenuType(entity.getMenuType());//继续添加该类型
			Object maxObj = this.menuManager.getMaxSearial();
			Long serial = Long.valueOf(maxObj.toString());
			if(serial == null){
				menu.setSerial(10L);
			}
			else{
				menu.setSerial(serial);
			}
			if(parentMenu!=null) {
				menu.setCasCode(parentMenu.getCasCode());  
				menu.setParentId(parentMenu.getId());
			}				
			else{
				menu.setCasCode(""); 
			}				
			
			entity = menu;
			logger.info("menu----"+entity.getCasCode());
			mv.addObject("entity", menu);				
			mv.addObject("MENU_ID", MENU_ID);					//传入菜单ID，作为子菜单的父菜单ID用
			mv.addObject("MSG", "add");							//执行状态 add 为添加
			mv.setViewName("jsp/system/menu/menu_edit");
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(Menu menu)throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"保存菜单");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			logger.info("menu----"+menu.getCasCode());
			menu.setMenuIcon("menu-icon fa fa-leaf black");//默认菜单图标
			menuManager.insert(menu); //保存菜单
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		mv.setViewName("redirect:/menu?MENU_ID="+menu.getParentId());  //保存成功跳转到列表页面
		return mv;
	}
  

	/**
	 * 请求编辑菜单页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(String id)throws Exception{
		ModelAndView mv = this.getModelAndView();		 
		try{ 		//接收过来的要修改的ID
			logger.error("id==="+id);
			entity=menuManager.getMenuById(id); 
			mv.addObject("entity", entity);				//放入视图容器 
			mv.addObject("pds", menuManager.getMenuById(entity.getParentId()));			//传入父菜单所有信息 
			mv.addObject("MENU_ID", entity.getParentId());	//传入父菜单ID，作为子菜单的父菜单ID用
			mv.addObject("MSG", "edit"); 
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			mv.setViewName("jsp/system/menu/menu_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 保存编辑
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Menu menu)throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"修改菜单");
		ModelAndView mv = this.getModelAndView();
		try{
			menuManager.update(menu);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		//mv.setViewName("redirect:?MSG='change'&MENU_ID="+menu.getPARENT_ID()); //保存成功跳转到列表页面
		mv.setViewName("redirect:/menu?MENU_ID="+menu.getParentId());  //保存成功跳转到列表页面
		return mv;
	}
	 
	/**
	 * 请求编辑菜单图标页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEditicon")
	public ModelAndView toEditicon(String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			logger.error("toEditicon===="+id);
			pd.put("MENU_ID",id);
			mv.addObject("pd", pd);
			mv.setViewName("jsp/system/menu/menu_icon");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 保存菜单图标 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/editicon")
	public ModelAndView editicon(Menu pd)throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"修改菜单图标");
		ModelAndView mv = this.getModelAndView(); 
		try{ 
			 
			pd = menuManager.editicon(pd);
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		mv.setViewName("jsp/save_result");
		return mv;
	}
	
	/**
	 * 显示菜单列表ztree(菜单管理)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAllMenu")
	public ModelAndView listAllMenu(Model model,String MENU_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			JSONArray arr = JSONArray.fromObject(menuManager.listAllMenu("0"));
			String json = arr.toString();
			json = json.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			logger.error(json);
			model.addAttribute("zTreeNodes", json);
			mv.addObject("MENU_ID",MENU_ID);
			mv.setViewName("jsp/system/menu/menu_ztree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	 
	/**
	 * 删除菜单
	 * @param MENU_ID
	 * @param out
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@RequestParam String MENU_ID)throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"删除菜单");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		try{
			if(menuManager.listSubMenuByParentId(MENU_ID).size() > 0){//判断是否有子菜单，是：不允许删除
				errInfo = "false";
			}else{
				menuManager.deleteMenuById(MENU_ID);
				errInfo = "success";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		//return AppUtil.returnObject(new PageData(), map);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject;       
	}
	 
	
}
