package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.AntibioticType;

public interface AntibioticService {

	public ArrayList<Antibiotic> findAllAntibiotics();
	public Page<Antibiotic> findByNameLikeIgnoreCase(String name, Pageable pageable);
	public Page<Antibiotic> findByType(AntibioticType type, Pageable pageable);
}
