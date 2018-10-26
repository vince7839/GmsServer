package com.vince7839.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.exception.MultiResultException;
import com.vince7839.service.IJobService;
import com.vince7839.service.ITaskService;
import com.vince7839.util.JobFilter;

public class JobAction extends BaseAction implements ModelDriven<Job> {
	IJobService jobService;
	Job model;
	Integer load = 10;
	Integer page = 0;
	Platform platform;
	Project project;
	Test t;
	
	public String save() {
		if(model.getTask() == null||model.getTest() == null) {
			buildJson(false, NO_SUCH_TARGET, null);
		}
		jobService.save(model);
		return FINISH;
	} 
		
	public String update() {
		System.out.println("job_update:" + model);
		Job job = jobService.get(model.getId());
		if (job == null) {
			addActionMessage("要修改的测试任务为空！id=" + model.getId());
			return ERROR;
		}

		if (model.getStatus() != null)
			job.setStatus(model.getStatus());

		if (model.getTester() != null)
			job.setTester(model.getTester());

		if (model.getToolVersion() != null)
			job.setToolVersion(model.getToolVersion());

		if (model.getSummary() != null)
			job.setSummary(model.getSummary());

		if (model.getStartDate() != null)
			job.setStartDate(model.getStartDate());

		if (model.getEndDate() != null)
			job.setEndDate(model.getEndDate());

		jobService.update(job);
		buildJson(true, NO_ERROR, null);
		return FINISH;
	}

	public String get() {
		System.out.println(model);
		Job j = jobService.get(model.getId());
		List<Job> list = new ArrayList<Job>();
		list.add(j);
		buildJson(true, NO_ERROR, list);
		return FINISH;
	}
	
	public String find() {
		Task task = model.getTask();
		Test test = model.getTest();
		if(task != null && test != null) {			
			Set<Job> jobs = task.getJobs();
			for(Job job:jobs) {
				if(job.getTest().getId() == test.getId()) {
					buildJson(true, NO_ERROR, job);
					return FINISH;
				}
			}
		}
		buildJson(false, NO_SUCH_TARGET, null);
		return FINISH;
	}

	public String size() {
		if (load == null) {
			load = 10;
		}
		JobFilter jobFilter = new JobFilter(platform,project,t);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("size", jobService.list(jobFilter).size());
		map.put("load", load);
		map.put("pageCount", jobService.pageCount(load, jobFilter));
		buildJson(true, NO_ERROR, map);
		return FINISH;
	}
	
	public String list() {
		JobFilter jobFilter = new JobFilter(platform,project,t);
		buildJson(true,NO_ERROR,jobService.list(jobFilter));
		return FINISH;
	}

	public String load() {
		JobFilter filter = new JobFilter(platform,project,t);
		List<Job> list = jobService.load(filter, page, load);
		buildJson(true, NO_ERROR, list);
		return FINISH;
	}
	
	@Override
	public Job getModel() {
		// TODO Auto-generated method stub
		model = new Job();
		return model;
	}

	public IJobService getJobService() {
		return jobService;
	}

	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}

	public String test() {
		jobService.test();
		return FINISH;
	}

	public Integer getLoad() {
		return load;
	}

	public void setLoad(Integer load) {
		this.load = load;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Test getT() {
		return t;
	}

	public void setT(Test t) {
		this.t = t;
	}

}
