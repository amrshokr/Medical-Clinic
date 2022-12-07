package com.medical.clinic.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.clinic.model.AppiontementResponse;
import com.medical.clinic.model.CancellationReasonEnum;
import com.medical.clinic.model.CreateDoctorModel;
import com.medical.clinic.model.CreatePataintModel;
import com.medical.clinic.model.CreationResponse;
import com.medical.clinic.model.Doctor;
import com.medical.clinic.model.MedicalAppiontementRequest;
import com.medical.clinic.model.Patient;
import com.medical.clinic.service.MedicalService;
import com.medical.clinic.util.Constants;

@RestController
@RequestMapping("/clinic")
public class MedicalClinicController {

	@Autowired
	MedicalService medicalService;

	@GetMapping("/getdoctors")
	public ResponseEntity<List<Doctor>> getDoctors() {

		return ResponseEntity.status(HttpStatus.OK).body(medicalService.getDoctorsList());
	}

	@PostMapping("/save-appiontement")
	public ResponseEntity<CreationResponse> saveAppionetment(
			@RequestBody MedicalAppiontementRequest medicalAppiontementModel) {
		CreationResponse appiontementModel = medicalService.createAppiontement(medicalAppiontementModel);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@PostMapping("/cancel-appiontement")
	public ResponseEntity<CreationResponse> cancelAppiontementById(
			@RequestParam(required = true) BigDecimal appiontementId,
			@RequestParam(required = true) CancellationReasonEnum cancelReason) {
		CreationResponse appiontementModel = medicalService.cancelAppiontementById(appiontementId, cancelReason);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.CANCELLED_SUCCESSFULLY)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@PostMapping("/save-doctor")
	public ResponseEntity<CreationResponse> saveDoctor(@RequestBody CreateDoctorModel createDoctorModel) {
		CreationResponse appiontementModel = medicalService.createDoctor(createDoctorModel);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@PostMapping("/save-pataint")
	public ResponseEntity<CreationResponse> savePataint(@RequestBody CreatePataintModel createPataintModel) {
		CreationResponse appiontementModel = medicalService.createPaitant(createPataintModel);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@GetMapping("/getappiontement-today")
	public ResponseEntity<List<AppiontementResponse>> getTodayAppiontement() {
		List<AppiontementResponse> appiontementResponses = medicalService.getAppiontementForToday();
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

	@GetMapping("/pataints")
	public ResponseEntity<List<Patient>> getAllPataint() {
		List<Patient> patients = medicalService.getAllPataint();
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}

	@GetMapping("/getappiontement/name-history")
	public ResponseEntity<List<AppiontementResponse>> getAppiontementTodayByPatientNameOrHistory(
			@RequestParam(required = true) String patainName, @RequestParam(required = true) Boolean history) {
		List<AppiontementResponse> appiontementResponses = medicalService.getAppiontementTodayByPatientNameOrHistory(patainName, history);
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}
	
	@GetMapping("/getappiontement/date")
	public ResponseEntity<List<AppiontementResponse>> getAppiontementHistoryByDate(String strDate) throws ParseException {
		List<AppiontementResponse> appiontementResponses = medicalService.getAppiontementHistoryByDate(strDate);
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

}
