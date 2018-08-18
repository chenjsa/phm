package com.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.pms.filter.SessionFilter;
 
@SpringBootApplication
@EnableTransactionManagement  

@EnableSwagger2
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})   
///@ImportResource(value = "classpath:dubbo-provider.xml")
@EnableCaching
@MapperScan("com.pms")
public class PmsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsWebApplication.class, args);
	} 
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();       
       // converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        return converter;
    }
    @Bean
	public SessionFilter getSessionFilter() {
		return new SessionFilter();
	}
    
	 
	  
}
