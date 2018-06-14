package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DiseaseSymptom implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	private Disease disease;
	
	@ManyToOne()
	private Symptom symptom;
	
	@Column(nullable=false)
	private Boolean specificFor;
	
	public DiseaseSymptom() {}

	public DiseaseSymptom(Long id, Disease disease, Symptom symptom, Boolean specificFor) {
		super();
		this.id = id;
		this.disease = disease;
		this.symptom = symptom;
		this.specificFor = specificFor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Boolean getSpecificFor() {
		return specificFor;
	}

	public void setSpecificFor(Boolean specificFor) {
		this.specificFor = specificFor;
	}

	@Override
	public String toString() {
		return "DiseaseSymptom [id=" + id + ", disease=" + disease + ", symptom=" + symptom + ", specificFor="
				+ specificFor + "]";
	}
}
