package com.vince7839.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.context.ContextLoader;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.SoftwareType;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;

/*全局的类型转换器，用于将接收的参数转换成我们自定义的pojo对象
 * 
 */

public class ParamConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Object value, Class toType) throws RuntimeException{
		// TODO Auto-generated method stub
		String[] arr = (String[]) value;
		String str = arr[0];
		System.out.println("convert string:" + str);
		if (toType == Date.class) {
			System.out.println("convert to date");
			Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
			Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
			Matcher matcher = pattern.matcher(str);
			Matcher matcher2 = pattern2.matcher(str);
			SimpleDateFormat format;
			if (matcher.matches()) {
				System.out.println("match date pattern1");
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else if(matcher2.matches()) {
				System.out.println("match date pattern2");
				 format = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				throw new RuntimeException();
			}
			try {
				Date date = format.parse(str);
				System.out.println("conver to date:" + format.format(date));
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
		} else if (toType == Status.class) {
			Status status = new StatusConverter().convertToEntityAttribute(str);
			System.out.println("convert to status:" + status);
			if (status != null && status != Status.UNKNOWN) // 从数据库查询结果允许转为unknown确保容错性，提交的参数不允许为unknown
				return status;
		} else if (toType == SoftwareType.class) {
			SoftwareType type = new SwTypeConverter().convertToEntityAttribute(str);
			System.out.println("conver to SwType:" + type);
			if (type != null && type != SoftwareType.Unknown) // 同上，Unkown属性只为处理数据库中的非法值，不允许从参数提供
				return type;
		} else if (toType == Platform.class) {
			IPlatformService platformService = (IPlatformService) ContextLoader.getCurrentWebApplicationContext()
					.getBean("platformService");
			Platform platform = platformService.get(Integer.valueOf(str));
			System.out.println("convert to platform:" + platform);
			return platform;
		} else if (toType == Project.class) {
			IProjectService projectService = (IProjectService) ContextLoader.getCurrentWebApplicationContext()
					.getBean("projectService");
			Project project = projectService.get(Integer.valueOf(str));
			System.out.println("convert to project:" + project);
			return project;
		} else if (toType == Task.class) {
			ITaskService taskService = (ITaskService) ContextLoader.getCurrentWebApplicationContext()
					.getBean("taskService");
			Task task = taskService.get(Integer.valueOf(str));
			System.out.println("convert to task:" + task);
			return task;
		}else if (toType == Test.class) {
			System.out.println("convert to test");
			ITestService testService = (ITestService) ContextLoader.getCurrentWebApplicationContext()
					.getBean("testService");
			Test test = testService.get(Integer.valueOf(str));
			System.out.println("convert to test:" + test);
			return test;
		}
		return super.convertValue(value, toType);

	}
}
