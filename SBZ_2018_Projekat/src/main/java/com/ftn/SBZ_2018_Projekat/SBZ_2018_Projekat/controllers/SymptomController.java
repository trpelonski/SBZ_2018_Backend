package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.SymptomService;

@RestController
@RequestMapping(value = "app/secured/")
public class SymptomController {
	
	@Autowired
	private SymptomService symptomService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="getSymptoms/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Symptom>> getSymptoms(@PathVariable int page){
		
		Long symptomsCount = symptomService.countSymptoms();
		
		if(symptomsCount<=0) {
			return new ResponseWrapper<Page<Symptom>>(null, false, "Ne postoji ni jedan simptom u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Symptom>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(symptomsCount/10)+1;
		Page<Symptom> symptoms = null;
		
		if(page>poslednja) {
			new ResponseWrapper<Page<Symptom>>(null, true, "Presli ste postojeci broj stranica.");
		}else {
			symptoms = symptomService.getSymptoms(new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Symptom>>(symptoms, true, "Uspesno vraceni simptomi.");
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertSymptom", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Symptom> insertSymptom(@RequestBody Symptom symptom){
		
		symptom = symptomService.insertSymptom(symptom);
		
		if(symptom==null) {
			return new ResponseWrapper<Symptom>(null,false,"Neuspesno unet simptom");
		}
				
		return new ResponseWrapper<Symptom>(symptom,true,"Uspesno unet simptom");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateSymptom", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Symptom> updateSymptom(@RequestBody Symptom symptom){
		
		symptom = symptomService.updateSymptom(symptom);
		
		if(symptom==null) {
			return new ResponseWrapper<Symptom>(null,false,"Neuspesno modifikovan simptom");
		}
				
		return new ResponseWrapper<Symptom>(symptom,true,"Uspesno modifikovan simptom");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteSymptom", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Symptom> deleteSymptom(@RequestParam(value="id") long id){
		
		try {
			symptomService.deleteSymptom(id);
		}catch(Exception e) {
			return new ResponseWrapper<Symptom>(null,false,"Neuspesno izbrisan simptom");
		}			
		return new ResponseWrapper<Symptom>(null,true,"Uspesno izbrisan simptom");		
	}
}
