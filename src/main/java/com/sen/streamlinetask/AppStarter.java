package com.sen.streamlinetask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sen.streamlinetask.filters.AuthInterceptor;

@SpringBootApplication
public class AppStarter {

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
	
/*	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {

	  @Autowired 
	  AuthInterceptor sessionInterceptor;

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(sessionInterceptor);
	  }
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(
	            dispatcherServlet);
	    registration.addUrlMappings("/*");
	    return registration;
	}*/
}
