package com.vince7839.action;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Test;
import com.vince7839.service.ITestService;

public class TestAction extends BaseAction implements ModelDriven<Test>{
	ITestService testService;
	Test test = new Test();
	
	public String save() {
		String name = test.getName();
		if(name == null || name.length() < 3) {
			buildJson(false,NAME_TOO_SHORT,null);
			return FINISH;
		}
		if(testService.isNameExists(name)) {
			buildJson(false,NAME_EXIST,null);
			return FINISH;
		}
		testService.save(test);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String delete() {
		if(testService.exists(test.getId())) {
			testService.delete(test);
			buildJson(true,NO_ERROR,null);
		} else {
			result.put(FAIL, "no such test");
		}
		return FINISH;
	}
	
	public String update() {
		if(!testService.exists(test.getId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		String name = test.getName();
		if(name != null && name.length() > 2) {
			buildJson(false,NAME_TOO_SHORT,null);	
			return FINISH;
		}
		testService.update(test);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String get() {
		test = testService.get(test.getId());
		return GET;
	}
	
	public String all() {
		buildJson(true,NO_ERROR,testService.all());
		return FINISH;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public Test getModel() {
		// TODO Auto-generated method stub
		return test;
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
}
