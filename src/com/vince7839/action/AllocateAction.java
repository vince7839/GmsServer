package com.vince7839.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.service.IJobService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;

public class AllocateAction extends BaseAction implements ModelDriven<Task>{
	ITaskService taskService;
	ITestService testService;
	IJobService jobService;
	Task task;
	List<Integer> suite;
	String tester;
	public String allocate() {
		String role = (String)ActionContext.getContext().getSession().get("role");
		if(!"admin".equals(role)) {
			addActionMessage("您未登录或者没有权限。");
			return ERROR;
		}
		System.out.println(task);
		if(task != null) {
			ActionContext.getContext().put("tests", testService.all());
		}
		return FINISH;
	}
	
	public String doAllocate() {
		System.out.println("doAllocate:" + task);
		if(task == null) {
			addActionMessage("要分配的任务不存在。");
			return ERROR;
		}
		if(suite == null||suite.isEmpty()) {
			addActionMessage("至少需要为此任务分配一种测试选项。");
			return ERROR;	
		}
		Task origin = taskService.get(task.getId());
		origin.setBugId(task.getBugId());
		origin.setStartDate(task.getStartDate());
		origin.setEndDate(task.getEndDate());
		origin.setSummary(task.getSummary());
		origin.setStatus(Status.TEST);
		origin.setLeader(task.getLeader());
		taskService.update(origin);
		if(suite !=null && !suite.isEmpty()) {
			for(Integer testId : suite) {
				Test test = testService.get(testId);
				Job job = new Job();
				job.setTask(task);
				job.setTest(test);
				job.setTester(tester);
				job.setStatus(Status.WAIT);
				jobService.save(job);
			}
		}
		return FINISH;
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		task = new Task();
		return task;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}

	public List<Integer> getSuite() {
		return suite;
	}

	public void setSuite(List<Integer> suite) {
		this.suite = suite;
		System.out.println("suite:" + suite);
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public IJobService getJobService() {
		return jobService;
	}

	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
}
