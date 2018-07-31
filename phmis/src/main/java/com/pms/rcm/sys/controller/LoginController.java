package com.pms.rcm.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.base.controller.BaseController;
import com.pms.base.util.Const;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.base.util.TimeUtil;
import com.pms.base.util.Tools;
import com.pms.filter.LoginFilter;
import com.pms.rcm.sys.manager.AdminInfoManager;
import com.pms.rcm.sys.manager.DeptManager;
import com.pms.rcm.sys.manager.MenuManager;
import com.pms.rcm.sys.manager.ModuleFunManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.AdminInfo;
import com.pms.rcm.sys.vo.Dept;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.MenuBean;
import com.pms.rcm.sys.vo.Role;
import com.pms.rcm.sys.vo.User;
import com.pms.rcm.task.manager.ActRuTaskManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 

/**
 * 总入口  
 */
@Controller
public class LoginController extends BaseController<User,UserManager> {
	private  static int noUseDays=0;
	
	@Resource(name="userManager")
	private UserManager userManager;
	@Resource(name="menuManager")
	private MenuManager menuManager;
	@Resource(name="moduleFunManager")
	private ModuleFunManager moduleFunManager;
	@Resource(name="deptManager")
	private DeptManager deptManager;  
	@Autowired
	private ActRuTaskManager actRuTaskManager;
	@Autowired
	private AdminInfoManager adminInfoManager;
	/**访问登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		System.out.println(pd.get("SYSNAME"));
		mv.setViewName("jsp/system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	private static int accountStrage_no_user_stop=1;//账号长期没有登录已经停用
	private static int accountStrage_locked=2;//账号登录失败，锁定时长还没过 
	/**请求登录，验证用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_login",method=RequestMethod.POST)
	@ResponseBody
	public   Object login(@RequestBody User entity)throws Exception{ 
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		Session session = super.getSession();
		String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//获取session中的验证码
		//String code = entity.getCode();
		//判断登录验证码
		User user=new User(); 
		AdminInfo adminInfo=new AdminInfo();
		adminInfo=this.adminInfoManager.getAdminInfo(entity.getNo());
		
		Map<String,String> disModuleFun = new HashMap<String, String>();//用户被禁止的功能权限
		boolean isSuper = false; 			
		//加密登录密码
		String psd =StrUtil.MD5Encode(entity.getPassword().trim());
		entity.setPassword(psd); 
	   /// isSuper = entity.isAdmin();	
		if(adminInfo!=null){//是超级用户	  
				///String xtPwd=Tools.readTxtFile(Const.ADMINPWD);
				if(psd.equals(adminInfo.getAdminPassword())){
						entity.setNo(adminInfo.getAdminId());
					    user = this.userManager.findLoginUser(entity); 
						user.setName("超级管理员");					  
						Dept localDept = deptManager.getByCasCode("001");
						if(localDept != null){
							user.setDeptId(localDept.getId());
							user.addManageDept(localDept);
							user.setAssignDept(localDept);
						}
						getRequest().setAttribute("superUser", "superUser");
						errInfo = "success";	
						user.setPwdEditDate(StrUtil.getDateString("yyyy-MM-dd"));
						session.setAttribute(Const.SESSION_USER, user);
						session.setAttribute("userType", entity.getUserStateId());
						//shiro加入身份验证
						Subject subject = SecurityUtils.getSubject(); 
					    UsernamePasswordToken token = new UsernamePasswordToken(user.getNo(), user.getPassword()); 
					    try { 
					        subject.login(token); 
					    } catch (AuthenticationException e) { 
					    	errInfo = "身份验证失败！";
					    }
				}else{
					errInfo = "密码错误";
				}  
		   
		}
		else{//不是超级用户	
			errInfo = "success";
			user = this.userManager.findLoginUser(entity);
			if(user==null){//非有效用户
				errInfo = "用户不存在";
			}else{
			 
					//loginLoginNoAdmin方法用了用户类型属性，是页面传入的，所以需要set
				//	user.setUserType(entity.getUserType());
					errInfo=loginLogicNoAdmin(user,session,entity.getPassword()); 
			}
			
			
		}		 
		

		map.put("result", errInfo); 
		JSONObject jsonObject = JSONObject.fromObject(map); 
		return jsonObject.toString();       
	}
	
	/**
	 * 用手机号登录
	 * @param mobile
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_app",method=RequestMethod.POST)
	@ResponseBody
	public   Object loginApp(@RequestParam String mobile,@RequestParam String password)throws Exception{
		logger.info("登录开始时间："+StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
		Map<String,Object> map = new HashMap<String,Object>();
		Session session = super.getSession();
		String errInfo="";
		String errCode="";
		User user = this.userManager.findByMobile(mobile);
		if(user==null){//非有效用户
			errInfo = "用户不存在";
		}else{
			//user.setToken(StrUtil.MD5Encode(user.getId()+StrUtil.getDateString("yyyyMMddHHmmss")));//app端退出后，设置user的token为空
			//List<SysStrategy> strategyList=sysStrategyManager.find("from SysStrategy");
			/*int r=accountStrage(user,strategyList);
			if(r==accountStrage_no_user_stop){//账号长期没有登录已经停用
				errInfo="账号："+user.getName()+"超过规定天数("+noUseDays+")未登录已经停用";
			}else if(r==accountStrage_locked){//账号登录失败，锁定时长还没过
				errInfo="账号："+user.getName()+"登录失败，未超过固定锁定时长";
			}else{
				errInfo= loginLogicNoAdmin(user,session,StrUtil.MD5Encode(password));
			}*/
		}
		if(Tools.isEmpty(errInfo)){
			errInfo = "success";					//验证成功
			errCode="0";
			map.put("data", user);
			//this.getRequest().getSession().setAttribute(Const.SESSION_USER_TOKEN, user.getToken());
			logBefore(logger, entity.getName()+"登录系统");
		}
		map.put("result", errInfo); 
		map.put("err_code", errCode);
		map.put("err_msg", errInfo);
		
