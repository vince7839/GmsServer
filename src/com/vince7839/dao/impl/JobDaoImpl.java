package com.vince7839.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.vince7839.dao.IJobDao;
import com.vince7839.entity.Job;

public class JobDaoImpl extends HibernateDaoSupport implements IJobDao {

	@Override
	public void save(Job j) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(j);
	}

	@Override
	public void delete(Job j) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(j);
	}

	@Override
	public void update(Job j) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(j);
	}

	@Override
	public Job get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Job.class, id);
	}

	@Override
	public List<Job> find(Job j) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(j);
	}

	public void test() {
		DetachedCriteria criteria=DetachedCriteria.forClass(Job.class);
		criteria.createAlias("task", "t");
		criteria.createAlias("project", "p");
		criteria.add(Restrictions.eqProperty("p.id","t.project_id"));
		criteria.add(Restrictions.eqProperty("t.id","task_id"));
		criteria.add(Restrictions.eq("task_id",1));
		List<Job> list = (List<Job>) getHibernateTemplate().findByCriteria(criteria);
		System.out.println(list);
		
	}
}
