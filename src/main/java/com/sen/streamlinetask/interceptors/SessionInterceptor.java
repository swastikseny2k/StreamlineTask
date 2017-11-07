package com.sen.streamlinetask.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final List<String> excludedURI = new ArrayList<String>();
	static {
		excludedURI.add("/loginuser");
		excludedURI.add("/register");
		//excludedURI.add("/error");
		excludedURI.add("/logout");
		excludedURI.add("/");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		boolean proceedProcessing = true;
		
		if(!excludedURI.contains(request.getRequestURI())) {
			
			HttpSession session = request.getSession();
			
			if(null == session
					|| (null != session
							&& (null == session.getAttribute("USERNAME")
								|| "".equalsIgnoreCase(session.getAttribute("USERNAME").toString())))) {
				
				proceedProcessing = false;
				response.setStatus(403);
				//response.sendRedirect("index.html");
				
			} else {
			
				/*int userRole = (Integer)session.getAttribute("role");
				
				request.setAttribute("role", userRole);
				
				if(userRole == Constants.REGULAR_ROLE
						&& adminAccessOnlyURI.contains(request.getRequestURI())){
					
					response.sendRedirect("/CricScorez/games/noaccesspage");
				}*/
				//proceedProcessing = false;
				//response.sendRedirect("index.html");
			}
			
		}
		
		return proceedProcessing;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {

	}
}