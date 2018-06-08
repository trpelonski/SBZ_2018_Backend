package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.DiseaseRepository;

@Service
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Override
	public Disease getDisease(Long id) {
		return diseaseRepository.getOne(id);
	}

	@Override
	public ArrayList<Disease> getAllDiseases() {
		return (ArrayList<Disease>) diseaseRepository.findAll();
	}

}
