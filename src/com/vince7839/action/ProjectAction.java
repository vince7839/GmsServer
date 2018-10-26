package com.vince7839.action;

import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.IProjectService;

public class ProjectAction extends BaseAction implements ModelDriven<Project> {
	IProjectService projectService;
	IPlatformService platformService;
	Project model;
	Platform p;
	public String save() {
		if (model.getPlatform() == null) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		String name = model.getName();
		if (name == null || name.length() < 2) {
			buildJson(false, NAME_TOO_SHORT, null);
			return FINISH;
		}
		if (projectService.isNameExists(name)) {
			buildJson(false, NAME_EXIST, null);
			return FINISH;
		}
		projectService.save(model);
		result.put("result", SUCCESS);
		return FINISH;
	}

	public String delete() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		if (!projectService.exists(model.getId())) {
			buildJson(false, NO_SUCH_TARGET, null);
		}
		projectService.delete(model);
		buildJson(true, NO_ERROR, null);
		return FINISH;
	}

	public String update() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		if (!projectService.exists(model.getId())) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		if (model.getPlatform() == null) {
			buildJson(true, NO_SUCH_TARGET, null);
			return FINISH;
		}
		String name = model.getName();
		if (name == null || name.length() < 3) {
			buildJson(true, NAME_TOO_SHORT, null);
			return FINISH;
		}
		projectService.update(model);
		buildJson(true, NO_ERROR, null);
		return FINISH;
	}

	public String get() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		Project p = projectService.get(model.getId());
		buildJson(true, NO_ERROR, p);
		return FINISH;
	}

	public String all() {
		buildJson(true, NO_ERROR, projectService.all());
		return FINISH;
	}

	public String list() {
		System.out.println(p);
		Set<Project> set = null;
		if(p != null) {
			set = p.getProjects();			
		}
		System.out.println(set);
		buildJson(true, NO_ERROR, set);
		return FINISH;
	}

	public IPlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(IPlatformService platformService) {
		this.platformService = platformService;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	@Override
	public Project getModel() {
		// TODO Auto-generated method stub
		model = new Project();
		return model;
	}

	public Platform getP() {
		return p;
	}

	public void setP(Platform p) {
		this.p = p;
	}
}
