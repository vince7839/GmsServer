package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.IJobDao;
import com.vince7839.entity.Job;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.exception.MultiResultException;
import com.vince7839.service.IJobService;
import com.vince7839.util.JobFilter;

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
	public List<Job> findByTask(Task task) {
		// TODO Auto-generated method stub
		return dao.findByTask(task);
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

	@Override
	public Job find(Task task,Test test) throws MultiResultException{
		// TODO Auto-generated method stub
		List<Job> result = dao.find(task,test);
		if (result != null ) {
			if(result.size() == 1) {
				return result.get(0);
			} else if(result.size() > 1){
				System.out.println("job find:"+result.size());
				throw new MultiResultException();
			}
		}
		return null;
	}

	@Override
	public int pageCount(int load,JobFilter filter) {
		// TODO Auto-generated method stub
		int size = list(filter).size();
		int remain = size % load;
		int completePage = size / load;
		int pageCount = completePage + (remain == 0 ? 0 : 1);
		return pageCount;
	}

	@Override
	public List<Job> list(JobFilter filter) {
		// TODO Auto-generated method stub
		return dao.list(filter);
	}

	@Override
	public List<Job> load(JobFilter filter, int page, int load) {
		// TODO Auto-generated method stub
		return dao.load(filter, page, load);
	}
}
