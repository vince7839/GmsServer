package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Task;

public interface ITaskService {
	final static int WAITING = 0;
	final static int TESTING = 1;
	final static int RETESTING = 2;
	final static int FINISHED = 3;
	final static int REJECTED = 4;
	void save(Task t);
	void delete(Task t);
	void update(Task t);
	Task get(Integer id);
	List<Task> all();
	boolean exists(Integer id);
	List<Task> listOrder();
}
