package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class HeartBeatEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long patientId;
	
	public HeartBeatEvent() {}

	public HeartBeatEvent(Long patientId) {
		super();
		this.patientId = patientId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "HeartBeatEvent [patientId=" + patientId + "]";
	}
}
