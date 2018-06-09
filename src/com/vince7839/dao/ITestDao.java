package com.vince7839.dao;

import com.vince7839.entity.Test;

public interface ITestDao {
	void save(Test t);
	void delete(Test t);
	void update(Test t);
	Test get(int id);
}
