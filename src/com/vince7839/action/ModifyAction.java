package com.vince7839.action;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Task;
import com.vince7839.service.ITestService;

public class ModifyAction extends BaseAction {
	ITestService testService;
	Task task;

	public String modify() {
		String role = (String)ActionContext.getContext().getSession().get("role");
		if(!"admin".equals(role)) {
			addActionMessage("您未登录或者没有权限。");
			return ERROR;
		}
		if(task == null) {
			addActionMessage("要修改的任务不存在。");
			return ERROR;
		}
		ActionContext.getContext().put("jobs", task.getJobs());
		ActionContext.getContext().put("task", task);
		ActionContext.getContext().put("tests",testService.list());
		return FINISH;
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public ITestService getTestService() {
		return testService;
	}
	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
}
