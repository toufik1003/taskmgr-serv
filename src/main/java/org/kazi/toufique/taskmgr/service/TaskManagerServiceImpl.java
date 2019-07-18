package org.kazi.toufique.taskmgr.service;

import org.kazi.toufique.taskmgr.exception.TaskManagerException;
import org.kazi.toufique.taskmgr.model.ParentTask;
import org.kazi.toufique.taskmgr.model.Task;
import org.kazi.toufique.taskmgr.repository.ParentTaskRepository;
import org.kazi.toufique.taskmgr.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("TaskManagerServiceImpl");

	private ParentTaskRepository parentTaskRepository;
	private TaskRepository taskRepository;

	@Autowired
	public void setParentTaskRepository(ParentTaskRepository parentTaskRepository) {
		this.parentTaskRepository = parentTaskRepository;
	}

	@Autowired
	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<ParentTask> getAllParentTask() throws TaskManagerException {
		try {
			return (List<ParentTask>) parentTaskRepository.findAll();
		} catch (Exception e) {
			throw new TaskManagerException("exception while retrieving parent task list : ", e);
		}
	}

	@Override
	public ParentTask getParentTaskById(long parentTaskId) throws TaskManagerException {
		try {
			return parentTaskRepository.findById(parentTaskId).get();
		} catch (Exception e) {
			throw new TaskManagerException("exception while retrieving parent task for id {" + parentTaskId + "} : ", e);
		}
	}

	@Override
	public Task saveTask(Task task) throws TaskManagerException {
		try {
			Long pid = task.getParentTaskId();
			if (pid == null || pid ==0) {
				task.setParentTask(null);
			}else {
				ParentTask pTask = new ParentTask();
				pTask.setParentTaskId(pid);
				task.setParentTask(pTask);
			}
			return taskRepository.save(task);
		} catch (Exception e) {
			throw new TaskManagerException("exception while saving a task : ", e);
		}
	}

	@Override
	public Task getTaskById(long taskId) throws TaskManagerException {
		try {
			Task task = taskRepository.findById(taskId).get();
			LOGGER.info("task found: [{}] for id : [{}]", task, taskId);
			return task;
		} catch (Exception e) {
			throw new TaskManagerException("exception while retrieving task for id {" + taskId + "} : ", e);
		}
	}

	/*
	 * @Override public void removeTask(long taskId) throws TaskManagerException {
	 * try { taskRepository.deleteById(taskId); } catch (Exception e) { throw new
	 * TaskManagerException("exception while removing a task : ", e); } }
	 */

	@Override
	public boolean isTaskFinished(Task task) throws TaskManagerException {
		return false;
	}

	@Override
	public List<Task> searchTaskBasedOnCriteria(Task task) throws TaskManagerException {
		List<Task> tasks = (List<Task>) taskRepository.findAll();
		LOGGER.info("all tasklist : {}", tasks);
		return tasks;
	}
}
