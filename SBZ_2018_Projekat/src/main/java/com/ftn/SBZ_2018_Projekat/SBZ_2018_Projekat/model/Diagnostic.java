package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.drools.core.factmodel.traits.Traitable;
import org.kie.api.definition.type.PropertyReactive;

import com.fasterxml.jackson.annotation.JsonBackReference;

@PropertyReactive
@Traitable
@Entity
public class Diagnostic implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private Date date;
	
	@ManyToOne(optional=false)
	@JsonBackReference
	private Patient patient;
	
	@ManyToOne(optional=false)
	private Disease disease;
	
	@ManyToMany
	private Set<Antibiotic> medications;
	
	@ManyToMany
	private Set<Symptom> symptoms;
	
	@ManyToOne(optional=false)
	private User doctor;
	
	public Diagnostic() {}

	public Diagnostic(Long id, Date date, Patient patient, Disease disease, Set<Antibiotic> medications, User doctor, Set<Symptom> symptoms) {
		super();
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.disease = disease;
		this.medications = medications;
		this.doctor = doctor;
		this.symptoms = symptoms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Set<Antibiotic> getMedications() {
		return medications;
	}

	public void setMedications(Set<Antibiotic> medications) {
		this.medications = medications;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Set<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Set<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public String toString() {
		return "Diagnostic [id=" + id + ", date=" + date + ", patient=" + patient + ", disease=" + disease
				+ ", medications=" + medications + ", symptoms=" + symptoms + ", doctor=" + doctor + "]";
	}
}
