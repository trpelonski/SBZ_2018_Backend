package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.DiseaseSymptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.DiseaseSymptomRepository;

@Service
public class DiseaseSymptomServiceImpl implements DiseaseSymptomService {

	@Autowired
	private DiseaseSymptomRepository diseaseSymptomRepository;
	
	@Override
	public ArrayList<DiseaseSymptom> findAllByDisease(Long id) {
		return diseaseSymptomRepository.findAllByDisease(id);
	}

	@Override
	public ArrayList<DiseaseSymptom> findBySymptoms(ArrayList<Symptom> symptoms) {
		return diseaseSymptomRepository.findBySymptomIn(symptoms);
	}

	@Override
	public DiseaseSymptom findByDiseaseAndSymptom(Disease disease, Symptom symptom) {
		return diseaseSymptomRepository.findByDiseaseAndSymptom(disease, symptom);
	}

	@Override
	public DiseaseSymptom insertDiseaseSymptom(DiseaseSymptom diseaseSymptom) {
		return diseaseSymptomRepository.save(diseaseSymptom);
	}

	@Override
	public void deleteDiseaseSymptom(Long id) {
		diseaseSymptomRepository.delete(id);
	}

}
