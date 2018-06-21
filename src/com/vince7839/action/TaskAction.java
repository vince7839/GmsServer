package com.vince7839.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Project;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.service.IJobService;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;

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
		jobService.save(j);
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
		if(!taskService.exists(task.getTestId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		Task t = taskService.get(task.getId());
		Integer pId = task.getProjectId();
		if(pId != null) t.setProjectId(pId);
		Integer tId = task.getTestId();
		if(tId != null) t.setTestId(tId);
		Integer status = task.getStatus();
		if(status != null) t.setStatus(status);
		Integer swType = task.getSoftwareType();
		if(swType != null) t.setSoftwareType(swType);
		
		taskService.update(task);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String get() {
		System.out.println(" get:"+task.getId());
		task = taskService.get(task.getId());
		if(task != null) {
			List list = new ArrayList<Object>();			
			list.add(fillResultMap(task));
			buildJson(true,NO_ERROR,list);
		}
		return FINISH;
	}
	
	public String all() {
		List<Task> resultList = taskService.all();
		buildJson(true,NO_ERROR,resultList);
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
	
	private Map<String,Object> fillResultMap(Task t){
		Map<String,Object> map = new HashMap<String,Object>();
		if(t != null) {
			Integer id =  t.getId();
			map.put("id",id);
			Integer pId = t.getProjectId();
			Project p = null;
			if(pId != null)
				 p = projectService.get(pId);
			map.put("project", p != null ? p.getName() : null);
			Integer testId = t.getTestId();
			Test test = null;
			if(testId != null) {
				test = testService.get(testId); 
			}
			map.put("test",test != null ? test.getName() : null);
			map.put("toolVersion", t.getToolVersion());
			map.put("status", t.getStatus());
			map.put("summary", t.getSummary());
			map.put("tester", t.getTester());
			Date date = t.getStartDate();
			map.put("startDate", date != null ? date.toString():null);
			date = t.getEndDate();
			map.put("endDate", date != null ? date.toString():null);
			map.put("failureCount",t.getFailureCount());
		}
		return map;
	}
	
	private List fillResultList(List<Task> tasks) {
		List list = new ArrayList<Map<String,String>>(); 
		for(Task t:tasks) {
			Map<String,Object> map = fillResultMap(t);
			list.add(map);
		}
		return list;
	}

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		System.out.println("get model");
		task = new Task(); 
		return task;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(hasFieldErrors()) {
			Map<String,List<String>> map = this.getFieldErrors();
			buildJson(false,FIELD_ERROR,map);
		}
		super.validate();
	}
}
