package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.io.Serializable;

import org.kie.api.definition.type.PropertyReactive;

public class PatientOxygen implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long patientId;
	
	private String patientStringId;
	
	private double level;

	public PatientOxygen() {}
	
	public PatientOxygen(Long patientId, double level, String patientStringId) {
		super();
		this.patientId = patientId;
		this.level = level;
		this.patientStringId = patientStringId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public String getPatientStringId() {
		return patientStringId;
	}

	public void setPatientStringId(String patientStringId) {
		this.patientStringId = patientStringId;
	}

	@Override
	public String toString() {
		return "PatientOxygen [patientId=" + patientId + ", patientStringId=" + patientStringId + ", level=" + level
				+ "]";
	}
}
