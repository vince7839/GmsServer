package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Job;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.util.JobFilter;

public interface IJobDao {
	void save(Job j);
	void delete(Job j);
	void update(Job j);
	Job get(Integer id);
	List<Job> find(Job j);
	void test();
	List<Job> list(JobFilter filter);
	List<Job> load(JobFilter filter,int page,int load);
	List<Job> findByTask(Task task);
	List<Job> find(Task task,Test test);
}
