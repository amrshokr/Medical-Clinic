package com.medical.clinic.repositry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.medical.clinic.model.Doctor;

public interface DoctorRepositry extends CrudRepository<Doctor, BigDecimal>{
	
	List<Doctor> findAll();
	

}
