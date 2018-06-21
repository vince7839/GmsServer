package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Test;

public interface ITestService {
	void save(Test t);
	void delete(Test t);
	void update(Test t);
	Test get(Integer id);
	List<Test> all();
	boolean exists(Integer id);
	boolean isNameExists(String name);
}
