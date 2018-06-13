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
	public Page<Antibiotic> findAllAntibiotics(Pageable pageable) {
		return antibioticRepository.findAllByOrderByNameAsc(pageable);
	}

	@Override
	public Page<Antibiotic> findByNameLikeIgnoreCase(String name, Pageable pageable) {
		return antibioticRepository.findByNameLikeIgnoreCaseOrderByNameAsc(name+"%", pageable);
	}

	@Override
	public Page<Antibiotic> findByType(AntibioticType type, Pageable pageable) {
		return antibioticRepository.findByTypeOrderByNameAsc(type, pageable);
	}

	@Override
	public Long countAllAntibiotics() {
		return antibioticRepository.count();
	}

	@Override
	public Long countByNameLikeIgnoreCase(String name) {
		return antibioticRepository.countByNameLikeIgnoreCase(name+"%");
	}

	@Override
	public Antibiotic insertAntibiotic(Antibiotic antibiotic) {
		return antibioticRepository.save(antibiotic);
	}

	@Override
	public Antibiotic updateAntibiotic(Antibiotic antibiotic) {
		return antibioticRepository.save(antibiotic);
	}

	@Override
	public void deleteAntibiotic(Long id) {
		antibioticRepository.delete(id);
	}

}
