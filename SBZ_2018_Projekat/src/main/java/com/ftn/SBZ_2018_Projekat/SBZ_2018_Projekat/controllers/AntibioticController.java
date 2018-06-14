package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.AntibioticService;

@RestController
@RequestMapping(value="app/secured/")
public class AntibioticController {

	@Autowired
	private AntibioticService antibioticService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertAntibiotic", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Antibiotic> insertAntibiotic(@RequestBody Antibiotic antibiotic){
		
		antibiotic = antibioticService.insertAntibiotic(antibiotic);
		
		if(antibiotic==null) {
			return new ResponseWrapper<Antibiotic>(null,false,"Neuspesno unet lek");
		}
				
		return new ResponseWrapper<Antibiotic>(antibiotic,true,"Uspesno unet lek");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateAntibiotic", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Antibiotic> updateAntibiotic(@RequestBody Antibiotic antibiotic){
		
		antibiotic = antibioticService.updateAntibiotic(antibiotic);
		
		if(antibiotic==null) {
			return new ResponseWrapper<Antibiotic>(null,false,"Neuspesno modifikovan lek");
		}
				
		return new ResponseWrapper<Antibiotic>(antibiotic,true,"Uspesno modifikovan lek");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteAntibiotic", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Antibiotic> deleteAntibiotic(@RequestParam(value="id") long id){
		
		try {
			antibioticService.deleteAntibiotic(id);
		}catch(Exception e) {
			return new ResponseWrapper<Antibiotic>(null,false,"Neuspesno izbrisan lek");
		}			
		return new ResponseWrapper<Antibiotic>(null,true,"Uspesno izbrisan lek");		
	}
	
}
