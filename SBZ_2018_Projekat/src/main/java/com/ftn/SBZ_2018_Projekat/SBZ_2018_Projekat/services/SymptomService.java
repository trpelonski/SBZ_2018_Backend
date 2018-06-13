package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;

public interface SymptomService {
	
	public ArrayList<Symptom> getAllSymptoms(Boolean toShow);
	public Symptom insertSymptom(Symptom symptom);
	public Symptom updateSymptom(Symptom symptom);
	public void deleteSymptom(Long id);
	public Page<Symptom> getSymptoms(Pageable pageable);
	public Long countSymptoms();
}
