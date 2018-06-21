package com.vince7839.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.vince7839.dao.IPlatformDao;
import com.vince7839.entity.Platform;

public class PlatformDaoImpl extends HibernateDaoSupport implements IPlatformDao {

	@Override
	public void save(Platform p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(p);
	}

	@Override
	public void delete(Platform p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(p);
	}

	@Override
	public void update(Platform p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(p);
	}

	@Override
	public Platform get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Platform.class,id);
	}

	@Override
	public List<Platform> all() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().loadAll(Platform.class);
	}

	@Override
	public List<Platform> getByName(String name) {
		// TODO Auto-generated method stub
		Platform p = new Platform();
		p.setName(name);
		return getHibernateTemplate().findByExample(p);
	}

}
