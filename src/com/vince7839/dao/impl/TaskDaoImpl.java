package com.vince7839.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.vince7839.dao.ITaskDao;
import com.vince7839.entity.Status;
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
	public List<Task> getByStatus(Status status) {
		// TODO Auto-generated method stub
		Task t = new Task();
		t.setStatus(status);
		return getHibernateTemplate().findByExample(t);
	}

	@Override
	public List<Task> listByPage(int page, int load) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Task.class);
		criteria.add(Restrictions.ne("status", Status.WAIT));
		List<Task> list = (List<Task>)getHibernateTemplate().findByCriteria(criteria,(page - 1)*load,load);
		System.out.println("listByPage size:" + list.size() + " load:" + load + " page:" + page);
		return list;
	}

}
