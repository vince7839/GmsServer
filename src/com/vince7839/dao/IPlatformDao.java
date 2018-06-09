package com.vince7839.dao;

import com.vince7839.entity.Platform;

public interface IPlatformDao {
	void save(Platform p);
	void delete(Platform p);
	void update(Platform p);
	Platform get(int id);
}
