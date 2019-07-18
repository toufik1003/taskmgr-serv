package org.kazi.toufique.taskmgr.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parent_task")
public class ParentTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_task_id")
	private long parentTaskId;

	@Column(name = "parent_task_name")
	private String parentTaskName;

	public ParentTask() {
	}

	public long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParentTask that = (ParentTask) o;
		return parentTaskId == that.parentTaskId && parentTaskName.equals(that.parentTaskName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parentTaskId, parentTaskName);
	}

	@Override
	public String toString() {
		return "ParentTask{" + "parentTaskId=" + parentTaskId + ", parentTaskName='" + parentTaskName + '\'' + '}';
	}
}
