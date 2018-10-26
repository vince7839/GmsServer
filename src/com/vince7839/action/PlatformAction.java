package com.vince7839.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.vince7839.entity.Platform;
import com.vince7839.service.IPlatformService;

public class PlatformAction extends BaseAction implements ModelDriven<Platform> {
	IPlatformService platformService;
	Platform model;

	public String save() {
		System.out.println(model.getName());
		String name = model.getName();
		if (name == null || name.length() < 3) {
			buildJson(false, NAME_TOO_SHORT, null);
			return FINISH;
		}
		if (platformService.isNameExists(model.getName())) {
			buildJson(false, NAME_EXIST, null);
			return FINISH;
		}
		platformService.save(model);
		buildJson(true, NO_ERROR, null);
		return FINISH;
	}

	public String delete() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		Platform p = platformService.get(model.getId());
		if (p != null) {
			platformService.delete(p);
			buildJson(true, NO_ERROR, null);
		} else {
			buildJson(false, NO_SUCH_TARGET, null);
		}
		return FINISH;
	}

	public String update() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		Platform p = platformService.get(model.getId());
		if (!platformService.exists(model.getId())) {
			buildJson(false, NO_SUCH_TARGET, null);
			return FINISH;
		}
		String name = model.getName();
		if (name != null || name.length() < 3) {
			buildJson(false, NAME_TOO_SHORT, null);
			return FINISH;
		}
		if (!name.equals(p.getName()) && platformService.isNameExists(name)) {
			buildJson(false, NAME_EXIST, null);
			return FINISH;
		}
		platformService.update(model);
		buildJson(true, NO_ERROR, null);
		return FINISH;

	}

	public String get() {
		if (model.getId() == null) {
			buildJson(false, ID_IS_NULL, null);
			return FINISH;
		}
		model = platformService.get(model.getId());
		return GET;
	}

	public String all() {
		buildJson(true, NO_ERROR, platformService.all());
		List<Platform> list = platformService.all();
		for(Platform p:list) {
			System.out.println(p.getProjects());
		}
		return FINISH;
	}

	public IPlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(IPlatformService service) {
		this.platformService = service;
	}

	@Override
	public Platform getModel() {
		// TODO Auto-generated method stub
		model = new Platform();
		return model;
	}

}
