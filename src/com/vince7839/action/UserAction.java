package com.vince7839.action;

import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction {
	String username;
	String password;
	public String login() {
		boolean ok = false;
		System.out.println("login:" + username + password);
		if("admin".equals(username) && "gms123456".equals(password)) {
			ok = true;
			ActionContext.getContext().getSession().put("role","admin");
		}else if("user".equals(username) && "srmmi".equals(password)) {
			ok = true;
			ActionContext.getContext().getSession().put("role","user");
		}
		buildJson(ok,NO_ERROR,"");
		return FINISH;
	}
	
	public String logout() {
		ActionContext.getContext().getSession().remove("role");
		return FINISH;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
