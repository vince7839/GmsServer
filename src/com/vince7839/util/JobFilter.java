package com.vince7839.util;

import com.vince7839.entity.Platform;
import com.vince7839.entity.Project;
import com.vince7839.entity.Test;

public class JobFilter {
	Platform platform;
	Project project;
	Test test;
	public JobFilter(Platform platform,Project project,Test test) {
		this.platform =platform;
		this.project = project;
		this.test = test;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
}
