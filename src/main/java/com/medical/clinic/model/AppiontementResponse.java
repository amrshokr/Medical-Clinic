package com.medical.clinic.model;

import java.math.BigDecimal;
import java.util.Date;

public class AppiontementResponse {

	private BigDecimal appiontementId;
	private String doctorName;
	private String patientName;
	private Date appiontementDate;
	
	public BigDecimal getAppiontementId() {
		return appiontementId;
	}
	public void setAppiontementId(BigDecimal appiontementId) {
		this.appiontementId = appiontementId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getAppiontementDate() {
		return appiontementDate;
	}
	public void setAppiontementDate(Date appiontementDate) {
		this.appiontementDate = appiontementDate;
	}
	public AppiontementResponse(String doctorName, String patientName, Date appiontementDate) {
		super();
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appiontementDate = appiontementDate;
	}
	public AppiontementResponse() {
		super();
	}
	
	
}
