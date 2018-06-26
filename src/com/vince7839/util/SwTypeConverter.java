package com.vince7839.util;

import javax.persistence.AttributeConverter;
import com.vince7839.entity.SoftwareType;

public class SwTypeConverter implements AttributeConverter<SoftwareType,String> {

	@Override
	public String convertToDatabaseColumn(SoftwareType type) {
		// TODO Auto-generated method stub
		return type == null ? null : type.toString();
	}

	@Override
	public SoftwareType convertToEntityAttribute(String column) {
		// TODO Auto-generated method stub
		SoftwareType type = null;
		if("MP".equalsIgnoreCase(column)) {
			type = SoftwareType.MP;
		} else if("MR".equalsIgnoreCase(column)) {
			type = SoftwareType.MR;
		} else if("SMP".equalsIgnoreCase(column)) {
			type = SoftwareType.SMP;
		} else {
			type = SoftwareType.Unknown;
		}
		return type;
	}
}
