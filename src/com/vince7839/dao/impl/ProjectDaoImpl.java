package com.vince7839.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.vince7839.dao.IProjectDao;
import com.vince7839.entity.Project;

public class ProjectDaoImpl extends HibernateDaoSupport implements IProjectDao {

	@Override
	public void save(Project p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(p);
	}

	@Override
	public void delete(Project p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(p);
	}

	@Override
	public void update(Project p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(p);
	}

	@Override
	public Project get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Project.class, id);
	}

	@Override
	public List<Project> all() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Project.class);
	}

	@Override
	public List<Project> getByName(String name) {
		// TODO Auto-generated method stub
		Project p = new Project();
		p.setName(name);
		return getHibernateTemplate().findByExample(p);
	}

	@Override
	public List<Project> listProjectByPlatform(Integer platformId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Project> list(Project p) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(p);
	}

}
