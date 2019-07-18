package org.kazi.toufique.taskmgr.exception;

public class TaskManagerException extends RuntimeException {

	public TaskManagerException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}

}
