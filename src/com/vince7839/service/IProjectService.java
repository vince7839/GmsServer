package com.vince7839.service;

import java.util.List;
import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;

public interface IProjectService {
	void save(Project p);
	void delete(Project p);
	void update(Project p);
	Project get(Integer id);
	List<Project> all();
	boolean exists(Integer id);
	boolean isNameExists(String name);
	List<Project> list(Project p);
	List<Project> listByPlatform(Platform p);
}
