package com.vince7839.action;

import java.util.HashMap;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.service.ITaskService;

public class TaskAction extends BaseAction implements ModelDriven<Task> {
	ITaskService taskService;
	Task model;
	Integer page = 0;
	Integer load = 10;
	Integer currentId = null;

	@Transactional
	public String save() {
		System.out.println("save" + model);
		if (model.getProject() == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		model.setStatus(Status.WAIT);
		taskService.save(model);
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
		System.out.println("[TaskAction]update:" + model);
		Task dbTask = taskService.get(model.getId());
		if (dbTask == null) {
			buildJson(false, NO_SUCH_TARGET, null);
		}
		currentId = dbTask.getId();		
		if (model.getSummary() != null)
			dbTask.setSummary(model.getSummary());

		if(model.getBugId() != null) {
			dbTask.setBugId(model.getBugId());
		}

		if(model.getLeader() != null) {
			dbTask.setLeader(model.getLeader());
		}

		if (model.getStartDate() != null)
			dbTask.setStartDate(model.getStartDate());

		if (model.getEndDate() != null)
			dbTask.setEndDate(model.getEndDate());

		if (model.getExpectStartDate() != null)
			dbTask.setExpectStartDate(model.getExpectStartDate());

		if (model.getExpectEndDate() != null)
			dbTask.setExpectEndDate(model.getExpectEndDate());

		taskService.update(dbTask);
		buildJson(true, NO_ERROR, null);
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

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		model = new Task();
		return model;
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

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}
}
