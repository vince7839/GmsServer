package com.vince7839.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {	
	final static int SAVE = 0;
	final static int DELETE = 1;
	final static int UPDATE = 2;
	final static int GET = 3;
	final static int ALL = 4;
	final static String FINISH = "finish";
	final static String RESULT_GET = "get";
	final static String RESULT_ALL = "all";
	Map<String,String> result = new HashMap<String,String>();
	List list;
	public String save() {
		return work(SAVE);
	}
	
	public String delete() {
		return work(DELETE);
	}
	
	public String update() {
		return work(UPDATE);	
	}
	
	public String get() {
		return work(GET);
	}
	
	public String all() {
		return work(ALL);
	}
	
	public String work(int type) {
		return NONE;
	}

	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
}
