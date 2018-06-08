package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.DiseaseSymptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;

public interface DiseaseSymptomService {

	public ArrayList<DiseaseSymptom> findAllByDisease(Long id);
	public ArrayList<DiseaseSymptom> findBySymptoms(ArrayList<Symptom> symptoms);
	
}
