package com.medical.clinic.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.clinic.exception.MedicalException;
import com.medical.clinic.model.CreationResponse;
import com.medical.clinic.model.AppiontementResponse;
import com.medical.clinic.model.CancelAppiontementModel;
import com.medical.clinic.model.CancellationReasonEnum;
import com.medical.clinic.model.CreateDoctorModel;
import com.medical.clinic.model.CreatePataintModel;
import com.medical.clinic.model.Doctor;
import com.medical.clinic.model.MedicalAppiontement;
import com.medical.clinic.model.MedicalAppiontementRequest;
import com.medical.clinic.model.Patient;
import com.medical.clinic.repositry.DoctorRepositry;
import com.medical.clinic.repositry.MedicalAppiontementReposirty;
import com.medical.clinic.repositry.PatientRepository;
import com.medical.clinic.util.Constants;

@Service
public class MedicalService {

	@Autowired
	DoctorRepositry doctorRepositry;

	@Autowired
	MedicalAppiontementReposirty appiontementReposirty;

	@Autowired
	PatientRepository patientRepository;

	public List<Doctor> getDoctorsList() {
		return doctorRepositry.findAll();
	}

	public CreationResponse createDoctor(CreateDoctorModel createDoctorModel) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			Doctor doctor = new Doctor();
			doctor.setDoctorName(createDoctorModel.getDoctorName());
			doctor.setDoctorPhone(createDoctorModel.getDoctorPhone());
			doctorRepositry.save(doctor);
			appiontementModel.setId(doctor.getDoctorId());
			appiontementModel.setStatus(Constants.SUCCESS);

		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;
	}

	public CreationResponse createPaitant(CreatePataintModel createPataintModel) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			Patient patient = new Patient();
			patient.setPatientName(createPataintModel.getPataintName());
			patient.setPatientPhone(createPataintModel.getPataintPhone());
			patientRepository.save(patient);
			appiontementModel.setId(patient.getPatientId());
			appiontementModel.setStatus(Constants.SUCCESS);

		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;

	}

	public CreationResponse createAppiontement(MedicalAppiontementRequest appiontementRequest) {
		CreationResponse appiontementModel = new CreationResponse();
		try {

			MedicalAppiontement appiontement = new MedicalAppiontement();
			Patient patient = new Patient();
			patient.setPatientId(appiontementRequest.getPatientId());
			appiontement.setPatient(patient);
			Doctor doctor = new Doctor();
			doctor.setDoctorId(appiontementRequest.getDoctorId());
			appiontement.setDoctor(doctor);
			appiontement.setIsActive(Constants.YES);
			if (appiontementRequest.getAppiontementDate() != null) {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(appiontementRequest.getAppiontementDate());
				Date todayDate = new SimpleDateFormat("dd/MM/yyyy").parse(appiontementRequest.getAppiontementDate());
				if (date.before(todayDate)) {
					throw new MedicalException("Can Not Accept Pervious Date");
				}
				appiontement.setAppiontementDate(date);
			}
			appiontementReposirty.save(appiontement);
			appiontementModel.setId(appiontement.getAppiontementId());
			appiontementModel.setStatus(Constants.SUCCESS);

		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;
	}

	public CreationResponse cancelAppiontementById(BigDecimal appiontementId, CancellationReasonEnum cancelReason) {
		CreationResponse appiontementModel = new CreationResponse();
		MedicalAppiontement medicalAppiontement = appiontementReposirty.findByAppiontementId(appiontementId);
		if (medicalAppiontement != null) {
			medicalAppiontement.setReason(cancelReason.name());
			medicalAppiontement.setIsActive(Constants.NO);
			appiontementReposirty.save(medicalAppiontement);
			appiontementModel.setId(medicalAppiontement.getAppiontementId());
			appiontementModel.setStatus(Constants.CANCELLED_SUCCESSFULLY);
		} else {
			throw new MedicalException("No Record Found!");
		}
		return appiontementModel;
	}

	public List<AppiontementResponse> getAppiontementForToday() {
		List<AppiontementResponse> appiontementResponsList = new ArrayList<>();
		List<MedicalAppiontement> appiontements = appiontementReposirty.getAppiontementForToday();
		if (appiontements != null & !appiontements.isEmpty()) {
			appiontements.forEach(k -> {
				AppiontementResponse appiontementResponse = new AppiontementResponse();
				appiontementResponse.setAppiontementId(k.getAppiontementId());
				appiontementResponse.setAppiontementDate(k.getAppiontementDate());
				appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
				appiontementResponse.setPatientName(k.getPatient().getPatientName());
				appiontementResponsList.add(appiontementResponse);
			});
		} else {
			throw new MedicalException("No Record Found!");
		}
		return appiontementResponsList;
	}

	public List<Patient> getAllPataint() {
		List<Patient> patients = patientRepository.findAll();
		return patients;
	}

	public List<AppiontementResponse> getAppiontementTodayByPatientNameOrHistory(String patainName, Boolean history) {
		List<AppiontementResponse> appiontementResponses = new ArrayList<>();
		Patient patient = patientRepository.getByPatiantName(patainName.toLowerCase().trim());
		if (patient != null) {
			List<MedicalAppiontement> medicalAppiontementReposirties = new ArrayList<>();
			if (history) {
				medicalAppiontementReposirties = appiontementReposirty.findByPatient(patient);
			} else {
				medicalAppiontementReposirties = appiontementReposirty.findByPatiantForToday(patient.getPatientId());
			}

			if (medicalAppiontementReposirties != null && !medicalAppiontementReposirties.isEmpty()) {
				medicalAppiontementReposirties.forEach(k -> {
					AppiontementResponse appiontementResponse = new AppiontementResponse();
					appiontementResponse.setAppiontementId(k.getAppiontementId());
					appiontementResponse.setAppiontementDate(k.getAppiontementDate());
					appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
					appiontementResponse.setPatientName(k.getPatient().getPatientName());
					appiontementResponses.add(appiontementResponse);
				});
			} else {
				throw new MedicalException("No Patiant Found");
			}
		} else {
			throw new MedicalException("No Appiontement Found!");
		}
		return appiontementResponses;

	}

	public List<AppiontementResponse> getAppiontementHistoryByDate(String strDate) throws ParseException {
		List<AppiontementResponse> appiontementResponses = new ArrayList<>();
		List<MedicalAppiontement> medicalAppiontementReposirties = new ArrayList<>();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		medicalAppiontementReposirties = appiontementReposirty.getAppiontementByDate(date);

		if (medicalAppiontementReposirties != null && !medicalAppiontementReposirties.isEmpty()) {
			medicalAppiontementReposirties.forEach(k -> {
				AppiontementResponse appiontementResponse = new AppiontementResponse();
				appiontementResponse.setAppiontementId(k.getAppiontementId());
				appiontementResponse.setAppiontementDate(k.getAppiontementDate());
				appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
				appiontementResponse.setPatientName(k.getPatient().getPatientName());
				appiontementResponses.add(appiontementResponse);
			});

		} else {
			throw new MedicalException("No Appiontement Found!");
		}
		return appiontementResponses;

	}

}
