package com.sen.streamlinetask.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sen.streamlinetask.beans.GenericResponse;
import com.sen.streamlinetask.entities.Tasks;
import com.sen.streamlinetask.services.TaskService;

@RestController
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/addtask", method=RequestMethod.POST)
	public GenericResponse addTask(@RequestBody Tasks task, HttpSession session) {
		return taskService.addTask(task, session);
	}
}
