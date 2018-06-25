package com.vince7839.entity;

import java.sql.Date;

public class Job {
	Integer id;
	Integer taskId;
	Integer testId;
	Status status;
	String tester;
	String toolVersion;
	Date startDate;
	Date endDate;
	String summary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public String getToolVersion() {
		return toolVersion;
	}
	public void setToolVersion(String toolVersion) {
		this.toolVersion = toolVersion;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", taskId=" + taskId + ", testId=" + testId + ", status=" + status + ", tester="
				+ tester + ", toolVersion=" + toolVersion + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", summary=" + summary + "]";
	}
}
