package com.vince7839.entity;

public class Test {
	public static final Integer CTS = 0x1;
	public static final Integer GTS = 0x10;
	public static final Integer VTS = 0x100;
	public static final Integer CTSV = 0x1000;
	public static final Integer PERFORMANCE = 0x10000;
	Integer id;
	String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
