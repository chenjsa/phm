package com.pms.rcm.sys.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.base.controller.BaseController;
import com.pms.base.util.AppUtil;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.vo.Dept;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 


/** 
 * 说明：组织机构 
 */
@Controller
@RequestMapping(value="/department")
public class DeptController extends BaseController<Dept,DeptManager> {
	
	String menuUrl = "department/list.do"; //菜单地址(权限用)
	@Resource(name="deptManager")
	private DeptManager deptManager;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(Dept dept) throws Exception{		 
		if(isUpdate(dept) ){
			if(dept.getParentId()==null)
				dept.setParentId("0");
		    this.deptManager.update(dept); 
		}else{
			this.deptManager.insert(dept);   
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
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam String DEPARTMENT_ID) throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"删除department");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd.put("DEPARTMENT_ID", DEPARTMENT_ID);
		String errMsg = "success";
		String errCode="0";
		String canDelete=deptManager.deleteCheck(DEPARTMENT_ID); 
		if(!canDelete.equals("canDelete")){//判断是否有子级，是：不允许删除
			errCode = "1";
			errMsg=canDelete;
		}else{
			try{
				deptManager.delete(entity, DEPARTMENT_ID);//执行删除
			}catch(Exception e){
				if (e instanceof DataIntegrityViolationException) {
					errCode="2";
					errMsg="机构已经被使用不能删除";
				}else{
					errCode="3";
					errMsg=e.getMessage();
				}
				
			}
		}
		map.put("errCode", errCode);
		map.put("errMsg", errMsg);
		return map;
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Dept dept) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改department"); 
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		deptManager.update(dept);
		mv.addObject("msg","success");
		mv.setViewName("jsp/save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
	    page.setEntityOrField(true);
	   
		logBefore(logger, Jurisdiction.getUsername()+"列表department");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();   		
		String keywords = pd.getString("keywords");					//检索条件 
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String DEPARTMENT_ID = null == pd.get("DEPARTMENT_ID")?"":pd.get("DEPARTMENT_ID").toString();
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			DEPARTMENT_ID = pd.get("id").toString();
		}
		pd.put("DEPARTMENT_ID", DEPARTMENT_ID);					//上级ID
		logger.info("DEPARTMENT_ID:"+DEPARTMENT_ID);
		Dept pdept=deptManager.load(DEPARTMENT_ID);
		page.setPd(pd); 
		List<Dept>	varList = deptManager.list(page);	//列出Dictionaries列表
		pd.put("id", DEPARTMENT_ID);
		mv.addObject("pd", pd);		//传入上级所有信息
		mv.addObject("pdept",pdept);
		mv.addObject("DEPARTMENT_ID", DEPARTMENT_ID);			//上级ID
		 
		
		mv.setViewName("jsp/system/dept/department_list");
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());				//按钮权限
		return mv;
	}
	
	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAllDepartment")
	public ModelAndView listAllDepartment(Model model,String DEPARTMENT_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			JSONArray arr = JSONArray.fromObject(deptManager.listAll("0"));
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("DEPARTMENT_ID",DEPARTMENT_ID);
			mv.addObject("pd", pd);	
			mv.setViewName("jsp/system/dept/department_ztree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	

	/**获取客户机构树
     * @param
     * @throws Exception
     */
	@RequestMapping(value="/listAllDepartmentTreeSelect")
	public ModelAndView listAllDepartmentTreeSelect()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();	
//		String CUST_ORG_ID = null == pd.get("CUST_ORG_ID")?"":pd.get("CUST_ORG_ID").toString();
//		pd.put("CUST_ORG_ID", CUST_ORG_ID);					    //上级ID
//		mv.addObject("pds",custOrgService.findById(pd));   //传入上级所有信息
//		mv.addObject("CUST_ORG_ID", CUST_ORG_ID);			//上级ID，暂时作为子级ID用(保存时会重新生成新的ID替换)
//		mv.setViewName("biz/base/custOrg/custOrg_edit");
//		mv.addObject("msg", "save");
		
		JSONArray json=deptManager.listTreeForSelect("001");
		
		logger.info("listAllDepartmentTreeSelect,能选择的机构树:"+json.toString());
		mv.setViewName("jsp/system/dept/department_ztree_select");
		mv.addObject("pd", pd);
		mv.addObject("custOrgTreeData", json);
		
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DEPARTMENT_ID = null == pd.get("DEPARTMENT_ID")?"":pd.get("DEPARTMENT_ID").toString();
		entity=new Dept(); 
		pd.put("entity", entity);
		pd.put("DEPARTMENT_ID", DEPARTMENT_ID);					//上级ID
		pd.put("PARENT_ID", DEPARTMENT_ID);
		mv.addObject("pd", pd);
		mv.addObject("pds",deptManager.get(DEPARTMENT_ID));		//传入上级所有信息
		mv.addObject("DEPARTMENT_ID", DEPARTMENT_ID);			//传入ID，作为子级ID用
		mv.setViewName("jsp/system/dept/department_edit");
		mv.addObject("msg", "save");
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DEPARTMENT_ID = pd.getString("DEPARTMENT_ID");
		entity =deptManager.get(DEPARTMENT_ID);	//根据ID读取
		pd.put("entity", entity); 
		mv.addObject("pd", pd);					//放入视图容器
		pd.put("DEPARTMENT_ID",entity.getParentId());			//用作上级信息
		mv.addObject("pds",deptManager.get(entity.getParentId()));				//传入上级所有信息
		mv.addObject("DEPARTMENT_ID", entity.getParentId());	//传入上级ID，作为子ID用
		pd.put("DEPARTMENT_ID",DEPARTMENT_ID);							//复原本ID
		mv.setViewName("jsp/system/dept/department_edit");
		mv.addObject("msg", "edit");
		return mv;
	}	

	/**判断编码是否存在
	 * @return
	 */
	@RequestMapping(value="/hasCode")
	@ResponseBody
	public Object hasCode(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(deptManager.getBySysCode(pd.getString("CODE")) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	@RequestMapping(value="/getById")
	@ResponseBody
	public Object getById(@RequestParam String id) throws Exception{  
		Dept result= baseManager.get(id);
		
		return JSONObject.fromObject(result);
	}
}

