package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("20m")
public class OxygenLevelDroppingEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PatientOxygen patientOxygen;
	
	private double amount;

	public OxygenLevelDroppingEvent() {}
	
	public OxygenLevelDroppingEvent(PatientOxygen patientOxygen, double amount) {
		super();
		this.patientOxygen = patientOxygen;
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PatientOxygen getPatientOxygen() {
		return patientOxygen;
	}

	public void setPatientOxygen(PatientOxygen patientOxygen) {
		this.patientOxygen = patientOxygen;
	}

	@Override
	public String toString() {
		return "OxygenLevelDroppingEvent [patientOxygen=" + patientOxygen + ", amount=" + amount + "]";
	}
}
