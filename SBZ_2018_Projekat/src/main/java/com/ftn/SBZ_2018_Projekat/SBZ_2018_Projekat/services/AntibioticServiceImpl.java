package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.AntibioticType;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.AntibioticRepository;

@Service
public class AntibioticServiceImpl implements AntibioticService{

	@Autowired
	private AntibioticRepository antibioticRepository;
	
	@Override
	public ArrayList<Antibiotic> findAllAntibiotics() {
		return (ArrayList<Antibiotic>) antibioticRepository.findAll();
	}

	@Override
	public Page<Antibiotic> findByNameLikeIgnoreCase(String name, Pageable pageable) {
		return antibioticRepository.findByNameLikeIgnoreCase(name, pageable);
	}

	@Override
	public Page<Antibiotic> findByType(AntibioticType type, Pageable pageable) {
		return antibioticRepository.findByType(type, pageable);
	}

}
