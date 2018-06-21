package com.vince7839.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.vince7839.dao.ITaskDao;
import com.vince7839.entity.Task;

public class TaskDaoImpl extends HibernateDaoSupport implements ITaskDao {

	@Override
	public void save(Task t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(Task t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(Task t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	@Override
	public Task get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Task.class, id);
	}

	@Override
	public List<Task> all() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Task.class);
	}

	@Override
	public List<Task> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		Task t = new Task();
		t.setStatus(status);
		return getHibernateTemplate().findByExample(t);
	}

}
