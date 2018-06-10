package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.AntibioticType;

public interface AntibioticRepository extends JpaRepository<Antibiotic,Long>{
	
	public Page<Antibiotic> findByNameLikeIgnoreCase(String name, Pageable pageable);
	
	public Page<Antibiotic> findByType(AntibioticType type, Pageable pageable);
	
}
