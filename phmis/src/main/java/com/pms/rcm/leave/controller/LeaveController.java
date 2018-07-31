package com.pms.rcm.leave.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.activiti.controller.BaseController;
import com.pms.base.util.Const;
import com.pms.base.util.DateUtil;
import com.pms.base.util.Jurisdiction; 
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.leave.manager.LeaveManager;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.DictionariesManager;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.Dictionaries;
import com.pms.rcm.sys.vo.User;
 

/** 
 * 说明：请假休假
 * 创建人：Taylor
 * 创建时间：2017-03-04
 * @param <dictionariesService>
 */
@Controller
@RequestMapping(value="/leave")
public class LeaveController<dictionariesService> extends BaseController {
	
	String menuUrl = "leave/list.do"; //菜单地址(权限用)
	@Resource(name="leaveManager")
	private LeaveManager leaveManager;
	@Resource(name="deptManager")
	private DeptManager deptManager;
	@Resource(name="dictionariesManager")
	private DictionariesManager dictionariesManager;
	@Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService; 
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Leave");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = leaveManager.list(page);	//列出Leave列表
		mv.setViewName("jsp/leave/leave_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
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
		//获取当前用户ID和Name
		Session session = Jurisdiction.getSession();
		User user = new User();
        user = (User)session.getAttribute(Const.SESSION_USER);
        pd.put("APPLY_USER_ID",user.getId());  
		pd.put("NAME", Jurisdiction.getUsername()); 
		//去新增页面
		mv.setViewName("jsp/leave/leave_edit");
		List<Dictionaries> dictionariesList = dictionariesManager.listSubDictByCode("005"); //列出数据字典（请假类型）
        mv.addObject("dictionariesList", dictionariesList); 
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
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
		pd = leaveManager.findById(pd);	//根据ID读取
		mv.setViewName("jsp/leave/leave_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	/**新增保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Leave");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//获取当前用户ID
        Session session = Jurisdiction.getSession();
        User user = new User();
        user = (User)session.getAttribute(Const.SESSION_USER);
        String strUID = user.getId();  
		
        logger.info("准备启动请假流程实例，用户ID：" + strUID);  
		//启动流程
        Map<String, Object> variables = new HashMap<String, Object>();
        identityService.setAuthenticatedUserId(strUID);
        Dept dept=deptManager.get(user.getDeptId());
        variables.put("casCode",dept.getCasCode());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave",variables);
        String proId=processInstance.getId();
        logger.info("流程实例已启动，流程实例ID：" + proId);  
		
        //保存请假数据
        logger.info("准备保存请假数据，用户ID：" + strUID); 
        pd.put("LEAVE_ID", this.get32UUID());    //主键
        pd.put("PROCESS_ID", proId);    //流程ID
        pd.put("APPLY_DATE", DateUtil.getTimeOffsetDesc(new Date()));   //申请时间
        leaveManager.save(pd);
        logger.info("保存成功，用户ID：" + strUID + "，流程实例ID：" + proId);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**修改保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Leave");
	//	if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		leaveManager.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Leave");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
	///	leaveManager.delete(pd);
		out.write("success");
		out.close();
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Leave到excel");
	//	if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("申请人ID");	//1
		titles.add("请假类型");	//2
		titles.add("申请时间");	//3
		titles.add("请假开始时间");	//4
		titles.add("请假结束时间");	//5
		titles.add("请假事由");	//6
		titles.add("主管审批人");	//7
		titles.add("审批说明");	//8
		titles.add("部门审批人");	//9
		titles.add("审批说明");	//10
		dataMap.put("titles", titles);
		List<PageData> varOList = leaveManager.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("APPLY_USER_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("LEAVE_TYPE"));	//2
			vpd.put("var3", varOList.get(i).getString("APPLY_DATE"));	//3
			vpd.put("var4", varOList.get(i).getString("START_DATE"));	//4
			vpd.put("var5", varOList.get(i).getString("END_DATE"));	//5
			vpd.put("var6", varOList.get(i).getString("REASON"));	//6
			vpd.put("var7", varOList.get(i).getString("MANAGER_ID"));	//7
			vpd.put("var8", varOList.get(i).getString("REMARKS1"));	//8
			vpd.put("var9", varOList.get(i).getString("LEADER_ID"));	//9
			vpd.put("var10", varOList.get(i).getString("REMARKS2"));	//10
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		String erv="";
		//ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
