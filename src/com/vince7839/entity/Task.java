package com.vince7839.entity;

import java.sql.Date;

public class Task {
	Integer id;
	Integer projectId;
	Integer status;
	Integer tag;
	Integer softwareType;
	String summary;
	Date startDate;
	Date expectDate;
	Date endDate;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public Date getExpectDate() {
		return expectDate;
	}
	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSoftwareType() {
		return softwareType;
	}
	public void setSoftwareType(Integer softwareType) {
		this.softwareType = softwareType;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", projectId=" + projectId + ", status=" + status + ", tag=" + tag + ", softwareType="
				+ softwareType + ", summary=" + summary + ", startDate=" + startDate + ", expectDate=" + expectDate
				+ ", endDate=" + endDate + "]";
	}
	
}
