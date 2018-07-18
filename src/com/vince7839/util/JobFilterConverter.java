package com.vince7839.util;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.Test;

/*
 * JobAction中筛选参数的转换器
 */

public class JobFilterConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Object value, Class toType) {
		// TODO Auto-generated method stub
		System.out.println("JobFilter converter:");
		String[] arr = (String[]) value;
		String str = arr[0];
		System.out.println("JobFilter converter:" + str);
		Integer id = Integer.valueOf(str);
		if(toType == Platform.class) {
			Platform platform = new Platform();
			platform.setId(id);
			return platform;
		}else if (toType == Project.class) {
			Project project = new Project();
			project.setId(id);
			return project;
		}else if (toType == Test.class) {
			Test test = new Test();
			test.setId(id);
			return test;
		}
		return super.convertValue(value, toType);
	}
	
}
