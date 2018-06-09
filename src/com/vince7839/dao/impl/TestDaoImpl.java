package com.vince7839.dao.impl;

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
	public Test get(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Test.class,id);
	}

}
