package com.vince7839.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.AttributeConverter;

public class DateConverter implements AttributeConverter<Date,String> {

	@Override
	public String convertToDatabaseColumn(Date date) {
		// TODO Auto-generated method stub
		System.out.println("[DateConverter]convert to string");
		if(date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = format.format(date);
			System.out.println(str);
			return str;
		}
		return null;
	}

	@Override
	public Date convertToEntityAttribute(String str) {
		// TODO Auto-generated method stub
		System.out.println("convert to date");
		if(str != null) {
			System.out.println("strinf from db:" + str);
			Date date = Date.valueOf(str);
			return date;
		}
		return null;
	}

}
