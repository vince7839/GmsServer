package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.IProjectDao;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.service.IProjectService;

public class ProjectServiceImpl implements IProjectService{
	IProjectDao dao;

	@Override
	public void delete(Project p) {
		// TODO Auto-generated method stub
		dao.delete(p);
	}

	@Override
	public void update(Project p) {
		// TODO Auto-generated method stub
		dao.update(p);
	}

	@Override
	public Project get(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public void save(Project p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	public IProjectDao getDao() {
		return dao;
	}

	public void setDao(IProjectDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Project> all() {
		// TODO Auto-generated method stub
		return dao.all();
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return id != null && get(id) != null;
	}

	@Override
	public boolean isNameExists(String name) {
		// TODO Auto-generated method stub
		return !dao.getByName(name).isEmpty();
	}

	@Override
	public List<Project> list(Project p) {
		// TODO Auto-generated method stub
		return dao.list(p);
	}

	@Override
	public List<Project> listByPlatform(Platform p) {
		// TODO Auto-generated method stub
		return null;
	}

}
