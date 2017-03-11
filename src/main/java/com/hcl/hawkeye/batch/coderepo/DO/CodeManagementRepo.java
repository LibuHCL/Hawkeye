/**
 * 
 */
package com.hcl.hawkeye.batch.coderepo.DO;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Lenovo
 *
 */
public class CodeManagementRepo {
	private static final long serialVersionUID = 1L;
	private static long projectConfId;
	private int id;
	private String author_name;
	private String author_email;
	private Timestamp date;
	/*private String date;*/
	private String branchName;
	private long projectId;
	public static long getProjectConfId() {
		return projectConfId;
	}
	public static void setProjectConfId(long projectConfId) {
		CodeManagementRepo.projectConfId = projectConfId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_email() {
		return author_email;
	}
	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/*public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}*/
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
