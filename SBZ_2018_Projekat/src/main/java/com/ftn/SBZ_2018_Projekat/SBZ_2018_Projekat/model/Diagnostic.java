package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

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
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, targetEntity=Symptom.class)
	@JoinTable(
	        name = "diagnostic_symptoms", 
	        joinColumns = { @JoinColumn(name = "diagnostic_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "symptoms_id") }
	    )
	private Set<Symptom> symptoms;
	
	@ManyToOne(optional=true)
	private User doctor;
	
	@Column(nullable = true, length=500)
	private String description;
	
	@OneToMany(mappedBy="diagnostic", fetch=FetchType.EAGER)
	private Set<DiagnosticDisease> diagnosticDiseases;
	
	public Diagnostic() {}

	public Diagnostic(Long id, Date date, Patient patient, User doctor, Set<Symptom> symptoms, String description, Set<DiagnosticDisease> diagnosticDiseases) {
		super();
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.doctor = doctor;
		this.symptoms = symptoms;
		this.description = description;
		this.diagnosticDiseases = diagnosticDiseases;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<DiagnosticDisease> getDiagnosticDiseases() {
		return diagnosticDiseases;
	}

	public void setDiagnosticDiseases(Set<DiagnosticDisease> diagnosticDiseases) {
		this.diagnosticDiseases = diagnosticDiseases;
	}

	@Override
	public String toString() {
		return "Diagnostic [id=" + id + ", date=" + date + ", patient=" + patient + ", symptoms=" + symptoms
				+ ", doctor=" + doctor + ", description=" + description + ", diagnosticDiseases=" + diagnosticDiseases
				+ "]";
	}
}
