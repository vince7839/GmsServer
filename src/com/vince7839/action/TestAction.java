package com.vince7839.action;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.vince7839.entity.Test;
import com.vince7839.service.ITestService;

public class TestAction extends BaseAction {
	ITestService service;
	Test test;
	public String work(int type) {
		HttpParameters params = ActionContext.getContext().getParameters();
		System.out.println("test:"+params);
		String name = params.get("name").getValue();
		String idStr = params.get("id").getValue();
		int id = 0;
		if(idStr != null) {
			id = Integer.parseInt(idStr);
		}
		Test t =new Test();
		try {
			if(type == SAVE) {								
				t.setName(name);
				service.save(t);				
			}else if(type == DELETE) {
				t.setId(id);
				service.delete(t);
			}else if(type == UPDATE) {
				t.setName(name);
				service.update(t);
			}else if(type == GET) {
				t.setId(id);
				test = service.get(id);	
				return RESULT_GET;
			}
			result.put("result", "success");	
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", "fail");			
		}
		return FINISH;
	}

	public ITestService getService() {
		return service;
	}

	public void setService(ITestService service) {
		this.service = service;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
}
