package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Substance;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.SubstanceRepository;

public class SubstanceServiceImpl implements SubstanceService {

	@Autowired
	private SubstanceRepository substanceRepository;
	
	@Override
	public Substance insertSubstance(Substance substance) {
		return substanceRepository.save(substance);
	}

	@Override
	public Substance updateSubstance(Substance substance) {
		return substanceRepository.save(substance);
	}

	@Override
	public void deleteSubstance(Long id) {
		substanceRepository.delete(id);
	}

	@Override
	public Page<Substance> getSubstance(Pageable pageable) {
		return substanceRepository.findAll(pageable);
	}

	@Override
	public Long countSubstances() {
		return substanceRepository.count();
	}

}
