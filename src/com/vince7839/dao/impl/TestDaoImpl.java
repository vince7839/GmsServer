package com.vince7839.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.vince7839.dao.ITestDao;
import com.vince7839.entity.Test;

public class TestDaoImpl extends HibernateDaoSupport implements ITestDao {

	@Override
	public void save(Test t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(Test t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(Test t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	@Override
	public Test get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Test.class,id);
	}

	@Override
	public List<Test> all() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Test.class);
	}

	@Override
	public List<Test> getByName(String name) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.setName(name);
		return getHibernateTemplate().findByExample(t);
	}

	@Override
	public List<Test> list() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Test.class);
	}

}
