package com.medical.clinic.repositry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medical.clinic.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, BigDecimal>{
	
	@Query(value = "SELECT * FROM MEDICAL_PATIENT WHERE LOWER(PATIENT_NAME)=?1",nativeQuery = true)
	public Patient getByPatiantName(String patientName);
	
	public List<Patient> findAll();
	

}
