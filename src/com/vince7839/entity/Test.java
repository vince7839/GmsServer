package com.vince7839.entity;

public class Test {
	private Integer id;
	private String name;

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
	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
}
