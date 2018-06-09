package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Task;

public interface ITaskService {
	void save(Task t);
	void delete(Task t);
	void update(Task t);
	Task get(int id);
	List<Task> all();
}
