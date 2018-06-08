package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Antibiotic implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=120)
	private String name;
	
	@ManyToOne(optional=false)
	private AntibioticType type; 
	
	@ManyToMany
	private Set<Substance> substances;
	
	public Antibiotic() {}

	public Antibiotic(Long id, String name, AntibioticType type, Set<Substance> substances) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.substances = substances;
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

	public AntibioticType getType() {
		return type;
	}

	public void setType(AntibioticType type) {
		this.type = type;
	}

	public Set<Substance> getSubstances() {
		return substances;
	}

	public void setSubstances(Set<Substance> substances) {
		this.substances = substances;
	}

	@Override
	public String toString() {
		return "Antibiotic [id=" + id + ", name=" + name + ", type=" + type + ", substances=" + substances + "]";
	}
}
