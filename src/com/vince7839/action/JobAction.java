package com.vince7839.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.Status;
import com.vince7839.entity.Test;
import com.vince7839.exception.MultiResultException;
import com.vince7839.service.IJobService;
import com.vince7839.util.JobFilter;

public class JobAction extends BaseAction implements ModelDriven<Job> {
	IJobService jobService;
	Job model;
	Integer load;
	Integer page;
	Platform platform;
	Project project;
	Test t;
	public String update() {
		System.out.println("job_update:" + model);
		Job j = null;
		if (model.getId() != null) {
			j = jobService.get(model.getId());
		} else {
			if (model.getTask() != null && model.getTest() != null) {
				try {
					j = jobService.find(model.getTask(), model.getTest());
				} catch (MultiResultException e) {
					buildJson(false, MULTI_RESULT_FOUND, null);
					return FINISH;
				}
			}
		}
		if (j == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		Status status = model.getStatus();
		if (status != null)
			j.setStatus(status);
		String tester = model.getTester();
		if (tester != null)
			j.setTester(tester);
		String toolVersion = model.getToolVersion();
		if (toolVersion != null)
			j.setToolVersion(toolVersion);
		String summary = model.getSummary();
		if (summary != null)
			j.setSummary(summary);
		Date startDate = model.getStartDate();
		if (startDate != null)
			j.setStartDate(startDate);
		Date endDate = model.getEndDate();
		if (endDate != null)
			j.setEndDate(endDate);
		buildJson(true, NO_ERROR, null);
		jobService.update(j);
		return FINISH;
	}

	public String get() {
		Job j = jobService.get(model.getId());
		List<Job> list = new ArrayList<Job>();
		list.add(j);
		buildJson(true, NO_ERROR, list);
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
		if(page == null) {
			page = 0;
		}
		if(load == null) {
			load = 10;
		}
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
