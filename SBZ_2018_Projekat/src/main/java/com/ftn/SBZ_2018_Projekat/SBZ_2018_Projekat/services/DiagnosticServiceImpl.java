package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<Diagnostic> getAllDiagnostics(Pageable pageable) {
		return diagnosticRepository.findAll(pageable);
	}

	@Override
	public Diagnostic updateDiagnostic(Diagnostic diagnostic) {
		return diagnosticRepository.save(diagnostic);
	}

	@Override
	public void deleteDiagnostic(Long id) {
		diagnosticRepository.delete(id);
	}

	@Override
	public Long countAllDiagnostics() {
		return diagnosticRepository.count();
	}

}
