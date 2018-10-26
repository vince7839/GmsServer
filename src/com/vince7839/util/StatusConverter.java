package com.vince7839.util;

import javax.persistence.AttributeConverter;
import com.vince7839.entity.Status;

public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status status) {
		// TODO Auto-generated method stub
		System.out.println("convert to column:" + status);
		return status == null ? null : status.toString();
	}

	@Override
	public Status convertToEntityAttribute(String column) {
		// TODO Auto-generated method stub
	//	System.out.println("[StatusConverter]convert enum from:" + column);
		Status status = null;
		if("wait".equalsIgnoreCase(column)) {
			status = Status.WAIT;
		}else if("pass".equalsIgnoreCase(column)) {
			status = Status.PASS;
		}else if("fail".equalsIgnoreCase(column)) {
			status = Status.FAIL;
		}else if("test".equalsIgnoreCase(column)) {
			status = Status.TEST;
		}else if("stop".equalsIgnoreCase(column)) {
			status = Status.STOP;
		}else if("retest".equalsIgnoreCase(column)) {
			status = Status.RETEST;
		}else if("debug".equalsIgnoreCase(column)) {
			status = Status.DEBUG;
		}else {
			status = Status.UNKNOWN;
		}
		return status;
	}

}
