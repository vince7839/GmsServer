package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Status;
import com.vince7839.entity.Task;

public interface ITaskDao {
	void save(Task t);
	void delete(Task t);
	void update(Task t);
	Task get(Integer id);
	List<Task> all();
	List<Task> getByStatus(Status status);
	List<Task> loadByPage(int page,int load);
}
