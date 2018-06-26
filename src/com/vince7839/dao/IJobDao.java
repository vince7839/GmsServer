package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Job;

public interface IJobDao {
	void save(Job j);
	void delete(Job j);
	void update(Job j);
	Job get(Integer id);
	List<Job> find(Job j);
	void test();
}
