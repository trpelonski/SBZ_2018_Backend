package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Symptom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=150)
	private String name;
	
	@Column(nullable=false)
	private Boolean toShow;
	
	@Column(nullable=false, unique=true, length=30)
	private String codename;
	
	@OneToMany(mappedBy="symptom", fetch=FetchType.EAGER, cascade = {CascadeType.REMOVE})
	@JsonBackReference(value="diseaseSymptoms-info")
	private Set<DiseaseSymptom> diseaseSymptoms;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, targetEntity=Diagnostic.class)
	@JoinTable(
	        name = "diagnostic_symptoms", 
	        inverseJoinColumns = { @JoinColumn(name = "diagnostic_id") }, 
	        joinColumns = { @JoinColumn(name = "symptoms_id") }
	    )
	@JsonBackReference(value="diagnostics-info")
	private Set<Diagnostic> diagnostics;
	
	public Symptom() {}

	public Symptom(Long id, String name, Boolean toShow, String codename) {
		super();
		this.id = id;
		this.name = name;
		this.toShow = toShow;
		this.codename = codename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getToShow() {
		return toShow;
	}

	public void setToShow(Boolean toShow) {
		this.toShow = toShow;
	}

	public Set<DiseaseSymptom> getDiseaseSymptoms() {
		return diseaseSymptoms;
	}

	public void setDiseaseSymptoms(Set<DiseaseSymptom> diseaseSymptoms) {
		this.diseaseSymptoms = diseaseSymptoms;
	}
	
	public Set<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(Set<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}
	
	public String getCodeName() {
		return codename;
	}

	public void setCodeName(String codename) {
		this.codename = codename;
	}

	@Override
	public String toString() {
		return "Symptom [id=" + id + ", name=" + name + "]";
	}
}
