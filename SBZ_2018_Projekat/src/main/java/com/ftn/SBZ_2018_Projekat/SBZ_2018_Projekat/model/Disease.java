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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Disease implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=90)
	private String name;
	
	@OneToMany(mappedBy="disease", fetch=FetchType.EAGER,cascade = {CascadeType.REMOVE})
	private Set<DiseaseSymptom> diseaseSymptoms;
	
	@OneToMany(mappedBy="disease", fetch=FetchType.EAGER,cascade = {CascadeType.REMOVE})
	@JsonBackReference
	private Set<DiagnosticDisease> diagnosticDiseases;
	
	@Column(nullable=false, unique=true, length=30)
	private String codename;
	
	public Disease() {}

	public Disease(Long id, String name, Set<DiseaseSymptom> diseaseSymptoms, String codename) {
		super();
		this.id = id;
		this.name = name;
		this.diseaseSymptoms = diseaseSymptoms;
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

	public Set<DiseaseSymptom> getDiseaseSymptoms() {
		return diseaseSymptoms;
	}

	public void setDiseaseSymptoms(Set<DiseaseSymptom> diseaseSymptoms) {
		this.diseaseSymptoms = diseaseSymptoms;
	}

	public Set<DiagnosticDisease> getDiagnosticDiseases() {
		return diagnosticDiseases;
	}

	public void setDiagnosticDiseases(Set<DiagnosticDisease> diagnosticDiseases) {
		this.diagnosticDiseases = diagnosticDiseases;
	}
	
	public String getCodeName() {
		return codename;
	}

	public void setCodeName(String codename) {
		this.codename = codename;
	}

	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + "]";
	}
}