//		CacheMgr.getInstance().addCache(user.getToken(), user.getName()+"|"+user.getPassword());
		
		JSONObject jsonObject = JSONObject.fromObject(map); 
		logger.info("登录结束时间："+StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
		return jsonObject.toString();
	}
	
	/**
	 * 不是超级用户的用户登录处理逻辑
	 * @param user
	 * @param session
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	private String loginLogicNoAdmin(User user,Session session,String password) throws NumberFormatException, ParseException{
		String errInfo="";
		errInfo = "success";
		if(user==null){//非有效用户
			errInfo = "用不存在";
		}
		 
		/*else if(user.getUserStateInfo().getUserStateName().trim().equals("0")){//账户弃用
			errInfo = "账号已经弃用";
		}*/
		else if(!user.getPassword().trim().equals(password.trim())){//密码验证失败
		    String nowDate = StrUtil.getDateString("yyyy-MM-dd");
		    int lock = 1;
			if(nowDate.equals(TimeUtil.toStrDateFromUtilDateByFormat(TimeUtil.toUtilDateFromStrDateByFormat(user.getLastLoginDate(), "yyyy-MM-dd"), "yyyy-MM-dd"))){
				lock = Integer.parseInt(user.getIsLock()) + 1;
			} 
			user.setIsLock(""+lock);
			//修改 isLock 字段 
			user.setLastLoginDate(StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
			this.userManager.update(user);
			errInfo = "密码错误";
		}
		else if(Integer.parseInt(StrUtil.parseDateString(user.getRegistrationDate())) > Integer.parseInt(StrUtil.getDateString("yyyyMMdd")) ){ //账户未生效
			errInfo = "账号未生效";
		}
		else{//验证通过
			Dept userDept = this.deptManager.get(user.getDeptId()); 
			session.setAttribute(LoginFilter.USER_SESSION_DEPT_NAME, userDept);
			/////////////////////////处理定期修改密码//////////////////
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			if(StrUtil.isNotEmpty(user.getPwdEditDate())){
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(format.parse(user.getPwdEditDate()));
					cal.add(Calendar.MONTH, 3);
					if(cal.getTime().before(new Date())){
						getRequest().setAttribute("toPassword", "toPassword");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else{//第一次使用开始记录
				user.setPwdEditDate(format.format(new Date()));
			} 
			//重置  isLock 标志
			user.setIsLock("1");
			user.setLastLoginDate(StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
			this.userManager.update(user); 
			for (Role role : user.getRoleSet()) {
//				if(RoleAction.ROLETYPE_MANAGE.equals(role.getRoleType())){//管理员角色
					if(user.getManageDeptSet().size()>0){
						Dept  assignDept = user.getManageDeptSet().iterator().next();
						user.setAssignDept(assignDept);
						break;
					}
//				}
			} 
			//disModuleFun = this.moduleFunManager.findDisModuleFun(user);//查询该用户禁用功能	 
			//session.setAttribute(LoginFilter.USER_SESSION_DISMODULEFUN, disModuleFun);//在Session中设置用户禁用的功能						 
			getRequest().setAttribute("topMenuList", new ArrayList<Menu>());
			/////////////////////处理菜单//////////////////////////////						
			session.setAttribute("loginIp",getRequest().getRemoteAddr());	
			session.setAttribute("loginRole",user.getRoleSet()); 
			String roleStr="";
			for(Role role:user.getRoleSet())
			{
				if(roleStr=="")
					roleStr=role.getRoleName();
				else
					roleStr=roleStr+","+role.getRoleName();
			}
			session.setAttribute("roleStr",roleStr);  
			//errInfo = "success";					//验证成功
			logBefore(logger, user.getName()+"登录系统");
			session.setAttribute(Const.SESSION_USER, user);
			//session.setAttribute("userType", user.getUserType());
			//shiro加入身份验证
			Subject subject = SecurityUtils.getSubject(); 
		    UsernamePasswordToken token = new UsernamePasswordToken(user.getNo(), user.getPassword()); 
		    
		    try { 
		        subject.login(token); 
		    } catch (AuthenticationException e) { 
		    	errInfo = "身份验证失败！";
		    }
		}
		return errInfo;
	}
	
	/**访问系统首页
	 * @param changeMenu：切换菜单参数
	 * @return
	 */
	@RequestMapping(value="/main/{changeMenu}")
	@SuppressWarnings("unchecked")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
		 
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
	
		try{
			Session session = super.getSession();
			User user = (User)session.getAttribute(Const.SESSION_USER);				//读取session中的用户信息(单独用户信息)			 
			if (user != null) {
				User userr = (User)session.getAttribute(Const.SESSION_USERROL);		//读取session中的用户信息(含角色信息)
				if(null == userr){
				    
                        session.setAttribute(Const.SESSION_USERROL, user);              //存入session  				
				}else{
					///user = userr;
				}
				String USERNAME = user.getName();  
				session.setAttribute(Const.SESSION_USERNAME, USERNAME);				//放入用户名到session
				List<Menu> allmenuList = new ArrayList<Menu>(); 
			    allmenuList = menuManager.listUserAllMenu("0",user);				//获取所有菜单					 
				//切换菜单处理=====start
				List<Menu> menuList = new ArrayList<Menu>();
				menuList=allmenuList; 
				mv.setViewName("jsp/system/index/main");
				mv.addObject("user", user);
				mv.addObject("menuList", menuList);
				String json=JSONArray.fromObject(getMenuBeanList(menuList)).toString(); 
				json = json.replaceAll("MENU_ID", "id").replaceAll("menuIcon", "icon").replaceAll("menuName", "text").replaceAll("subMenu", "menus").replaceAll("hasMenu", "checked").replaceAll("actionPath", "url");
			
				//System.out.println("menuJson==="+json);
				mv.addObject("menuJson", json);
			}else {
				mv.setViewName("jsp/system/index/login");//session失效后跳转登录页面
			}
		} catch(Exception e){
			mv.setViewName("jsp/system/index/login");
			logger.error(e.getMessage(), e);
		}
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		
		return mv;
	}
	
	public List<MenuBean> getMenuBeanList(List<Menu> menuList){
		List<MenuBean> list=new ArrayList<MenuBean>();
		for(Menu menu:menuList){
			 MenuBean bean=new MenuBean();
			 bean.setId(menu.getId());
			 bean.setIcon(menu.getMenuIcon());
			 bean.setText(menu.getMenuName());
			 bean.setUrl(menu.getActionPath());
			 if(!menu.getSubMenu().isEmpty()){
				 bean.setMenus(getMenuBeanList(menu.getSubMenu()));
			 }
			 list.add(bean);
		}
		return list;
	}
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		String USERNAME = Jurisdiction.getUsername();	//当前登录的用户名
		logBefore(logger, USERNAME+"退出系统");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session = Jurisdiction.getSession();	//以下清除session缓存
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("jsp/system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "jsp/system/index/tab";
	}
	
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/login_default")
	public ModelAndView defaultPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(); 
		User user=this.getLoginUser();
		if(!user.isAdmin()){
			int self=this.actRuTaskManager.getSelfTaskCount(this.getLoginUser());
			int group =this.actRuTaskManager.getGroupTaskCount(this.getLoginUser());
			pd.put("self",self);//系统用户数
			pd.put("group",group);//系统用户数
			mv.addObject("pd",pd);
		}
		
		mv.setViewName("jsp/system/index/default");
		return mv;
	}
	
	@RequestMapping(value="/checkUserPwd")
	@ResponseBody
	public Object checkUserPwd(@RequestBody User user){
		String result=this.baseManager.checkUserAuth(user.getNo(),user.getPassword(),"1");
		if(result.equals("")){
			return false;
		}else{
			return true;
		}
	}
}
