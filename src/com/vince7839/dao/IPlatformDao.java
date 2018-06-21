package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Platform;

public interface IPlatformDao {
	void save(Platform p);
	void delete(Platform p);
	void update(Platform p);
	Platform get(Integer id);
	List<Platform> all();
	List<Platform> getByName(String name);
}
