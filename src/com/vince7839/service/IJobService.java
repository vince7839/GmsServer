package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Job;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.exception.MultiResultException;
import com.vince7839.util.JobFilter;

public interface IJobService {
	void save(Job j);
	void delete(Job j);
	void update(Job j);
	Job get(Integer id);
	List<Job> find(Job j);
	List<Job> findByTask(Task task);
	Job find(Task task,Test test) throws MultiResultException;
	int pageCount(int load,JobFilter filter);
	void test();
	List<Job> list(JobFilter filter);
	List<Job> load(JobFilter filter,int page,int load);
}
