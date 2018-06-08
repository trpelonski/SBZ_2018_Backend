package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import java.util.ArrayList;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;

public interface DiagnosticService {

	public ArrayList<Diagnostic> getAllByPatient(Long id);
	
}
