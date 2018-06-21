package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Test;

public interface ITestDao {
	void save(Test t);
	void delete(Test t);
	void update(Test t);
	Test get(Integer id);
	List<Test> all();
	List<Test> getByName(String name);
}
