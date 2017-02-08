package com.hcl.hawkeye.resourcemanagement.DO;

import java.io.Serializable;

public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resourceID;
	private String projectID;
	private String firstName;
	private String lastName;
	private String employeeID;
	private String companyID;
	private String clientID;
	private String email;
	private String phoneNumber;
	private String role;
	private String workLocation;
	private String projectJoiningDate;
	private String plannedReleaseDate;
	private String exitDate;
	private String resourceStatus;
	private String ingAgreement;
	
	public String getResourceID() {
		return resourceID;
	}
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public String getProjectJoiningDate() {
		return projectJoiningDate;
	}
	public void setProjectJoiningDate(String projectJoiningDate) {
		this.projectJoiningDate = projectJoiningDate;
	}
	public String getPlannedReleaseDate() {
		return plannedReleaseDate;
	}
	public void setPlannedReleaseDate(String plannedReleaseDate) {
		this.plannedReleaseDate = plannedReleaseDate;
	}
	public String getExitDate() {
		return exitDate;
	}
	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}
	public String getResourceStatus() {
		return resourceStatus;
	}
	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	public String getIngAgreement() {
		return ingAgreement;
	}
	public void setIngAgreement(String ingAgreement) {
		this.ingAgreement = ingAgreement;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientID == null) ? 0 : clientID.hashCode());
		result = prime * result + ((companyID == null) ? 0 : companyID.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
		result = prime * result + ((exitDate == null) ? 0 : exitDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((ingAgreement == null) ? 0 : ingAgreement.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((plannedReleaseDate == null) ? 0 : plannedReleaseDate.hashCode());
		result = prime * result + ((projectID == null) ? 0 : projectID.hashCode());
		result = prime * result + ((projectJoiningDate == null) ? 0 : projectJoiningDate.hashCode());
		result = prime * result + ((resourceID == null) ? 0 : resourceID.hashCode());
		result = prime * result + ((resourceStatus == null) ? 0 : resourceStatus.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((workLocation == null) ? 0 : workLocation.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		if (companyID == null) {
			if (other.companyID != null)
				return false;
		} else if (!companyID.equals(other.companyID))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		if (exitDate == null) {
			if (other.exitDate != null)
				return false;
		} else if (!exitDate.equals(other.exitDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (ingAgreement == null) {
			if (other.ingAgreement != null)
				return false;
		} else if (!ingAgreement.equals(other.ingAgreement))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (plannedReleaseDate == null) {
			if (other.plannedReleaseDate != null)
				return false;
		} else if (!plannedReleaseDate.equals(other.plannedReleaseDate))
			return false;
		if (projectID == null) {
			if (other.projectID != null)
				return false;
		} else if (!projectID.equals(other.projectID))
			return false;
		if (projectJoiningDate == null) {
			if (other.projectJoiningDate != null)
				return false;
		} else if (!projectJoiningDate.equals(other.projectJoiningDate))
			return false;
		if (resourceID == null) {
			if (other.resourceID != null)
				return false;
		} else if (!resourceID.equals(other.resourceID))
			return false;
		if (resourceStatus == null) {
			if (other.resourceStatus != null)
				return false;
		} else if (!resourceStatus.equals(other.resourceStatus))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (workLocation == null) {
			if (other.workLocation != null)
				return false;
		} else if (!workLocation.equals(other.workLocation))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Resource [resourceID=" + resourceID + ", projectID=" + projectID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", employeeID=" + employeeID + ", companyID=" + companyID + ", clientID="
				+ clientID + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role + ", workLocation="
				+ workLocation + ", projectJoiningDate=" + projectJoiningDate + ", plannedReleaseDate="
				+ plannedReleaseDate + ", exitDate=" + exitDate + ", resourceStatus=" + resourceStatus
				+ ", ingAgreement=" + ingAgreement + "]";
	}
	
	
}
