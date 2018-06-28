package com.vince7839.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Status;
import com.vince7839.exception.MultiResultException;
import com.vince7839.service.IJobService;

public class JobAction extends BaseAction implements ModelDriven<Job> {
	IJobService jobService;
	Job job;

	public String update() {
		System.out.println("job_update" + job);
		Job j = null;
		if (job.getId() != null) {
			j = jobService.get(job.getId());
		} else {
			try {
				j = jobService.find(job.getTaskId(), job.getTestId());
			} catch (MultiResultException e) {
				buildJson(false, MULTI_RESULT_FOUND, null);
				return FINISH;
			}
		}
		if (j == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		Status status = job.getStatus();
		if (status != null)
			j.setStatus(status);
		String tester = job.getTester();
		if (tester != null)
			j.setTester(tester);
		String toolVersion = job.getToolVersion();
		if (toolVersion != null)
			j.setToolVersion(toolVersion);
		String summary = job.getSummary();
		if (summary != null)
			j.setSummary(summary);
		Date startDate = job.getStartDate();
		if (startDate != null)
			j.setStartDate(startDate);
		Date endDate = job.getEndDate();
		if (endDate != null)
			j.setEndDate(endDate);
		buildJson(true, NO_ERROR, null);
		jobService.update(j);
		return FINISH;
	}

	public String get() {
		Job j = jobService.get(job.getId());
		List<Job> list = new ArrayList<Job>();
		list.add(j);
		buildJson(true, NO_ERROR, list);
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
		jobService.test();
		return FINISH;
	}
}
