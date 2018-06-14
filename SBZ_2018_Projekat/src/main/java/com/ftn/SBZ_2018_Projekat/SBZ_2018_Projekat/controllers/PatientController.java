package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;

@RestController
@RequestMapping(value="app/secured/")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertPatient", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Patient> insertPatient(@RequestBody Patient patient){
		
		patient = patientService.insertPatient(patient);
		
		if(patient==null) {
			return new ResponseWrapper<Patient>(null,false,"Neuspesno unet pacijent");
		}
				
		return new ResponseWrapper<Patient>(patient,true,"Uspesno unet pacijent");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updatePatient", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Patient> updatePatient(@RequestBody Patient patient){
		
		patient = patientService.updatePatient(patient);
		
		if(patient==null) {
			return new ResponseWrapper<Patient>(null,false,"Neuspesno modifikovan pacijent");
		}
				
		return new ResponseWrapper<Patient>(patient,true,"Uspesno modifikovan pacijent");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deletePatient", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Patient> deletePatient(@RequestParam(value="id") long id){
		
		Patient patient = patientService.findById(id);
		for(Diagnostic diagnostic : patient.getDiagnostics()) {
			diagnostic.setPatient(null);
		}	
		
		try {
			patientService.deletePatient(id);
		}catch(Exception e) {
			return new ResponseWrapper<Patient>(null,false,"Neuspesno izbrisan pacijent");
		}
		
		return new ResponseWrapper<Patient>(null,true,"Uspesno izbrisan pacijent");		
	}
}
