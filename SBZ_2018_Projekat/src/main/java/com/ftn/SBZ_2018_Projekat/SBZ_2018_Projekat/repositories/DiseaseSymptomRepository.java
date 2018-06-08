package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.DiseaseSymptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;

public interface DiseaseSymptomRepository extends JpaRepository<DiseaseSymptom,Long>{

	@Query("select ds from DiseaseSymptom ds INNER JOIN ds.disease d where d.id = ?1 order by ds.specificFor desc")
	public ArrayList<DiseaseSymptom> findAllByDisease(Long id);
	
	public ArrayList<DiseaseSymptom> findBySymptomIn(ArrayList<Symptom> symptoms);
	
}
