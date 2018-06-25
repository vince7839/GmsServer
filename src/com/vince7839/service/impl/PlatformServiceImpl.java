package com.vince7839.service.impl;

import java.util.List;

import com.vince7839.dao.IPlatformDao;
import com.vince7839.entity.Platform;
import com.vince7839.service.IPlatformService;

public class PlatformServiceImpl implements IPlatformService {
	IPlatformDao dao;
	@Override
	public void save(Platform p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public void delete(Platform p) {
		// TODO Auto-generated method stub
		dao.delete(p);
	}

	@Override
	public void update(Platform p) {
		// TODO Auto-generated method stub
		dao.update(p);
	}

	@Override
	public Platform get(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	public IPlatformDao getDao() {
		return dao;
	}

	public void setDao(IPlatformDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Platform> all() {
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

}
