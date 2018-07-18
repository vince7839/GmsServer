package com.vince7839.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="job")
public class Job {
	Integer id;
	Status status;
	String tester;
	String toolVersion;
	Date startDate;
	Date endDate;
	String summary;
	Task task;
	Test test;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="tester")
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	@Column(name="tool_version")
	public String getToolVersion() {
		return toolVersion;
	}
	public void setToolVersion(String toolVersion) {
		this.toolVersion = toolVersion;
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
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="status")
	@Convert(converter=com.vince7839.util.StatusConverter.class)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name="task_id")
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@ManyToOne
	@JoinColumn(name="test_id")
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", status=" + status + ", tester=" + tester + ", toolVersion=" + toolVersion
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", summary=" + summary + ", task=" + task
				+ ", test=" + test + "]";
	}
}
