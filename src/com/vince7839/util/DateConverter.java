package com.vince7839.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Object value, Class toType) {
		// TODO Auto-generated method stub
		try {
		String [] arr = (String[])value;
		String str = arr[0];
		System.out.println("convert string:" + str);
		if(toType == Date.class) {
			System.out.println("convert to date");
			Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
			Matcher matcher = pattern.matcher(str);
			if(matcher.matches()) {
				Date date = Date.valueOf(str);
				System.out.println("convert matches:" + (date == null ? "null" : date.toString()));
				return date;
			}else {
				System.out.println("not matches");
			}		
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.convertValue(value, toType);
	}
}
