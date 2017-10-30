package com.sen.streamlinetask.restcontroller;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public GenericResponse logout(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		GenericResponse response = new GenericResponse();
		response.setResponseCode(AppConstants.SUCCESS_CODE);
		response.setResponse(AppConstants.SUCCESS);
		
		return response;
	}
}
