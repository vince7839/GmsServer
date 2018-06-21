package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Platform;

public interface IPlatformService {
	void save(Platform p);
	void delete(Platform p);
	void update(Platform p);
	Platform get(Integer id);
	List<Platform> all();
	boolean exists(Integer id);
	boolean isNameExists(String name);
}
