package com.vince7839.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Project;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.service.IJobService;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;
import com.vince7839.util.JobBuilder;

public class TaskAction extends BaseAction implements ModelDriven<Task>{
	ITaskService taskService;
	IProjectService projectService;
	ITestService testService;
	IJobService jobService;
	Task task;
	public String save() {
		System.out.println("save"+task);
		if(!projectService.exists(task.getProjectId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}		
		taskService.save(task);
		jobService.save(JobBuilder.build(task, JobBuilder.CTS_ID));
		jobService.save(JobBuilder.build(task, JobBuilder.GTS_ID));
		jobService.save(JobBuilder.build(task, JobBuilder.VTS_ID));
		jobService.save(JobBuilder.build(task, JobBuilder.GSI_ID));
		jobService.save(JobBuilder.build(task, JobBuilder.CTSV_ID));
		jobService.save(JobBuilder.build(task, JobBuilder.PERFORMANCE_ID));		
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String delete() {
		if(taskService.exists(task.getId())) {
			taskService.delete(task);
			buildJson(true,NO_ERROR,null);
		} else {
			result.put(FAIL, "no such test");
		}		
		return FINISH;
	}
	
	public String update() {
		System.out.println(task);
		if(!taskService.exists(task.getId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		
		Task t = taskService.get(task.getId());
		Integer pId = task.getProjectId();
		if(pId != null) t.setProjectId(pId);
		Integer status = task.getStatus();
		if(status != null) t.setStatus(status);
		Integer swType = task.getSoftwareType();
		if(swType != null) t.setSoftwareType(swType);
		String summary = task.getSummary();
		if(summary != null) t.setSummary(summary);
		
		taskService.update(task);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String get() {
		System.out.println(" get:"+task.getId());
		task = taskService.get(task.getId());
		if(task != null) {
			List list = new ArrayList<Object>();			
			list.add(buildTaskMap(task));
			buildJson(true,NO_ERROR,list);
		}
		return FINISH;
	}
	
	public String all() {
		List<Task> resultList = taskService.all();
		buildJson(true,NO_ERROR,buildTaskList(resultList));
		return FINISH;
	}
	
	public String order() {
		
		return ALL;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
	
	private Map<String,Object> buildTaskMap(Task t){
		Map<String,Object> map = new HashMap<String,Object>();
		if(t != null) {
			Integer id =  t.getId();
			map.put("id",id);
			Integer pId = t.getProjectId();
			Project p = null;
			if(pId != null) p = projectService.get(pId);
			map.put("project", p != null ? p.getName() : null);
			List<Job> jobs = jobService.findByTask(t.getId());
			map.put("items",jobs);
			map.put("status", t.getStatus());
			map.put("summary", t.getSummary());
			Date date = t.getStartDate();
			map.put("startDate", date != null ? date.toString():null);
			date = t.getEndDate();
			map.put("endDate", date != null ? date.toString():null);
		}
		return map;
	}
	
	private List buildTaskList(List<Task> tasks) {
		List list = new ArrayList<Map<String,String>>(); 
		for(Task t:tasks) {
			Map<String,Object> map = buildTaskMap(t);
			list.add(map);
		}
		return list;
	}

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		task = new Task(); 
		return task;
	}

	public IJobService getJobService() {
		return jobService;
	}

	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
}
