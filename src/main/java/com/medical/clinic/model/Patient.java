package com.medical.clinic.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEDICAL_PATIENT")
public class Patient {
	
	private BigDecimal patientId;
	private String patientName;
	private String patientPhone;
	private Date createdDate;
	
	@Id
	@Column(name="PATIENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public BigDecimal getPatientId() {
		return patientId;
	}
	public void setPatientId(BigDecimal patientId) {
		this.patientId = patientId;
	}
	@Column(name="PATIENT_NAME")
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	@Column(name="PATIENT_PHONE")
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	

}
