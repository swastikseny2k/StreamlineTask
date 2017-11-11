package com.sen.streamlinetask.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Before("execution(* com.sen.streamlinetask..*.*(..))")
	public void traceEnter(JoinPoint jp) {
		logger.info("Entering method " + jp.getSignature().getName() + " of class "+ jp.getSignature().getClass());
	}
	
	@After("execution(* com.sen.streamlinetask..*.*(..))")
	public void traceExit(JoinPoint jp) {
		logger.info("Exiting method " + jp.getSignature().getName() + " of class "+ jp.getSignature().getClass());
	}
	
	/*@Around("execution(* com.sen.streamlinetask..*.*(..))")
	public Object logException(ProceedingJoinPoint jp) {
		Object result = null;
		try {
			result = jp.proceed();
			
		} catch(Exception ex) {
			
			logger.error("Exiting method " + jp.getSignature().getName() + " of class "+ jp.getSignature().getClass());
		} catch (Throwable e) {

			logger.error("Exiting method " + jp.getSignature().getName() + " of class "+ jp.getSignature().getClass());
		}
		return result;
	}*/
	
	@AfterThrowing(pointcut="execution(* com.sen.streamlinetask..*.*(..))", throwing="ex" )
	public void logException(Exception ex) {

		logger.error("Error has occurred");
		logger.error(ex);
	}
}
