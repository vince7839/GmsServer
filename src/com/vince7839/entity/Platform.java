package com.vince7839.entity;

import java.util.Set;

public class Platform {
	Integer id;
	String name;
	Set<Project> projects;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + ", projects size:" + projects.size() + "]";
	}
}
