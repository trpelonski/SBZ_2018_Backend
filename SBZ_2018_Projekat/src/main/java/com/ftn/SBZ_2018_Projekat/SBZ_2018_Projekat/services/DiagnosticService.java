package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;

public interface DiagnosticService {

	public ArrayList<Diagnostic> getAllByPatient(Long id);	
	public Diagnostic insertDiagnostic(Diagnostic diagnostic);
	public Page<Diagnostic> getAllDiagnostics(Pageable pageable);
	public Long countAllDiagnostics();
	public Diagnostic updateDiagnostic(Diagnostic diagnostic);
	public void deleteDiagnostic(Long id);
	
}
