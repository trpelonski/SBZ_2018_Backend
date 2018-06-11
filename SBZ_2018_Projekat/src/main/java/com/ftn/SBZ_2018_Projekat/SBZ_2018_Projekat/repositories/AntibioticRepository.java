package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.AntibioticType;

public interface AntibioticRepository extends JpaRepository<Antibiotic,Long>{
	
	public Page<Antibiotic> findAllByOrderByNameAsc(Pageable pageable);
	
	public Page<Antibiotic> findByNameLikeIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
	
	public Page<Antibiotic> findByTypeOrderByNameAsc(AntibioticType type, Pageable pageable);
	
	public Long countByNameLikeIgnoreCase(String name);
	
	public Long countByType(AntibioticType type);
	
}
