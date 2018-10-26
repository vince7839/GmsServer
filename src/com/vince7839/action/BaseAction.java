package com.vince7839.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable{	
	final static String SAVE = "save";
	final static String DELETE = "delete";
	final static String UPDATE = "update";
	final static String GET = "get";
	final static String ALL = "all";
	final static String FINISH = "finish";
	final static String STREAM = "stream";
	final static String FAIL = "fail"; 
	final static String ERROR = "error"; 
	final static int NO_ERROR = 0;
	final static int UNKNOWN_ERROR = 1;
	final static int NO_SUCH_TARGET = 2;
	final static int NAME_TOO_SHORT = 3;
	final static int NAME_EXIST = 4;	
	final static int FIELD_ERROR = 5;
	final static int MULTI_RESULT_FOUND = 6;
	final static int ID_IS_NULL = 7;
	final static int INTERNAL_ERROR = 8;
	final Map<Integer,String> errMap = new HashMap<Integer,String>();	
	Map<String,Object> result = new HashMap<String,Object>();

	public BaseAction() {
		errMap.put(NO_SUCH_TARGET, "no such target");
		errMap.put(NAME_TOO_SHORT, "name least 3 chars");
		errMap.put(UNKNOWN_ERROR,"unknown error");
		errMap.put(NAME_EXIST,"name exists");
		errMap.put(FIELD_ERROR,"field error");
		errMap.put(NO_ERROR,null);
		errMap.put(MULTI_RESULT_FOUND, "multi result found");
		errMap.put(ID_IS_NULL, "id cannot null");
		errMap.put(INTERNAL_ERROR, "internal error");
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
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(hasFieldErrors()) {
			Map<String,List<String>> map = this.getFieldErrors();
			buildJson(false,FIELD_ERROR,map);
		}
	}
	
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
	}	
}
