package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Substance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=120)
	private String name;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, targetEntity=Patient.class)
	@JoinTable(
	        name = "patient_allergic_to_substance", 
	        inverseJoinColumns = { @JoinColumn(name = "patient_id") }, 
	        joinColumns = { @JoinColumn(name = "allergic_to_substance_id") }
	    )
	@JsonBackReference
	private Set<Patient> patients;
	
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, targetEntity=Antibiotic.class)
	@JoinTable(
	        name = "antibiotic_substances", 
	        joinColumns = { @JoinColumn(name = "substances_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "antibiotic_id") }
	    )
	@JsonBackReference
	private Set<Antibiotic> antibiotics;
	
	public Substance() {}

	public Substance(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Substance [id=" + id + ", name=" + name + "]";
	}
}
