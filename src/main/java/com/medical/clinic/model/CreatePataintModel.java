package com.medical.clinic.model;

public class CreatePataintModel {
	String pataintName;
	String pataintPhone;
	public String getPataintName() {
		return pataintName;
	}
	public void setPataintName(String pataintName) {
		this.pataintName = pataintName;
	}
	public String getPataintPhone() {
		return pataintPhone;
	}
	public void setPataintPhone(String pataintPhone) {
		this.pataintPhone = pataintPhone;
	}
	public CreatePataintModel(String pataintName, String pataintPhone) {
		super();
		this.pataintName = pataintName;
		this.pataintPhone = pataintPhone;
	}
	public CreatePataintModel() {
		super();
	}
	
	
	
	
	
	
}
