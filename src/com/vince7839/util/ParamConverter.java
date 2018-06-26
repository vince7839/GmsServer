package com.vince7839.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.vince7839.entity.SoftwareType;
import com.vince7839.entity.Status;

public class ParamConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Object value, Class toType) {
		// TODO Auto-generated method stub
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
			Status status = new StatusConverter().convertToEntityAttribute(str);
			System.out.println("convert to status:" + status);
			if (status != null && status != Status.Unknown)   //从数据库查询结果允许转为unknown确保容错性，提交的参数不允许为unknown
				return status;
		} else if (toType == SoftwareType.class) {			
			SoftwareType type = new SwTypeConverter().convertToEntityAttribute(str);
			System.out.println("conver to SwType:" + type);
			if(type !=null && type != SoftwareType.Unknown) //同上，Unkown属性只为处理数据库中的非法值，不允许从参数提供
				return type;
		}
		return super.convertValue(value, toType);
	}
}
