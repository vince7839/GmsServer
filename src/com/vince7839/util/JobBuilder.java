package com.vince7839.util;

import org.springframework.web.context.ContextLoader;

import com.vince7839.entity.Job;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.exception.BuildTestException;
import com.vince7839.factory.ITestFactory;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.ITestService;

public class JobBuilder {

	public static Job build(Task task, ITestFactory testFactory) throws BuildTestException {
		Job job = new Job();
		
		//断言 根据test的id从数据库查询出来的test，两者的name应该相同，否则抛出异常
		Test test = testFactory.getTest();
		System.out.println(test);
		if (test.getId() == null || test.getName() == null) {
			throw new BuildTestException();
		}
		ITestService testService = (ITestService) ContextLoader.getCurrentWebApplicationContext()
				.getBean("testService");
		Test entity = testService.get(test.getId());
		if (entity.getName() == null) {
			throw new BuildTestException();
		}
		if(!test.getName().equalsIgnoreCase(entity.getName())) {
			throw new BuildTestException();
		}
		job.setTask(task);
		job.setTest(test);
		return job;
	}
}
