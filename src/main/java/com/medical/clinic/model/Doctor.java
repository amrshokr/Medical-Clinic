package com.medical.clinic.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEDICAL_DOCTOR")
public class Doctor {
	
	private BigDecimal doctorId;
	private String doctorName;
	private String doctorPhone;
	
	@Id
	@Column(name="DOCTOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public BigDecimal getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(BigDecimal doctorId) {
		this.doctorId = doctorId;
	}
	@Column(name="DOCTOR_NAME")
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@Column(name="DOCTOR_PHONE")
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	
	

}
