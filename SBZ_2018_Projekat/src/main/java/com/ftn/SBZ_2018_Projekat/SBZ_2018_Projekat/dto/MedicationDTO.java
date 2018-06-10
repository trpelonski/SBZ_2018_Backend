package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicationDTO {

	private Set<Antibiotic> antibiotics;
	private Set<Antibiotic> allergicTo;
	private Patient patient;
	
	public MedicationDTO() {}

	public MedicationDTO(Set<Antibiotic> antibiotics, Set<Antibiotic> allergicTo, Patient patient) {
		super();
		this.antibiotics = antibiotics;
		this.allergicTo = allergicTo;
		this.patient = patient;
	}

	public Set<Antibiotic> getAntibiotics() {
		return antibiotics;
	}

	public void setAntibiotics(Set<Antibiotic> antibiotics) {
		this.antibiotics = antibiotics;
	}

	public Set<Antibiotic> getAllergicTo() {
		return allergicTo;
	}

	public void setAllergicTo(Set<Antibiotic> allergicTo) {
		this.allergicTo = allergicTo;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "MedicationDTO [antibiotics=" + antibiotics + ", allergicTo=" + allergicTo + ", patient=" + patient
				+ "]";
	}
}
