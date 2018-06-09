package com.vince7839.dao;

import java.util.List;

import com.vince7839.entity.Project;

public interface IProjectDao {
	void save(Project p);
	void delete(Project p);
	void update(Project p);
	Project get(int id);
	List<Project> all();
}
