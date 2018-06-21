package com.vince7839.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {	
	final static String SAVE = "save";
	final static String DELETE = "delete";
	final static String UPDATE = "update";
	final static String GET = "get";
	final static String ALL = "all";
	final static String FINISH = "finish";
	final static String FAIL = "fail"; 

	final static int NO_ERROR = 0;
	final static int UNKNOWN_ERROR = 1;
	final static int NO_SUCH_TARGET = 2;
	final static int NAME_TOO_SHORT = 3;
	final static int NAME_EXIST = 4;	
	final static int FIELD_ERROR = 5;
	
	final Map errMap = new HashMap<Integer,String>();	
	Map<String,Object> result = new HashMap<String,Object>();

	public BaseAction() {
		errMap.put(NO_SUCH_TARGET, "no such target");
		errMap.put(NAME_TOO_SHORT, "name least 3 chars");
		errMap.put(UNKNOWN_ERROR,"unknown error");
		errMap.put(NAME_EXIST,"name exists");
		errMap.put(FIELD_ERROR,"field error");
		errMap.put(NO_ERROR,"no error happend");
	}
	
	public void buildJson(boolean success,int code,Object data) {
		result.clear();
		result.put("success",success);
		result.put("code", code);		
		result.put("msg", errMap.get(code));
		if(data != null) {
			result.put("data",data);
		}
	}
	
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}	
}
