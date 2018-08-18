package com.pms.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pms.base.util.Const;
import com.pms.base.util.Jurisdiction;
import com.pms.rcm.maintain.manager.LogInfoManager;
import com.pms.rcm.maintain.vo.LogInfo;
import com.pms.rcm.sys.manager.MenuManager;
import com.pms.rcm.sys.vo.Menu;
import com.pms.rcm.sys.vo.User;

public class SysemLogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private LogInfoManager logInfoManager;
	@Autowired
	private MenuManager menuManager;
	 
	  

	/**
	 * 获取请求Body
	 *
	 * @param request
	 * @return
	 */
	public String getBodyString(final ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = cloneInputStream(request.getInputStream());
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Description: 复制输入流</br>
	 * 
	 * @param inputStream
	 * @return</br>
	 */
	public InputStream cloneInputStream(ServletInputStream inputStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}
	 

	/**	 
	 * 在请求处理之前进行调用（Controller方法调用之前），只有返回true才会继续向下执行，返回false取消当前请求	 
	 */	
	@Override	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,			
			Object handler,Exception arg3) throws Exception {		
		 try {
			 HandlerMethod handlerMethod = (HandlerMethod) handler;
			    Method method=handlerMethod.getMethod();
			    if(method.getName().equals("getMenuFun")||method.getName().equals("list")||method.getName().equals("getEntityById")){
			    	// return super.preHandle(request, response, handler);
			    	return;
			    }
	            if (handler instanceof HandlerMethod) {	                
	                String methodName = handlerMethod.getMethod().getName();//方法名称
	                if(methodName.equals("error") || methodName.equals("success")) {
	                   // return super.preHandle(request, response, handler);
	                }
	                String uri = request.getRequestURI();//请求路径
	                ///System.out.println(uri);
	                String[] urlArr=uri.split("/");
	                String url=urlArr[1]; 
	                Menu menu=this.menuManager.getMenuByUrl(url);
	                String remoteAddr = getIpAddr(request);//ip 
	                String queryString =getBodyString(request);//getOpenApiRequestData(request); 	 
	                if(urlArr.length>3){
	                	queryString=urlArr[3];
	                }
	                if(menu!=null){
	                	 String value="IP["+remoteAddr+"] "+menu.getMenuName()+": " + methodName + " 操作 " + "请求参数 : " + queryString + "";
	 	                 this.addSystemLog(value);
	                }
	               
	            }
		       // return super.preHandle(request, response, handler);

	        } catch (Exception e) {
	            e.printStackTrace();
		       // return super.preHandle(request, response, handler);

	        	//出错
	          ///  logger.error(LogUtil.setLogError(e, "用户操作日志记录异常" , "RequestParamInfoIntorceptor"));
	        }
	       /// return super.preHandle(request, response, handler);
	}	
	 //获取客户端IP
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
 
	public Session getSession(){
		Session session = Jurisdiction.getSession();
		return session;
	}
	 protected User getLoginUser(){ 
			Object obj = getSession().getAttribute(Const.SESSION_USER);
			if(obj != null){
				return (User)obj;
			}
			else{ 
				return null;
			}
		}
	/**	 
	 * <p>Discription:[保存操作日志]</p>	 
	 * Created on 2017年11月20日 下午3:07:33	 
	 * @param operationContent 操作内容	  
	 */	
	public void addSystemLog(String operationContent) {		
		// 获取此次请求的request对象		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();			
		User user=this.getLoginUser();
		// 浏览器标标识		 			
		LogInfo systemLog = new LogInfo();		
		systemLog.setDate(new Date(System.currentTimeMillis()));		
		systemLog.setLogContent(operationContent);
		systemLog.setRadarId(user.getId());	
		systemLog.setTypeId("4028838f654c42b601654c5408110000");
		systemLog.setUserName(user.getName()); 		
		logInfoManager.insert(systemLog);	
	}		
	
 
	
	/**	 
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）	 
	 */	
	@Override
	public boolean preHandle(HttpServletRequest request,			
			HttpServletResponse response, Object handler)			
					throws Exception {	
		return super.preHandle(request, response, handler);
	}	
	/**	 
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）	 
	 */	
	@Override	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,			
			Object arg2, ModelAndView arg3) throws Exception {	
		
	}

}
