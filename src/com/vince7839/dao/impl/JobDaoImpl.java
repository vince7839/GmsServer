package com.vince7839.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.vince7839.dao.IJobDao;
import com.vince7839.entity.Job;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.util.JobFilter;

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
		System.out.println("job dao find:" + j);
		return getHibernateTemplate().findByExample(j);
	}

	public void test() {
		/*
		 * DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);
		 * criteria.createAlias("task", "task"); criteria.createAlias("task.project",
		 * "project"); criteria.createAlias("project.platform", "platform");
		 * criteria.add(Restrictions.eq("platform.id", 2)); List<Job> list = (List<Job>)
		 * getHibernateTemplate().findByCriteria(criteria);
		 */
		// JobFilter filter = new JobFilter(platform, project, test)
		// List<Job> list = list
		// for (Job j : list)
		// System.out.println(j);
	}

	@Override
	public List<Job> list(JobFilter filter) {
		// TODO Auto-generated method stub
		List<Job> list = (List<Job>) getHibernateTemplate().findByCriteria(buildCriteria(filter));
		return list;
	}

	@Override
	public List<Job> load(JobFilter filter, int page, int load) {
		// TODO Auto-generated method stub
		HibernateTemplate template = getHibernateTemplate();
		List<Job> list = (List<Job>) template.findByCriteria(buildCriteria(filter), (page-1) * load, load);
		System.out.println("load size:" + list.size());
		return list;
	}

	private DetachedCriteria buildCriteria(JobFilter filter) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);
	//	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("task", "task");
		criteria.createAlias("task.project", "project");
		if (filter.getTest() != null) {
			criteria.add(Restrictions.eq("test.id", filter.getTest().getId()));
		}
		if (filter.getProject() != null) {
			criteria.add(Restrictions.eq("project.id", filter.getProject().getId()));
		} else if (filter.getPlatform() != null) {
			criteria.add(Restrictions.eq("project.platform.id", filter.getPlatform().getId()));
		}
		return criteria;
	}

	@Override
	public List<Job> findByTask(Task task) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("task.id", task.getId()));
		List<Job> list = (List<Job>) getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@Override
	public List<Job> find(Task task, Test test) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("task", task));
		criteria.add(Restrictions.eq("test", test));
		List<Job> list = (List<Job>)getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
