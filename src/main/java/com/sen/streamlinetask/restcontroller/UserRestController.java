package com.sen.streamlinetask.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sen.streamlinetask.beans.AppConstants;
import com.sen.streamlinetask.beans.GenericResponse;
import com.sen.streamlinetask.entities.Users;
import com.sen.streamlinetask.services.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/loginuser", method=RequestMethod.POST)
	public GenericResponse loginUser(@RequestBody Users user, HttpServletRequest request) {
		
		return userService.findByUserNameAndPassword(user, request);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public GenericResponse registerUser(@RequestBody Users user) {
		
		return userService.registerUser(user);
	}
	
	@RequestMapping(value="/getusername", method=RequestMethod.GET)
	public GenericResponse getUserName(HttpSession session) {
		
		GenericResponse response = new GenericResponse();
		
		if(session == null
				|| session.getAttribute("NAME") == null
				|| session.getAttribute("NAME").toString().equals("")) {
			
			response.setResponseCode(AppConstants.FAIL_CODE);
			response.setResponse(AppConstants.FAILURE);
		} else {
			response.setResponseCode(AppConstants.SUCCESS_CODE);
			response.setResponse(AppConstants.SUCCESS);
			response.setResponseData(session.getAttribute("NAME").toString());
		}
		
		return response;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public GenericResponse logout(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		GenericResponse response = new GenericResponse();
		response.setResponseCode(AppConstants.SUCCESS_CODE);
		response.setResponse(AppConstants.SUCCESS);
		
		return response;
	}
}
