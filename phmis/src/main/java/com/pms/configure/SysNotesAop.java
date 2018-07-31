package com.pms.configure;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.base.common.Email;
import com.pms.base.common.EmailTread;
import com.pms.base.util.StrUtil;
import com.pms.base.util.TimeUtil; 
import com.pms.rcm.notes.manager.SrsNotesManager;
import com.pms.rcm.notes.vo.SrsNotes; 
import com.pms.rcm.sys.manager.EmailManager;
import com.pms.rcm.sys.manager.UserManager;
import com.pms.rcm.sys.vo.User;

import net.sf.json.JSONArray;


@Aspect
@Service
public class SysNotesAop {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SrsNotesManager srsNotesManager; 
	@Resource(name="emailManager")
	private EmailManager emailManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	 
	 
    @Pointcut("execution(* com.pms.rcm.*.*Manager.*(..))")//com.srs.rcm.checkplan.manager
    public void pointcutName(){}
    
    @Pointcut("execution(* com.pms.rcm.*.*Manager.*(..))")//com.srs.rcm.checkplan.manager
    private void pointcutName2(){}
    
    @Before("pointcutName() || pointcutName2()")
//    @Before(value="pointcutName(p)",argNames="p")  
    public void performance(JoinPoint jp){
    	logger.info("--------Spring AOP 切面通知----------");
    }
    /**
     * 处理逻辑：获取方法是否有SysNotesAnnotation注解；如果有注解则生成SrsNotes消息保存到实体；保存实体时根据SysNotesAnnotation注解传入的参数，确定消息类型。
     * 注意：SysNotesAnnotation注解必须是新增或者信息planExe实体
     * @param point
     * @param returnResult
     */
    @AfterReturning(pointcut="pointcutName() || pointcutName2()",returning="returnResult")
	//Object returnValue：与注解中的returnValue名字一致
	public void afterReturning(JoinPoint point,Object returnResult){
    	logger.info("------spring aop 后置通知，调用的方法："+point.getSignature()+"，传递参数："+Arrays.toString(point.getArgs())+"，返回参数："+returnResult);

	      Object[] args = point.getArgs();
	        Class<?>[] argTypes = new Class[point.getArgs().length];
	        for (int i = 0; i < args.length; i++) {
	              argTypes[i] = args[i].getClass();
	        }
	        Method method = null;
	        try {
	            method = point.getTarget().getClass()
	                    .getMethod(point.getSignature().getName(), argTypes);
	        } catch (NoSuchMethodException e) {
	            logger.warn("---调用反射方法getMethod时，方法："+point.getSignature().getName()+",参数，造成方法没有找到。"+e.getMessage());
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        }
	        SysNotesAnnotation sysNotesAnnotation=null;
	        if(method!=null){
	        	 try{
	 	        	sysNotesAnnotation = method.getAnnotation(SysNotesAnnotation.class);
	 	        }catch(Exception e){
	 	        	logger.warn("方法:"+method.getName()+"的被切面捕获，但可能没有注解");
	 	        	
	 	        }
	        }
	       
	        if(sysNotesAnnotation!=null){
	        	logger.info("-----spring aop 中有sysNotesAnnotation注解:"+sysNotesAnnotation);
	        	 
	        	
	        }
	  }
}
