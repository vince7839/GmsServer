package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Task;

public interface ITaskService {
	void save(Task t);
	void delete(Task t);
	void update(Task t);
	Task get(Integer id);
	List<Task> all();
	boolean exists(Integer id);
	List<Task> listOrder();
	List<Task> loadByPage(int page,int load);
	int pageCount(int load);
}
