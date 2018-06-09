package com.vince7839.service;

import java.util.List;

import com.vince7839.entity.Project;

public interface IProjectService {
	void save(Project p);
	void delete(Project p);
	void update(Project p);
	Project get(int id);
	List<Project> all();
}
