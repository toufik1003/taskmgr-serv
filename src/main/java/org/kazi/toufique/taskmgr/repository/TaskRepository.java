package org.kazi.toufique.taskmgr.repository;

import java.util.List;

import org.kazi.toufique.taskmgr.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

	//select t1.task_id,t1.task_name,t1.parent_task_id,t1.priority,t1.start_date,t1.end_date,t1.task_end,pt1.parent_task_name from task t1 inner join parent_task pt1 on (t1.parent_task_id=pt1.parent_task_id)
	@Query(value="select t1.task_id,t1.task_name,t1.parent_task_id,t1.priority,t1.start_date,t1.end_date,t1.task_end,pt1.parent_task_name from task t1 inner join parent_task pt1 on (t1.parent_task_id=pt1.parent_task_id)", nativeQuery=true)	
	List<Task> searchAllTasks();
}
