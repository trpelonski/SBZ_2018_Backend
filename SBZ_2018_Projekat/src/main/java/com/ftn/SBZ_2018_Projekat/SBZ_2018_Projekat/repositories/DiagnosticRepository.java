package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;

public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long>{

	public ArrayList<Diagnostic> findAllByPatientId(Long id);
	
}
