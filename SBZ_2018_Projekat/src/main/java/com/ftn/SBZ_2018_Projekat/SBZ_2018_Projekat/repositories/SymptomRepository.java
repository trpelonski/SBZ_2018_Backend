package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom,Long>{

	public ArrayList<Symptom> findAllByToShow(Boolean toShow);
	
}
