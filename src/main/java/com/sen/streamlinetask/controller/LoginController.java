package com.sen.streamlinetask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sen.streamlinetask.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/showhomepage", method=RequestMethod.GET)
	public String openHomePage() {
		return "homepage";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String openLoginPage() {
		return "login";
	}
}
