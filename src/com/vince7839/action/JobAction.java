package com.vince7839.action;

import java.sql.Date;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.service.IJobService;

public class JobAction extends BaseAction implements ModelDriven<Job>{
	IJobService jobService;
	Job job;
	public String update() {
		Job j = jobService.get(job.getId());
		if(j == null) {
			buildJson(false, NO_SUCH_TARGET, null);
		}
		Integer status = job.getStatus();
		if(status != null) j.setStatus(status);
		String tester = job.getTester();
		if(tester != null) j.setTester(tester);
		String toolVersion = job.getToolVersion();
		if(toolVersion != null) j.setToolVersion(toolVersion);
		String summary = job.getSummary();
		if(summary != null) j.setSummary(summary);
		Date startDate = job.getStartDate();
		if(startDate != null) j.setStartDate(startDate);
		Date endDate = job.getEndDate();
		if(endDate != null) j.setEndDate(endDate);
		buildJson(true, NO_ERROR, null);
		jobService.update(j);
		return FINISH;
	}
	@Override
	public Job getModel() {
		// TODO Auto-generated method stub
		job = new Job();
		return job;
	}
	public IJobService getJobService() {
		return jobService;
	}
	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
	
	public String test() {
		HttpParameters p = ActionContext.getContext().getParameters();
		System.out.println(p);
		return FINISH;
	}
}