package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.SymptomRepository;

@Service
public class SymptomServiceImpl implements SymptomService {

	@Autowired
	private SymptomRepository symptomRepository;
	
	@Override
	public ArrayList<Symptom> getAllSymptoms(Boolean toShow) {	
		return symptomRepository.findAllByToShow(toShow);
	}

	@Override
	public Symptom insertSymptom(Symptom symptom) {
		return symptomRepository.save(symptom);
	}

	@Override
	public Symptom updateSymptom(Symptom symptom) {
		return symptomRepository.save(symptom);
	}

	@Override
	public void deleteSymptom(Long id) {
		symptomRepository.delete(id);
	}

	@Override
	public Page<Symptom> getSymptoms(Pageable pageable) {
		return symptomRepository.findAll(pageable);
	}

	@Override
	public Long countSymptoms() {
		return symptomRepository.count();
	}

	@Override
	public List<Symptom> getAllSymptoms() {
		return symptomRepository.findAll();
	}

	@Override
	public Symptom getOneByCodename(String codename) {
		return symptomRepository.getOneByCodename(codename);
	}

}
