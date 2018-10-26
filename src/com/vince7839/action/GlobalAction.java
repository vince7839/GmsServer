package com.vince7839.action;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.service.IPlatformService;

public class GlobalAction extends BaseAction {
	IPlatformService platformService;
	public String admin() {
		System.out.println("platform size:" + platformService.all().size());
		ActionContext.getContext().put("platforms", platformService.all());
		return FINISH;
	}
	
	public String guide() {
		return FINISH;
	}

	public IPlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(IPlatformService platformService) {
		this.platformService = platformService;
	}
}
