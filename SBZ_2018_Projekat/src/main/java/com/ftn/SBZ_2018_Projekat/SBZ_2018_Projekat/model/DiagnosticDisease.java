package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DiagnosticDisease implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional=false)
	@JsonBackReference
	private Diagnostic diagnostic;
	
	@ManyToOne(optional=false)
	private Disease disease;
	
	@ManyToMany
	private Set<Antibiotic> medications;
	
	public DiagnosticDisease() {}

	public DiagnosticDisease(Long id, Diagnostic diagnostic, Disease disease, Set<Antibiotic> medications) {
		super();
		this.id = id;
		this.diagnostic = diagnostic;
		this.disease = disease;
		this.medications = medications;
	}

	public DiagnosticDisease(Disease disease) {
		super();
		this.disease = disease;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
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

	@Override
	public String toString() {
		return "DiagnosticDisease [id=" + id + ", diagnostic=" + diagnostic + ", disease=" + disease + ", medications="
				+ medications + "]";
	}
}
