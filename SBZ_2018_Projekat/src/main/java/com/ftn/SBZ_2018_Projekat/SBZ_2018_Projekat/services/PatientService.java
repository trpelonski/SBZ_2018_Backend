package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;

public interface PatientService {

	public List<Patient> getAllPatients();
	public Patient findByStringId(String stringId);
	public Patient insertPatient(Patient patient);
	public Page<Patient> getAllPatients(Pageable pageable);
	public Long countAllPatients();
	public Page<Patient> getAllPatientsByFirstNameAndLastName(String name, Pageable pageable);
	public Long countPatientsByFirstNameAndLastName(String name);
	public Patient updatePatient(Patient patient);
	public void deletePatient(Long id);
	public Patient findById(Long id);
	
}
