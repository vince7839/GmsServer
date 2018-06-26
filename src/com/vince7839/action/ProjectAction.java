package com.vince7839.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Project;
import com.vince7839.service.IPlatformService;
import com.vince7839.service.IProjectService;

public class ProjectAction extends BaseAction  implements ModelDriven<Project>{
	IProjectService projectService;
	IPlatformService platformService;
	Project project;
	
	public String save() {
		if(!platformService.exists(project.getPlatformId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}
		String name = project.getName();
		if(name == null||name.length() <2) {
			buildJson(false,NAME_TOO_SHORT,null);
			return FINISH;
		}
		if(projectService.isNameExists(name)) {
			buildJson(false,NAME_EXIST,null);
			return FINISH;
		}			
		projectService.save(project);
		result.put("result",SUCCESS);		
		return FINISH;
	}
	
	public String delete() {
		if(projectService.exists(project.getId())) {
			projectService.delete(project);
			buildJson(true,NO_ERROR,null);
		}else {
			buildJson(false,NO_SUCH_TARGET,null);
		}
		return FINISH;
	}
	
	public String update() {
		if(!projectService.exists(project.getId())) {
			buildJson(false,NO_SUCH_TARGET,null);
			return FINISH;
		}			
		if(!platformService.exists(project.getPlatformId())) {
			buildJson(true,NO_SUCH_TARGET,null);
			return FINISH;
		}
		String name = project.getName();
		if(name == null||name.length() < 3) {
			buildJson(true,NAME_TOO_SHORT,null);
			return FINISH;
		}
		projectService.update(project);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String get() {		
		Project p = projectService.get(project.getId());
		buildJson(true, NO_ERROR, p);
		return FINISH;
	}
	
	public String all() {
		buildJson(true,NO_ERROR,projectService.all());
		return FINISH;
	}
	
	public String list() {
		List<Project> list = projectService.list(project);
		buildJson(true, NO_ERROR, list);
		return FINISH;
	}
		
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
		project = new Project();
		return project;
	}
}
