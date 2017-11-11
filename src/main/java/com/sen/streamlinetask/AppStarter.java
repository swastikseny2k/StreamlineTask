package com.sen.streamlinetask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sen.streamlinetask.interceptors.SessionInterceptor;

@SpringBootApplication
public class AppStarter extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppStarter.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
	
	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {

	  @Autowired 
	  SessionInterceptor sessionInterceptor;

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(sessionInterceptor);
	  }
	}
	
	/*@Configuration
	@EnableWebMvc
	public class MvcConfiguration extends WebMvcConfigurerAdapter{
	    @Bean
	    public ViewResolver getViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/static/");
	        resolver.setSuffix(".html");
	        return resolver;
	    }

	    @Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }    
	}*/
	
	/*@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(
	            dispatcherServlet);
	    registration.addUrlMappings("/app/*");
	    registration.addUrlMappings("/api/*");
	    return registration;
	}*/
}
