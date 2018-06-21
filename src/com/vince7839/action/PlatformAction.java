package com.vince7839.action;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Platform;
import com.vince7839.service.IPlatformService;

public class PlatformAction extends BaseAction implements ModelDriven<Platform>{
	IPlatformService service;
	Platform platform = new Platform();
	
	public String save() {
		System.out.println(platform.getName());
		String name = platform.getName();
		if(name == null || name.length() < 3) {
			buildJson(false,NAME_TOO_SHORT,null);
			return FINISH;
		}
		if(service.isNameExists(platform.getName())) {
			buildJson(false,NAME_EXIST,null);
			return FINISH;
		}
		service.save(platform);
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String delete() {
		Platform p = service.get(platform.getId());
		if(p != null) {
			service.delete(p);
			buildJson(true,NO_ERROR,null);
		}else {
			buildJson(false,NO_SUCH_TARGET,null);
		}	
		return FINISH;
	}
	
	public String update() {		
		if(service.exists(platform.getId())) {
			String name = platform.getName();
			if(name != null && name.length() > 2) {
				service.update(platform);
			} else {
				buildJson(false,NAME_TOO_SHORT,null);
			}			
		} else {
			buildJson(false,NO_SUCH_TARGET,null);
		}
		buildJson(true,NO_ERROR,null);
		return FINISH;
	}
	
	public String get() {
		platform = service.get(platform.getId());	
		return GET;		
	}
	
	public String all() {
		buildJson(true,NO_ERROR,service.all());
		return FINISH;	
	}
	
	public IPlatformService getService() {
		return service;
	}

	public void setService(IPlatformService service) {
		this.service = service;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	@Override
	public Platform getModel() {
		// TODO Auto-generated method stub
		return platform;
	}
}
