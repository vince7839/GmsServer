package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.ITaskDao;
import com.vince7839.entity.Status;
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
	public Task get(Integer id) {
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

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		 return id != null && get(id) != null;
	}

	@Override
	public List<Task> listOrder() {
		// TODO Auto-generated method stub
		return dao.getByStatus(Status.WAIT);
	}

	@Override
	public List<Task> listByPage(int page, int load) {
		// TODO Auto-generated method stub		
		if(page > pageCount(load))
			return null;
		else 
			return dao.listByPage(page, load);
	}

	@Override
	public int pageCount(int load) {
		// TODO Auto-generated method stub
		int size = all().size();		
		int remain = size % load;
		int completePage = size / load;
		int pageCount = completePage + (remain == 0 ? 0 : 1);
		System.out.println("all task size:" + size + " load:" + load + " page Count:" + pageCount);
		return pageCount;
	}
}
