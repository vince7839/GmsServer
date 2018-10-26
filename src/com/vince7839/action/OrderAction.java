package com.vince7839.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Status;
import com.vince7839.entity.Task;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.ITaskService;

public class OrderAction extends BaseAction  implements ModelDriven<Task>{
	IPlatformService platformService;
	ITaskService taskService;
	Task model;
	public String order() {
		ActionContext context = ActionContext.getContext();
		String role = (String)context.getSession().get("role");
		if(role == null) {
			addActionMessage("请先登录再进行操作。");
			return ERROR;
		}
		List<Platform> platforms = platformService.all();
		System.out.println(platforms);
		ActionContext.getContext().put("platforms", platforms);
		return FINISH; 
	}
	public IPlatformService getPlatformService() {
		return platformService;
	}
	public void setPlatformService(IPlatformService platformService) {
		this.platformService = platformService;
	}
	
	public String doOrder() {
		System.out.println("save" + model);
		if (model.getProject() == null) {
			System.out.println("project null");
		}
		model.setStatus(Status.WAIT);
		model.setOrderDate(new Date());
		taskService.save(model);
		return FINISH;
	}
	
	public String query() {
		List<Task> orders = taskService.listOrder();
		ActionContext.getContext().put("orders", orders);
		return FINISH;
	}
	
	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		model = new Task();
		return model;
	}
	public ITaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
}
