package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.AntibioticType;

public interface AntibioticService {

	public Page<Antibiotic> findAllAntibiotics(Pageable pageable);
	public Long countAllAntibiotics();
	public Page<Antibiotic> findByNameLikeIgnoreCase(String name, Pageable pageable);
	public Long countByNameLikeIgnoreCase(String name);
	public Page<Antibiotic> findByType(AntibioticType type, Pageable pageable);
	public Antibiotic insertAntibiotic(Antibiotic antibiotic);
	public Antibiotic updateAntibiotic(Antibiotic antibiotic);
	public void deleteAntibiotic(Long id);
}
