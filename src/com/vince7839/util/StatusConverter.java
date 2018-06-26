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
		System.out.println("[StatusConverter]convert enum from:" + column);
		Status status = null;
		if("waiting".equalsIgnoreCase(column)) {
			status = Status.Waiting;
		}else if("passed".equalsIgnoreCase(column)) {
			status = Status.Passed;
		}else if("failed".equalsIgnoreCase(column)) {
			status = Status.Failed;
		}else if("testing".equalsIgnoreCase(column)) {
			status = Status.Testing;
		}else if("stopped".equalsIgnoreCase(column)) {
			status = Status.Stopped;
		}else if("abandoned".equalsIgnoreCase(column)) {
			status = Status.Abandoned;
		}else if("na".equalsIgnoreCase(column)) {
			status = Status.NA;
		} else {
			status = Status.Unknown;
		}
		return status;
	}

}
