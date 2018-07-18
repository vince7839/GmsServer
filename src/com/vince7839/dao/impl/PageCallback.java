package com.vince7839.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import com.vince7839.entity.Task;

public class PageCallback implements HibernateCallback<List> {
	private String hql;
	private int page;
	private int load;

	public static PageCallback build(String hql, int page, int load) {
		PageCallback instance = new PageCallback();
		instance.hql = hql;
		instance.page = page;
		instance.load = load;
		return instance;
	}

	private PageCallback() {

	}

	@Override
	public List doInHibernate(Session session) throws HibernateException {
		// TODO Auto-generated method stub
		List<Task> list = session.createQuery(hql).setFirstResult(page * load).setMaxResults(load).list();
		return list;
	}

}
