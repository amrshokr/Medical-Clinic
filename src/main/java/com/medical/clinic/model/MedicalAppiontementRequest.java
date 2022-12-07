package com.medical.clinic.model;

import java.math.BigDecimal;

public class MedicalAppiontementRequest {

	private BigDecimal patientId;
	private BigDecimal doctorId;
	private String appiontementDate;
	public BigDecimal getPatientId() {
		return patientId;
	}
	public void setPatientId(BigDecimal patientId) {
		this.patientId = patientId;
	}
	public BigDecimal getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(BigDecimal doctorId) {
		this.doctorId = doctorId;
	}
	public String getAppiontementDate() {
		return appiontementDate;
	}
	public void setAppiontementDate(String appiontementDate) {
		this.appiontementDate = appiontementDate;
	}
	public MedicalAppiontementRequest(BigDecimal patientId, BigDecimal doctorId, String appiontementDate) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appiontementDate = appiontementDate;
	}
	public MedicalAppiontementRequest() {
		super();
	}
	
	
	
}
