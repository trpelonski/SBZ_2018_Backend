package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class UrinationEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long patientId;
	private double amount;
	
	public UrinationEvent() {}
	
	public UrinationEvent(Long patientId, double amount) {
		super();
		this.patientId = patientId;
		this.amount = amount;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UrinationEvent [patientId=" + patientId + ", amount=" + amount + "]";
	}	
}
