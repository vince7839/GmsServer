package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.ITaskDao;
import com.vince7839.entity.Task;
import com.vince7839.service.ITaskService;

public class TaskServiceImpl implements ITaskService {
	ITaskDao dao;
	@Override
	public void save(Task t) {
		// TODO Auto-generated method stub
		dao.save(t);
	}

	@Override
	public void delete(Task t) {
		// TODO Auto-generated method stub
		dao.delete(t);
	}

	@Override
	public void update(Task t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	public Task get(int id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	public ITaskDao getDao() {
		return dao;
	}

	public void setDao(ITaskDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Task> all() {
		// TODO Auto-generated method stub
		return dao.all();
	}
}
