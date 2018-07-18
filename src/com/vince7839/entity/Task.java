package com.vince7839.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name="task")
public class Task {
	Integer id;
	Status status;
	Integer tag;
	Integer bugId;
	SoftwareType softwareType;
	String summary;
	Date startDate; //实际开始日期
	Date endDate; //实际完成日期
	Date orderDate;//建立预约的时间
	Date expectStartDate; //期望开始时间
	Date expectEndDate; //期望完成时间
	Date scheduleDate; //预计安排的时间
	Project project;
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="start_date")
	@Type(type="timestamp")
	//@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="end_date")
	@Type(type="timestamp")
//	@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@ManyToOne
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Column(name="order_date")
	@Type(type="timestamp")
	//@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name="expect_start_date")
	@Type(type="timestamp")
//	@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getExpectStartDate() {
		return expectStartDate;
	}
	public void setExpectStartDate(Date expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	@Column(name="expect_end_date")
	@Type(type="timestamp")
//	@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getExpectEndDate() {
		return expectEndDate;
	}
	public void setExpectEndDate(Date expectEndDate) {
		this.expectEndDate = expectEndDate;
	}
	
	@Column(name="schedule_date")
	@Type(type="timestamp")
//	@Convert(converter=com.vince7839.util.DateConverter.class)
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", status=" + status + ", tag=" + tag + ", bugId=" + bugId + ", softwareType="
				+ softwareType + ", summary=" + summary + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", orderDate=" + orderDate + ", expectStartDate=" + expectStartDate + ", expectEndDate="
				+ expectEndDate + ", scheduleDate=" + scheduleDate + ", project=" + project + "]";
	}
}
