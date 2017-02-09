/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DO;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author HCL
 *
 */
public class TeamHappiness {

	private BigInteger id;
	private Integer projectId;
	private String email;
	private Integer scale;
	private String description;
	private Timestamp captureDate;

	public TeamHappiness() {

	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Timestamp captureDate) {
		this.captureDate = captureDate;
	}
	

	public TeamHappiness(BigInteger id, Integer projectId, String email, Integer scale, String description,
			Timestamp captureDate) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.email = email;
		this.scale = scale;
		this.description = description;
		this.captureDate = captureDate;
	}

	

}
