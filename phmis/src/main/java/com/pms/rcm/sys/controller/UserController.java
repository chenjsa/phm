package com.pms.rcm.sys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.radar.manager.ProvinceInfoManager;
import com.pms.rcm.radar.manager.RadarUserInfoManager;
import com.pms.rcm.radar.vo.ProvinceInfo;
import com.pms.rcm.radar.vo.RadarUserInfo;
import com.pms.rcm.sys.manager.ContactInfoManager;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.PhysiclalStatusManager;
import com.pms.rcm.sys.manager.PostInfoManager;
import com.pms.rcm.sys.manager.ProfessionalFieldManager;
import com.pms.rcm.sys.manager.RoleManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.manager.UserTypeInfoManager;
import com.pms.rcm.sys.vo.ContactInfo;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.PhysiclalStatus;
import com.pms.rcm.sys.vo.PostInfo;
import com.pms.rcm.sys.vo.ProfessionalField;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.sys.vo.UserTypeInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
 

@Controller
@RequestMapping(value="/user")
public class UserController  extends BaseController<User,UserManager> {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	@Resource(name="deptManager")
	private DeptManager deptManager;
	@Resource(name="userManager")
	private UserManager userManager;
	@Resource(name="roleManager")
	private RoleManager roleManager;
	@Resource(name="userTypeInfoManager")
	private UserTypeInfoManager userTypeInfoManager;
	@Resource(name="radarUserInfoManager")
	private RadarUserInfoManager radarUserInfoManager;
	@Resource(name="provinceInfoManager")
	private ProvinceInfoManager provinceInfoManager;
	@Resource(name="contactInfoManager")
	private ContactInfoManager contactInfoManager;
	@Resource(name="postInfoManager")
	private PostInfoManager postInfoManager;
	@Resource(name="physiclalStatusManager")
	private PhysiclalStatusManager physiclalStatusManager;
	@Resource(name="professionalFieldManager")
	private ProfessionalFieldManager professionalFieldManager;
	@Value("${logFolder}")
	private String logFolder;
	public UserController(){ 
		 
	}
	 
	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAllUser")
	public ModelAndView listAllUser(Model model,String DEPARTMENT_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			User user=this.getLoginUser();			
			Set<Dept> managerDepts=new HashSet<Dept>();
			List<Dept> deptList=new ArrayList<Dept>();
			if(user.isAdmin()){
				managerDepts=user.getManageDeptSet(); 
				for(Dept dept:managerDepts){				
					List list=deptManager.listAllForUser(dept.getId());
					dept.setSubDepartment(list);
					deptList.add(dept);
					dept.setTreeurl("user/list.do?DEPARTMENT_ID="+dept.getId());  
					dept.setTarget("treeFrame"); 
					DEPARTMENT_ID=dept.getId();
				} 
				String hql="from ProvinceInfo "; 
				List<ProvinceInfo> list = this.provinceInfoManager.find(hql);
				List<Dept> dlist=new ArrayList<Dept>();
				for(ProvinceInfo depar : list){
					Dept dept1=new Dept();
					dept1.setDeptName(depar.getProvinceName());
					dept1.setParentId("999999"); 
					List list1=deptManager.listAllForArea(depar.getId());
					dept1.setSubDepartment(list1);
					//dept1.setTreeurl("user/listRadar.do?radarId="+depar.getId());  
					dept1.setTarget("treeFrame"); 
					dlist.add(dept1); 
				}
				//dept.setSubDepartment(dlist);
				deptList.addAll(dlist);
			}else{
				Dept d=deptManager.get(user.getDeptId());
				if(d!=null){
					managerDepts.add(d); 
					for(Dept dept:managerDepts){				
						List list=deptManager.listAllForUser(dept.getId());
						dept.setSubDepartment(list);
						deptList.add(dept);
						dept.setTreeurl("user/list.do?DEPARTMENT_ID="+dept.getId());  
						dept.setTarget("treeFrame"); 
						DEPARTMENT_ID=dept.getId();
					} 
				}else{
					RadarUserInfo radarUserInfo=this.radarUserInfoManager.get(user.getStationId()); 
					Dept dept1=new Dept();
					dept1.setDeptName(radarUserInfo.getStationName());
					dept1.setParentId(radarUserInfo.getProvinceId().toString());  
					dept1.setTreeurl("user/listRadar.do?radarId="+radarUserInfo.getId());  
					dept1.setTarget("treeFrame");  
					deptList.add(dept1);
				}
					
			}
			  
			
			
			JSONArray arr = JSONArray.fromObject(deptList);
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			logger.info(json);
			model.addAttribute("zTreeNodes", json);
			mv.addObject("DEPARTMENT_ID",DEPARTMENT_ID);
			mv.addObject("pd", pd);	
			mv.setViewName("jsp/system/user/user_ztree");
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
		return mv;
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/listRadar")
	public ModelAndView listRadar(Page page,User user) throws Exception{
	    page.setEntityOrField(true);	   
		logBefore(logger, Jurisdiction.getUsername()+"列表user");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();   		
		this.entitySearch=user;
		String radarId = null == pd.get("radarId")?"":pd.get("radarId").toString();
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			radarId = pd.get("id").toString();
		}
		pd.put("radarId", radarId);				 
		List<User>	varList = userManager.findPageByRadarId(radarId,page,this.entitySearch);	//列出Dictionaries列表 
		logger.info("radarId=="+radarId);
		mv.addObject("radarUserInfo",this.radarUserInfoManager.get(radarId));
		pd.put("id", radarId);
		mv.addObject("pd", pd);		//传入上级所有信息 
		mv.addObject("entitySearch",this.entitySearch);
		mv.addObject("radarId", radarId);			//上级ID
		 
		
		mv.setViewName("jsp/system/user/user_list");
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());				//按钮权限
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,User user) throws Exception{
	    page.setEntityOrField(true);	   
		logBefore(logger, Jurisdiction.getUsername()+"列表user");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();   		
		this.entitySearch=user;
		String DEPARTMENT_ID = null == pd.get("DEPARTMENT_ID")?"":pd.get("DEPARTMENT_ID").toString();
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			DEPARTMENT_ID = pd.get("id").toString();
		}
		pd.put("DEPARTMENT_ID", DEPARTMENT_ID);					//上级ID
		logger.info("DEPARTMENT_ID:"+DEPARTMENT_ID);
		Dept pdept=deptManager.load(DEPARTMENT_ID);
		page.setPd(pd); 
		List<User>	varList = userManager.findPageByDeptId(DEPARTMENT_ID,page,this.entitySearch);	//列出Dictionaries列表
		
		pd.put("id", DEPARTMENT_ID);
		mv.addObject("pd", pd);		//传入上级所有信息
		mv.addObject("pdept",pdept);
		mv.addObject("entitySearch",this.entitySearch);
		mv.addObject("DEPARTMENT_ID", DEPARTMENT_ID);			//上级ID
		 
		
		mv.setViewName("jsp/system/user/user_list");
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());				//按钮权限
		return mv;
	}
	

	/**去新增页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();		
			String DEPARTMENT_ID = null == pd.get("DEPARTMENT_ID")?"":pd.get("DEPARTMENT_ID").toString();
			///model.addAttribute("zTreeNodes", this.getDeptTreeJson(DEPARTMENT_ID));
			
			String radarId = null == pd.get("radarId")?"":pd.get("radarId").toString();
			User user=this.getLoginUser();
			
			/***
			 * 刷角色树
			 */
			//System.out.println(user.getName());
			List<Menu> roleList=this.userManager.listTree(user,null);
			JSONArray arry = JSONArray.fromObject(roleList);
			String json1 = arry.toString(); 
			json1 = json1.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			model.addAttribute("roleZTreeNodes", json1); 
			
			mv.addObject("radarUserInfo",this.radarUserInfoManager.get(radarId));
			mv.addObject("dept",this.deptManager.get(DEPARTMENT_ID));
			 SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
			 Date date=new Date();
			 String dateForNow=sp.format(date); 
			 entity=new User();
			entity.setRegistrationDate(dateForNow);
			List<UserTypeInfo> userTypeInfos=this.userTypeInfoManager.getAll();
			List<ContactInfo> contactInfos=contactInfoManager.findAll(); 
			List<PostInfo> postInfos=postInfoManager.findAll();
			List<ProfessionalField> professionalFields=this.professionalFieldManager.findAll();
			List<PhysiclalStatus> physiclalStatuses=physiclalStatusManager.findAll();
			mv.addObject("entity",entity);
			mv.addObject("userTypeInfos", userTypeInfos);
			mv.addObject("contactInfos", contactInfos);
			mv.addObject("postInfos", postInfos);
			mv.addObject("professionalFields", professionalFields);
			mv.addObject("physiclalStatuses", physiclalStatuses);
			mv.addObject("msg", "add");
			mv.setViewName("jsp/system/user/user_edit");
			mv.addObject("pd", pd);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	

	/**去新增页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();		
			/***
			 * 刷机构树
			 */
			User user=this.getLoginUser();
			Set<Dept> managerDepts=user.getManageDeptSet();
			entity=userManager.get(pd.getString("id"));
			Set<Dept> entityManagerDepts=entity.getManageDeptSet();
			 
			List<Dept> deptList=new ArrayList<Dept>();
			for(Dept dept:managerDepts){				
				List<Dept> list=deptManager.listTreeForAdd(dept.getId(),entityManagerDepts);
				dept.setSubDepartment(list);				
				dept.setTreeurl("");  
				dept.setHasDepartment(false); 
				logger.info(dept.getDeptName()+"----"+dept.isHasDepartment());
				if(entityManagerDepts.contains(dept)){					
					dept.setHasDepartment(true); 
				}
				dept.setTarget("treeFrame");   
				deptList.add(dept);
			}
			JSONArray arr = JSONArray.fromObject(deptList);
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			/***
			 * 刷角色树
			 */
			List<UserTypeInfo> userTypeInfos=this.userTypeInfoManager.getAll();
			List<ContactInfo> contactInfos=contactInfoManager.findAll(); 
			List<PostInfo> postInfos=postInfoManager.findAll();
			List<ProfessionalField> professionalFields=this.professionalFieldManager.findAll();
			List<PhysiclalStatus> physiclalStatuses=physiclalStatusManager.findAll();
			mv.addObject("entity",entity);
			mv.addObject("userTypeInfos", userTypeInfos);
			mv.addObject("contactInfos", contactInfos);
			mv.addObject("postInfos", postInfos);
			mv.addObject("professionalFields", professionalFields);
			mv.addObject("physiclalStatuses", physiclalStatuses);
			
			this.entity=userManager.get(pd.getString("id"));
			Dept dept=this.deptManager.get(entity.getDeptId());
			Set<Role> roleSet=entity.getRoleSet();
			List<Menu> roleList=this.userManager.listTree(user,roleSet);
			JSONArray arry = JSONArray.fromObject(roleList);
			String json1 = arry.toString(); 
			json1 = json1.replaceAll("MENU_ID", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			model.addAttribute("roleZTreeNodes", json1); 
			
			
			mv.addObject("dept",dept);
			mv.addObject("entity",this.entity);
			mv.addObject("msg", "add");
			mv.setViewName("jsp/system/user/user_edit");
			mv.addObject("pd", pd);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	

	
	

	/**去选择所属机构
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toTree")
	public ModelAndView toTree(Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();		
			/***
			 * 刷机构树
			 */
			User user=this.getLoginUser();
			Set<Dept> managerDepts=user.getManageDeptSet();
			String fromType=pd.getString("fromType");
			String id=pd.getString("id");
			Set<Dept> entityManagerDepts=new TreeSet<Dept>();
			/*if(fromType!=null&&fromType.equals("dept")){
				if(id!=null&&id.length()>1){
					Dept dept1=deptManager.get(id);
					entityManagerDepts.add(dept1);
				}		
			}else{
				if(id!=null&&id.length()>1){
					entity=userManager.get(id);
					Dept dept1=deptManager.get(entity.getDeptId());
					entityManagerDepts.add(dept1);
				}		
			}  */
			List<Dept> deptList=new ArrayList<Dept>();
			for(Dept dept:managerDepts){				
				List<Dept> list=deptManager.listTreeForAdd(dept.getId(),entityManagerDepts);
				dept.setSubDepartment(list);				
				dept.setTreeurl("");  
				dept.setHasDepartment(false); 
				logger.info(dept.getDeptName()+"----"+dept.isHasDepartment());
				if(entityManagerDepts.contains(dept)){					
					dept.setHasDepartment(true); 
				}
				dept.setTarget("treeFrame");   
				deptList.add(dept);
			}
			JSONArray arr = JSONArray.fromObject(deptList);
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);  	 
			mv.setViewName("jsp/system/user/user_dept_tree");
			mv.addObject("pd", pd);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/toRadioTree")
	public ModelAndView toRadioTree(Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();		
			/***
			 * 刷机构树
			 */
			User user=this.getLoginUser();
			Set<Dept> managerDepts=user.getManageDeptSet();
			String fromType=pd.getString("fromType");
			String id=pd.getString("id");
			Set<Dept> entityManagerDepts=new TreeSet<Dept>();
			 if(fromType!=null&&fromType.equals("dept")){
				if(id!=null&&id.length()>1){
					Dept dept1=deptManager.get(id);
					entityManagerDepts.add(dept1);
				}		
			}else{
				if(id!=null&&id.length()>1){
					entity=userManager.get(id);
					Dept dept1=deptManager.get(entity.getDeptId());
					entityManagerDepts.add(dept1);
				}		
			}  
			List<Dept> deptList=new ArrayList<Dept>();
			for(Dept dept:managerDepts){				
				List<Dept> list=deptManager.listTreeForAdd(dept.getId(),entityManagerDepts);
				dept.setSubDepartment(list);				
				dept.setTreeurl("");  
				dept.setHasDepartment(false); 
				logger.info(dept.getDeptName()+"----"+dept.isHasDepartment());
				if(entityManagerDepts.contains(dept)){					
					dept.setHasDepartment(true); 
				}
				dept.setTarget("treeFrame");   
				deptList.add(dept);
			}
			JSONArray arr = JSONArray.fromObject(deptList);
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);  	 
			mv.setViewName("jsp/system/user/user_dept_radio_tree");
			mv.addObject("pd", pd);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	

	/**去新增页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toMove")
	public ModelAndView toMove(Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();		 
			entity=userManager.get(pd.getString("id"));
			Dept dept=this.deptManager.get(entity.getDeptId()); 
			mv.addObject("dept",dept);
			mv.addObject("entity",this.entity); 
			mv.setViewName("jsp/system/user/user_move");
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/move")
	public ModelAndView move(User user) throws Exception { 	
		entity = this.baseManager.get(user.getId());		
		String newDeptId =user.getDeptId();
		Dept assignDept = super.getAssignDept();//指派的机构	
		this.baseManager.updateDept(entity, newDeptId, assignDept); 
		ModelAndView mv = this.getModelAndView();  
		mv.addObject("msg","success");
		mv.setViewName("jsp/save_result");		
		return mv;
	}
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(User user) throws Exception{	
		entity=user; 
		/*PageData pd=this.getPageData();
		String manageDeptIds=pd.getString("manageDeptIds");		
		String mgrDeptIds[]=manageDeptIds.split(",");
		for(String deptId:mgrDeptIds)
		{
			logger.info("deptId==="+deptId);
			Dept dept=this.deptManager.get(deptId);
			entity.addManageDept(dept);
		}
		
		String roleIds=pd.getString("roleIds");
		String roleArr[]=roleIds.split(",");
		for(String roleId:roleArr)
		{
			Role role=this.roleManager.get(roleId);
			entity.getRoleSet().add(role);
		} */
		super.fillCollectionField(entity, "manageDeptSet", "manageDeptIds");
		super.fillCollectionField(entity, "roleSet", "roleIds");	
		if(isUpdate(entity) ){				
			entity = userManager.update(entity); 				
		}
		else{
			//entity.setComeFromSign("1");
			SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
		    String useDate=sp.format(date); 
			entity.setRegistrationDate(useDate);  
			userManager.insert(entity);			 
		}
	
		/*if(entity.getUserStateInfo().getUserStateName().equals("2")){
			Dept dept=this.deptManager.get(entity.getDeptId());
			if(dept.getConnMan()!=null)
				dept.setConnMan(dept.getConnMan()+","+entity.getName());
			else
				dept.setConnMan(entity.getName());
			this.deptManager.update(dept);
		}*/
	
		ModelAndView mv = this.getModelAndView();  
		mv.addObject("msg","success");
		mv.setViewName("jsp/save_result");
		return mv;
	}
	/**删除用户
	 * @param out
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/deleteU",method=RequestMethod.POST)
	public Object deleteU(String userId) throws Exception{ 
		logBefore(logger, Jurisdiction.getUsername()+"删除会员");
		Map<String,String> map = new HashMap<String,String>(); 
		String errCode="0";
		String errMsg="success";
		try{ 
			userManager.delete(entity, userId);
		}catch(Exception e){
			if (e instanceof DataIntegrityViolationException) {
				errCode="1";
				errMsg="用户已经发生业务不能删除,只能弃用";
			}else{
				errCode="1";
				errMsg=e.getMessage();
			}
			
		}
		map.put("errCode", errCode);
		map.put("errMsg", errMsg);
		return map;
	}
	
	/**判断用户名是否存在
	 * @return
	 */
	@RequestMapping(value="/hasU")
	@ResponseBody
	public Object hasU(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData(); 
		try{
			pd = this.getPageData();
			if(!userManager.findUserNo(pd.getString("no")).isEmpty()){
				errInfo = "error";
			}
			/*if(userManager.findByMobile(pd.getString("mobile"))!=null){
				errInfo = "error";
			}*/
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@ApiOperation(value="根据用户id重置密码", notes="根据用户id重置密码",httpMethod = "POST")
	@ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "path")})
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public Object resetPassword(@RequestParam String userId){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		try{
			String pw=StrUtil.randomPassword(6);
			logger.info("重置密码，新密码:"+pw);
			
			String passWord = StrUtil.MD5Encode(pw);
			User user=this.userManager.get(userId);
			userManager.updatePassword(userId,passWord);
			//根据系统配置发送密码通知
			//sendMsg(user.getEmail(),"密码修改提示",user.getName()+",您好，你在代维管理平台的登陆密码被管理员重置为:"+pw+",请妥善保存。"); 
			 
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	 
	
	@ApiOperation(value="根据用户mobile重置密码", notes="根据用户mobile重置密码",httpMethod = "POST")
	@ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = "用户手机号", required = true, dataType = "String", paramType = "path")})
	@RequestMapping(value="/resetPasswordByMobile",method=RequestMethod.POST)
	@ResponseBody
	public Object resetPasswordByMobile(@RequestParam String mobile){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		String err_code="0";
		try{
			String pw=StrUtil.randomPassword(6);
			logger.info("重置密码，新密码:"+pw);
			
			String passWord = StrUtil.MD5Encode(pw);
			User user=userManager.findByMobile(mobile);
			
			if(user!=null){
				userManager.updatePassword(user.getId(),passWord);				
				//根据系统配置发送密码通知
				//sendMsg(user.getEmail(),"密码修改提示",user.getName()+",您好，你在代维管理平台的登陆密码更改为:"+pw+",请妥善保存。"); 
			}else{
				errInfo="错误的手机号";
				err_code="3";
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo=e.getMessage();
			err_code="1";
		}
		map.put("result", errInfo);				//返回结果
		map.put("err_code", err_code);
		map.put("err_msg", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**解锁
	 * @return
	 */
	@RequestMapping(value="/resetLock")
	@ResponseBody
	public Object resetLock(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData(); 
		try{
			pd = this.getPageData();
			String id = pd.getString("id");
			userManager.resetLock(id);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**解锁
	 * @return
	 */
	@RequestMapping(value="/startUp")
	@ResponseBody
	public Object startUp(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData(); 
		try{
			pd = this.getPageData();
			String id = pd.getString("id");
			User user=userManager.get(id);
			//user.setState("1");
			userManager.update(user);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/giveUp")
	@ResponseBody
	public Object giveUp(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData(); 
		try{
			pd = this.getPageData();
			String id = pd.getString("id");
			User user=userManager.get(id);
			if(userManager.giveUpValidate(user)){
				//user.setState("0");
				userManager.update(user);
			}else{
				 errInfo = "error";
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@ResponseBody
	@ApiOperation(value="获取登录user", notes="获取登录user",httpMethod = "GET")
    @RequestMapping(value="/getLoginUser",method=RequestMethod.GET)
	public User getSessionLoginUser(){
		Dept dept=this.deptManager.get(this.getLoginUser().getDeptId());
		User user=this.getLoginUser();
		user.setDeptName(dept.getDeptName());
		return user;
	}
	
	
	private String getDeptTreeJson(String DEPARTMENT_ID) throws Exception{
		/***
		 * 刷机构树
		 */
		User user=this.getLoginUser();
		Set<Dept> managerDepts=user.getManageDeptSet();
		List<Dept> deptList=new ArrayList<Dept>();

		Dept dept1=this.deptManager.get(DEPARTMENT_ID);
		Set<Dept> cDepts= new TreeSet<Dept>();
		cDepts.add(dept1);
		for(Dept dept:managerDepts){				
			List<Dept> list=this.deptManager.listTreeForAdd(dept.getId(),cDepts);
			dept.setSubDepartment(list);				
			dept.setTreeurl("");  
			dept.setTarget("treeFrame");  
			dept.setHasDepartment(false); 
			if(cDepts.contains(dept)){
				dept.setHasDepartment(true);
			} 
			deptList.add(dept);
		}
		JSONArray arr = JSONArray.fromObject(deptList);
		String json = arr.toString();
		json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("parentId", "pId").replaceAll("deptName", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
		
		return json;
	}
	
	@ResponseBody
	@ApiOperation(value="user信息", notes="根据url的id来获取user信息",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "user的ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/getEntityById/{id}",method=RequestMethod.GET)
	public User getEntityById(@PathVariable("id") String id)throws Exception{		 
		 User user= this.entity=this.baseManager.get(id); 				
		 Dept dept=this.deptManager.get(user.getDeptId());
		 user.setDeptName(dept.getDeptName()+"("+dept.getDeptNo()+")");
		 return user;
	}
	
	@ResponseBody
	@ApiOperation(value="根据手机号修改密码", notes="根据手机号修改密码",httpMethod = "POST")
	@ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = "user的手机号", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "oldpwd", value = "user的旧密码", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "newpwd", value = "user的新密码", required = true, dataType = "String", paramType = "from")})
    @RequestMapping(value="/updatePwdByMobile",method=RequestMethod.POST)
	public Object updatePwdByMobile(@RequestParam String mobile,@RequestParam String oldpwd,@RequestParam String newpwd)throws Exception{	
		Map<String,String> map = new HashMap<String,String>(); 
		String err_code = "0"; 
		String err_msg="";
		User user =this.baseManager.findByMobile(mobile);
		if(user==null){
			err_msg="未找到用";
			err_code="3";
		}else{
			if(!oldpwd.equals("") && StrUtil.MD5Encode(oldpwd).equals(user.getPassword())){
				this.baseManager.updatePassword(user.getId(), StrUtil.MD5Encode(newpwd));
				err_code="0";
				err_msg="修改成功";
			}else{
				if(oldpwd.equals("")){
					err_code="1";
					err_msg="旧密码不能为空";
				}else if(!StrUtil.MD5Encode(oldpwd).equals(user.getPassword())){
					err_code="2";
					err_msg="旧密码错误";
				}
			}
		}
		map.put("err_code", err_code);
		map.put("err_msg", err_msg);
		return map;
	}
	
	
	@ResponseBody
	@ApiOperation(value="根据ID修改密码", notes="根据ID修改密码",httpMethod = "POST")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "user的id", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "oldpwd", value = "user的旧密码", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "newpwd", value = "user的新密码", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "mobile", value = "user手机号", required = true, dataType = "String", paramType = "from"),
				@ApiImplicitParam(name = "email", value = "user的email", required = true, dataType = "String", paramType = "from")})
    @RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public Object updatePwd(@RequestParam String id,@RequestParam String oldpwd,@RequestParam String newpwd,@RequestParam String mobile,@RequestParam String email)throws Exception{	
		Map<String,String> map = new HashMap<String,String>(); 
		String err_code = "0"; 
		String err_msg="";
		User user =this.baseManager.get(id);
		if(!oldpwd.equals("") && StrUtil.MD5Encode(oldpwd).equals(user.getPassword())){
			user.setEmail(email);
			user.setTelephone(mobile);
			this.baseManager.update(user);
			this.baseManager.updatePassword(user.getId(), StrUtil.MD5Encode(newpwd));
			//根据系统配置发送密码通知
			//sendMsg(user.getEmail(),"密码修改提示",user.getName()+",您好，你在代维管理平台的登陆密码更改为:"+newpwd+",请妥善保存。"); 
			 
			//	this.getSession().setAttribute(Const.SESSION_USER, user);
			err_code="0";
			err_msg="修改成功";
		}else{
			if(oldpwd.equals("")){
				err_code="1";
				err_msg="旧密码不能为空";
			}else if(!StrUtil.MD5Encode(oldpwd).equals(user.getPassword())){
				err_code="2";
				err_msg="旧密码错误";
			}
		}
		map.put("err_code", err_code);
		map.put("err_msg", err_msg);
		return map;
	}
	
	@ResponseBody
	@ApiOperation(value="用户管理机构信息", notes="用户管理机构信息",httpMethod = "GET") 
    @RequestMapping(value="/getManagerDept",method=RequestMethod.GET)
	public User getManagerDept()throws Exception{		 
		 User user= this.getLoginUser();			 
		 return user;
	}
	
}
