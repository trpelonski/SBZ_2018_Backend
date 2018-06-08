package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy="disease", fetch=FetchType.EAGER)
	private Set<DiseaseSymptom> diseaseSymptoms;
	
	public Disease() {}

	public Disease(Long id, String name, Set<DiseaseSymptom> diseaseSymptoms) {
		super();
		this.id = id;
		this.name = name;
		this.diseaseSymptoms = diseaseSymptoms;
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

	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + "]";
	}
}
