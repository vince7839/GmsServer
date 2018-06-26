package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.IJobDao;
import com.vince7839.entity.Job;
import com.vince7839.service.IJobService;

public class JobServiceImpl implements IJobService {
	IJobDao dao;
	@Override
	public void save(Job j) {
		// TODO Auto-generated method stub
		dao.save(j);
	}

	@Override
	public void delete(Job j) {
		// TODO Auto-generated method stub
		dao.delete(j);
	}

	@Override
	public void update(Job j) {
		// TODO Auto-generated method stub
		dao.update(j);
	}

	@Override
	public Job get(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<Job> find(Job j) {
		// TODO Auto-generated method stub
		return dao.find(j);
	}

	@Override
	public List<Job> findByTask(Integer taskId) {
		// TODO Auto-generated method stub
		Job job = new Job();
		job.setTaskId(taskId);
		return dao.find(job);
	}

	public IJobDao getDao() {
		return dao;
	}

	public void setDao(IJobDao dao) {
		this.dao = dao;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		dao.test();
	}

}
