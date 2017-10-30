package com.sen.streamlinetask.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sen.streamlinetask.beans.AppConstants;
import com.sen.streamlinetask.beans.GenericResponse;
import com.sen.streamlinetask.entities.Users;
import com.sen.streamlinetask.repositories.UserRepository;
import com.sen.streamlinetask.utils.AppUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public GenericResponse findByUserNameAndPassword(Users loginUser, HttpServletRequest request) {
		
		List<Users> users = userRepository.findByUserNameAndPassword(loginUser.getUserName(), loginUser.getPassword());
		
		GenericResponse response = new GenericResponse();
		
		if(null != users
				&& users.size() > 0) {
			response.setResponseCode(AppConstants.SUCCESS_CODE);
			response.setResponse(AppConstants.SUCCESS);
			response.setResponseData(users.get(0));
			
			HttpSession session = request.getSession(true);
			
			session.setAttribute("NAME", users.get(0).getFirstName() + " " + users.get(0).getLastName());
			session.setAttribute("USERNAME", users.get(0).getUserName());
			session.setAttribute("USERID", users.get(0).getUserID());
			
		} else {
			response.setResponseCode(AppConstants.FAIL_CODE);
			response.setResponse(AppConstants.FAILURE);
			response.setResponseData(loginUser);
		}
		
		return response;
	}

	public GenericResponse registerUser(Users user) {
		
		GenericResponse response = new GenericResponse();
		
		if(user.getUserName() != null && user.getPassword() != null) {
			user.setCreatedDate(AppUtil.getCurrentTime());
			user.setLastUpdatedDate(AppUtil.getCurrentTime());
			user.setCreatedBy(user.getUserName());
			user.setLastUpdatedBy(user.getUserName());
			
			userRepository.save(user);
			
			response.setResponseCode(AppConstants.SUCCESS_CODE);
			response.setResponse(AppConstants.SUCCESS);
			response.setResponseData(user);
		} else {
			
			response.setResponseCode(AppConstants.FAIL_CODE);
			response.setResponse(AppConstants.FAILURE);
			response.setResponseData(user);
		}
		
		return response;
	}
}
