package com.vince7839.action;

import java.util.Map;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Platform;
import com.vince7839.service.IPlatformService;

public class PlatformAction extends BaseAction {
	IPlatformService service;
	Platform platform;
	public String work(int type) {
		HttpParameters params = ActionContext.getContext().getParameters();
		System.out.println("platform_get:"+params);
		String name = params.get("name").getValue();
		String idStr = params.get("id").getValue();
		int id = 0;
		if(idStr != null) {
			id = Integer.parseInt(idStr);
		}
		Platform p =new Platform();
		try {
			if(type == SAVE) {								
				p.setName(name);
				service.save(p);				
			}else if(type == DELETE) {
				p.setId(id);
				service.delete(p);
			}else if(type == UPDATE) {
				p.setId(id);
				p.setName(name);
				service.update(p);
			}else if(type == GET) {
				p.setId(id);
				platform = service.get(id);	
				return RESULT_GET;
			}
			result.put("result", "success");	
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", "fail");			
		}
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
}
