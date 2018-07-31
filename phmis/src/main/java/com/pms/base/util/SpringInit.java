package com.pms.base.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 

public class SpringInit implements ServletContextListener {
	
	private static WebApplicationContext springContext;
    
    public SpringInit() {
        super();
    }
 
	public void contextDestroyed(ServletContextEvent event) {
		
	}
 
	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		 
		
	}
	
	public static ApplicationContext getApplicationContext() {
        return springContext;
    }

}
