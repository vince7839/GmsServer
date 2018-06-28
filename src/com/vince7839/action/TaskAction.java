package com.vince7839.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Project;
import com.vince7839.entity.SoftwareType;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.factory.CtsFactory;
import com.vince7839.factory.GtsFactory;
import com.vince7839.factory.PerformFactory;
import com.vince7839.factory.VerifierFactory;
import com.vince7839.factory.VtsFactory;
import com.vince7839.service.IJobService;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;
import com.vince7839.util.JobBuilder;

public class TaskAction extends BaseAction implements ModelDriven<Task>{
	ITaskService taskService;
	IProjectService projectService;
	ITestService testService;
	IJobService jobService;
	IPlatformService platformService;
	Task task;
	public String save() {
		System.out.println("save"+task);
		if(!projectService.exists(task.getProjectId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		taskService.save(task);		
		//使用工厂方法模式生产某个test 方便后期扩展
		jobService.save(JobBuilder.build(task, new CtsFactory()));
		jobService.save(JobBuilder.build(task, new GtsFactory()));
		jobService.save(JobBuilder.build(task, new VtsFactory()));
		jobService.save(JobBuilder.build(task, new VerifierFactory()));
		jobService.save(JobBuilder.build(task, new PerformFactory()));
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
		System.out.println("[TaskAction]update:" + task);
		if(!taskService.exists(task.getId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		
		Task t = taskService.get(task.getId());
		Integer pId = task.getProjectId();
		if(pId != null) t.setProjectId(pId);
		Status status = task.getStatus();
		if(status != null) t.setStatus(status);
		SoftwareType swType = task.getSoftwareType();
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
	
	private Map<String,Object> buildTaskMap(Task task){
		Map<String,Object> map = new HashMap<String,Object>();
		if(task != null) {
			Integer id =  task.getId();
			map.put("id",id);
			
			Integer projectId = task.getProjectId();
			if(projectService.exists(projectId)) {
				Project project = projectService.get(projectId);
				map.put("project", project.getName());
				Integer platformId = project.getPlatformId();
				map.put("platform", platformService.exists(platformId) ? platformService.get(platformId).getName() : null);
				map.put("spl", project.getSpl());
			} else {
				map.put("project", null);
				map.put("platform", null);
				map.put("spl", null);
			}
			
			map.put("bugId", task.getBugId());
			map.put("softwareType", task.getSoftwareType());
			List<Job> jobs = jobService.findByTask(task.getId());
			Map<String,Object> itemMap = new HashMap<String,Object>();
			for(Job job:jobs) {
				if(testService.exists(job.getTestId())) {
					Test test = testService.get(job.getTestId());
					String name = test.getName() == null ? "null" : test.getName();
					itemMap.put(name, job.getStatus());
				}				
			}			
			map.put("items",itemMap);
			map.put("status", task.getStatus());
			map.put("summary", task.getSummary());
			Date date = task.getStartDate();
			map.put("startDate", date != null ? date.toString():null);
			date = task.getEndDate();
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

	public IPlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(IPlatformService platformService) {
		this.platformService = platformService;
	}

}
