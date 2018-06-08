package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;

public interface DiseaseService {

	public Disease getDisease(Long id);
	public ArrayList<Disease> getAllDiseases();
	
}
