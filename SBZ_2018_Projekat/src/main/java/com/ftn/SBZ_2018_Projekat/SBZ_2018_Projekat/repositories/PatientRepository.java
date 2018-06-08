package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

	public Patient findOneByStringId(String stringId);
	
	@Query("select p from Patient p where (UPPER(CONCAT(p.firstname, ' ', p.lastname)) LIKE UPPER(?1) or UPPER(CONCAT(p.lastname, ' ', p.firstname)) LIKE UPPER(?1)) order by p.firstname, p.lastname asc")
	public Page<Patient> findByFirstNameAndLastName(String name, Pageable pageable);
	
	@Query("select count(p) from Patient p where (UPPER(CONCAT(p.firstname, ' ', p.lastname)) LIKE UPPER(?1) or UPPER(CONCAT(p.lastname, ' ', p.firstname)) LIKE UPPER(?1))")
	public Long countByFirstNameAndLastName(String name);
	
	public Page<Patient> findAllByOrderByFirstnameAscLastnameAsc(Pageable pageable);
}
