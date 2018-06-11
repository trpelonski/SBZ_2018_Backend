package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.DiagnosticRepository;

@Service
public class DiagnosticServiceImpl implements DiagnosticService{

	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	@Override
	public ArrayList<Diagnostic> getAllByPatient(Long id) {
		return diagnosticRepository.findAllByPatientId(id);
	}

	@Override
	public Diagnostic insertDiagnostic(Diagnostic diagnostic) {
		return diagnosticRepository.save(diagnostic);
	}

}
