package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=true, unique = true, length=13)
	private String stringId;
	
	@Column(nullable=false, length=90)
	private String firstname;
	
	@Column(nullable=false, length=90)
	private String lastname;
	
	@ManyToMany
	private Set<Antibiotic> allergicToAntibiotic;
	
	@ManyToMany
	private Set<Substance> allergicToSubstance;
	
	@OneToMany(mappedBy="patient", fetch=FetchType.EAGER)
	private Set<Diagnostic> diagnostics;
	
	public Patient() {}

	public Patient(Long id, String stringId, String firstname, String lastname, Set<Antibiotic> allergicToAntibiotic,
			Set<Substance> allergicToSubstance, Set<Diagnostic> diagnostics) {
		super();
		this.id = id;
		this.stringId = stringId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.allergicToAntibiotic = allergicToAntibiotic;
		this.allergicToSubstance = allergicToSubstance;
		this.diagnostics = diagnostics;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Antibiotic> getAllergicToAntibiotic() {
		return allergicToAntibiotic;
	}

	public void setAllergicToAntibiotic(Set<Antibiotic> allergicToAntibiotic) {
		this.allergicToAntibiotic = allergicToAntibiotic;
	}

	public Set<Substance> getAllergicToSubstance() {
		return allergicToSubstance;
	}

	public void setAllergicToSubstance(Set<Substance> allergicToSubstance) {
		this.allergicToSubstance = allergicToSubstance;
	}
	
	public Set<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(Set<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", stringId=" + stringId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", allergicToAntibiotic=" + allergicToAntibiotic + ", allergicToSubstance=" + allergicToSubstance
				+ ", diagnostics=" + diagnostics + "]";
	}
}
