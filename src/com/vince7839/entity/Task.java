package com.vince7839.entity;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {
	Integer id;
	Integer projectId;
	Status status;
	Integer tag;
	Integer bugId;
	SoftwareType softwareType;
	String summary;
	Date startDate;
	Date expectDate;
	Date endDate;
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="expect_date")
	public Date getExpectDate() {
		return expectDate;
	}
	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="project_id")
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	@Column(name="software_type")
	@Convert(converter=com.vince7839.util.SwTypeConverter.class)
	public SoftwareType getSoftwareType() {
		return softwareType;
	}
	public void setSoftwareType(SoftwareType softwareType) {
		this.softwareType = softwareType;
	}
	@Column(name="tag")
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	@Column(name="bug_id")
	public Integer getBugId() {
		return bugId;
	}
	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}
	@Column(name="status")
	@Convert(converter=com.vince7839.util.StatusConverter.class)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", projectId=" + projectId + ", status=" + status + ", tag=" + tag + ", bugId="
				+ bugId + ", softwareType=" + softwareType + ", summary=" + summary + ", startDate=" + startDate
				+ ", expectDate=" + expectDate + ", endDate=" + endDate + "]";
	}
}
