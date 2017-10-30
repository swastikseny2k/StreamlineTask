package com.sen.streamlinetask.services;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.sen.streamlinetask.beans.AppConstants;
import com.sen.streamlinetask.beans.GenericResponse;
import com.sen.streamlinetask.entities.Tasks;
import com.sen.streamlinetask.repositories.TaskRepository;
import com.sen.streamlinetask.utils.AppUtil;

@Service
public class TaskService {

	private TaskRepository taskRepository;

	public GenericResponse addTask(Tasks task, HttpSession session) {
		GenericResponse response = new GenericResponse();

		if (task.getTaskDesc() != null && task.getScheduleDate() != null) {
			task.setCreatedDate(AppUtil.getCurrentTime());
			task.setLastUpdatedDate(AppUtil.getCurrentTime());
			task.setCreatedBy((String)session.getAttribute("USERNAME"));
			task.setLastUpdatedBy((String)session.getAttribute("USERNAME"));

			taskRepository.save(task);

			response.setResponseCode(AppConstants.SUCCESS_CODE);
			response.setResponse(AppConstants.SUCCESS);
			response.setResponseData(task);
		} else {

			response.setResponseCode(AppConstants.FAIL_CODE);
			response.setResponse(AppConstants.FAILURE);
			response.setResponseData(task);
		}

		return response;
	}
}
