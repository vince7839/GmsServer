package com.vince7839.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.vince7839.entity.Status;

public class ParamConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Object value, Class toType) {
		// TODO Auto-generated method stub
		try {
			String[] arr = (String[]) value;
			String str = arr[0];
			System.out.println("convert string:" + str);
			if (toType == Date.class) {
				System.out.println("convert to date");
				Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
				Matcher matcher = pattern.matcher(str);
				if (matcher.matches()) {
					Date date = Date.valueOf(str);
					System.out.println("convert matches:" + (date == null ? "null" : date.toString()));
					return date;
				} else {
					System.out.println("not matches");
				}
			} else if (toType == Status.class) {
				Status status = null;
				if("waiting".equalsIgnoreCase(str)) {
					status = Status.Waiting;
				} else if ("testing".equalsIgnoreCase(str)){
					status = Status.Testing;
				}else if ("passed".equalsIgnoreCase(str)){
					status = Status.Passed;
				}else if ("failed".equalsIgnoreCase(str)){
					status = Status.Failed;
				}else if ("stopped".equalsIgnoreCase(str)){
					status = Status.Stopped;
				}else if ("na".equalsIgnoreCase(str)){
					status = Status.NA;
				}else if ("abandoned".equalsIgnoreCase(str)){
					status = Status.Abandoned;
				}
				System.out.println("convert to status:"+status);
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.convertValue(value, toType);
	}
}
