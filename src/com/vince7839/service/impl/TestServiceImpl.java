package com.vince7839.service.impl;

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
	public Test get(int id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	public ITestDao getDao() {
		return dao;
	}

	public void setDao(ITestDao dao) {
		this.dao = dao;
	}

}
