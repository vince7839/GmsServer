package com.vince7839.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Job;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.service.IJobService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;
import com.vince7839.util.JobFilter;

public class TableAction extends BaseAction {
	ITaskService taskService;
	ITestService testService;
	IJobService jobService;
	List<Test> testList;
	List list;
	int type = 0;
	int page = 1;
	int load = 10;
	
	public String execute() {
		testList = testService.list();
		ActionContext.getContext().put("page",page);
		if(type == 0) {
			list = buildTaskList(taskService.listByPage(page, load));
			ActionContext.getContext().put("pageCount", taskService.pageCount(load));
		}else {
			JobFilter filter = new JobFilter(null,null,testService.get(type));
			list = buildJobList(jobService.load(filter, page, load));
			ActionContext.getContext().put("pageCount", jobService.pageCount(load, filter));
		}
		ActionContext.getContext().put("load",load);
		System.out.println("type:" + type + " list size:" + list.size() + " page:" + page) ;
		return FINISH;
	}
	
	public String query() {
		list = buildTaskList(taskService.listOrder());
		return FINISH;
	}
	
	private Map<String, Object> buildTaskMap(Task task) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (task != null) {
			Integer id = task.getId();
			map.put("id", id);

			Project project = task.getProject();
			if (project != null) {
				map.put("project", project.getName());
				Platform platform = project.getPlatform();
				map.put("platform", platform != null ? platform.getName() : null);
				map.put("spl", project.getSpl());
			} else {
				map.put("project", null);
				map.put("platform", null);
				map.put("spl", null);
			}

			map.put("bugId", task.getBugId());
			map.put("softwareType", task.getSoftwareType());
			List<Job> jobs = jobService.findByTask(task);
			Map<String, Object> itemMap = new HashMap<String, Object>();
			for (Job job : jobs) {
				Test test = job.getTest();
				if (test != null) {
					String name = test.getName() == null ? "null" : test.getName();
					itemMap.put(name, job.getStatus());
					map.put(name,job.getStatus());
				}
			}

			map.put("items", itemMap);
			map.put("status", task.getStatus());
			map.put("summary", task.getSummary());
			map.put("leader", task.getLeader());
			Date date = task.getStartDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("startDate", date != null ? format.format(date) : null);
			date = task.getEndDate();
			map.put("endDate", date != null ? format.format(date) : null);
			date = task.getExpectStartDate();			
			map.put("expectStartDate", date != null ? format.format(date) : null);
			date = task.getExpectEndDate();
			map.put("expectEndDate", date != null ? format.format(date) : null);
			date = task.getOrderDate();			
			map.put("orderDate", date != null ? format2.format(date) : null);
		}
		return map;
	}

	private Map buildJobMap(Job job) {
			Map<String,String> map = new HashMap<String,String>();
			Project project = job.getTask().getProject();
			if(project != null) {
				map.put("project",project.getName());
				map.put("spl", project.getSpl());
				Platform platform = project.getPlatform();
				map.put("platform", platform != null ? platform.getName() : "");
			}
			map.put("toolVersion", job.getToolVersion());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = job.getStartDate();
			map.put("startDate", date != null ? format.format(date) : "");
			date = job.getEndDate();
			map.put("endDate", date != null ? format.format(date) : "");
			map.put("status", job.getStatus().toString());
			map.put("summary", job.getSummary());
			map.put("tester", job.getTester());
			return map;
	}
	
	private List buildTaskList(List<Task> tasks) {
		List list = new ArrayList<Map<String, String>>();
		for (Task t : tasks) {
			Map<String, Object> map = buildTaskMap(t);
			list.add(map);
		}
		return list;
	}
	
	private List buildJobList(List<Job> jobs) {
		List list = new ArrayList<Map<String, String>>();
		for (Job j : jobs) {
			Map<String, Object> map = buildJobMap(j);
			list.add(map);
		}
		return list;
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
	public IJobService getJobService() {
		return jobService;
	}
	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
	public List<Test> getTestList() {
		return testList;
	}
	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLoad() {
		return load;
	}
	public void setLoad(int load) {
		this.load = load;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
