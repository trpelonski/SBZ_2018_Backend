package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.DiagnosticDisease;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.DiagnosticDiseaseRepository;

@Service
public class DiagnosticDiseaseServiceImpl implements DiagnosticDiseaseService {

	@Autowired
	private DiagnosticDiseaseRepository diagnosticDiseaseRepository;
	
	@Override
	public DiagnosticDisease insertDiagnosticDisease(DiagnosticDisease diagnosticDisease) {
		return diagnosticDiseaseRepository.save(diagnosticDisease);
	}

}
