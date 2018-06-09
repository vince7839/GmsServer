package com.vince7839.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Task;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;

public class TaskAction extends BaseAction {
	ITaskService taskService;
	IProjectService projectService;
	ITestService testService;
	Task task;

	public String work(int type) {
		HttpParameters params = ActionContext.getContext().getParameters();
		System.out.println("task:"+params);
		String tester = params.get("tester").getValue();
		String summary = params.get("summary").getValue();
		String toolVersion = params.get("toolVersion").getValue();
		String projectIdStr = params.get("projectId").getValue();
		String testIdStr = params.get("testId").getValue();
		String idStr = params.get("id").getValue();
		int projectId = 0;
		int testId =0;
		int id = 0;
		if(projectIdStr != null) {
			projectId = Integer.parseInt(projectIdStr);
		}
		if(idStr != null) {
			id = Integer.parseInt(idStr);
		}
		Task t =new Task();
		try {
			if(type == SAVE) {								
				t.setProjectId(projectId);
				t.setTestId(testId);
				t.setTester(tester);
				t.setToolVersion(toolVersion);
				t.setSummary(summary);
				taskService.save(t);				
			}else if(type == DELETE) {
				t.setId(id);
				taskService.delete(t);
			}else if(type == UPDATE) {
				t.setId(id);
				t.setProjectId(projectId);
				t.setTestId(testId);
				t.setTester(tester);
				t.setToolVersion(toolVersion);
				t.setSummary(summary);
				taskService.update(t);
			}else if(type == GET) {
				t.setId(id);				
				task = taskService.get(id);
				result = fillResultMap(task);
				return FINISH;
			}else if(type == ALL) {
				List<Task> resultList = taskService.all();
				list = fillResultList(resultList);
				System.out.println("task size:"+list.size());
				return RESULT_ALL;
			}
			result.put("result", "success");	
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", "fail");			
		}
		return FINISH;
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
	
	private Map<String,String> fillResultMap(Task t){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", ""+t.getId());
		map.put("project", projectService.get(t.getProjectId()).getName());
		map.put("test", testService.get(t.getTestId()).getName());
		map.put("toolVersion", t.getToolVersion());
		map.put("status", ""+t.getStatus());
		map.put("summary", t.getSummary());
		map.put("tester", t.getTester());
		map.put("startDate", t.getStartDate().toString());
		map.put("endDate", t.getEndDate().toString());
		map.put("failureCount",""+t.getFailureCount());
		return map;
	}
	
	private List fillResultList(List<Task> tasks) {
		List list = new ArrayList<Map<String,String>>(); 
		for(Task t:tasks) {
			Map<String,String> map = fillResultMap(t);
			list.add(map);
		}
		return list;
	}

}
