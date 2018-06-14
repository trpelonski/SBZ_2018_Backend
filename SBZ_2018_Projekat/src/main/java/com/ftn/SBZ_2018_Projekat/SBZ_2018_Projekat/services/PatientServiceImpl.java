package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	@Override
	public Patient findByStringId(String stringId) {
		return patientRepository.findOneByStringId(stringId);
	}

	@Override
	public Patient insertPatient(Patient patient) {
	
		patient = patientRepository.save(patient);
		patient.setStringId(createStringId(patient.getId()));
		
		return patientRepository.save(patient);
	}

	@Override
	public Page<Patient> getAllPatients(Pageable pageable) {	
		return patientRepository.findAllByOrderByFirstnameAscLastnameAsc(pageable);
	}
	
	@Override
	public Long countAllPatients() {
		return patientRepository.count();
	}

	@Override
	public Page<Patient> getAllPatientsByFirstNameAndLastName(String name, Pageable pageable) {
		return patientRepository.findByFirstNameAndLastName(name.trim()+"%", pageable);
	}

	@Override
	public Long countPatientsByFirstNameAndLastName(String name) {
		return patientRepository.countByFirstNameAndLastName(name.trim()+"%");
	}
	
	@Override
	public Patient updatePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void deletePatient(Long id) {
		patientRepository.delete(id);	
	}
	
	@Override
	public Patient findById(Long id) {
		return patientRepository.findOne(id);
	}

	private String createStringId(Long id) {
		
		String retVal = "P#";
		String zeros = "";
		
		for(int i=0; i<11-id.toString().length(); i++)
			zeros+="0";
			
		retVal+=zeros+id;
		
		return retVal;
	}

}
