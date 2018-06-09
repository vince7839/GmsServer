package com.vince7839.service;

import com.vince7839.entity.Test;

public interface ITestService {
	void save(Test t);
	void delete(Test t);
	void update(Test t);
	Test get(int id);
}
