package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto;

import java.util.ArrayList;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;

public class ReportDTO {

	private ArrayList<Patient> allPatients;
	private ArrayList<Patient> selectedPatients;
	
	public ReportDTO(ArrayList<Patient> allPatients) {	
		this.allPatients = allPatients;
		this.selectedPatients = new ArrayList<Patient>();	
	}

	public ArrayList<Patient> getAllPatients() {
		return allPatients;
	}

	public void setAllPatients(ArrayList<Patient> allPatients) {
		this.allPatients = allPatients;
	}

	public ArrayList<Patient> getSelectedPatients() {
		return selectedPatients;
	}

	public void setSelectedPatients(ArrayList<Patient> selectedPatients) {
		this.selectedPatients = selectedPatients;
	}

	public void addSelectedPatient(Patient patient) {
		
		if(!foundSelectedPatient(patient)) {
			this.selectedPatients.add(patient);
		}
			
	}
	
	private boolean foundSelectedPatient(Patient patientToAdd) {
		
		for(Patient patient : this.selectedPatients) {
			if(patient.getId()==patientToAdd.getId()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "ReportDTO [allPatients=" + allPatients + ", selectedPatients=" + selectedPatients + "]";
	}
}
