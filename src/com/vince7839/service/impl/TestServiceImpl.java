package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.ITestDao;
import com.vince7839.entity.Test;
import com.vince7839.service.ITestService;

public class TestServiceImpl implements ITestService {
	ITestDao dao;
	@Override
	public void save(Test t) {
		// TODO Auto-generated method stub
		dao.save(t);
	}

	@Override
	public void delete(Test t) {
		// TODO Auto-generated method stub
		dao.delete(t);
	}

	@Override
	public void update(Test t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	public Test get(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	public ITestDao getDao() {
		return dao;
	}

	public void setDao(ITestDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Test> all() {
		// TODO Auto-generated method stub
		return dao.all();
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return get(id) != null;
	}

	@Override
	public boolean isNameExists(String name) {
		// TODO Auto-generated method stub
		return !dao.getByName(name).isEmpty();
	}

	@Override
	public List<Test> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

}
