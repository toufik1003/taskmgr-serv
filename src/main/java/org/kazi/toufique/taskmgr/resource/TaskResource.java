package org.kazi.toufique.taskmgr.resource;

import org.kazi.toufique.taskmgr.model.ParentTask;
import org.kazi.toufique.taskmgr.model.Task;
import org.kazi.toufique.taskmgr.service.TaskManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TaskResource {

	private static final Logger LOGGER = LoggerFactory.getLogger("TaskResource");

	private TaskManagerService taskManagerService;

	@Autowired
	public void setTaskManagerService(TaskManagerService taskManagerService) {
		this.taskManagerService = taskManagerService;
	}

	@GetMapping(value = "/getAllParentTask")
	public List<ParentTask> getAllParentTask() {
		return taskManagerService.getAllParentTask();
	}

	@PostMapping(value = "/saveTask")
	public Task saveTask(@RequestBody Task task) {
		LOGGER.info("Task requested : {}", task);
		Task savedTask = taskManagerService.saveTask(task);
		return savedTask;
	}
	
	@GetMapping(value = "/getTaskById/{taskId}")
	public Task getTaskById(@PathVariable String taskId) {
		Task task = taskManagerService.getTaskById(Long.parseLong(taskId));
		return task;
	}

	@GetMapping(value = "/endTask/{taskId}")
	public void endTask(@PathVariable String taskId) {
		Task task = taskManagerService.getTaskById(Long.parseLong(taskId));
		task.setTaskEnded(true);
		taskManagerService.saveTask(task);
	}

	@PostMapping(value = "/searchTask")
	public List<Task> searchTask(@RequestBody Task task) {
		return taskManagerService.searchTaskBasedOnCriteria(task);
	}
	
	/*
	@RequestMapping(method = { RequestMethod.OPTIONS })
	public void searchTaskOptions() {
		//return taskManagerService.searchTaskBasedOnCriteria(task);
		LOGGER.info("pre-flight request called");
	}*/
}
