package org.kazi.toufique.taskmgr.service;

import org.kazi.toufique.taskmgr.exception.TaskManagerException;
import org.kazi.toufique.taskmgr.model.ParentTask;
import org.kazi.toufique.taskmgr.model.Task;

import java.util.List;

public interface TaskManagerService {

	List<ParentTask> getAllParentTask() throws TaskManagerException;

	ParentTask getParentTaskById(long parentTaskId) throws TaskManagerException;

	Task saveTask(Task task) throws TaskManagerException;

	Task getTaskById(long taskId) throws TaskManagerException;

	// void removeTask(long taskId) throws TaskManagerException;
	boolean isTaskFinished(Task task) throws TaskManagerException;

	List<Task> searchTaskBasedOnCriteria(Task task) throws TaskManagerException;

}
