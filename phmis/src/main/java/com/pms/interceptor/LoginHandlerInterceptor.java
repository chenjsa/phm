package com.pms.interceptor;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pms.base.util.Const;
import com.pms.base.util.StrUtil;
import com.pms.base.util.TimeUtil; 
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.User;
 
@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	protected Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class); 
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//logger.info("========================登录拦截开始："+StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
		String path = request.getServletPath();
		//logger.info(path+":"+path.matches(Const.NO_INTERCEPTOR_PATH));
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			String headerToken=request.getHeader("token");
		    
			//返回包含当前已经过验证的用户的名称的java.security.Principal对象
			Principal userPrincipal =request.getUserPrincipal();
			//logger.info("拦截器获取的请求信息:"+userPrincipal+"------:"+path+",header token:"+request.getHeader("token"));
			//logger.info("session中保存的信息："+(String) request.getSession().getAttribute(Const.SESSION_USER_TOKEN));
			
			if(headerToken!=null && !headerToken.equals("")){//app端的请求，有token
				String sessionToken=(String) request.getSession().getAttribute(Const.SESSION_USER_TOKEN);
				if(sessionToken==null && !headerToken.equals("")){
					List<User> userList=userManager.find("from User where token='"+headerToken+"'");
					User user=null;
					if(userList!=null && userList.size()==1){
						user=userList.get(0);
					}
//					User user=userManager.getUserByToken(headerToken);
					if(user!=null){
						//如果系统时间减去上次登录时间超过30天则，token过期
						String sysDate=StrUtil.getDateString("yyyy-MM-dd HH:mm:ss");
						String lastLoginDate=user.getLastLoginDate();
						int betweenDay=TimeUtil.daysBetween(TimeUtil.toUtilDateFromStrDateByFormat(lastLoginDate, "yyyy-MM-dd HH:mm:ss"), TimeUtil.toUtilDateFromStrDateByFormat(sysDate, "yyyy-MM-dd HH:mm:ss"));
						if(betweenDay>10){
							request.setAttribute("err_msg", "token无效或已经过期");
							request.setAttribute("err_code", "-2");
							return false;
						}
						///request.getSession().setAttribute(Const.SESSION_USER_TOKEN, user.getToken());
						request.getSession().setAttribute(Const.SESSION_USER, user);
						//有传token过来，并且token能查到用户，token也没有过期，则更新用户登录时间
						if(!StrUtil.parseDateString(lastLoginDate).equals(StrUtil.parseDateString(sysDate))){
							user.setLastLoginDate(sysDate);
							userManager.update(user);
						}
						return true;
					}else{
						request.setAttribute("err_msg", "token无效或已经过期");
						request.setAttribute("err_code", "-2");
						logger.info("token无效或已经过期");
						return false;
					}
				}else if(sessionToken.equals(headerToken)){
					return true;
				}else{
					request.setAttribute("err_msg", "token无效或已经过期");
					request.setAttribute("err_code", "-2");
					return false;
				}
				
				
			}
			//app端的请求，有token ---------结束
			
			User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
			//System.out.println("USER=="+user.getName());
			if(user!=null){
				path = path.substring(1, path.length());
				boolean b =true;// Jurisdiction.hasJurisdiction(path); //访问权限校验
				if(!b){
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;		
			}
		}
	}
	
}
