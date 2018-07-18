package com.vince7839.action;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Job;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.SoftwareType;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.entity.Test;
import com.vince7839.exception.BuildTestException;
import com.vince7839.factory.CtsFactory;
import com.vince7839.factory.GsiFactory;
import com.vince7839.factory.GtsFactory;
import com.vince7839.factory.PerformFactory;
import com.vince7839.factory.RuleFactory;
import com.vince7839.factory.VerifierFactory;
import com.vince7839.factory.VtsFactory;
import com.vince7839.service.IJobService;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.IProjectService;
import com.vince7839.service.ITaskService;
import com.vince7839.service.ITestService;
import com.vince7839.util.JobBuilder;

public class TaskAction extends BaseAction implements ModelDriven<Task> {
	ITaskService taskService;
	IProjectService projectService;
	ITestService testService;
	IJobService jobService;
	IPlatformService platformService;
	Task model;
	Integer page;
	Integer load;

	@Transactional
	public String save() {
		System.out.println("save" + model);
		if (model.getProject() == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		model.setStatus(Status.Waiting);
		taskService.save(model);
		// 使用工厂方法模式生产某个test 方便后期扩展
		try {
			jobService.save(JobBuilder.build(model, new CtsFactory()));
			jobService.save(JobBuilder.build(model, new GtsFactory()));
			jobService.save(JobBuilder.build(model, new VtsFactory()));
			jobService.save(JobBuilder.build(model, new GsiFactory()));
			jobService.save(JobBuilder.build(model, new VerifierFactory()));
			jobService.save(JobBuilder.build(model, new PerformFactory()));
			jobService.save(JobBuilder.build(model, new RuleFactory()));
			buildJson(true, NO_ERROR, null);
			// throw new RuntimeException();
		} catch (BuildTestException e) {
			buildJson(false, INTERNAL_ERROR, e);
		}
		return FINISH;
	}

	public String delete() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		if (taskService.exists(model.getId())) {
			taskService.delete(model);
			buildJson(true, NO_ERROR, null);
		} else {
			result.put(FAIL, "no such test");
		}
		return FINISH;
	}

	public String update() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		System.out.println("[TaskAction]update:" + model);
		if (!taskService.exists(model.getId())) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}

		Task t = taskService.get(model.getId());
		if (t == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		if (model.getProject() != null)
			t.setProject(model.getProject());
		Status status = model.getStatus();
		if (status != null)
			t.setStatus(status);
		SoftwareType swType = model.getSoftwareType();
		if (swType != null)
			t.setSoftwareType(swType);
		String summary = model.getSummary();
		if (summary != null)
			t.setSummary(summary);

		Date startDate = model.getStartDate();
		if (startDate != null)
			t.setStartDate(startDate);

		Date endDate = model.getEndDate();
		if (endDate != null)
			t.setEndDate(endDate);

		Date orderDate = model.getOrderDate();
		if (orderDate != null)
			t.setOrderDate(orderDate);

		Date scheduleDate = model.getScheduleDate();
		if (scheduleDate != null)
			t.setScheduleDate(scheduleDate);

		Date expectStartDate = model.getExpectStartDate();
		if (expectStartDate != null)
			t.setExpectStartDate(expectStartDate);

		Date expectEndDate = model.getExpectEndDate();
		if (expectEndDate != null)
			t.setExpectEndDate(expectEndDate);

		taskService.update(t);
		buildJson(true, NO_ERROR, null);
		return FINISH;
	}

	public String get() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		System.out.println(" get:" + model.getId());
		model = taskService.get(model.getId());
		if (model != null) {
			buildJson(true, NO_ERROR, buildTaskMap(model));
		}
		return FINISH;
	}

	public String all() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date;
		List<Task> resultList = taskService.all();
		buildJson(true, NO_ERROR, buildTaskList(resultList));
		return FINISH;
	}

	public String size() {
		if (load == null) {
			load = 10;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("size", taskService.all().size());
		map.put("load", load);
		map.put("pageCount", taskService.pageCount(load));
		buildJson(true, NO_ERROR, map);
		return FINISH;
	}

	public String list() {
		if (page == null) {
			page = 0;
		}
		if (load == null) {
			load = 10;
		}
		List list = buildTaskList(taskService.loadByPage(page, load));
		buildJson(true, NO_ERROR, list);
		return FINISH;
	}

	public String order() {
		buildJson(true, NO_ERROR, buildTaskList(taskService.listOrder()));
		return FINISH;
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
				}
			}

			map.put("items", itemMap);
			map.put("status", task.getStatus());
			map.put("summary", task.getSummary());
			Date date = task.getStartDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("startDate", date != null ? format.format(date) : null);
			date = task.getEndDate();
			map.put("endDate", date != null ? format.format(date) : null);
		}
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

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		model = new Task();
		return model;
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLoad() {
		return load;
	}

	public void setLoad(Integer load) {
		this.load = load;
	}

}
