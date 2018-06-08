package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

}
