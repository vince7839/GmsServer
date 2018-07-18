package com.vince7839.entity;

import javax.persistence.*;

@Entity
@Table(name="project")
public class Project {
	Integer id;
	String name;
	String spl;
	Platform platform;
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="spl")
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="platform_id")
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", spl=" + spl + ", platform=" + platform + "]";
	}
}
