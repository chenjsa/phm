package com.pms.configure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.pms.interceptor.LoginHandlerInterceptor;
import com.pms.interceptor.SysemLogInterceptor;
 
/**
 * @author zh
 * @ClassName cn.saytime.Swgger2
 * @Description
 * @date 2017-07-10 22:12:31
 */
@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

	@Bean
	public SysemLogInterceptor sysemLogInterceptor() {
	    return new SysemLogInterceptor();
	}
	
	@Bean
	public LoginHandlerInterceptor loginHandlerInterceptor() {
	    return new LoginHandlerInterceptor();
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(sysemLogInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("jsp/*");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public ITemplateResolver  templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");

        templateResolver.setCacheable(false);
        return templateResolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // templateEngine
        return templateEngine;
    }


    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"thyme/*"});
        return viewResolver;
    } 
  //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
       public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }   
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login_toLogin.do");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 

}