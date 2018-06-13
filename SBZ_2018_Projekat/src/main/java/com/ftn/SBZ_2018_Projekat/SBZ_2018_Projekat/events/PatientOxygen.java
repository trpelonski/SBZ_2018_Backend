package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.io.Serializable;

public class PatientOxygen implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long patientId;
	
	private double level;

	public PatientOxygen() {}
	
	public PatientOxygen(Long patientId, double level) {
		super();
		this.patientId = patientId;
		this.level = level;
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

	@Override
	public String toString() {
		return "PatientOxygen [patientId=" + patientId + ", level=" + level + "]";
	}
}
