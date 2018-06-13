package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;

public interface DiseaseService {

	public Disease getDisease(Long id);
	public ArrayList<Disease> getAllDiseases();
	public Disease insertDisease(Disease disease);
	public Disease updateDisease(Disease disease);
	public void deleteDisease(Long id);
	public Page<Disease> getDiseases(Pageable pageable);
	public Long countDiseases();
}
