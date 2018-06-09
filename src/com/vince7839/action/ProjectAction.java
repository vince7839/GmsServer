package com.vince7839.action;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Project;
import com.vince7839.service.IProjectService;

public class ProjectAction extends BaseAction {
	IProjectService service;
	Project project;
	public String work(int type) {
		HttpParameters params = ActionContext.getContext().getParameters();
		System.out.println("project_get:"+params);
		String name = params.get("name").getValue();
		String spl = params.get("spl").getValue();
		String idStr = params.get("id").getValue();
		String platformIdStr = params.get("platformId").getValue();
		int id = 0;
		int platformId = 0;
		if(idStr != null) {
			id = Integer.parseInt(idStr);
		}
		if(platformIdStr != null) {
			platformId =  Integer.parseInt(params.get("platformId").getValue());
		}
		Project p =new Project();
		try {
			if(type == SAVE) {								
				p.setName(name);
				p.setSpl(spl);
				p.setPlatformId(platformId);
				service.save(p);				
			}else if(type == DELETE) {
				p.setId(id);
				service.delete(p);
			}else if(type == UPDATE) {
				p.setId(id);
				p.setName(name);
				p.setPlatformId(platformId);
				p.setSpl(spl);
				service.update(p);
			}else if(type == GET) {
				p.setId(id);
				project = service.get(id);	
				return RESULT_GET;
			}else if(type == ALL) {				
				list = service.all();
				return RESULT_ALL;
			}
			result.put("result", "success");	
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", "fail");			
		}
		return FINISH;
	}
	
	public IProjectService getService() {
		return service;
	}

	public void setService(IProjectService service) {
		this.service = service;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
