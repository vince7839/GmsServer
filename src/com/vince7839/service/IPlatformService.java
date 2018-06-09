package com.vince7839.service;

import com.vince7839.entity.Platform;

public interface IPlatformService {
	void save(Platform p);
	void delete(Platform p);
	void update(Platform p);
	Platform get(int id);
}
