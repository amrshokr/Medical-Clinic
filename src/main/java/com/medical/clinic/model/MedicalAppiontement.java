package com.medical.clinic.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MEDICAL_APPIONTEMENT")
public class MedicalAppiontement {
	
	private BigDecimal appiontementId;
	private Patient patient;
	private Doctor doctor;
	private Date appiontementDate;
	private String isActive;
	private String reason;
	
	@Id
	@Column(name="APPIONTEMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public BigDecimal getAppiontementId() {
		return appiontementId;
	}
	public void setAppiontementId(BigDecimal appiontementId) {
		this.appiontementId = appiontementId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DOCTOR_ID")
	public Doctor getDoctor() {
		return doctor;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PATIENT_ID")
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Column(name="APPIONTEMENT_DATE")
	public Date getAppiontementDate() {
		return appiontementDate;
	}
	public void setAppiontementDate(Date appiontementDate) {
		this.appiontementDate = appiontementDate;
	}
	@Column(name="ISACTIVE")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="REASON")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public MedicalAppiontement(BigDecimal appiontementId, Patient patient, Doctor doctor, Date appiontementDate,
			String isActive, String reason) {
		super();
		this.appiontementId = appiontementId;
		this.patient = patient;
		this.doctor = doctor;
		this.appiontementDate = appiontementDate;
		this.isActive = isActive;
		this.reason = reason;
	}
	public MedicalAppiontement() {
		super();
	}
	
	

}
