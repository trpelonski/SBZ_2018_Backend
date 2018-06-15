package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Disease insertDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

	@Override
	public Disease updateDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

	@Override
	public void deleteDisease(Long id) {
		diseaseRepository.delete(id);
	}

	@Override
	public Page<Disease> getDiseases(Pageable pageable) {
		return diseaseRepository.findAll(pageable);
	}

	@Override
	public Long countDiseases() {
		return diseaseRepository.count();
	}

	@Override
	public Disease getDiseaseByCodename(String codename) {
		return diseaseRepository.getOneByCodename(codename);
	}

}
