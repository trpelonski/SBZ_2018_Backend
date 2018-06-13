package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Substance;

public interface SubstanceService {

	public Substance insertSubstance(Substance substance);
	public Substance updateSubstance(Substance substance);
	public void deleteSubstance(Long id);
	public Page<Substance> getSubstance(Pageable pageable);
	public Long countSubstances();
	
}
