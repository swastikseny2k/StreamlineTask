package com.sen.streamlinetask.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sen.streamlinetask.beans.GenericResponse;
import com.sen.streamlinetask.entities.Tasks;
import com.sen.streamlinetask.services.TaskService;

@RestController
public class TaskRestController {

	private TaskService taskService;
	
	public GenericResponse addTask(@RequestBody Tasks task, HttpSession session) {
		return taskService.addTask(task, session);
	}
}
