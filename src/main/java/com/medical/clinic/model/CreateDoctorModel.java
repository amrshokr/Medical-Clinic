package com.medical.clinic.model;

public class CreateDoctorModel {

	private String DoctorName;
	private String doctorPhone;
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public CreateDoctorModel(String doctorName, String doctorPhone) {
		super();
		DoctorName = doctorName;
		this.doctorPhone = doctorPhone;
	}
	public CreateDoctorModel() {
		super();
	}
	
	
}
